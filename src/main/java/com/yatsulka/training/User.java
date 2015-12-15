package com.yatsulka.training;

public class User {
	Integer id;
	String lastName;
	String firstName;
	String city;

	public User(Integer id, String firstName,String lastName, String city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}

	public User(String lastName, String firstName, String city) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return "id= " + id + ";  lastName = " + lastName + ";  firstName = " + firstName + "; city =" + city;
	}

}
