package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import model.*;
import model.Country.CountryBuilder;
import view.*;

public class Controller {
	
	private MenuView menu;
	private BufferedReader reader;
	private MySQLCountry sqlCountry;
	private Database database = Database.getInstance();
	
	public Controller() {
		/*Database database = Database.getInstance();
		Continent cont =  Continent.AFRICA;
		Country c = new Country.CountryBuilder("ARL", "Andromeda", cont, 349851).setHeadOfState("Dilmae").build();
		MySQLCountry sql = new MySQLCountry() ;
	
		System.out.println(sql.addNewCountry(c));
		System.out.println(sql.findCountryByCode("ARL"));*/
		sqlCountry = new MySQLCountry();
		menu = new MenuView();
		reader = new BufferedReader(new InputStreamReader(System.in));
		getMenuOption();
		
	}

	private void getMenuOption() {
		menu.options();
		String optionSelected = getInput();;
		switch(optionSelected) {
			case "1":
				menu.showAllContries(sqlCountry.getCountries());
				getMenuOption();
			case "2":
				countryByName();
			case "3":
				countryByCode();
			case "4":
				addCountry();
			case "0":
				System.exit(0);;
		};
		
	}

	private String getInput() {
		String input;
		try {
			input = reader.readLine();	
			return input;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print("Try again: ");
			return getInput();
		}
		
	}

	private void countryByName() {
		System.out.println("Find a country by Name");
		System.out.print("Name: ");
		String name = getInput();
		menu.showCountry(sqlCountry.findCountryByName(name), name);
		getMenuOption();
	}
	
	private void countryByCode() {
		System.out.println("Find a country by code");
		System.out.print("Code: ");
		String code = getInput();
		menu.showCountry(sqlCountry.findCountryByCode(code), code);
		getMenuOption();
	}
	
	public void addCountry() {	
		System.out.println("Add a new country");
		System.out.print("Code: ");	
		String code = getInput();
		System.out.print("Name: ");	
		String name = getInput();
		System.out.print("Continent: ");	
		Continent continent = Continent.valueOf((getInput()).toUpperCase().replace(" ", "_"));
		System.out.print("Surface area: ");	
		Float surfaceArea = Float.valueOf(getInput());
		System.out.print("Head of state: ");	
		String headOfState = getInput();
		CountryBuilder countryBuilder = new CountryBuilder(code, name, continent, surfaceArea);
		if(headOfState != null) {
			countryBuilder.setHeadOfState(headOfState);
		}
		sqlCountry.addNewCountry(countryBuilder.build());
		getMenuOption();
	}
	
	
}
