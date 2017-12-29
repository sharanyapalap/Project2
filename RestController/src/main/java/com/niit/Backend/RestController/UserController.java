package com.niit.Backend.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Backend.Dao.FriendDao;
import com.niit.Backend.Dao.UserDao;
import com.niit.Backend.Model.UserDet;
import com.niit.Backend.util.Date_Time;


@RestController
public class UserController 
{
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDAO;
	
	@Autowired
	private FriendDao friendDAO;

	@Autowired
	private UserDet user;

	@Autowired
	HttpSession session;

	@GetMapping("/getUserList")
	public ResponseEntity<List<UserDet>> getUserList() throws NullPointerException
	{
			List<UserDet> list = userDAO.getUserList();
			if (list.isEmpty()) 
			{
				user.setErrorCode("100");
				user.setErrorMsg("Users are not available");
			}
			else
			{
				DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
				for(UserDet user : list)
				{
					user.setErrorCode("200");
					user.setErrorMsg("Success");
					if(user.getDob() != null)
						user.setBirthdate(df.format(user.getDob()));
				}
			}
			return new ResponseEntity<List<UserDet>>(list, HttpStatus.OK);
	}

	@GetMapping("/getUser-{id}")
	public ResponseEntity<UserDet> getUser(@PathVariable("id") String userName) {
		user = userDAO.getUser(userName);

		if (user == null) {
			user = new UserDet();
			user.setErrorCode("404");
			user.setErrorMsg("User " + userName + " is not found.");
		}
		user.setErrorCode("200");
		user.setErrorMsg("User " + userName + " is found.");
		return new ResponseEntity<UserDet>(user, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<UserDet> validateUser(@RequestBody UserDet user) 
	{
		System.out.println("Name - "+user.getUsername());
//		System.out.println("Password "+user.getPassword());
		boolean value = userDAO.validateUser(user.getUsername(), user.getPassword());
		System.out.println(value);
		if (value == false) 
		{
			user = new UserDet();
			user.setErrorCode("404");
			user.setErrorMsg("Wrong username or password.");
		}
		else
		{
			if(user.getStatus()=='R')
			{
				user = new UserDet();
				user.setErrorCode("404");
				user.setErrorMsg("Registeration is rejected. Please Contact Admin");
			}
			if(user.getStatus()=='N')
			{
				user = new UserDet();
				user.setErrorCode("404");
				user.setErrorMsg("Registeration approval is pending. Please try again later");
			}
			else
			{
				user = userDAO.getUser(user.getUsername());
				user.setIsOnline('Y');
				Date_Time dt = new Date_Time();
				user.setLast_seen(dt.getDateTime());
				userDAO.addUser(user);
				friendDAO.setUsersOnline(user.getUsername());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("role", user.getRole());
				session.setAttribute("isLoggedIn", "true");
				if(user.getDob()!=null)
					user.setBirthdate( dt.toStringDate(user.getDob()));
				
				user.setErrorCode("200");
				user.setErrorMsg("Success");
				System.out.println("Name = "+session.getAttribute("username").toString());
				System.out.println("Role = "+session.getAttribute("role").toString());
			}
		}

		return new ResponseEntity<UserDet>(user, HttpStatus.OK);
	}

	@PostMapping("/add_User")
	public ResponseEntity<UserDet> addUser(@RequestBody UserDet user) 
	{	    
	    user.setStatus('N');
	    user.setIsOnline('N');
		boolean value = userDAO.addUser(user);
		if (value == true) 
		{
			user.setErrorCode("200");
			user.setErrorMsg("User added Successfully");
		} 
		else 
		{
			user.setErrorCode("100");
			user.setErrorMsg("Add User Failed");
		}
		return new ResponseEntity<UserDet>(user, HttpStatus.OK);
	}

	@PostMapping("/updateUser")
	public ResponseEntity<UserDet> updateUser(@RequestBody UserDet user)
	{
		if(user != null)
		{
			boolean value = userDAO.addUser(user);
			if (value == true) 
			{
				user.setErrorCode("200");
				user.setErrorMsg("User updated Successfully");
			} 
			else 
			{
				user.setErrorCode("100");
				user.setErrorMsg("Add User Failed");
				return null;
			}
		}
		return new ResponseEntity<UserDet>(user, HttpStatus.OK);
	}
	
	@GetMapping("/delete_user-{id}")
	public ResponseEntity<UserDet> deleteUser(@PathVariable("id") String userName) 
	{
		UserDet user = userDAO.getUser(userName);
		boolean value = userDAO.deleteUser(user);
		user = new UserDet();
		if (value == true) 
		{
			user.setErrorCode("200");
			user.setErrorMsg("User Deleted Successfully");
		}
		else
		{
			user.setErrorCode("100");
			user.setErrorMsg("Delete User Failed");
		}
		return new ResponseEntity<UserDet>(user, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<UserDet> logout()
	{
		log.info("isLoggedIN - "+session.getAttribute("isLoggedIn"));
		if(session.getAttribute("isLoggedIn") != null)
		{
			user = userDAO.getUser(session.getAttribute("username").toString());
			user.setIsOnline('N');
			Date_Time dt = new Date_Time();
			user.setLast_seen(dt.getDateTime());
			userDAO.addUser(user);
			friendDAO.setUsersOffline(session.getAttribute("username").toString());
			user = new UserDet();
			user.setErrorCode("200");
			user.setErrorMsg("You have logged out.");
			session.invalidate();
		}
		else
		{
			user = new UserDet();
			user.setErrorCode("500");
			user.setErrorMsg("You have not logged in");
			log.info(user.getErrorMsg());
		}
		return new ResponseEntity<UserDet>(user, HttpStatus.OK);
	}
}