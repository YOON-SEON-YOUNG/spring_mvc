package com.kh.sample01;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnetionTest {

	private static final String DRIVE = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ID = "spring";
	private static final String PW = "1234";
	
	public static void main(String[] args) {
	
		try {
			testConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void testConnection() throws Exception {
		Class.forName(DRIVE);
		Connection conn = DriverManager.getConnection(URL, ID, PW);
		System.out.println("conn: " + conn);
	}
	
}
