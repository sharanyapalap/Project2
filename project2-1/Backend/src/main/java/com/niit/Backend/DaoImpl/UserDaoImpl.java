package com.niit.Backend.DaoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.Model.UserDet;


public class UserDaoImpl implements UserDao
{
    @Autowired
    public SessionFactory sessionFactory;
    
	public UserDaoImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	
	public boolean addUser(UserDet user) {
        try{
		sessionFactory.getCurrentSession().save(user); 
        }
        catch(Exception e)
        {
        	System.out.println("Exception has occured..."+e);
        }
		return false;
        
	}
    @Transactional
    
	public boolean updateOnlineStatus(String status, UserDet user)
    {
    	 try
    	 {
    	   user.setIsOnline(status);	 
    	   sessionFactory.getCurrentSession().save(user); 
    	 }
    	  catch(Exception e)
    	 {
           System.out.println("Exception has occured..."+e);
    	   
    	 }
		return false;
    	 	
	}

	public UserDet getUserDet(String username) {
     
		Session session= sessionFactory.openSession();
		UserDet user=(UserDet)session.get(UserDet.class,username);
		session.close();
		return user;
	}

	public boolean addUserDet(UserDet user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateOnlineStatus(String status, UserDao user) {
		// TODO Auto-generated method stub
		return false;
	}

	public UserDet getUserDao(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	/*public UserDet getUserDao(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDet getUserDao(String username) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	public UserDet getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkLogin(UserDet userPart) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<UserDet> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}	
}

