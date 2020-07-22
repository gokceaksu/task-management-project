package com.example.service;

import com.example.entity.User;

public interface UserService {

	User createUser(User user);

	User retrieveUser(Long id);

	User updateUser(Long id, User user);

	void deleteUser(Long id);

}
