package model;

import java.util.ArrayList;

/*
 *  @Author Bruno Ribeiro - 2017138
 *	@Collaborator Amilcar Aponte
 *	https://github.com/brunobpr/Design_Patterns
*/
public interface CountryDAO {

	// The client program must present the user with a menu with the following
	// options:

	/**
	 * Retrieve all records stored in the database table
	 * 
	 * @return an array list of countries
	 */
	public ArrayList<Country> getCountries();

	/**
	 * Retrieve records by country name
	 * 
	 * @param name
	 *            The name of the country to be found
	 * @return n array list of countries with the given name
	 */
	public ArrayList<Country> findCountryByName(String name);

	/**
	 * Retrieve records by country code
	 * 
	 * @param code
	 *            The code of the country to be found
	 * @return the country of the given code, or null if there is no country
	 */
	public Country findCountryByCode(String code);

	/**
	 * Add new records into the database
	 * 
	 * @param country
	 *            the country to be added to the database
	 * @return true if the country was added, false if there was any issue
	 */
	public boolean addNewCountry(Country country);

}
