package controller;
import java.sql.SQLException;

import model.*;
public class Controller {
		
	public Controller() {
		Database database = Database.getInstance();
		
		
		System.out.println();
		
		try {
			while( database.selectQuery("SELECT * FROM country;").next() ) {
					System.out.println(database.getResult().getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while( database.getResult().next() ) {
					System.out.println(database.getResult().getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
