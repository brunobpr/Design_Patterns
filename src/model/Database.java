package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

public enum Database {
		instance;
		
		private String server = "jdbc:mysql://52.50.23.197:3306/world";
		private String user = "cctstudent";
		private String password = "Pass1234!";
		private Connection conn;
		private Statement stmt;
		private ResultSet resultSet;
		
		private Database () {
			try {
				conn = DriverManager.getConnection( server, user, password );
			    stmt = conn.createStatement() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
		public ResultSet selectQuery(String query) {
			try {
				resultSet = stmt.executeQuery( query ) ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resultSet;
		}
		
		public boolean insertQuery(String query) {
			try {
				return stmt.execute( query ) ;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		public void close() {try {
				resultSet.close();
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public ResultSet getResult() {
			return resultSet;
		}
		
		
		public static Database getInstance() {
			return instance;
		}
	
		
		
}
