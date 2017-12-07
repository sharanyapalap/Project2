package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.Forum;



public interface ForumDao {
	public boolean addForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteBlog(Forum forum);
	public Forum getBlog(int blogId);
	public List<Forum> getAllForums();
	public boolean approveBlog(Forum forum);
	public boolean rejectForum(Forum forum);

}