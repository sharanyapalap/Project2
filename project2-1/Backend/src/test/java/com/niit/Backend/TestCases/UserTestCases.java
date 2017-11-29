package com.niit.Backend.TestCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.Model.UserDet;


public class UserTestCases 
{
	Logger log = LoggerFactory.getLogger(UserTestCases.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDet userDet;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public UserTestCases()
	{
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.Backend");
		context.refresh();

		userDao = (UserDao) context.getBean("userDAO");
		userDet = (UserDet) context.getBean("userDet");
		
	}
	
	public void testAdd()
	{
		log.info("Add User Test started");
		
		userDet.setUsername("Shari");
		userDet.setFirst_name("Sharanya");
		userDet.setLast_name("Palaparthi");
		userDet.setDob(new Date());
		userDet.setGender('F');
		userDet.setMail_id("shari@gmail.com");
		userDet.setPassword("shari");
		userDet.setStatus('N');
		userDet.setRole("USER");
		
		userDao.addUser(userDet);
		log.info("Add User Test end");
	}
	
	public void getUserDetails()
	{
		log.info("Get User Details Started");
		String userName = "shari";
		userDet = userDao.getUser(userName);
		System.out.println("Name - "+userDet.getFirst_name());
		System.out.println("Date - "+userDet.getDob());
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	       Date dateobj = userDet.getDob();
	       String datetime = df.format(dateobj).toString();
	       System.out.println("Date - "+datetime);
		log.info("Get User Ended");
	}
	
	public void validateUser()
	{
		log.info("Validate User Started");
		String userName = "testuser";
		String password = "testuser";
		boolean value = userDao.validateUser(userName, password);
		if(value)
			System.out.println("Valid");
		else
			System.out.println("Invalid");
		log.info("Validate User Ended");
	}
	
	public void deleteUser()
	{
		log.info("Delete Success initiated.");
		userDet = userDao.getUser("testuser");
		userDao.deleteUser(userDet);
		log.info("Delete Success");
	}
	
	public void list()
	{
		log.info("List Users");
		List<UserDet> list = userDao.getUserList();
		int size = list.size();
		for(int index = 0; index < size; index++)
		{
			System.out.print("Name = "+list.get(index).getFirst_name());
			System.out.println("\t Email = "+list.get(index).getMail_id());
		}
	}
	
	public static void main(String[] args) 
	{
		UserTestCases tuser = new UserTestCases();
		//tuser.testAdd();
       tuser.getUserDetails();
//		tuser.validateUser();
//		tuser.deleteUser();
	//tuser.list();
		
		System.out.println("Success");
	}
}
