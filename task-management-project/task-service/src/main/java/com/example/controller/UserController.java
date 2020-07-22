package com.example.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entity.Task;
import com.example.entity.User;
import com.example.enumeration.TaskState;
import com.example.service.TaskService;
import com.example.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	TaskService taskService;

	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User createdUser = userService.createUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable Long id) {

		User user = userService.retrieveUser(id);
		if (user == null) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<User>(user, HttpStatus.FOUND);
	}

	@PostMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

		User updatedUser = userService.updateUser(id, user);
		if (updatedUser == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {

		userService.deleteUser(id);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/{userId}/tasks/{taskId}")
	public ResponseEntity markAsDone(@PathVariable Long userId, @PathVariable Long taskId) {

		try {
			Task task = taskService.markTaskAsDone(userId, taskId);
			return new ResponseEntity<Task>(task, HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

	@GetMapping("/{userId}/tasks")
	public List<Task> retrieveAllTasksByState(@PathVariable Long userId, @RequestParam TaskState state) {

		List<Task> tasks = taskService.findTasksByUserId(userId, state);
		return tasks;
	}

	/*
	 * @GetMapping("/{userId}/tasks") public List<Task>
	 * retrieveAllTasks(@PathVariable Long userId) {
	 * 
	 * List<Task> tasks = taskService.findTasksByUserId(userId, null); return
	 * tasks; }
	 */

}
