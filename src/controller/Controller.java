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
	private MySQLCountryDAO sqlCountry;
	private Database database = Database.getInstance();
	private Validator validator = Validator.getInstance();
	
	public Controller() {
		sqlCountry = new MySQLCountryDAO();
		menu = new MenuView();
		reader = new BufferedReader(new InputStreamReader(System.in));
		getMenuOption();
	}

	private void getMenuOption() {
		menu.options();
		String optionSelected = getInput();;
		switch(optionSelected) {
			case "1":
				menu.showCountriesList(sqlCountry.getCountries());
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

	

	private void countryByCode() {
		System.out.println("Find a country by code");
		System.out.print("Code: ");
		String code = getInput();
		menu.showCountry(sqlCountry.findCountryByCode(code), code);
		getMenuOption();
	}
	
	private void countryByName() {
		System.out.println("Find a country by Name");
		System.out.print("Name: ");
		String name = getInput();
		menu.showCountriesList(sqlCountry.findCountryByName(name));
		getMenuOption();
	}
	
	
	
	public void addCountry() {	
		System.out.println("Add a new country or type 0 to return.");
		String code = countryCodeInput();
		String name = countryNameInput();
		Continent continent = countryContinentInput();
		Float surfaceArea = countrySurfaceInput();	
		String headOfState = countryHeadInput();
		CountryBuilder countryBuilder = new CountryBuilder(code, name, continent);
		if(headOfState != null) {
			countryBuilder.setHeadOfState(headOfState);
		}
		if(headOfState != null) {
			countryBuilder.setSurfaceArea(surfaceArea);
		}
		sqlCountry.addNewCountry(countryBuilder.build());
		getMenuOption();
	}
	
	public String countryCodeInput() {
		System.out.print("Code: ");	
		String code = getInput();
		while(validator.countryCode(code)) {
			System.out.print("Only letters or numbers. 3 chars long.\nCode: ");	
			code = getInput();
		}
		return code;
	}
	
	public String countryNameInput() {
		System.out.print("Name: ");	
		String name = getInput();
		while(validator.countryName(name)) {
			System.out.print("Country name not valid!\nName: ");
			name = getInput();
		}
		return name;
	}
	
	public float countrySurfaceInput() {
		System.out.print("Surface Area: ");	
		String input = getInput();
		while(!input.matches("[0-9]+")) {
			System.out.print("Not a valid number!\nSurface Area: ");
			input = getInput();
		}
		return Float.valueOf(input) ;
	}
	
	public Continent countryContinentInput() {
		Continent  continent = null;
		try {
			System.out.print("Continent:");
			String input = getInput().toUpperCase().replace(" ", "_");
		    continent = Continent.valueOf(input);		
		}catch(IllegalArgumentException e) {
			menu.displayContinents();
			return countryContinentInput();
		}
		return continent;
	}
	
	public String countryHeadInput() {
		System.out.print("Head of State: ");	
		String headOfState = getInput();
		while(!headOfState.matches("[A-Za-z' ]+")) {
			System.out.print("This is not a valid name.\nHead of State: ");	
			headOfState = getInput();
		}
		return headOfState;
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
}
