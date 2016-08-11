package uk.org.kelsohighschool.ps.sunnyDaleSchool.db;

import java.sql.*;

public class DatabaseConnection {
	
	private Connection conn;
	private Statement statement;
	private ResultSet resultSet;
	
	public DatabaseConnection(String url, String username, String password) throws SQLException {
		conn = DriverManager.getConnection(url, username, password);
	}
	
	
	
	public String GetVersion() throws SQLException { 
		statement = conn.createStatement();  
		resultSet = statement.executeQuery("show server_version");  
		String rtnVal = "";  
		if (resultSet.next()) {   
			rtnVal = resultSet.getString(1);  
		}  
		return rtnVal;
	}
	
	public void close() { 
		try {
			if (resultSet != null) {
				resultSet.close();
			}   
			if (statement != null) {
				statement.close();  
			}   
			if (conn != null) {
				conn.close();
			} 
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
