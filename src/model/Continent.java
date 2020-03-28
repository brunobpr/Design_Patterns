package model;


/*
 * @Author Bruno Ribeiro - 2017138
 * @Collaborator Amilcar Aponte 
 * 
 * An ENUM to guarantee that the value of the continent
 * matches the options on the database
*/

public enum Continent {
	
	
	ASIA("Asia"),
	EUROPE("Europe"),
	NORTH_AMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica"),
	SOUTH_AMERICA("South America");
	
	private final String continent;
	
	private Continent(String continent) {
		 this.continent =  continent;
	}
	
	@Override
	public String toString() {
		return this.continent;
	}
	
	
	
	
}
