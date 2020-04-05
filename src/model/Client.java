package model;

import controller.Controller;

/*
 * @Author Bruno Ribeiro - 2017138
 * @Collaborator Amilcar Aponte 
 * 
 * https://github.com/brunobpr/Design_Patterns
 * 
*/

public class Client {
	// This is the Main Class, it cannot have any direct access to the database
	// It will be only used to create a new instance of the Controller class.

	public static void main(String[] args) {
		// Starting a new Controller.
		// The controller is the only object created without following a design pattern
		Controller controller = new Controller();
	}

}
