package com.niit.Backend.TestCases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Backend.Dao.ForumDao;
import com.niit.Backend.Model.Forum;

public class ForumTestCases 
{
	Logger log = LoggerFactory.getLogger(ForumTestCases.class);
	
	@Autowired
	ForumDao forumDAO;
	
	@Autowired
	Forum forum;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public ForumTestCases()
	{
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.raaji.CollaborationProject");
		context.refresh();

		forumDAO = (ForumDao) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
		
	}
   public void forumAdd()
   {
	   log.info("Add forum Test started");
	  
	   forum.setDescription("Here it contains username title and some other details...");
	   forum.setUsername("sharanya");
	   forum.setForum_title("Fresher_Gradguate");
	   forum.setDate_time("DATE_TIME");
	   
	   forumDAO.addForum(forum);
	   log.info("Add Forum Test end");
   }
   public void UpdateForum()
  	{
  		log.info("Update Success initiated.");
  		forum = forumDAO.getForum("testForum");
  		forumDAO.updateForum(forum);
  		log.info("Update Success");
  	}
   public void deleteForum()
	{
		log.info("Delete Success initiated.");
		forum = forumDAO.getForum("testForum");
		forumDAO.deleteForum(forum);
		log.info("Delete Success");
	}
   public static void main(String[] args) 
	{
	   ForumTestCases tforum = new  ForumTestCases ();
//		tforum.blogAdd();
	   tforum.UpdateForum();
//		tforum.deleteForum();
//		tforum.list();
		
		System.out.println("Success");
	}
   
}