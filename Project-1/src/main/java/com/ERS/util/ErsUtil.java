package com.ERS.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ErsUtil {
	private static final String URL = "jdbc:postgresql://ers.cfedhkleidqr.us-east-2.rds.amazonaws.com:5432/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Zechion110!";
	
	private static Connection conn;

	public static Connection getConnection() {

		try { 

			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Can not connect to database!");
		}

		return conn;
	}
}
