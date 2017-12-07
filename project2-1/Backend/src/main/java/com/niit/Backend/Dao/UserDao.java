package com.niit.Backend.Dao;

import com.niit.Backend.Model.UserDet;

public interface UserDao {
  public boolean addUser(UserDet user);
  public boolean updateOnlineStatus(String status,UserDet user);
  public UserDet getUser(String username);
  
}