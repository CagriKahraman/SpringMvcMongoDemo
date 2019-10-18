package com.jcg.springmvc.mongo;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id, name ,lastName, phone;

	public Customer() {
		super();
	}

	public Customer(String id, String name , String lastName, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}