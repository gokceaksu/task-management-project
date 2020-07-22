package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User retrieveUser(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> oldUser = userRepository.findById(id);
		if (!oldUser.isPresent())
			return null;

		user.setId(id);
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}

}
