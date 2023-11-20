package com.example.survey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user")
@Entity
public class User {
	
	@Column(name="name")
	private String name;
	@Id
	@Column(name="phone_number")
	private String phoneNumber;
	@Column(name="email")
	private String email;
	@Column(name="age")
	private int age;
	@Column(name="title_id")
	private int titleId;
	@Column(name="q_id")
	private int qId;
	@Column(name="ans")
	private String ans;
	
	public User() {
		super();
	}

	public User(String name, String phoneNumber, String email, int age, int titleId, int qId, String ans) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.age = age;
		this.titleId = titleId;
		this.qId = qId;
		this.ans = ans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public int getTitleId() {
		return titleId;
	}

	public int getqId() {
		return qId;
	}
	
}
