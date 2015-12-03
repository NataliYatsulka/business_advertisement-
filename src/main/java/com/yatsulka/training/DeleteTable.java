package com.yatsulka.training;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteTable {
	{
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");

			Statement st = conn.createStatement();
			String sqlQuiry = "DELETE FROM test_users WHERE id=1";
			ResultSet rs = st.executeQuery(sqlQuiry);
			int maxCols = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= maxCols; i++) {

				if (i > 1) {
					System.out.println(", ");

				}
				System.out.println(rs.getMetaData().getColumnLabel(i));
			}
			System.out.println();
			System.out.println("----------------------------------------------------------");
			while (rs.next()) {
				for (int i = 0; i <= maxCols; i++) {
					System.out.println(rs.getString(i) + ", ");

				}
				System.out.println();
			}
			st.close();
			conn.close();

		} catch (SQLException s) {
			System.out.println(s.toString());
		}
	}
}