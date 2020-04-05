package model;

/*
 * @Author Bruno Ribeiro - 2017138
 * @Collaborator Amilcar Aponte
 * https://github.com/brunobpr/Design_Patterns
 * 
 * This class follows the Builder Pattern to create a new country
 * The first thing done before creating a Country Class was to analyse the country table on the database
 * By doing so I could check which parameters were essencial or not.
 * From my point of view, code, name and continent were essencial. The reason for that is outlined below
*/
public class Country {

	// Creating variables with the same datatypes found on the database.
	private String code;
	private String name;
	private Continent continent;
	private float surfaceArea;
	private String headOfState;

	// Private constructor, the client is only allowed to create
	// a country through the country builder, this constructor receives the
	// countrybuilder from the build() method.
	// It use the values defined on the inner class (builder) to create a new
	// Country
	private Country(CountryBuilder countryBuilder) {
		this.code = countryBuilder.code;
		this.name = countryBuilder.name;
		this.continent = countryBuilder.continent;
		this.surfaceArea = countryBuilder.surfaceArea;
		this.headOfState = countryBuilder.headOfState;
	}

	// This method will generate a well formatted print statement with information
	// regarding the country
	@Override
	public String toString() {
		return "Code: " + getCode() + "     | Name: " + getName() + "\nContinent     : " + getContinent()
				+ "\nSurface Area  : " + getSurfaceArea() + "\nHead of State : " + getHeadOfState()
				+ "\n________________________________________\n";
	}

	// START of getters---------------------------------
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
	// END of getters---------------------------------

	// The CountryBuilder is the class responsible to instanciate a new country
	// The Controller will have access to this inner class
	public static class CountryBuilder {
		// Always the Builder class has to have the same attributes
		private String code;
		private String name;
		private Continent continent;
		private float surfaceArea;
		private String headOfState;

		/**
		 * @param code
		 *            this is the most important attribute, it's a primary key,
		 *            therefore it's mandatory
		 * @param name
		 *            according to the database, it can't be null and there is no
		 *            default value, therefore it's also mandatory
		 * @param continent
		 *            it also can't be null, but it has a default value of 'Asia' on the
		 *            database
		 */
		public CountryBuilder(String code, String name, Continent continent) {
			this.code = code;
			this.name = name;
			this.continent = continent;
		}

		public CountryBuilder() {
		}

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

		// Surface Area was not considered essential because it has a default value of
		// 0.00 on the database
		// So if a user does not know the surface area, they can leave it as null (will
		// be create with 0.00)
		public CountryBuilder setSurfaceArea(float surfaceArea) {
			this.surfaceArea = surfaceArea;
			return this;
		}

		// The Head of State is the only attribute than can be null according to the
		// database
		// So it is not essencial nor mandatory
		public CountryBuilder setHeadOfState(String headOfState) {
			this.headOfState = headOfState;
			return this;
		}

		/**
		 * the build method is the most important method on the builder pattern it is
		 * responsible for.... build a new instance of the country object
		 * 
		 * @return a country object with all the information set
		 */
		public Country build() {
			return new Country(this);
		}

	}

}
