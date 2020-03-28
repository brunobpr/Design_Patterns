package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountryDAO implements CountryDAO {

	Database db = Database.getInstance();
	String name;
	String code;
	float surfaceArea;
	String headOfState;
	Continent continent;

	@Override
	public ArrayList<Country> getCountries() {
		ArrayList<Country> countriesList = new ArrayList<Country>();
		db.selectQuery("SELECT * FROM country WHERE NOT continent = '';");
		int conter = 1;
		try{while(db.getResult().next()) {
				code = db.getResult().getString(1);
				name = db.getResult().getString(2);
				String cont = db.getResult().getString(3).toUpperCase().replace(" ", "_");
				continent = Continent.valueOf(cont);
				surfaceArea = db.getResult().getFloat(4);
				headOfState = db.getResult().getString(5);
				Country.CountryBuilder countryBuilder = new Country.CountryBuilder()
						.setCode(code)
						.setName(name)
						.setContinent(continent)
						.setSurfaceArea(surfaceArea)
						.setHeadOfState(headOfState);
				countriesList.add(countryBuilder.build());			
			}
		}catch(SQLException sql){
			sql.getMessage();
		}
		
		return countriesList;
	}

	@Override
	public ArrayList<Country> findCountryByName(String name) {
		ArrayList<Country> countriesList = new ArrayList<Country>();
		db.selectQuery("SELECT * FROM country WHERE name='" + name + "';");
		try {
			while (db.getResult().next()) {
				code = db.getResult().getString(1);
				name = db.getResult().getString(2);
				continent = Continent.valueOf(db.getResult().getString(3).toUpperCase().replace(" ", "_"));
				surfaceArea = db.getResult().getFloat(4);
				headOfState = db.getResult().getString(5);
				Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code, name, continent);
				
				countryBuilder.setSurfaceArea(surfaceArea).setHeadOfState(headOfState);
				countriesList.add(countryBuilder.build());
			}
		} catch (SQLException sql) {
			sql.getMessage();
		}

		return countriesList;
	}

	@Override
	public Country findCountryByCode(String code) {
		Country country;
		try {
			db.selectQuery("SELECT * FROM country WHERE code='" + code + "';").next();
			name = db.getResult().getString(2);
			continent = Continent.valueOf(db.getResult().getString(3).toUpperCase().replace(" ", "_"));
			surfaceArea = db.getResult().getFloat(4);
			headOfState = db.getResult().getString(5);
			country = new Country.CountryBuilder().setCode(code).setName(name).setContinent(continent)
					.setSurfaceArea(surfaceArea).setHeadOfState(headOfState).build();
			return country;
		} catch (SQLException sql) {
			System.out.println(sql.getMessage());
			return null;
		}
	}

	@Override
	public boolean addNewCountry(Country country) {
		code = country.getCode();
		name = country.getName();
		continent = country.getContinent();
		System.out.println(continent);
		surfaceArea = country.getSurfaceArea();
		headOfState = country.getHeadOfState();
		return db.insertQuery("INSERT INTO country (code, name, continent, surfaceArea, headOfState) VALUES ('" + code
				+ "', '" + name + "', '" + continent + "', '" + surfaceArea + "', '" + headOfState + "');");

	}

}
