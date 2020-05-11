/**
 * 
 * <h2>Person Class</h2>
 * <p>This is the person class that defines what a person object contains and what it's capable of</p>
 *
 * @author GJ Steyn
 * @version 1.00, 1 May 2020
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	// ATTRIBUTES
	private String name;
	private String surname;
	private String telNumber;
	private String email;
	private String address;
	private String affiliation;
	
	// METHODS
	/**
  *
  * Constructor for a person.
  * <br>
  * This method is called when a person object is created.
  *
  * @param name name of the person
  * @param surname last name of the person
  * @param telNumber contact number for a person
  * @param email email address
  * @param address home address
  * @param affiliation what association a person has to a project
  * @see Person
  */
	public Person(String name, String surname, String telNumber, String email, String address, String affiliation) {
		this.name = name;
		this.surname = surname;
		this.telNumber = telNumber;
		this.email = email;
		this.address = address;
		this.affiliation = affiliation;
	}
	
	/**
  *
  * Overloads the toString function for display purposes.
  * <br>
  * This method is called when the user wants to display a person's full details.
  *
  * @return detailed person description
  * @see Person
  */
	public String toString() {
		String output = affiliation.substring(0, 1).toUpperCase() + affiliation.substring(1) + " Details:";
		output += "\n\tName: " + name + " " + surname;
		output += "\n\tTel: " + telNumber;
		output += "\n\tEmail: " + email;
		output += "\n\tAddress: " + address;
		
		return output;
	}
	
	/**
  *
  * @return name of person
  */
	public String getName() {
		return this.name;
	}
	
	/**
  *
  * @param name name to set
  */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
  *
  * @return last name of person
  */
	public String getSurname() {
		return this.surname;
	}
	
	/**
  *
  * @param surname last name to set
  */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
  *
  * @return contact number
  */
	public String getNumber() {
		return telNumber;
	}
	
	/**
  *
  * @param number number to set
  */
	public void setNumber(String number) {
		this.telNumber = number;
	}
	
	/**
  *
  * @return email address
  */
	public String getEmail() {
		return this.email;
	}
	
	/**
  *
  * @param email email address to set
  */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
  *
  * @return home/work address
  */
	public String getAddress() {
		return this.address;
	}
	
	/**
  *
  * @param address home/work address to set
  */
	public void setAddress(String address) {
	  this.address = address;
	}
	
	/**
  *
  * @return affiliation the person has to a project
  */
	public String getAffiliation() {
		return affiliation;
	}
	
	/**
  *
  * Outputs the data for the person as it should be written to a file.
  * <br>
  * This method is called when the program terminates.
  *
  * @return comma-separated format
  * @see Main
  */
  public String toFileFormat() {
    String output = name + ",";
    output += surname + ",";
    output += telNumber + ",";
    output += email + ",";
    output += address + ",";
    output += affiliation;
    
    return output;
  }

  /**
  *
  * Runs a validator on an email address.
  * <br>
  * This method is called when the user enters a new email for a person.
  *
  * @param email email address to be validated
  * @return true or false for email address
  * @see Main
  */
  public static boolean validEmail(String email) {
    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    
    Pattern pattern = Pattern.compile(regex);
    
    Matcher matcher = pattern.matcher(email);
    
    return matcher.matches();
  }

  /**
  *
  * Runs a validator on a contact number.
  * <br>
  * This method is called when the user enters a new contact number for a person.
  *
  * @param tel contact number to be validated
  * @return true or false for tel address
  * @see Main
  */
  public static boolean validTel(String tel) {
    String regex = "\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}";
    
    Pattern pattern = Pattern.compile(regex);
    
    Matcher matcher = pattern.matcher(tel);
    
    return matcher.matches();
  }

}