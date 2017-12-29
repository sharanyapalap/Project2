package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.Job;

//import com.niit.collaboration.model.Job;

public interface JobDao 
{
	
	public boolean addJob(Job job);
	
	public Job getJob(String name);
	
	public List<Job> listJobs();
	
	public boolean deleteJob(int job_id);
	
	public boolean invalidateJob(int job_id);
	
}
