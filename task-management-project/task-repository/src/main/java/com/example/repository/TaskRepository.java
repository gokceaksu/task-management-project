package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
	
	

}
