package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Task;
import com.example.entity.User;
import com.example.enumeration.TaskState;

@Repository
public class TaskDao {
	

	@Autowired
	EntityManager em;
	
	
	public List<Task> findTasksByUser(User user, TaskState state) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Task> cq = cb.createQuery(Task.class);
		Root<Task> task = cq.from(Task.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(task.get("user"), user));
		
		if(state != null) {
			predicates.add(cb.equal(task.get("taskState"), state));
		}
		cq.where(predicates.toArray(new Predicate[]{}));
		
		return em.createQuery(cq).getResultList();
	}

}
