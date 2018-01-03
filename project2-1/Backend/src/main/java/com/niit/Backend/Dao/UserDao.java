package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.User;

//import com.niit.collaboration.model.User;

public interface UserDao
{

	public boolean addUser(User user);
	
	public boolean validateUser(String userName, String password);
	
	public User getUser(String userName);
	
	public List<User> getUserList();
	
	public boolean deleteUser(User user);
	
}
