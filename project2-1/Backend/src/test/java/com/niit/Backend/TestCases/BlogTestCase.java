package com.niit.Backend.TestCases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Backend.Dao.BlogDao;
import com.niit.Backend.Model.Blog;


public class BlogTestCase 
{
	Logger log = LoggerFactory.getLogger(BlogTestCase.class);
	
	@Autowired
	BlogDao blogDAO;
	
	@Autowired
	Blog blog;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public BlogTestCase()
	{
		
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.Backend");
		context.refresh();

		blogDAO = (BlogDao) context.getBean("blogDAO");
		blog = (Blog) context.getBean("blog");
		
	}
   public void blogAdd()
   {
	   log.info("Add blog Test started");
	  
	   blog.setDescription("sdfghgjdkdkluruighfkjdsfdks");
	   blog.setUsername("mallika");
	   blog.setBlog_title("aaaaaa");
	   blog.setDate_time("DATE_TIME");
	   
	   blogDAO.addBlog(blog);
	   log.info("Add Blog Test end");
   }
   /*public void UpdateBlog()
  	{
  		log.info("Update Success initiated.");
  		blog = blogDAO.getBlog("testBlog");
  		blogDAO.updateBlog(blog);
  		log.info("Update Success");
  	}*/
   public void deleteBlog()
	{
		log.info("Delete Success initiated.");
		blog = blogDAO.getBlog("testBlog");
		blogDAO.deleteBlog(blog);
		log.info("Delete Success");
	}
   public static void main(String[] args) 
	{
	   BlogTestCase tblog = new BlogTestCase ();
        tblog.blogAdd();

	//tblog.UpdateBlog();
	//tblog.deleteBlog();
//		tblog.list();
		
		System.out.println("Success");
	}
   
}