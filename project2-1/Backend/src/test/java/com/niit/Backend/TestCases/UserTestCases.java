package com.niit.Backend.TestCases;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.Model.UserDet;

@Repository("userDAO")
public class UserTestCases {

static UserDao userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.raaji.CollaborationProject");
		context.refresh();
		
		userDAO=(UserDao)context.getBean("userDAO");
	}
	
	

	@Test
	public void addUserTest()
	{
		UserDet user=new UserDet();
		 user.setUsername("Raaji");
		 user.setFirstname("Raaji");
		 user.setLastname("Papanaboina");
		 user.setPassword("0675");
		 user.setEmailId("rajipapanaboina@gmail.com");
		 user.setRole("Admin");
		 user.setIsOnline("N");
		assertTrue("Problem in Inserting User",userDAO.addUserDao(user));
	
	}
	
	@Test
	public void isOnlineTest()
	{
		UserDao user=userDAO.getUserDao("Raaji");
		assertTrue("Problem in assertion..",userDAO.updateOnlineStatus("Y", user));
	}

}
