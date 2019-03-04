import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.text.DateFormatter;

public class Controller {

	/* Member variables */
    private Dao dao;
    private ConsoleUtils consoleUtils;
    private DateTimeFormatter dateFormatter;
    
    /* Constructor */
    public Controller(){
        this.dao = new Dao();
        this.consoleUtils = new ConsoleUtils();
        dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    }
    
    /* Methods */

	/*
	 * Runs the program
	 */
    public void start() {

        consoleUtils.printHelp(); // Print the action menu

        boolean quit = false;
        while(!quit) {

			// Prompt the user for input, collect input, parse into parts
            String input = consoleUtils.promptString("> ");
            String[] actionParts = input.split(" ");
            String action = actionParts[0].trim(); // Primary action

			// Figure out what to do depending on the user's primary action
            if (action.equals("add")) {
            	try {
            		processAddAction();
                    consoleUtils.info("Update was Succesful");
            	} catch (IllegalArgumentException e) {
            		consoleUtils.error("Invalid Entry");
            	}
            } else if (action.equals("delete")) {
            	try {
            		processDeleteAction(actionParts);
                    consoleUtils.info("Delete was Succesful");
            	} catch (Exception e) {
            		consoleUtils.error("Invalid Entry");
            	}
            } else if (action.equals("update")) {
            	try {
            		processUpdateAction(actionParts);
                    consoleUtils.info("Update was Succesful");
            	} catch (IllegalArgumentException e) {
            		consoleUtils.error("Invalid Entry");
            	}
            } else if (action.equals("list")) {
            	try {
            		processListAction(actionParts);
            	} catch (Exception e) {
            		consoleUtils.error(e.getMessage());
            	}
            } else if (action.equals("sort")) {
	        	try {
	        		processSortAction(actionParts);
	        	} catch (Exception e) {
	        		consoleUtils.error(e.getMessage());
	        	}
            } else if (action.equals("quit")) {

                quit = true;

            } else if (action.equals("help")) {

            	processHelpAction();

            } else if(action.length() == 0 ){
                
            } else {
            	consoleUtils.error("Invalid Entry, try again");
            }
        }

    }

	/*
	 * The user requested that a given todo item be marked as complete
	 * This method conveys that request to the dao
	 */
    public void processUpdateAction(String[] actionParts){

        if (actionParts.length > 2){
            consoleUtils.error("Too many inputs to stop command");
            return;
        }

        int id = Integer.parseInt(actionParts[1]);
        
        dao.update(id);
    }

	/*
	 * The user requested that a given todo item be deleted
	 * This method conveys that request to the dao
	 */
    public void processDeleteAction(String[] actionParts){

        if(actionParts.length > 2){
            consoleUtils.error("Too many inputs to delete command");
            return;
        }

        int id = Integer.parseInt(actionParts[1]);
		
        dao.delete(id);
    }

	/*
	 * The user wants to view a list of todo entries
	 * This method conveys that request to the dao,
	 * along with any special options (completed-only, incomplete-only, due, overdue)
	 */
    public void processListAction(String[] actionParts){
        if (actionParts.length > 4){
            consoleUtils.error("Too many inputs to list command");
            return;
        }
        
        String stm = String.join(",", actionParts);
        if (stm.contains("-c") && stm.contains("-i")) {
            consoleUtils.error("Completed and Incomple flags are mutually exclusive, for getting list of all entries no falg is needed");
            return;
        }
        
        if (stm.contains("-d") && stm.contains("-o")) {
            consoleUtils.error("Due and Overdue flags are mutually exclusive, for getting list of all entries no falg is needed");
            return;
        }
        
        Boolean isCompleted = null;
        LocalDate due = null;
        boolean isOverdue = false;
        
        for (String actionPart: actionParts) {
        	if (!actionPart.equals("list") && 
        		!actionPart.equalsIgnoreCase("-c") && 
        		!actionPart.equalsIgnoreCase("-i") && 
        		!actionPart.equalsIgnoreCase("-d") &&
        		!actionPart.equalsIgnoreCase("-o"))
        	{
        		consoleUtils.error("Invalid Entry");
                return;
        	}
        	
        	if (actionPart.toLowerCase().contains("-c")) {
        		isCompleted = true;
        	}
        	
        	if (actionPart.toLowerCase().contains("-i")) {
        		isCompleted = false;
        	}
        	
        	if (actionPart.toLowerCase().contains("-d")) {
        		due = LocalDate.parse(consoleUtils.promptString("Due Date (mm/dd/yyyy):"), dateFormatter);
        	}
        	
        	if (actionPart.toLowerCase().contains("-o")) {
        		isOverdue = true;
        	}
        }

        consoleUtils.printList(dao.list(isCompleted, due, isOverdue));
    }

	/*
	 * The user wants to add a new entry to the todo table
	 * This method conveys that request to the dao, along with
	 * the specified todo description 
	 */
    public void processAddAction(){
        String toDoDescription = consoleUtils.promptString("ToDo Description: ");
        LocalDate dueDate = LocalDate.parse(consoleUtils.promptString("Due Date (mm/dd/yyyy):"), dateFormatter);
        
        dao.add(toDoDescription, dueDate);
    }
    
    /*
	 * It should display the menu of actions
	 */
    public void processHelpAction() {
    	consoleUtils.printHelp(); // Print the action menu
    }
    
    /*
	 * The user wants to view a list of todo entries
	 * This method conveys that request to the dao,
	 * along with any special options (completed-only, incomplete-only, due, overdue)
	 */
    public void processSortAction(String[] actionParts){
    	if (actionParts.length > 1){
            consoleUtils.error("Too many inputs to list command");
            return;
        }
    	
    	consoleUtils.printList(dao.sort());
    }
}