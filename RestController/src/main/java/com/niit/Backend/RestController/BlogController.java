package com.niit.Backend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Backend.Dao.BlogDao;
import com.niit.Backend.Model.Blog;



@RestController
public class BlogController {
    
	@Autowired
	BlogDao blogDAO;
	
	@PostMapping(value="/insertBlog")
	
	public ResponseEntity<String> insertBlog(Blog blogPart)
	{
		int i=10;
	 if(blogDAO.addBlog(blogPart))
	 {
		 return new ResponseEntity<String>("Blog is added successfully",HttpStatus.OK);
		 
	 }
	 
	 else
	 {
		 return new ResponseEntity<String>("error in response entity",HttpStatus.SERVICE_UNAVAILABLE);
	 }
		
		
	}
	
}
