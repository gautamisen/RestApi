package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.User;
import com.restapi.service.UserServiceImpl;

@RestController
@RequestMapping("/Doremon")
public class LoginController {
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/login")
	public String login(@RequestBody User u) {
		String email = u.getEmail();
		String password = u.getPassword();
//		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}"; 
		
		if(email.isEmpty() || email.isBlank()) {
			return "Email is required.";
		}
		if(password.isEmpty() || password.isBlank()) {
			return "Password is required.";
			
		}
//		if(!(password.matches(pattern))) {
//			return "Please Enter password As per indicated.";
//		}
		
		User user = userService.findUserByEmailAndPassword(email, password);
		if(user == null) {
			return "Invalid Username and password";
		}
		
		return "Login success";
	}
}
