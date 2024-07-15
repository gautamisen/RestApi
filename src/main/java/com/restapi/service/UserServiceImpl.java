package com.restapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.restapi.model.User;
import com.restapi.respository.UserRespository;

@Service
public class UserServiceImpl implements UseService {
	@Autowired
	UserRespository userRepo;

	@Override
	public List<User> UserList() {
		
		return userRepo.findAll(Sort.by(Direction.ASC,"name"));
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepo.save(user);
	}

	@Override
	public User updateUser(int id, User user) {
		// TODO Auto-generated method stub
		User oldUser = findUserById(id);
		oldUser.setName(user.getName());
		oldUser.setAddress(user.getAddress());
		oldUser.setAge(user.getAge());
		oldUser.setEmail(user.getEmail());
		oldUser.setPassword(user.getPassword());
		oldUser.setModified(new Date());
		oldUser.setStatus(user.getStatus());
		
		User save = userRepo.save(oldUser);
		
		return save;
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		List<User> userList = UserList();
		User u = null;
		for( User user : userList) {
			if(user.getId() == id) {
				u = user;
			}
		}
		return u;
	}

	@Override
	public void deleteUser(int id) {
		
		// TODO Auto-generated method stub
		
		User ud = findUserById(id);
		userRepo.delete(ud);
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userRepo.findByEmailAndPassword(email, password);
	}
	
	
}
