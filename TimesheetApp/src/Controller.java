import java.util.List;

public class Controller {

	/* Member variables */
	
    Timesheet timesheet;
    ConsoleUtils consoleUtils;
    
    /* Constructor */
    
    public Controller(){
        this.timesheet = new Timesheet();
        this.consoleUtils = new ConsoleUtils();
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

                processAddAction();

            } else if (action.equals("delete")) {
            	try {
            		processDeleteAction(actionParts);
                    consoleUtils.info("Delete was Succesful");
            	} catch (Exception e) {
            		consoleUtils.error("Invalid Entry");
            	}
            } else if (action.equals("stop")) {
            	try {
            		processStopAction(actionParts);
                    consoleUtils.info("Stop was Succesful");
            	} catch (IllegalArgumentException e) {
            		consoleUtils.error("This was already stopped");
            	}
            } else if (action.equals("list")) {
            	try {
            		processListAction(actionParts);
                    consoleUtils.info("List was Succesful");
            	} catch (IllegalArgumentException e) {
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
	 * The user requested that a given TimesheetEntry be stopped (marked as complete)
	 * This method conveys that request to the Timesheet
	 */
    public void processStopAction(String[] actionParts){

        if (actionParts.length > 2){
            consoleUtils.error("Too many inputs to stop command");
            return;
        }

        int id = Integer.parseInt(actionParts[1]);

        TimesheetEntry timesheetEntry = timesheet.get(id);
        
        timesheet.stop(timesheetEntry);
    }

	/*
	 * The user requested that a given TimesheetEntry be deleted
	 * This method conveys that request to the Timesheet
	 */
    public void processDeleteAction(String[] actionParts){

        if(actionParts.length > 2){
            consoleUtils.error("Too many inputs to delete command");
            return;
        }

        int id = Integer.parseInt(actionParts[1]);
        
        TimesheetEntry timesheetEntry = timesheet.get(id);
		
        timesheet.delete(timesheetEntry);
    }

	/*
	 * The user wants to view a list of timesheet entries
	 * This method conveys that request to the Timesheet,
	 * along with any special options (active-only, filter by project name)
	 */
    public void processListAction(String[] actionParts){
    
        if (actionParts.length > 3){
            consoleUtils.error("Too many inputs to list command");
            return;
        }
        
        boolean activeOnly = false;
        String projectName = null;
        
        for (String actionPart: actionParts) {
        	if (actionParts.length > 1 && !actionPart.toLowerCase().contains("project") && !actionPart.contains("-a")) 
        		throw new IllegalArgumentException("Invalid Entry");
        	
        	if (actionPart.toLowerCase().contains("project")) {
        		String project = consoleUtils.promptString("Project Name (one word only):");
        		
        		String[] projectWords = project.split(" ");
                
        		projectName = projectWords.length > 0 ? projectWords[0] : project;
        	}
        	
        	if (actionPart.contains("-a")) {
        		activeOnly = true;
        	}
        }
        
        List<TimesheetEntry> timesheetEntries = timesheet.list(activeOnly, projectName);

        consoleUtils.printList(timesheetEntries);
    }

	/*
	 * The user wants to add a new entry to the Timesheet
	 * This method conveys that request to the Timesheet, along with
	 * the specified project name and task description 
	 */
    public void processAddAction(){
    
        String project = consoleUtils.promptString("Project Name (one word only):");
        String description = consoleUtils.promptString("Task:");
        
        String[] projectWords = project.split(" ");
        
        project = projectWords.length > 0 ? projectWords[0] : project;
        
        timesheet.add(project, description);
    }
    
    /*
	 * It should display the menu of actions
	 */
    public void processHelpAction() {
    	consoleUtils.printHelp(); // Print the action menu
    }
}