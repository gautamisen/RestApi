package com.restapi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.User;
import com.restapi.service.UserServiceImpl;

@RestController
@RequestMapping("/api/v2")
//Url = http://localhost:8080/api
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/user-list")
	public List<User> userList(){
		return userService.UserList();
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody User u) {
		String name = u.getName();
		String email = u.getEmail();
		String address = u.getAddress();
		int age = u.getAge();
		String password = u.getPassword();
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}"; 
		
		
		if(name.isEmpty())
		{
			return "Name of a user is required";
		}
		if(email.isEmpty())
		{
			return "Email is required";
		}
		if(address.isEmpty())
		{
			return "Address is required";
		}if(password.isEmpty())
		{
			return "Password is required";
		}
		if(!(password.matches(pattern))) {
			return "Password must have atleast one uppercase letter \n"
					+ "Paaword must have atleast one lowecase letter \n"
					+ "Password must have atleast one number \n "
					+ "Password must have atleast one special character\n"
					+ "Password must be 8 character long";
		}
		if(age>99 || age < 16) {
			return "Invalid Age";
		}
		
		u.setModified(new Date());
		u.setCreated(new Date());
		u.setStatus(1);
		
		User addUser = userService.addUser(u);
		if(addUser == null) {
			return "something Went Wrong.";
		}
		return "User Added successfully...";
	}
	
	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") int id , @RequestBody User user) {
		String name = user.getName();
		String email = user.getEmail();
		String address = user.getAddress();
		int age = user.getAge();
		String password = user.getPassword();
		
		int status=user.getStatus();
		
		if(name.isEmpty())
		{
			return "Name of a user is required";
		}
		if(email.isEmpty())
		{
			return "Email is required";
		}
		if(address.isEmpty())
		{
			return "Address is required";
		}if(password.isEmpty())
		{
			return "Password is required";
		}
		if(age>99 || age < 16) {
			return "Invalid Age";
		}
		
		User updateUser = userService.updateUser(id, user);
		if(updateUser==null)
		{
			return"Update Failed!!!!!";
		}
		
		return "Update Successfully";
		
	}
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id)
	{
		userService.deleteUser(id);
		return"User Deleted Successfully";
		
	}
}
