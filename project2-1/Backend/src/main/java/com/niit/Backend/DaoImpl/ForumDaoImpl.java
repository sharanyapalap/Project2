package com.niit.Backend.DaoImpl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Backend.Dao.ForumDao;
import com.niit.Backend.Model.Forum;
import com.niit.Backend.Model.UserDet;

@SuppressWarnings("deprecation")
@Repository("forumDAO")
@EnableTransactionManagement
public class ForumDaoImpl implements ForumDao 
{
private static final Logger log = LoggerFactory.getLogger(ForumDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ForumDaoImpl(SessionFactory sessionFactory) 
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
	public boolean addForum(Forum forum) {
		log.info("Add Blog Method Started");
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			log.info("Add forum Method Success");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Add forum has an Error");
			ex.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean updateBlog(Forum forum) {
		log.info("Update forum method Started");
		try
		{
			log.info("Update forum Success");
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception ex)
		{
			log.info("Update forum Unsuccessful");
			ex.printStackTrace();
			return false;
		}
	}
	@Transactional
	public boolean deleteForum(Forum forum) {
		log.info("Delete forum method Started");
		try
		{
			log.info("Delete forum Success");
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception ex)
		{
			log.info("Delete forum Unsuccessful");
			ex.printStackTrace();
			return false;
		}
	}
	@Transactional
	public Forum getForum(String title) {
		log.debug("Starting of Method Get forum "+title);
		try
		{
			Forum forum =  sessionFactory.getCurrentSession().get(Forum.class, title);
			forum.setErrorCode("200");
			forum.setErrorMsg("User Found");
			return forum;
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
	public List<Forum> getForumByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public List<Forum> getAllForums() {
		log.info("Starting of List Method");
		String hql_string = "FROM FORUM";
		Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
		log.info("List Retrieved");
		return query.list();
	}
	
	public boolean updateForum(Forum forum) {
		// TODO Auto-generated method stub
		return false;
	}
		public boolean deleteForum(Forum forum) {
		// TODO Auto-generated method stub
		return false;
	}
	public Forum getForum(String title) {
		// TODO Auto-generated method stub
		return null;
	}
		}
