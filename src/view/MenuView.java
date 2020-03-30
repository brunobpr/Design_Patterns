package view;

import java.util.ArrayList;
import model.Country;

/*
 * @Author Bruno Ribeiro
 * @Collaborator Amilcar Aponte
 * 
 * The only purpose of this class is to print info to the console
 * Including menu items and countries info
*/
public class MenuView {

	public MenuView() {
		// This will be displayed as soon as the code is compiled
		// IT is an welcome page
		System.out.println("Bruno Pereira Ribeiro - 2017138\nCCT College Dublin");
		System.out.println("OODB - Pattern Combination");
		divider();
		System.out.println("\nWELCOME TO WORLD DB\n");
	}

	// Print menu options to the use
	public void options() {
		System.out.println("Please, choose one option:");
		System.out.println("| 1 | - List all countries");
		System.out.println("| 2 | - Find a country by name");
		System.out.println("| 3 | - Find a country by code");
		System.out.println("| 4 | - Add a new country");
		System.out.println("| 5 | - Exit");
	}

	// Print a line to facilitate readability
	private void divider() {
		System.out.println("________________________________________");
	}

	// Print lots of lines to clean the console, it makes easir to divide new or old
	// info
	private void clean() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	// This methods prints all countries on the array
	// And aslo keep track of how many countries is being displayed
	// This method is called from the Controller class on: Switch case "2" and from
	// the method countryByName()
	public void showCountriesList(ArrayList<Country> countries) {
		clean();
		System.out.println("Number of countries found: " + countries.size());
		int counter = 1;
		for (Country country : countries) {
			System.out.println("------------------[" + counter + "]------------------");
			System.out.println(country);
			counter++;
		}
	}

	// This methods prints the country info
	// This method is called from the Controller class on the method countryByCode()
	public void showCountry(Country country, String countrySearched) {
		clean();
		if (country != null) {
			System.out.println("Country found: " + country.getName());
			divider();
			System.out.println(country);
		} else
			System.out.println(countrySearched + " does not exist!\n");
	}

	// This methods prints the options of continents
	// It is called from the Controller class on the method addNewCountry() when the
	// user enter a string that does not matches with the enum
	public void displayContinents() {
		divider();
		System.out.println("Continent not valid!\nChoose one: ");
		System.out.println("Asia");
		System.out.println("Europe");
		System.out.println("North America");
		System.out.println("South America");
		System.out.println("Africa");
		System.out.println("Oceania");
		System.out.println("Antarctica");
		divider();
	}
}
