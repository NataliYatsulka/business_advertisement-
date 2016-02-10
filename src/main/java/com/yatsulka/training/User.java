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

	public Integer getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "id= " + id + ";  lastName = " + lastName + ";  firstName = " + firstName + "; city =" + city;
	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((city == null) ? 0 : city.hashCode());
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
