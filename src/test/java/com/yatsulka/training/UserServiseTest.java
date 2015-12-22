package com.yatsulka.training;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserServiseTest {
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static UserServise userService = new UserServise();

	@Before
	public void cleanUp() {
		deleteUsers();
	}
		
	@Test
	public void testGetUsers() {
		List<User> listUsers = userService.getUsers();
		assertEquals(0, listUsers.size());

		// G (prerequisite)
		User user = new User("Petro111", "Porox", "KKKuiv");
		userService.create(user);

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
//		assertEquals("Petro", createdUser.firstName);
//		assertEquals("Porox", createdUser.lastName);
//		assertEquals("KKKuiv", createdUser.city);
		
		// These two lines can be considered equivalent:
		assertEquals(user, createdUser);
		// assertTrue(user.equals(createdUser));
		
		User userFromDb = userService.getUserById(createdUser.id);
		assertEquals(user, userFromDb);
	}
	
	@Test(expected = Exception.class)
	public void testCreate_lastName_isNull() {
		// GIVEN
		User user = new User(null, "Porox", "KKKuiv");
		
		// WHEN
		userService.create(user);

		// THEN
		// expect some exceptions
	}

	@Test(expected = RuntimeException.class)
	public void testCreate_lastName_isTooLong() {
		// GIVEN
		User user = new User("1234567890123456789012345678901234567890asdfgh", "Porox", "KKKuiv");
		
		// WHEN
		userService.create(user);

		// THEN
		// expect RuntimeException exceptions
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
