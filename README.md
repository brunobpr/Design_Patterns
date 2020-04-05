# Design Patterns 
 Institution Name    : CCT College Dublin
 Module  Title       : Object Orientation with Design Patterns
 Project Title       : Pattern combination
 Assignment Compiler : Amilcar Aponte 


## Assignment Introduction
You have been provided with access to a real database server that contains only one table (country), and have been tasked to develop a Java program in charge of:
* Retrieve all records stored in the database table
* Retrieve records by country name
* Retrieve records by country code
* Add new records into the database

## Patterns Used
### Singleton
*Database connection, for this CA there was only one class acessing the databae, but in a larger project, avoiding creating new instances of the database everytime the connection is needed could result in a waste of resources. That's why using Singleton pattern is the best approach for this scenario. 

### Builder
*The biggest advantage of using this pattern to create new country objects is the acceptance of passing paramenters in differente ways and order. 

### Data Access Object
*When handling retrieving and addind data to an external data storage, DAO avoids that the client side doesn't have direct access to the data. In this case, DAO is in chage of creating operations that will be done on the database.


 
