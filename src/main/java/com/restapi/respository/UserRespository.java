package com.restapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.model.User;
@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(String email, String password);
}
