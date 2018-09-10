package com.zocom.assignment.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	
	private static Database mInstance;
	
	private Database() {}
	
	public static Database getInstance() {
		if(mInstance == null) {
			mInstance = new Database();
		}
		return mInstance;
	}
	
	public PreparedStatement prepare(String query) throws SQLException {
		return getConnection().prepareStatement(query);
	}
	
	public Connection getConnection() {
		try {
			Properties props = new Properties();
			props.setProperty("user", "root");
			props.setProperty("password", "");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/centric?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", props);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
