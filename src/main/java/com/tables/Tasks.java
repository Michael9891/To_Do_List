package com.tables;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class Tasks implements Serializable{
	/**
	 * Task class
	 * @param description - describe task
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID",nullable=false)
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name = "NAME", nullable=false)
	private Users user;
	@Column(name="TASK")
	private String task;
	@Column(name="DATE")
	private String date;
	@Column(name="DESCRIPTION")
	private String description;
	
	public Tasks() {
		
	}
	public Tasks(Users user, String task, String date,String description) {
		super();
		this.user = user;
		this.task = task;
		this.date = date;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date2) {
		this.date = date2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
