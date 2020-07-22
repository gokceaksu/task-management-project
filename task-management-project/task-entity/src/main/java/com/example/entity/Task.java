package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import com.example.enumeration.TaskState;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Audited
@Data
@Entity
@Table(name = "TASK")
public class Task {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@CreationTimestamp
	@Getter
	@Setter
	private Date createTime;

	@UpdateTimestamp
	@Getter
	@Setter
	private Date lastUpdateTime;
	
	@Getter
	@Setter
	private String taskName;
	
	@Getter
	@Setter
	private String eventDescription;
	
	@Column(columnDefinition = "varchar(20) default 'CREATED'") 
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private TaskState taskState;
	
	@ManyToOne
	@Getter
	@Setter
	private User user;

}
