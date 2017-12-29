package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.UserDet;

//import com.niit.collaboration.model.User;

public interface UserDao
{

	public boolean addUser(UserDet user);
	
	public boolean validateUser(String userName, String password);
	
	public UserDet getUser(String userName);
	
	public List<UserDet> getUserList();
	
	public boolean deleteUser(UserDet user);
	
}
