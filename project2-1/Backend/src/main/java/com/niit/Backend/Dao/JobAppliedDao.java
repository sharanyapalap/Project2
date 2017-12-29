package com.niit.Backend.Dao;

import java.util.List;

import com.niit.Backend.Model.JobApplied;

//import com.niit.collaboration.model.Job;
//import com.niit.collaboration.model.JobApplied;

public interface JobAppliedDao
{
	
	public boolean applyNew(JobApplied jobApplied);
	
	public List<JobApplied> listByUser(String username);
	
	public List<JobApplied> listByCompany();
	
}
