package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.*;
import model.Country.CountryBuilder;
import view.*;


/*
 * @Author Bruno Ribeiro - 2017138
 * @Collaborator Amilcar Aponte 
 * This is the controller class, it is works as a bridge between models and views
 * It is the only class that have access to those packages
*/
public class Controller {
	
	private MenuView menu;
	private BufferedReader reader;
	private MySQLCountryDAO sqlCountry;
	
	//Getting the intance of the validator class
	private Validator validator = Validator.getInstance();
	
	//Constructor
	public Controller() {
		//New instance of the MySQLCountryDAO
		sqlCountry = new MySQLCountryDAO();
		//New instance of the MenuView
		menu = new MenuView();
		//New BufferedReaer
		reader = new BufferedReader(new InputStreamReader(System.in));
		getMenuOption();
	}

	//This method is responsible displaying the menu options and redirect the user for the option entered
	//It uses a switch statement to manage it
	private void getMenuOption() {
		//Print the options
		menu.options();
		//Get the input and store in the optionSelected string
		String optionSelected = getInput();;
		switch(optionSelected) {
			case "1":
				//sqlCountry.getCountries() returns a list of countries, 
				//which is sent over to menu.showCountriesList()
				menu.showCountriesList(sqlCountry.getCountries());
				getMenuOption();
			case "2":
				countryByName();
			case "3":
				countryByCode();
			case "4":
				addCountry();
			case "0":
				Database.instance.close();
				System.exit(0);;
			default:
				System.out.println("Option not available! 0-5");
				getMenuOption();
		};
		
	}

	
	//This method is called from the switch case "3"
	private void countryByCode() {
		System.out.println("Find a country by code");
		System.out.print("Code: ");
		String code = getInput();
		//sqlCountry.findCountryByCode(code) returns a country or null
		//menu.showCountry receives a country and the string code
		//If the country is null, the menuShowCountry will print -code- not found!
		menu.showCountry(sqlCountry.findCountryByCode(code), code);
		//Return to the menu
		getMenuOption();
	}
	//This method is called from the switch case "2"
	private void countryByName() {
		System.out.println("Find a country by Name");
		System.out.print("Name: ");
		String name = getInput();
		// sqlCountry.findCountryByName returns a list of countries
		// this list is sent over to menu.showCountriesList to be printed
		menu.showCountriesList(sqlCountry.findCountryByName(name));
		//Return to the menu
		getMenuOption();
	}
	
	
	//This method is called from the switch case "4"
	public void addCountry() {	
		System.out.println("Add a new country or type 0 to return.");
		//For each elemented there is a method, this is to make it more modular
		String code = countryCodeInput();
		String name = countryNameInput();
		Continent continent = countryContinentInput();
		Float surfaceArea = countrySurfaceInput();	
		String headOfState = countryHeadInput();
		// The country object doesn't have too many attributes
		// There isn't a huge difference between using constructors or methods.
		// But according to the database, code, name and continent cannot be null and doent have a default value
		CountryBuilder countryBuilder = new CountryBuilder(code, name, continent);
		//The headOfState will be only set if it isn't null
		if(headOfState != null) {
			countryBuilder.setHeadOfState(headOfState);
		}
		//The surfaceArea will be only set if it isn't null
		if(surfaceArea != null) {
			countryBuilder.setSurfaceArea(surfaceArea);
		}
		// countryBuilder.build() returns the new country
		// sqlCountry.addNewCountry receives the new country, extract its info and add it to the database
		sqlCountry.addNewCountry(countryBuilder.build());
		getMenuOption();
	}
	
	
	
	/**This method is responsible to get, validate and return the country code
	 * @return country code entered by the usar
	 */
	public String countryCodeInput() {
		System.out.print("Code: ");	
		String code = getInput();
		//validator.countryCode returns false if the country code is valid, in that way the loop is broken
		while(validator.countryCode(code)) {
			//If not valid, give a hint and ask for new input
			System.out.print("Only letters or numbers. 3 chars long.\nCode: ");	
			code = getInput();
		}
		return code;
	}
	
	
	
	/**This method is responsible to get, validate and return the country name
	 * @return country name entered by the usar
	 */
	public String countryNameInput() {
		System.out.print("Name: ");	
		String name = getInput();
		//validator.countryName returns false if the country name is valid, in that way the loop is broken
		while(validator.countryName(name)) {
			//If not valid, give a hint and ask for new input
			System.out.print("Country name not valid!\nName: ");
			name = getInput();
		}
		return name;
	}
	
	/**This method is responsible to get, validate and return the country the surface area
	 * @return float country surface area entered by the suar
	 */
	public float countrySurfaceInput() {
		System.out.print("Surface Area: ");	
		String input = getInput();
		//It only accepts numbers
		while(!input.matches("[0-9]+")) {
			//If its not a number, give a hint and ask for new input
			System.out.print("Not a valid number!\nSurface Area: ");
			input = getInput();
		}
		//return the float value of the string
		return Float.valueOf(input) ;
	}
	
	
	/**This method is responsible to get, validate and return the country continent
	 * @return an ENUM corresponding to country continenet entered by the suar
	 */
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
	
	/**This method is responsible to get, validate and return the country head of state
	 * @return country head of state entered by the suar
	 */
	public String countryHeadInput() {
		System.out.print("Head of State: ");	
		String headOfState = getInput();
		while(!headOfState.matches("[A-Za-z' ]+")) {
			System.out.print("This is not a valid name.\nHead of State: ");	
			headOfState = getInput();
		}
		return headOfState;
	}
	
	
	/**This methos has the only function of getting the input from the Buffered Reader
	 * @return a string with the input by the suar
	 */
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
