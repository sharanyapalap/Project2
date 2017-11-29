package com.niit.Backend.DaoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Backend.Dao.ForumDao;
import com.niit.Backend.Model.Forum;
import com.niit.Backend.Model.UserDet;


@SuppressWarnings("deprecation")
@Repository("userDAO")
@EnableTransactionManagement


public class ForumDaoImpl implements ForumDao 
{
private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
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

	public boolean addForum(Forum forum) {
		log.info("Add Forum Method Started");
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			log.info("Add Forum Method Success");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Add Forum has an Error");
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteForum(int id) {
		log.info("Delete Forum method Started");
		try
		{
			log.info("Delete Forum Success");
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception ex)
		{
			log.info("Delete Forum Unsuccessful");
			ex.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		// TODO Auto-generated method stub
		return false;
	}

	public Forum getForum(int id) {
		log.debug("Starting of Method Get User "+id);
		try
		{
			Forum forum =  sessionFactory.getCurrentSession().get(Forum.class, id);
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

	public List<Forum> getUserForums(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Forum> getForumList() {
		// TODO Auto-generated method stub
		return null;

   }
}