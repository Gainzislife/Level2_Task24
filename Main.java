/**
 * 
 * <h1>Project Management System</h1>
 * <h2>Main class</h2>
 * <p>This is the main class for the project</p>
 *
 * @author GJ Steyn
 * @version 1.00, 1 May 2020
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
  // ATTRIBUTES
  // Constants
  private static final Scanner SCAN = new Scanner(System.in);
  public static final DateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
  
  // Variables
  private static ArrayList<Project> projects = new ArrayList<>();
  private static ArrayList<Person> clients = new ArrayList<>();
  private static ArrayList<Person> contractors = new ArrayList<>();
  private static ArrayList<Person> architects = new ArrayList<>();
  
  // METHODS
  /**
  *
  * Reads persons.txt.
  * <br>
  * This method reads all the data in the text file and saves it in Person lists depending on their affiliation
  * <br>
  * This method is called when the program starts
  *
  * @see Person
  */
  private static void readPersons() {
    try {
      final File PERSONFILE = new File("C:\\hyperionDev\\L2_T24\\persons.txt");
      Scanner scanPersons = new Scanner(PERSONFILE);
      
      // Loop through persons file by line
      while(scanPersons.hasNext()) {
        // Save line data in personData
        String personData = scanPersons.nextLine();
        
        if(personData.trim().isEmpty()) {
          break;
        }
        
        // Split the data into an array to associate indexes to variables
        String[] arrPersonData = personData.split(",");
        
        String name = arrPersonData[0];
        String surname = arrPersonData[1];
        String tel = arrPersonData[2];
        String email = arrPersonData[3];
        String address = arrPersonData[4];
        String affiliation = arrPersonData[5];
        
        // Add person to respective list depending on affiliation
        if(affiliation.equals("client")) {
          clients.add(new Person(name, surname, tel, email, address, affiliation));
        } else if(affiliation.equals("contractor")) {
          contractors.add(new Person(name, surname, tel, email, address, affiliation));
        } else if(affiliation.equals("architect")) {
          architects.add(new Person(name, surname, tel, email, address, affiliation));
        } else {
          System.out.println("Warning! Couldn't associate person to affiliation.");
        }
      }
      
      scanPersons.close();
      
    } catch (Exception e) {
      System.out.println("Problem in 'readPersons': " + e);
    }
  }
  
  /**
  *
  * Search Person list.
  * <br>
  * This method searches through a Person list and returns the object matched with the email parameter.
  * <br>
  * This method is called when the program is reading projects
  *
  * @param email unique person identification
  * @param list specific list to search through
  * @return Person object matching the email parameter
  * @see Person
  */
  private static Person lookUpPerson(String email, ArrayList<Person> list) {
    Iterator<Person> iterator = list.iterator();
    
    while(iterator.hasNext()) {
      Person p = iterator.next();
      
      if(p.getEmail().equals(email)) {
        return p;
      }
    }
    
    System.out.println("Problem looking for person");
    return null;
  }
    
  /**
  *
  * Reads projects.txt.
  * <br>
  * This method reads all the data in the text file and saves it in a Project list
  * <br>
  * This method is called when the program starts
  *
  * @see Projects
  */
  private static void readProjects() {
    
    try {
      final File PROJECTFILE = new File("C:\\hyperionDev\\L2_T24\\projects.txt");
      Scanner scanProjects = new Scanner(PROJECTFILE);
      
      while(scanProjects.hasNext()) {
        // Save each line of project data in a string variable
        String projectData = scanProjects.nextLine();
        
        if(projectData.trim().isEmpty()) {
          System.out.println("No projects in file.");
          break;
        }
        
        // Split data into an array
        String[] arrProjectData = projectData.split(",");
        
        // Save each array index to associated variable
        int number = Integer.parseInt(arrProjectData[0]);
        String projectName = arrProjectData[1];
        String building = arrProjectData[2];
        String address = arrProjectData[3];
        String erf = arrProjectData[4];
        double totalCost = Double.parseDouble(arrProjectData[5]);
        double totalPaid = Double.parseDouble(arrProjectData[6]);
        String deadline = arrProjectData[7];
        String completionDate = arrProjectData[8];
        boolean isFinalised = Boolean.parseBoolean(arrProjectData[9]);
        
        // ID is the email. It connects the person to the project
        String clientId = arrProjectData[10];
        String contractorId = arrProjectData[11];
        String architectId = arrProjectData[12];
        
        // Get the person associated with each ID
        Person client = lookUpPerson(clientId, clients);
        Person contractor = lookUpPerson(contractorId, contractors);
        Person architect = lookUpPerson(architectId, architects);
        
        projects.add(new Project(number, projectName, building, address, erf, totalCost, totalPaid, deadline, completionDate, isFinalised, client, contractor, architect));
      }
      
      scanProjects.close();
      
    } catch (Exception e) {
      System.out.println("Exception Thrown: " + e);
    }
  }
  
  /**
  *
  * Writes to projects.txt and persons.txt.
  * <br>
  * This method writes all the information about the objects found in the lists to their respective file
  * <br>
  * This method is only called when the program terminates
  *
  * @see Person
  * @see Project
  */
  private static void writeToFiles() {
    try {
      Formatter fPersons = new Formatter("C:\\hyperionDev\\L2_T24\\persons.txt");
      Formatter fProjects = new Formatter("C:\\hyperionDev\\L2_T24\\projects.txt");
      
      // Write all data from projects to file
      int projectSize = projects.size();
      for(int i = 0; i < projectSize; i++) {
        fProjects.format("%s\n", projects.get(i).toFileFormat());
      }
      
      // Write data from persons to file
      // Clients to file
      int clientSize = clients.size();
      for(int i = 0; i < clientSize; i++) {
        fPersons.format("%s\n", clients.get(i).toFileFormat());
      }
      
      // Contractors to file
      int contractorSize = contractors.size();
      for(int i = 0; i < contractorSize; i++) {
        fPersons.format("%s\n", contractors.get(i).toFileFormat());
      }
      
      // Architects to file
      int architectSize = architects.size();
      for(int i = 0; i < architectSize; i++) {
        fPersons.format("%s\n", architects.get(i).toFileFormat());
      }
      
      fPersons.close();
      fProjects.close();
      
    } catch (Exception e) {
      System.out.println("Cannot write to file: " + e);
    }
  }
  
  /**
  *
  * Get user input for person.
  * <br>
  * This method asks the user for input for a person and adds it to a specific person list.
  * <br>
  * This method is only called when the user creates a new project
  *
  * @param affiliation what affiliation the person has to the specific project
  * @return Person object that has just been created
  * @see Person
  */
  private static Person addPerson(String affiliation) {
    // Make first letter of affiliation upper case
    String upperCaseAffiliation = affiliation.substring(0,1).toUpperCase() + affiliation.substring(1);
    
    // Ask for name and surname
    System.out.println(upperCaseAffiliation + " name?");
    String name = SCAN.nextLine();
    System.out.println(upperCaseAffiliation + " surname?");
    String surname = SCAN.nextLine();
    
    // Ask for telephone number and validate input
    String number;
    do {
      System.out.println(upperCaseAffiliation + " number?");
      number = SCAN.nextLine();
      
      if(!Person.validTel(number)) {
        System.out.println("Invalid number. Ex: 082 555 2034");
      }
    } while(!Person.validTel(number));
    
    // Ask for email address and validate input
    String email;
    do {
      System.out.println(upperCaseAffiliation + " email?");
      email = SCAN.nextLine();
      
      if(!Person.validEmail(email)) {
        System.out.println("Invalid email. Ex: name@mail.com");
      }
    } while(!Person.validEmail(email));
    
    // Ask for address
    System.out.println(upperCaseAffiliation + " address?");
    String address = SCAN.nextLine();
    
    Person p = new Person(name, surname, number, email, address, affiliation);
    
    if(affiliation.equals("client")) {
      clients.add(p);
    } else if(affiliation.equals("contractor")) {
      contractors.add(p);
    } else if(affiliation.equals("architect")) {
      architects.add(p);
    } else {
      System.out.println("Warning: Unknown affiliation.");
      return null;
    }
    
    return p;
  }
  
  /**
  *
  * Display error message and list projects and their numbers.
  * <br>
  * This method is only called when the user enters an invalid input
  *
  */
  private static void invalidInput() {
    System.out.println("Project number not found!");
    System.out.println("List of project numbers:");
    int size = projects.size();
    for(int j = 0; j < size; j++) {
      if(projects.get(j) != null) {
        System.out.println("\t" + projects.get(j).getProjectNumber());
      }
    }
    System.out.println("");
  }
  
  /**
  *
  * Creates a new project
  * <br>
  * This method is called when menu option 1 is selected
  *
  */
  private static void createProject() {
    // Create new project number
    // Check if the project number already exists
    boolean checkNumber = false;
    int number;
    do {
      System.out.println("Project number?");
      number = SCAN.nextInt();
      SCAN.nextLine(); // Bypass 'Enter' pressed from user
      checkNumber = false;
      
      for(int i = 0; i < projects.size(); i++) {
        if(projects.get(i).getProjectNumber() == number) {
          System.out.println("Project number already exists.");
          checkNumber = true;
          break;
        }
      }
    } while(checkNumber);
    
    // Create client object
    Person client = addPerson("client");
    
    // Get some project details
    System.out.println("Project address?");
    String address = SCAN.nextLine();
    System.out.println("Building type?");
    String building = SCAN.nextLine();
    System.out.println("ERF Number?");
    String erf = SCAN.nextLine();
    
    // Check correct date input
    String deadline = null;
    do {
      System.out.println("Deadline? (dd/mm/yyyy)");
      deadline = SCAN.nextLine();
      
      try {
        DATEFORMAT.parse(deadline);
      } catch (ParseException e) {
        System.out.println("Invalid date.");
        deadline = null;
      }
    } while(deadline == null);
    
    // Create contractor and architect objects
    Person contractor = addPerson("contractor");
    Person architect = addPerson("architect");
    
    // Ask if the project has a predetermined name
    while(true) {
      System.out.println("Does the project have a name? (Y/N)");
      String question = SCAN.nextLine();
      
      // Create object with projectName
      if(question.equals("Y") || question.equals("y")) {
        System.out.println("Name of the project?");
        String projectName = SCAN.nextLine();
        projects.add(new Project(number, projectName, building, address, erf, deadline, client, contractor, architect));
        break;
      } else if(question.equals("N") || question.equals("n")) {
        // Generate a projectName
        projects.add(new Project(number, building, address, erf, deadline, client, contractor, architect));
        break;
      } else {
        System.out.println("Wrong input! Enter 'Y' or 'N'.\n");
      }
    }
  }
  
  /**
  *
  * Change the deadline of a project
  * <br>
  * This method is called when menu option 2 is selected
  *
  */
  private static void changeProjectDeadline() {
    // Ask user for which project they want to access
    System.out.println("CHANGE PROJECT DEADLINE");
    System.out.println("Project number?");
    int selection = SCAN.nextInt();
    SCAN.nextLine(); // To bypass 'Enter' from user
    
    // Get the length of projects array
    int length = projects.size();
    
    // Loop through 
    for(int i = 0; i < length; i++) {
      if(projects.get(i).getProjectNumber() == selection) {
        System.out.println("Current deadline: " + projects.get(i).getDeadline());
          
        // Check correct date input
        String newDeadline = null;
        do {
          System.out.println("Set new deadline: (dd/mm/yyyy)");
          newDeadline = SCAN.nextLine();
            
          try {
            DATEFORMAT.parse(newDeadline);
            projects.get(i).setDeadline(newDeadline);
          } catch (ParseException e) {
            System.out.println("Invalid date.");
            newDeadline = null;
          }
        } while(newDeadline == null);
          
        System.out.println("New deadline set!\n");
        break;
      }
    }
    
    invalidInput();
  }
  
  /**
  *
  * Add payment for a project
  * <br>
  * This method is called when menu option 3 is selected
  *
  */
  private static void changeAmountPaid() {
    // Ask user for which project they want to access
    System.out.println("Select project number:");
    int selection = SCAN.nextInt();
    SCAN.nextLine(); // Bypass the 'Enter' from user
    
    // Get the length of projects array
    int length = projects.size();
    
    // Search project number of each object in projects array and compare to selection
    for(int i = 0; i < length; i++) {
      if(projects.get(i).getProjectNumber() == selection) {
        System.out.println(projects.get(i).getMonetaryDetails());
        
        while(true) {
          System.out.println("Payment amount?");
          try {
            double amount = SCAN.nextDouble();
            SCAN.nextLine(); // Bypass the 'Enter' from user
            projects.get(i).setTotalPaid(amount);
            
            System.out.println(projects.get(i).getMonetaryDetails());
            break;
          } catch (Exception e) {
            System.out.println("Invalid input.");
          }
        }
        
        break;
      }
    }
    
    invalidInput();
  }
  
  /**
  *
  * Updates any selected details about a Person object in the contractor list
  * <br>
  * This method is called when menu option 4 is selected
  *
  */
  // Menu option 4
  private static void updateContractorDetails() {
    // Ask user for which contractor they want to access
    System.out.println("Enter contractor's name:");
    String name = SCAN.nextLine();
    
    // Get the length of contractors array
    int length = contractors.size();
    
    // Search contractor name of each object in contractors array and compare to name
    for(int i = 0; i < length; i++) {
      if(contractors.get(i).getName().equals(name)) {
          
        // Give menu to select what you want to update from the contractor
        System.out.println("UPDATE CONTRACTOR DETAILS");
        System.out.println("1 - update name");
        System.out.println("2 - update surname");
        System.out.println("3 - update tel");
        System.out.println("4 - update email");
        System.out.println("5 - update address");
          
        String update = SCAN.nextLine();
          
        switch(update) {
          case "1":
            System.out.println("Current name: " + contractors.get(i).getName());
            System.out.println("Enter new name:");
            String newName = SCAN.nextLine();
            contractors.get(i).setName(newName);
            break;
            
          case "2":
            System.out.println("Current surname: " + contractors.get(i).getSurname());
            System.out.println("Enter new surname:");
            String newSurname = SCAN.nextLine();
            contractors.get(i).setSurname(newSurname);
            break;
            
          case "3":
            System.out.println("Current number: " + contractors.get(i).getNumber());
            
            // Get client telephone number and validate input
            String newTel;
            do {
              System.out.println("Enter new number:");
              newTel = SCAN.nextLine();
                
              if(!Person.validTel(newTel)) {
                System.out.println("Invalid number. Ex: 082 555 2034");
              }
            } while(!Person.validTel(newTel));
              
            contractors.get(i).setNumber(newTel);
            break;
            
          case "4":
            System.out.println("Current email: " + contractors.get(i).getEmail());
            // Get client email address and validate input
            
            String newEmail;
            do {
              System.out.println("Enter new email:");
              newEmail = SCAN.nextLine();
              
              if(!Person.validEmail(newEmail)) {
                System.out.println("Invalid email. Ex: name@mail.com");
              }
            } while(!Person.validEmail(newEmail));
            
            contractors.get(i).setEmail(newEmail);
            break;
            
          case "5":
            System.out.println("Current address: " + contractors.get(i).getAddress());
            System.out.println("Enter new address:");
            String newAddress = SCAN.nextLine();
            contractors.get(i).setAddress(newAddress);
            break;
            
          default:
            System.out.println("Unknown input!");
        }
          
        int test = Integer.parseInt(update.trim());
        if(test > 0 && test < 5) {
          System.out.println(contractors.get(i).toString() + "\n");
          break;
        } else {
          System.out.println("Returning to Main Menu...\n");
          break;
        }
      }       
    }
    
    // Error for wrong user input
    System.out.println("Contractor not found!");
    System.out.println("List of contractor names:");
    for(int j = 0; j < length; j++) {
      if(contractors.get(j) != null) {
        System.out.println("\t" + contractors.get(j).getName());
      }
    }
    System.out.println("");
  }
  
  /**
  *
  * Mark a project as complete
  * <br>
  * This method is called when menu option 5 is selected
  *
  */
  // Menu option 5
  private static void finaliseProject() {
    final String FILENAME = "Completed project.txt";
    
    System.out.println("Select project number:");
    int selection = SCAN.nextInt();
    SCAN.nextLine(); // Bypass the 'Enter' from user
    
    // Get the length of projects list
    int length = projects.size();
    
    // Search project number of each object in projects list and compare to selection
    for(int i = 0; i < length; i++) {
      if(projects.get(i).getProjectNumber() == selection) {
          
        System.out.println(projects.get(i).finalise());
          
        // Error handling for the file
        try {
          // Write to file encoding
          FileWriter fileWriter = new FileWriter(FILENAME);
            
          // Wrap FileWriter in BufferedWriter
          BufferedWriter bw = new BufferedWriter(fileWriter);
          
          // Write all the details about the project to the file
          bw.write(projects.get(i).toString());
            
          // Close the file
          bw.close();
        } catch(IOException e) {
          System.out.println("Error writing to file '" + FILENAME + "'!");
        }
          
        break;
      } else {
        System.out.println("PROJECT NOT FOUND!");
        System.out.println("List of projects:");
        for(int j = 0; j < length; j++) {
          if(projects.get(j) != null) {
            System.out.println("\t" + projects.get(j).toConsoleView());
          }
        }
        System.out.println("");
      }
    }
  }
  
  /**
  *
  * List all incomplete projects
  * <br>
  * This method is called when menu option 6 is selected
  *
  */
  // Menu option 6
  private static void incompleteProjects() {
    // Print incomplete projects to console
    System.out.println("Incomplete Projects:\n");
    System.out.println("Project Number, Project Name");
    
    int size = projects.size();
    
    for(int i = 0; i < size; i++) {
      if(!projects.get(i).isFinalised) {
        System.out.println(projects.get(i).toConsoleView());
      }
    }
    
    // Empty new line for easy reading
    System.out.println("");
  }
  
  /**
  *
  * List all projects that are past their due dates
  * <br>
  * This method is called when menu option 7 is selected
  *
  */
  // Menu option 7
  private static void pastDue() {
    // Compare due date with current date
    System.out.println("List of projects past due date:\n");
    System.out.println("Project Number, Project Name");
    
    // Initialize Calendar objects
    Calendar currentDate = Calendar.getInstance();
    Calendar dueDate = Calendar.getInstance();
    
    int size = projects.size();
    
    for(int i = 0; i < size; i++) {
      try {
        dueDate.setTime(DATEFORMAT.parse(projects.get(i).getDeadline()));
      } catch (ParseException e) {
        System.out.println("Problem reading date.");
      }
            
      if(currentDate.after(dueDate) && !projects.get(i).isFinalised) {
        System.out.println(projects.get(i).toConsoleView());
      }
    }
    
    // Empty new line for easy reading
    System.out.println("");
  }
  
  /**
  *
  * Display menu options
  * <br>
  * This method is part of the main screen for the program and is called on startup and when the user is done with any other option
  *
  */
  // Menu
  private static void showMenu() {
    // Output menu options
    System.out.println("POISED MAIN MENU");
    System.out.println("Select an option:");
    System.out.println("1 - create new project");
    System.out.println("2 - change due date of a project");
    System.out.println("3 - change the total amount paid for a project");
    System.out.println("4 - update a contractor's details");
    System.out.println("5 - finalise a project");
    System.out.println("6 - incomplete projects");
    System.out.println("7 - projects past due");
    System.out.println("0 - exit");
  }
  
  // Main Program
	public static void main(String[] args) {
	  
	  // Read all persons from file
	  readPersons();
		
		// Declare input variable and set to anything except 0
		int menuInput = -1;
		
		// Read all projects from file
		readProjects();
		
		// Dates have to be correct input
		DATEFORMAT.setLenient(false);
		
		while(menuInput != 0) {
		  // Show options available in application
			showMenu();
			
			// Try to get a non-string input
			try {
  			// Capture menu input from user
  			menuInput = SCAN.nextInt();
  			SCAN.nextLine(); // Bypass the 'Enter' pressed from user
  			
  			if(menuInput == 1) {
  				createProject();
  			} else if(menuInput == 2) {
  				// Change due date of existing project
  				changeProjectDeadline();
  			} else if(menuInput == 3) {
  				// Change the total amount paid for a project
          changeAmountPaid();
  			} else if(menuInput == 4) {
  				// Update a contractor's details
  				updateContractorDetails();
  			} else if(menuInput == 5) {
  				// Finalize a project
  				finaliseProject();
  			} else if(menuInput == 6) {
  			  // List incomplete projects
  			  incompleteProjects();
  			} else if(menuInput == 7) {
  			  // List projects that are past their due date
  			  pastDue();
  			} else if(menuInput == 0) {
  				// Exit the program
  			  writeToFiles();
  				System.out.println("GOODBYE");
  				break;
  			} else {
  				// Error for number not matching a menu option
  				System.out.println("NOTICE: Only menu options accepted.\n");
  			}
  		} catch (Exception e) {
  		  // Error if anything except a number is entered
  	    System.out.println("WARNING! Please input a number.\n");
  	    SCAN.nextLine(); // Bypass enter that is pressed
  	  }
		}
		
		// Close the scanner
    SCAN.close();
	}
	
}
