package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.ForumReply;

//import com.niit.collaboration.model.ForumReply;

public interface ForumReplyDao 
{

	public boolean addReply(ForumReply reply);
	
	public boolean deleteReply(ForumReply reply);
	
	public ForumReply getReply(int id);
	
	public List<ForumReply> getForumReply(int forum_id);
}
