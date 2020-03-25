package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLCountry implements CountryDAO{

	Database db = Database.getInstance();
	
	@Override
	public ArrayList<Country> getCountries() {
		String name;
		String code;
		float surfaceArea;
		String headOfState;
		Continent continent;
		
		ArrayList<Country> countriesList = new ArrayList<Country>();
		db.selectQuery("SELECT * FROM country;");
		try{while(db.getResult().next()) {
				code = db.getResult().getString(1);
				name = db.getResult().getString(2);
				continent = Continent.valueOf(db.getResult().getString(3).toUpperCase().replace(" ", "_"));
				surfaceArea = db.getResult().getFloat(4);
				headOfState = db.getResult().getString(5);
				Country.CountryBuilder countryBuilder = new Country.CountryBuilder(code, name, continent, surfaceArea);
				if(headOfState !=null) {
					countryBuilder.setHeadOfState(headOfState);
				}		
				countriesList.add(countryBuilder.build());
				
			}
		}catch(SQLException sql){
			
		}
		return countriesList;
	}

	@Override
	public Country findCountryByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country findCountryByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewCountry(Country country) {
		// TODO Auto-generated method stub
		return false;
	}

}
