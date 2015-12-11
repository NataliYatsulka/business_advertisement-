package com.yatsulka.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServise {

	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	/*
	 * 1. create ConnectionService class 2. move
	 * DriverManager.getConnection(...) code to ConnectionService 3. private
	 * ConnectionService connectionService = new ConnectionService();
	 * 
	 * 4. replace all conn = DriverManager.getConnection() with
	 * connectionService.getConnection()
	 */
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		Statement statement = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", DB_USER, DB_PASSWORD);
			statement = conn.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM `test_users`");

			while (rs.next()) {
				User user = readUserFromResultSet(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return users;

	}

	public User create(User user) {

		Statement statement = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", DB_USER, DB_PASSWORD);
			statement = conn.createStatement();
			String query = "INSERT INTO `test_users` (`first_name`, `last_name`, `city`, `phone` ) VALUES ('"
					+ user.firstName + "' , '" + user.lastName + "' , '" + user.city + "' , ' 6565 ')";
			System.out.println(query);
			statement.executeUpdate(query);

			// ResultSet rs = statement.executeQuery("SELECT * FROM `test_users`
			// WHERE id = " + id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	public User update(User user) {

		Statement statement = null;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", DB_USER, DB_PASSWORD);
			statement = conn.createStatement();
			String query = "UPDATE  `test_users` SET `first_name` = " + "'" + user.firstName + "'" + ", `last_name` = "
					+ "'" + user.lastName + "'" + ", `city` = " + "'" + user.city + "'" + "  WHERE id = " + user.id;
			System.out.println(query);
			statement.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return getUserById(user.id);

	}

	public User getUserById(int id) {
		Statement statement = null;
		User user = null;

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", DB_USER, DB_PASSWORD);
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM `test_users` WHERE id = " + id);

			while (rs.next()) {
				user = readUserFromResultSet(rs);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return user;
	}

	public List<User> deleteUsersById(int id) {
		Statement statement = null;
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", DB_USER, DB_PASSWORD);
			statement = conn.createStatement();
			statement.execute("DELETE  FROM `test_users` WHERE id = " + id);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return getUsers();
	}

	private User readUserFromResultSet(ResultSet rs) throws SQLException {
		Integer userid = rs.getInt("id");
		String userLastName = rs.getString("last_name");
		String userFirstName = rs.getString("first_name");
		String userCity = rs.getString("city");

		User user = new User(userid, userLastName, userFirstName, userCity);
		System.out.println(user);

		return user;
	}
}