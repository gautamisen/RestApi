package com.restapi.service;

import java.util.List;

import com.restapi.model.User;

public interface UseService {
	public List<User> UserList();
	
	public User addUser(User user);
	
	public User updateUser(int id, User user);
	
	public User findUserById(int id);
	
	public void deleteUser(int id);
	
	public User findUserByEmailAndPassword(String email, String password);
}
