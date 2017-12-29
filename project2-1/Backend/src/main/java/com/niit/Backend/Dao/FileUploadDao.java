package com.niit.Backend.Dao;

import com.niit.Backend.Model.FileUpload;

//import com.niit.collaboration.model.FileUpload;

public interface FileUploadDao
{
	public void save(FileUpload fileUpload, String username);
	public FileUpload getFile(String username);
}
