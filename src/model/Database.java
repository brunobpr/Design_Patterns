package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

/*
 * @Author Bruno Ribeiro - 2017138
 * @Collaborator Amilcar Aponte
 * https://github.com/brunobpr/Design_Patterns
 * 
 * This Database uses the ENUM Singleton Pattern to create a single instance of the database connection
 * The database is used throughtout the whole program, so there is no need to implement a Lazy Singleton
 * And since ENUM cannot be broken using a SetAccessible to the Constructors, 
 * I believe using it would be the most advantageous 
 * 
*/
public enum Database {

	// For this program only the MySQLCountryDAO class is using the database
	// connection
	// But not matter how many classes there are, all of them would use the same
	// instance of the database
	// This is the reason for using Singleton Pattern for DB connection.
	instance;

	// Setting the given parameters to access the databse
	// The verifyServerCertificate=false&useSSL=true was added to make the error below disappear
	// Error: Establishing SSL connection without server's identity verification is not recommended
	private String server = "jdbc:mysql://52.50.23.197:3306/world?verifyServerCertificate=false&useSSL=true";
	private String user = "cctstudent";
	private String password = "Pass1234!";
	private Connection conn;
	private Statement stmt;
	private ResultSet resultSet;

	private Database() {
		// Creating a new connection to the server, using the username and password that
		// were given
		try {
			conn = DriverManager.getConnection(server, user, password);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// When managing database connections, it's mandatory to catch any error that
			// may occur
			e.printStackTrace();
		}

	}

	/**
	 * @param query
	 *            a string containing a SQL "SELECT" query
	 * @return the ResultSet from the database
	 */
	public ResultSet selectQuery(String query) {
		try {
			resultSet = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Try again!\n" + e.getMessage());
		}
		return resultSet;
	}

	/**
	 * @param query
	 *            a string containing a SQL "INSERT" query;
	 * @return true or false depending if insert query was successful
	 */
	public boolean insertQuery(String query) {
		try {
			return stmt.execute(query);
		} catch (SQLException e) {
			System.out.println("Try again!\n" + e.getMessage());
			return false;
		}

	}

	// Close all Database related elements
	public void close() {
		try {
			resultSet.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the results to the DAO classes
	 */
	public ResultSet getResult() {
		return resultSet;
	}

	/**
	 * @return the instance of the database connection
	 */
	public static Database getInstance() {
		return instance;
	}

}
