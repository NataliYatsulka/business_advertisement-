package com.yatsulka.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServise {


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

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");

			statement = conn.createStatement();

			// �������� ������ � ��
			ResultSet rs = statement.executeQuery("SELECT * FROM `test_users`");

			// � ���� ��� �� ���� �������� �� ���� while ���������
			while (rs.next()) {
				Integer userid = rs.getInt("id");
				String userLastName = rs.getString("last_name");
				String userFirstName = rs.getString("first_name");

				System.out.println("userid : " + userid);
				System.out.println("username : " + userLastName);
				System.out.println("username : " + userFirstName);

				User user = new User(userid, userLastName, userFirstName);
				users.add(user);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return users;

	}
	
	public List<User> deleteUsers() {
        Statement statement = null;

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
            statement = conn.createStatement();
            statement.execute("DELETE FROM `test_users` WHERE id=1");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return getUsers();
    }
}
