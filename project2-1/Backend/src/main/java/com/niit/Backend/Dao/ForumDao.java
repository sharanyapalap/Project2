package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.Forum;

public interface ForumDao 
{
	public boolean addForum(Forum forum);
	public boolean updateForum(Forum forum);	
	public boolean deleteForum(Forum forum);
	public Forum getForum(String title);
	public List<Forum> getForumByUser(String username);
	public List<Forum> getAllForums();

}