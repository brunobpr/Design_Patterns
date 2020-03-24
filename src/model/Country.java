package model;

public class Country {
	private String code;
	private String name;
	private Continent continent;
	private float surfaceArea;
	private String headOfState;
	
	private Country(CountryBuilder countryBuilder) {
		this.code = countryBuilder.code;
		this.name = countryBuilder.name;
		this.continent = countryBuilder.continent;
		this.surfaceArea = countryBuilder.surfaceArea;
		this.headOfState = countryBuilder.headOfState;
	}
	
	
	@Override
	public String toString() {
		return "Code: " + getCode() + " | Name: " + getName() 
				+ "\nContinent     : " + getContinent()
				+ "\nSurface Area  : " + getSurfaceArea()
				+ "\nHead of State : " + getHeadOfState()
				+ "\n________________________________________"
		;
	}
	
	
	
	
	
	public String getCode() {
		return code;
	}


	public String getName() {
		return name;
	}


	public Continent getContinent() {
		return continent;
	}


	public float getSurfaceArea() {
		return surfaceArea;
	}


	public String getHeadOfState() {
		return headOfState;
	}

	public static class CountryBuilder {
		private String code;
		private String name;
		private Continent continent;
		private float surfaceArea;
		private String headOfState;
		
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


