package objects;


/*
 * @Author Bruno Ribeiro
 * @Collaborator Amilcar Aponte 
 * 
 * An ENUM to guarantee that the value of the continent
 * matches the options on the database
*/

public enum Continent {
	
	
	ASIA("Asis"),
	EUROPE("Europe"),
	NORTHAMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica"),
	SOUTHAMERICA("South America");
	
	
	private final String continent;
	
	private Continent(String continent) {
		this.continent = continent;
	}
	
	
	@Override
	public String toString() {
		return this.continent;
	}
	
	
	
}
