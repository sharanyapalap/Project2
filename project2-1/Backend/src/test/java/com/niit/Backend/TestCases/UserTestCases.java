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
		context.scan("com.niit.Backend");
		context.refresh();
		
		userDAO=(UserDao)context.getBean("userDAO");
	}
	
	

	@Test
	public void addUserTest()
	{
		UserDet user=new UserDet();
		 user.setUsername("sharanya");
		 user.setFirstname("sharanya");
		 user.setLastname("palaparthi");
		 user.setPassword("0675");
		 user.setEmailId("sharanya.palaparthi@gmail.com");
		 user.setRole("Admin");
		 user.setIsOnline("N");
		assertTrue("Problem in Inserting User",userDAO.addUser(user));
	
	}
	
	@Test
	public void isOnlineTest()
	{
		UserDet user=userDAO.getUser("sharanya");
		assertTrue("Problem in assertion..",userDAO.updateOnlineStatus("Y", user));
	}

}
