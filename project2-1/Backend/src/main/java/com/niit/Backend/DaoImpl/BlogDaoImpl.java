package com.niit.Backend.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Backend.Dao.BlogDao;
import com.niit.Backend.Model.Blog;
import com.niit.Backend.Model.UserDet;

@SuppressWarnings("deprecation")
@Repository("blogDAO")
@EnableTransactionManagement


public class BlogDaoImpl implements BlogDao 
{
private static final Logger log = LoggerFactory.getLogger(BlogDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDaoImpl(SessionFactory sessionFactory) 
	{
		try 
		{
			this.sessionFactory = sessionFactory;
			log.info("Connection Established Successfully");
		} 
		catch (Exception ex) 
		{
			log.error("Failed to establish connection");
			ex.printStackTrace();
		}
	}
	@Transactional
	public boolean addBlog(Blog blog) {
		log.info("Add Blog Method Started");
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			log.info("Add blog Method Success");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Add blog has an Error");
			ex.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean updateBlog(Blog blog) {
		log.info("Update Blog method Started");
		try
		{
			log.info("Update blog Success");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception ex)
		{
			log.info("Update Blog Unsuccessful");
			ex.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean deleteBlog(Blog blog) {
		log.info("Delete Blog method Started");
		try
		{
			log.info("Delete blog Success");
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception ex)
		{
			log.info("Delete Blog Unsuccessful");
			ex.printStackTrace();
			return false;
		}
	}
	@Transactional
	public Blog getBlog(String title) {
		log.debug("Starting of Method Get Blog "+title);
		try
		{
			Blog blog =  sessionFactory.getCurrentSession().get(Blog.class, title);
			blog.setErrorCode("200");
			blog.setErrorMsg("User Found");
			return blog;
		}
		catch(Exception ex)
		{
			UserDet user = new UserDet();
			ex.printStackTrace();
			user.setErrorCode("404");
			user.setErrorMsg("User Not Found");
			return null;
		}
	}
	@Transactional
	public List<Blog> getBlogByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public List<Blog> getAllBlogs() {
		log.info("Starting of List Method");
		String hql_string = "FROM Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
		log.info("List Retrieved");
		return query.list();
	}

	}
