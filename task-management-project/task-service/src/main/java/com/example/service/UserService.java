package com.example.service;

import java.util.List;

import com.example.entity.User;

public interface UserService {

	User createUser(User user);

	User retrieveUser(Long id);
	
	List<User> retrieveAllUsers();

	User updateUser(Long id, User user);

	void deleteUser(Long id);

}
