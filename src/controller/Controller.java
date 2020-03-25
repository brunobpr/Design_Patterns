package controller;
import java.sql.SQLException;
import model.*;;

public class Controller {
		
	public Controller() {
		Database database = Database.getInstance();
		
		MySQLCountry c = new MySQLCountry() ;
	
		System.out.println(c.getCountries());
	}
	
	
}
