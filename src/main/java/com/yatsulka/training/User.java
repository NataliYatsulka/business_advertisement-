package com.yatsulka.training;

public class User {
	Integer id;
	String lastName;
	String firstName;
	String city;
	

	public User(Integer id, String lastName, String firstName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "id= " + id + ";  lastName = " + lastName + ";  firstName = " + firstName;
	}

}
