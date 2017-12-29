package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.BlogComment;

//import com.niit.collaboration.model.BlogComment;

public interface CommentDao
{
	
	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteComment(int id);
	
	public List<BlogComment> getBlogComments(String blog_id);
}