/**
 * 
 * <h2>Project Class</h2>
 * <p>This is the project class that defines what a project object contains and what it's capable of</p>
 *
 * @author GJ Steyn
 * @version 1.00, 1 May 2020
 */

import java.util.Calendar;

public class Project {
	// ATTRIBUTES
	private int projectNumber;
	private String projectName;
	private String buildingType;
	private String address;
	private String erfNumber;
	private double totalCost;
	private double totalPaid;
	private String deadline;
	private String completionDate;
	public boolean isFinalised;
	private Person client;
	private Person contractor;
	private Person architect;
	
	// METHODS	
	/**
  *
  * Constructor for a project without a specific project name.
  * <br>
  * This method is called when the user does not give a project name and one is automatically generated.
  *
  * @param number unique number for a project
  * @param type what type of building the project is for
  * @param address address of the project
  * @param erf erf number
  * @param deadline due date for the project
  * @param client which Person object from client list is associated with the project
  * @param contractor which Person object from contractor list is associated with the project
  * @param architect which Person object from architect list is associated with the project
  * @see Person
  */
	public Project(int number, String type, String address, String erf, String deadline, Person client, Person contractor, Person architect) {
		this.projectNumber = number;
		this.buildingType = type;
		this.address = address;
		this.erfNumber = erf;
		this.deadline = deadline;
		this.client = client;
		this.contractor = contractor;
		this.architect = architect;
		this.totalPaid = 0;
		this.projectName = generateProjectName();
		this.totalCost = 1000;
		this.completionDate = deadline;
		this.isFinalised = false;
	}
	
	/**
  *
  * Constructor for a project with a predetermined project name.
  * <br>
  * This method is called when the user used a predetermined name for a project.
  *
  * @param number unique number for a project
  * @param name name that the user uses for the project
  * @param type what type of building the project is for
  * @param address address of the project
  * @param erf erf number
  * @param deadline due date for the project
  * @param client which Person object from client list is associated with the project
  * @param contractor which Person object from contractor list is associated with the project
  * @param architect which Person object from architect list is associated with the project
  * @see Person
  */
	public Project(int number, String name, String type, String address, String erf, String deadline, Person client, Person contractor, Person architect) {
		this.projectNumber = number;
		this.projectName = name;
		this.buildingType = type;
		this.address = address;
		this.erfNumber = erf;
		this.deadline = deadline;
		this.client = client;
		this.contractor = contractor;
		this.architect = architect;
		this.totalPaid = 0;
		this.totalCost = 1000;
		this.completionDate = deadline;
		this.isFinalised = false;
	}
	
	/**
  *
  * Constructor for a project that is read from the file.
  * <br>
  * This method is called when there are projects read from the projects file.
  *
  * @param number unique number for a project
  * @param name name that is associated with the project
  * @param type what type of building the project is for
  * @param address address of the project
  * @param erf erf number
  * @param totalCost what the project will cost to complete
  * @param totalPaid amount that the client has paid
  * @param deadline due date for the project
  * @param completionDate date that a project is completed, deadline used as default
  * @param isFinalised a check to mark completed projects
  * @param client which Person object from client list is associated with the project
  * @param contractor which Person object from contractor list is associated with the project
  * @param architect which Person object from architect list is associated with the project
  * @see Person
  */
	public Project(int number, String name, String type, String address, String erf, double totalCost, double totalPaid, String deadline, String completionDate, boolean isFinalised, Person client, Person contractor, Person architect) {
	  this.projectNumber = number;
	  this.projectName = name;
	  this.buildingType = type;
	  this.address = address;
	  this.erfNumber = erf;
	  this.totalCost = totalCost;
	  this.totalPaid = totalPaid;
	  this.deadline = deadline;
	  this.completionDate = completionDate;
	  this.isFinalised = isFinalised;
	  this.client = client;
	  this.contractor = contractor;
	  this.architect = architect;
	}
	
