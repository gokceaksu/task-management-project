package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Task;
import com.example.entity.User;
import com.example.enumeration.TaskState;
import com.example.repository.TaskDao;
import com.example.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TaskDao taskDao;

	@Autowired
	UserService userservice;

	@Override
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Task retrieveTask(Long id) {
		return taskRepository.findById(id).get();
	}

	@Override
	public Task updateTask(Long id, Task task) {
		Optional<Task> oldTask = taskRepository.findById(id);
		if (!oldTask.isPresent())
			return null;

		task.setId(id);
		return taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public Task assignTaskToUser(Long taskId, Long userId) {

		User user = userservice.retrieveUser(userId);
		if (user == null)
			throw new RuntimeException("User does not exist!");

		Task task = retrieveTask(taskId);
		task.setUser(user);
		task.setTaskState(TaskState.PROCESS);
		updateTask(taskId, task);
		return task;
	}

	@Override
	public Task markTaskAsDone(Long userId, Long taskId) {

		Task task = retrieveTask(taskId);

		List<Task> taskList = findTasksByUserId(userId, null);
		if (!taskList.contains(task)) {
			throw new RuntimeException("Task is not assigned to this user!");
		}

		task.setTaskState(TaskState.DONE);
		updateTask(taskId, task);
		return task;
	}

	@Override
	public List<Task> findTasksByUserId(Long userId, TaskState state) {

		User user = userservice.retrieveUser(userId);
		if (user == null)
			throw new RuntimeException("User does not exist!");

		return taskDao.findTasksByUser(user, state);
	}

}
