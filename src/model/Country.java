package model;

/*
 * @Author Bruno Ribeiro - 2017138
 * @Collaborator Amilcar Aponte
 * 
 * This class uses the Builder pattern to create a new country
 * 
*/
public class Country {
	private String code;
	private String name;
	private Continent continent;
	private float surfaceArea;
	private String headOfState;
	
	//Private constructor, the client is only allowed to create 
	//a country through the country builder
	private Country(CountryBuilder countryBuilder) {
		this.code = countryBuilder.code;
		this.name = countryBuilder.name;
		this.continent = countryBuilder.continent;
		this.surfaceArea = countryBuilder.surfaceArea;
		this.headOfState = countryBuilder.headOfState;
	}
	
	//This method will generate a well formated print statement with information regarding the country
	@Override
	public String toString() {
		return "Code: " + getCode() + "     | Name: " + getName() 
				+ "\nContinent     : " + getContinent()
				+ "\nSurface Area  : " + getSurfaceArea()
				+ "\nHead of State : " + getHeadOfState()
				+ "\n________________________________________\n"
		;
	}
	

	//START of getters---------------------------------
	public String getCode() { return code; }
	public String getName() { return name; }
	public Continent getContinent() {return continent;}
	public float getSurfaceArea() {return surfaceArea;}
	public String getHeadOfState() {return headOfState; }
	//END  of getters---------------------------------
	
	
	
	//The 
	public static class CountryBuilder {
		private String code;
		private String name;
		private Continent continent;
		private float surfaceArea;
		private String headOfState;
		
		public CountryBuilder(String code, String name, Continent continent) {
			this.code = code;
			this.name = name;
			this.continent = continent;
		}
		public CountryBuilder() {}
		
		public CountryBuilder setCode(String code) {
			this.code = code;
			return this;
		}
		
		public CountryBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public CountryBuilder setContinent(Continent continent) {
			this.continent = continent; 
			return this;
		}
		
		public CountryBuilder setSurfaceArea(float surfaceArea) {
			this.surfaceArea = surfaceArea;
			return this;
		}
		
		public CountryBuilder setHeadOfState(String headOfState) {
			this.headOfState = headOfState;
			return this;
		}
		
		public Country build() {
			return new Country(this);
		}
		
	}
	
	
	
	
	
}


