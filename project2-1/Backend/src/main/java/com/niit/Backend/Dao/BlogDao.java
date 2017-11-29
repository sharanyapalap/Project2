package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.Blog;

public interface BlogDao 
{
	public boolean addBlog(Blog blog);
	
	
	public boolean updateBlog(Blog blog);	
	
	public boolean deleteBlog(Blog blog);
	
	public Blog getBlog(String title);
	
	public List<Blog> getBlogByUser(String username);
	
	
	public List<Blog> getAllBlogs();

}