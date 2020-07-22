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
import com.example.enumeration.TaskState;
import com.example.service.TaskService;


@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	TaskService taskService;

	
	@PostMapping("/")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {

		Task createdTask = taskService.createTask(task);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdTask.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> retrieveTask(@PathVariable Long id) {

		Task task = taskService.retrieveTask(id);
		if (task == null) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<Task>(task, HttpStatus.FOUND);
	}
	
	
	@GetMapping("/")
	public List<Task> retrieveAllTasks(@RequestParam(required = false) Long userId, @RequestParam(required = false) TaskState taskState) {
		
		if(userId == null) {
			return taskService.retrieveAllTasks();
		}
		else {
			return taskService.findTasksByUserId(userId, taskState);
		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {

		Task updatedTask = taskService.updateTask(id, task);
		if (updatedTask == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(updatedTask.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id) {

		taskService.deleteTask(id);
	}

	
	@SuppressWarnings("rawtypes")
	@PostMapping("/{taskId}/users/{userId}")
	public ResponseEntity assignTask(@PathVariable Long taskId, @PathVariable Long userId) {

		try {
			Task task = taskService.assignTaskToUser(taskId, userId);
			return new ResponseEntity<Task>(task, HttpStatus.OK);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
		}
	}

}
