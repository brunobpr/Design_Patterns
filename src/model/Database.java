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
			Connection conn;
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
				this.resultSet = stmt.executeQuery( query ) ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return this.resultSet;
		}
		
		
		public ResultSet getResult() {
			return resultSet;
		}
		
		
		public static Database getInstance() {
			return instance;
		}
	
		
		
}
