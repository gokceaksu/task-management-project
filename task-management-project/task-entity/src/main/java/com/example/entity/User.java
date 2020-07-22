package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Audited
@Data
@Entity
@Table(name = "USER")
public class User {

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
	private String name;

}
