package model;

import java.sql.SQLException;
import java.util.ArrayList;

/*	@Author Bruno Ribeiro
 *	@Collaborator Amilcar Aponte
 *   https://github.com/brunobpr/Design_Patterns
 */
public class MySQLCountryDAO implements CountryDAO {

	Database db = Database.getInstance();
	
	@Override
	public ArrayList<Country> getCountries() {
		ArrayList<Country> countriesList = new ArrayList<Country>();
		// By the time this query was last tested, someone managed to insert a new
		// country without a continent
		// Even though it's not allowed by the database, the easiest solution was to
		// change the query to not get any country withouth continent
		// in order to avoid conflicts with the ENUM
		db.selectQuery("SELECT * FROM country WHERE NOT continent = '';");
		try {
			// This while loop will get all the countries from the database and add it to
			// the countriesList.
			while (db.getResult().next()) {
				// Getting the info from the country table and storing onto the variables
				String code = db.getResult().getString(1);
				String name = db.getResult().getString(2);
				// Converting the cont string to the same format of the ENUM
				// eg: south america is converted to SOUTH_AMERICA
				String cont = db.getResult().getString(3).toUpperCase().replace(" ", "_");
				Continent continent = Continent.valueOf(cont);
				float surfaceArea = db.getResult().getFloat(4);
				String headOfState = db.getResult().getString(5);
				// The country object doesn't have too many attributes
				// There isn't a huge difference between using constructors or methods.
				Country.CountryBuilder countryBuilder = new Country.CountryBuilder().setCode(code).setName(name)
						.setContinent(continent).setSurfaceArea(surfaceArea).setHeadOfState(headOfState);
				// The CountryBuilder.build() returns a new Country object, this new country is
				// added to the list straight away
				countriesList.add(countryBuilder.build());
			}
		} catch (SQLException sql) {
			// Print the error message if there is any problem within the database
			sql.getMessage();
		}

		// Return the populated (or not) list
		return countriesList;
	}

	@Override
	public ArrayList<Country> findCountryByName(String name) {
		// It is possible to have more than one country with the same name (for this
		// project)
		// So we need to retun a array list, null, with one or muiltiple countries
		ArrayList<Country> countriesList = new ArrayList<Country>();
		db.selectQuery("SELECT * FROM country WHERE name LIKE '%" + name + "%';");
		try {
			while (db.getResult().next()) {
				// Getting the info from the country table and storing onto the variables
				String code = db.getResult().getString(1);
				// Converting the string to the same format of the ENUM
				// eg: south america is converted to SOUTH_AMERICA
				Continent continent = Continent.valueOf(db.getResult().getString(3).toUpperCase().replace(" ", "_"));
				float surfaceArea = db.getResult().getFloat(4);
				String headOfState = db.getResult().getString(5);
				// The country object doesn't have too many attributes
				// There isn't a huge difference between using constructors or methods.
				Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code, name, continent);
				countryBuilder.setSurfaceArea(surfaceArea).setHeadOfState(headOfState);
				// The CountryBuilder.build() returns a new Country object, this new country is
				// added to the list straight away
				countriesList.add(countryBuilder.build());
			}
		} catch (SQLException sql) {
			// Print the error message if there is any problem within the database
			sql.getMessage();
		}

		return countriesList;
	}

	@Override
	public Country findCountryByCode(String code) {
		// According to the database code is the primary key, therefore it's unique
		Country country;
		try {
			// Getting the info from the country table and storing onto the variables
			db.selectQuery("SELECT * FROM country WHERE code='" + code + "';").next();
			String name = db.getResult().getString(2);
			// Converting the string to the same format of the ENUM
			// eg: south america is converted to SOUTH_AMERICA
			Continent continent = Continent.valueOf(db.getResult().getString(3).toUpperCase().replace(" ", "_"));
			float surfaceArea = db.getResult().getFloat(4);
			String headOfState = db.getResult().getString(5);
			// The country object doesn't have too many attributes
			// There isn't a huge difference between using constructors or methods.
			Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code, name, continent);
			countryBuilder.setSurfaceArea(surfaceArea).setHeadOfState(headOfState);

			// The country returned from CountryBuilder.build() returns straight away
			return countryBuilder.build();
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
			return null;
		}
	}

	@Override
	public boolean addNewCountry(Country country) {
		// Getting the values from the country object
		String code = country.getCode();
		String name = country.getName();
		Continent continent = country.getContinent();
		float surfaceArea = country.getSurfaceArea();
		String headOfState = country.getHeadOfState();
		// Execute the insert query, it returns a boolean value to verify if the
		// insertion was successfull
		return db.insertQuery("INSERT INTO country (code, name, continent, surfaceArea, headOfState) VALUES ('" + code
				+ "', '" + name + "', '" + continent + "', '" + surfaceArea + "', '" + headOfState + "');");

	}

}
