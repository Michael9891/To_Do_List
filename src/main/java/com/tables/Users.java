package com.tables;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="NAME")
	private String userName;
	@Column(name="PASSWORD")
	private String password;
	//list of items related to to user with primary key ID
	@OneToMany(fetch = FetchType.EAGER , mappedBy = "user")
	private List<Tasks>tasks = new LinkedList<Tasks>();
	
	public Users() {}
	
	public Users(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.tasks = new LinkedList<Tasks>();
	}
	public List<Tasks> getItems() {
		return tasks;
	}
	public void setItems(List<Tasks> items) {
		this.tasks = items;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
