package model;

/*
 * @Author Bruno Ribeiro
 * @Collaborator Amilcar Aponte
 * https://github.com/brunobpr/Design_Patterns
*/

public class Validator {

	// This is a Validation class, it might be used for more than on class
	// But there is no need to create new instances for each one of them
	private static Validator instance = null;

	// Constructor
	private Validator() {
	}

	public boolean countryCode(String code) {
		// The country code must be three characteres long and only letters or numbers
		if (code.length() == 3)
			if (code.matches("[A-Za-z0-9]+"))
				return false;
		return true;
	}

	public boolean countryName(String name) {
		// The country name cannot be null and must contain only letters and spaces
		if (name != null)
			if (name.matches("[A-Za-z ]+"))
				return false;
		return true;
	}

	//This is the Lazy Singleton, the validation will only occurr when a new country is being checked
	//So there is no need to crete a new instance in compiling time.
	public static Validator getInstance() {
		if (instance == null) {
			instance = new Validator();
		}
		return instance;
	}

}
