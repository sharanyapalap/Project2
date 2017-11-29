package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.UserDet;

public interface UserDao {

	//public boolean addUser(UserDet user);
	//public boolean updateOnlineStatus(String status, UserDet user);
      public boolean addUser(UserDet user);
	  public boolean validateUser(String userName, String password);
	  public UserDet getUser(String userName);
	  public List<UserDet> getUserList();
	  public boolean deleteUser(UserDet user);

}
