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
		context.scan("com.niit.Backend");
		context.refresh();

		forumDAO = (ForumDAO) context.getBean("forumDAO");
		forum = (Forum) context.getBean("forum");
		
	}
	public void addforum()
	{
		log.info("Add forum Test started");
		  
		   forum.setDescription("sdfghgjdkdkluruighfkjdsfdks");
		   forum.setUsername("mallika");
		   forum.setBlog_title("aaaaaa");
		   forum.setDate_time("DATE_TIME");
		   
		   ForumDao.addForum(forum);
		   log.info("Add Blog Test end");
	}
	public void deleteBlog()
	{
		log.info("Delete Success initiated.");
		forum = forumDAO.getBlog("testBlog");
		forumDAO.deleteForum(forum);
		log.info("Delete Success");
	}
  

public static void main(String[] args) 
	{
	   BlogTestCase tblog = new BlogTestCase ();
//		tblog.ForumAdd();
	

//		tblog.deleteBlog();
	
		
		System.out.println("Success");
	}
}