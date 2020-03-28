package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Validator {

private static Validator instance = null;
	
	// HashMap to save one event per date
	private boolean isValid;
	
	
	
	// Constructor
	private Validator () {
			isValid = false;		
	}
	
	public boolean countryCode(String code) {
		if(code.length() == 3) 
			if(code.matches("[A-Za-z0-9]+"))
					return false;	
		return true;
	}
	
	public boolean countryName(String name) {
		if(name != null) 
			if(name.matches("[A-Za-z ]+"))
					return false;	
		return true;
	}
	
	public static Validator getInstance() {
		if(instance == null) {
			instance = new Validator();
		}
		return instance;	
	}
	
	
}
