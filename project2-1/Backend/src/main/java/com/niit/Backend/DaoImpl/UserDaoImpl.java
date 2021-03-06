package com.niit.Backend.DaoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.Model.User;

@SuppressWarnings("deprecation")
@Repository("userDAO")
@EnableTransactionManagement
public class UserDaoImpl implements UserDao
{
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDaoImpl(SessionFactory sessionFactory) 
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
	public boolean addUser(User user) 
	{
		log.info("Add User Method Started");
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			log.info("Add User Method Success");
			return true;
		}
		catch(Exception ex)
		{
			log.error("Add User has an Error");
			ex.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean validateUser(String userName, String password) 
	{
		log.info("Validate User Method Started");
		try
		{
			User user =  sessionFactory.getCurrentSession().get(User.class, userName);
			if(user.getPassword().equals(password))
			{
				user.setErrorCode("200");
				user.setErrorMsg("User Found");
				log.info("Valid User");
				return true;
			}
			else
			{
				user.setErrorCode("100");
				user.setErrorMsg("Password is incorrect");
				log.info("Invalid password");
				return false;
			}
		} catch(Exception ex)
		{
			User user = new User();
			user.setErrorCode("100");
			user.setErrorMsg("Username not found");
			log.error("Username Not found in database");
			return false;
		}
	}

	@Transactional
	public User getUser(String userName) 
	{
		log.debug("Starting of Method Get User "+userName);
		try
		{
			User user =  sessionFactory.getCurrentSession().get(User.class, userName);
			user.setErrorCode("200");
			user.setErrorMsg("User Found");
			return user;
		}
		catch(Exception ex)
		{
			User user = new User();
			ex.printStackTrace();
			user.setErrorCode("404");
			user.setErrorMsg("User Not Found");
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<User> getUserList() 
	{
		log.info("Starting of List Method");
		String hql_string = "FROM User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql_string);
		log.info("List Retrieved");
		return query.list();
	}

	@Transactional
	public boolean deleteUser(User user) 
	{
		log.info("Delete User method Started");
		try
		{
			log.info("Delete user Success");
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception ex)
		{
			log.info("Delete User Unsuccessful");
			ex.printStackTrace();
			return false;
		}
	}
}