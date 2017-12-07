package com.niit.Backend.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.Model.UserDet;

@RestController
public class UserController {
	@Autowired
	UserDao userDAO;
	
	@PostMapping(value="/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody UserDet userPart)
	{
		System.out.println("Registration");
		
		userPart.setRole("User");
		userPart.setIsOnline("N");
		if(userDAO.addUser(userPart))
		{
			return new ResponseEntity<String>("Registered",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error in Registration",HttpStatus.OK);
		}
	}

	@PostMapping(value="/login")
	public ResponseEntity<UserDet> checkLogin(@RequestBody UserDet userdet)
	{
		
		if(userDAO.checkLogin(userdet))
		{
			return new ResponseEntity<UserDet>(userdet,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserDet>(userdet,HttpStatus.OK);
		}
	}

	
	
	
	
	
}
