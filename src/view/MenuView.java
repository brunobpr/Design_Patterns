package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Country;

public class MenuView {

	public MenuView() {
		System.out.println("Bruno Pereira Ribeiro - 2017138\nCCT College Dublin");
		System.out.println("OODB - Pattern Combination");
		divider();
		System.out.println("\nWELCOME TO WORLD DB\n");
	}

	public void options() {
		System.out.println("Please, choose one option:");
		System.out.println("| 1 | - List all countries");
		System.out.println("| 2 | - Find a country by country code");
		System.out.println("| 3 | - Find a country by name");
		System.out.println("| 4 | - Add a new country");
		System.out.println("| 5 | - Exit");	
	}

	private void divider() {
		System.out.println("________________________________________");
	}

	private void clean() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public void showAllContries(ArrayList<Country> countries) {
		clean();
		System.out.println("List of All Countries");
		for(Country country : countries) {
			System.out.println(country);
		}
	}

	public void showCountry(Country country, String countrySearched) {
		clean();
		if(country != null) {
			System.out.println("Country found: " + country.getName());
			divider();
			System.out.println(country);
		}
		else System.out.println(countrySearched + " does not exist!\n");
	}
}
