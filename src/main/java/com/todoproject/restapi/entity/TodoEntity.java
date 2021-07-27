package com.todoproject.restapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "todo")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@EqualsAndHashCode.Include
	private int id;
	
	@Column(name = "name", length = 200)
	private String name;
	
	@Column(name = "deadline")
	private long deadline;
	
	@Column(name = "creation_date")
	private long creationDate;
	
	@Column(name = "priority")
	private Priority priority;
}
