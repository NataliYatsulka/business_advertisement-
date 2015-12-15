package com.yatsulka.training;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Test;

public class UserServiseTest {
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static UserServise userService = new UserServise();

	@Test
	public void testGetUsers() {

		// TODO: check number of users

		List<User> listUsers = userService.getUsers();
		assertEquals(0, listUsers.size());
		// G
		User user = new User("Petro111", "Porox", "KKKuiv");
		User createdUser = userService.create(user);
		// W
		listUsers = userService.getUsers();
		// T
		assertEquals(1, listUsers.size());
		assertEquals("Petro111", listUsers.get(0).firstName);
		assertEquals("Porox", listUsers.get(0).lastName);
		assertEquals("KKKuiv", listUsers.get(0).city);

	}

	@Test
	public void testCreate() {
		// GIVEN
		User user = new User("Petro", "Porox", "KKKuiv");
		// WHEN
		User createdUser = userService.create(user);
		// THEN
		assertEquals("Petro", createdUser.firstName);
		assertEquals("Porox", createdUser.lastName);
		assertEquals("KKKuiv", createdUser.city);

	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUserById() {
		fail("Not yet implemented");
	}

	@After
	public void cleanUp() {
		deleteUsers();
	}

	private void deleteUsers() {
		Statement statement = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", DB_USER, DB_PASSWORD);
			statement = conn.createStatement();
			statement.execute("DELETE  FROM `test_users` ");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