	/**
  *
  * Overloads the toString function for display purposes.
  * <br>
  * This method is called when the user wants to display a project's full details.
  *
  * @return detailed project description
  * @see Project
  */
	public String toString() {
		String output = "Project Number: " + projectNumber;
		output += "\nProject Name: " + projectName;
		output += "\n" + client.toString();
		output += "\nBuilding Type: " + buildingType.substring(0, 1).toUpperCase() + buildingType.substring(1);
		output += "\nAddress: " + address;
		output += "\n" + contractor.toString();
		output += "\n" + architect.toString();
		output += "\nERF Number: " + erfNumber;
		output += "\nDeadline: " + deadline;
		output += "\nTotal Paid: R " + totalPaid;
		
		return output;
	}
	
	/**
  *
  * Outputs the data for the project as it should be written to a file.
  * <br>
  * This method is called when the program terminates.
  *
  * @return comma-separated format
  * @see Main
  */
	public String toFileFormat() {
	  String output = projectNumber + ",";
	  output += projectName + ",";
	  output += buildingType + ",";
	  output += address + ",";
	  output += erfNumber + ",";
	  output += totalCost + ",";
	  output += totalPaid + ",";
	  output += deadline + ",";
	  output += completionDate + ",";
	  output += isFinalised + ",";
	  output += client.getEmail() + ",";
	  output += contractor.getEmail() + ",";
	  output += architect.getEmail();
	  
	  return output;
	}
	
	/**
  *
  * Outputs minimal data about a project.
  * <br>
  * This method is called when non-detailed data about a project needs to be displayed.
  *
  * @return minimal project information
  * @see Project
  */
	public String toConsoleView() {
	  String output = "\t" + projectNumber + " - " + projectName;
	  return output;
	}
	
	/**
  *
  * A method to generate a project name.
  * <br>
  * This method is called when the user does not enter a predetermined name.
  *
  * @return auto-generated project name
  * @see Project
  */
	private String generateProjectName() {
		// Code to return surname + type of building as string
	  String capitalise = this.buildingType.substring(0, 1).toUpperCase() + this.buildingType.substring(1);
		String result = capitalise + " " + this.client.getSurname();
		return result;
	}
	
	/**
  *
  * @param deadline set deadline date
  */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	/**
  *
  * @return current deadline
  */
	public String getDeadline() {
    return this.deadline;
  }
	
	/**
  *
  * A method to set a project as complete
  * <br>
  * This method is called when the user wants to mark a project as complete and generate an invoice if the project is not paid for
  * 
  * @return detailed invoice or notification for payment
  */
	public String finalise() {
		String output;
		
		// Check if the project has been finalized before
		if(isFinalised) {
			output = "Project already finalised";
		}
		else {
		  if(this.totalPaid >= this.totalCost) {
		    output = "No invoice generated. Project paid in full.";
		  } else {
		    output = "INVOICE\n";
	      output += "Client:\n";
	      output += this.client.getName() + "\n";
	      output += this.client.getNumber() + "\n";
	      output += this.client.getEmail() + "\n";
	        
	      output += "Total Cost:\tR " + this.totalCost + "\n";
	      output += "Total Paid:\tR " + this.totalPaid + "\n";
	      output += "Remaining:\tR " + (this.totalCost - this.totalPaid) + "\n";
	        
	      Calendar currentDate = Calendar.getInstance();
	        
	      this.setCompletionDate(Main.DATEFORMAT.format(currentDate.getTime()));
	        
	      output += "Completion Date: " + getCompletionDate();
		  }
		}
		
		isFinalised = true;
		return output;
	}
	
	/**
  *
  * @param paid payment added to project
  */
	public void setTotalPaid(double paid) {
		this.totalPaid += paid;
	}
	
	/**
  *
  * @return details about payments
  */
	public String getMonetaryDetails() {
	  String output = "Cost of project: R " + totalCost;
	  output += "\nPaid for project: R " + totalPaid;
	  
	  double remaining = totalCost - totalPaid;
	  
	  output += "\nRemaining: R " + remaining;
	  
	  return output;
	}
	
	/**
  *
  * @return project number
  */
	public int getProjectNumber() {
		return this.projectNumber;
	}
	
	/**
  *
  * @param date date when the project is finalized
  */
	public void setCompletionDate(String date) {
		this.completionDate = date;
	}
	
	/**
  *
  * @return date when the project was completed
  */
	public String getCompletionDate() {
		return this.completionDate;
	}
}
