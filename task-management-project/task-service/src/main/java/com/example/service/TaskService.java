package com.example.service;

import java.util.List;

import com.example.entity.Task;
import com.example.enumeration.TaskState;

public interface TaskService {

	Task createTask(Task task);

	Task retrieveTask(Long id);
	
	List<Task> retrieveAllTasks();
	
	Task updateTask(Long id, Task task);

	void deleteTask(Long id);

	Task assignTaskToUser(Long taskId, Long userId);

	Task markTaskAsDone(Long userId, Long taskId);

	List<Task> findTasksByUserId(Long userId, TaskState state);


}
