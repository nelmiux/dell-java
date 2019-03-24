import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtils {

	/* Member variables */
    private Scanner scanner;
    
    /* Constructor */
    public ConsoleUtils(){
        scanner = new Scanner(System.in);
    }

	/* Methods */
	
	/*
	 * Prints the menu of actions to the console
	 */
    public void printHelp(){
        System.out.println("Valid commands: ");
        System.out.println("  list [-c] [-i] [-d] [-o]     list entries; all flags are optional; completed or incompleted flags are mutually exclusive; due or overdue flags are mutually exclusive, and list entries depending of due date");
        System.out.println("  sort                         list entries sorted by due date, soones first");
        System.out.println("  add                          add an entry, and set to incomplete");
        System.out.println("  update ID           	       update the entry's to complete");
        System.out.println("  delete ID         	       delete entry with the ID");
        System.out.println("  help              	       print help");
        System.out.println("  quit              	       quit the app");
        System.out.println();
    }

	/*
	 * Prints an informational message to the console
	 */
    public void info(String msg){
        System.out.println("["+msg+"]");
        System.out.println();
    }

	/*
	 * Prints an error message to the console
	 */
    public void error(String msg){
        System.out.println("[ERROR: "+msg+"]");
        System.out.println();
        }

	/*
	 * Prompts the user to enter input
	 * Returns the text entered by the user
	 */
    public String promptString(String label){
        System.out.print(label+" ");
        return scanner.nextLine();
    }

	/*
	 * Prints a list of todo objects in a pretty table
	 */
    public void printList(List<TodoItem> entries){
        int longestDescription = 11;

        for(TodoItem entry : entries){
            if(entry.getDescription().length() > longestDescription) {
            	longestDescription = entry.getDescription().length();
            }
        }
        
        String descriptionHeader = String.format("%"+longestDescription+"s", "Description");
        String descriptionUnderline = "";
        String isCompletedHeader = "Completed";
        String isCompletedUnderline = "";
        String dueDateHeader = "Due Date";
        String dueDateUnderline = "";
        for(int i=0;i<longestDescription;i++){
        	descriptionUnderline+="-";
        }
        for(int i=0;i<isCompletedHeader.length();i++) {
        	isCompletedUnderline+="-";
        }
        for(int i=0;i<dueDateHeader.length() + 2;i++) {
        	dueDateUnderline+="-";
        }

        System.out.println(" ID | "+descriptionHeader   +" | "+isCompletedHeader+" | "+dueDateHeader+" ");
        System.out.println("----+-"+descriptionUnderline+"-+-"+isCompletedUnderline+"-+-"+dueDateUnderline+"-");

        for(TodoItem entry : entries){
            String description = String.format("%-"+longestDescription+"s", entry.getDescription());
            String completed = String.format("%-"+isCompletedHeader.length()+"s", entry.getIsCompleted() ? "Yes" : "No");
            String dueDate = String.format("%-"+dueDateHeader.length()+"s", entry.getDue());
            String line = String.format(" %2s | %s | %s | %s", entry.getId(), description, completed, dueDate);
            System.out.println(line);
        }

        if(entries.size() == 1){
            System.out.println("[1 entry]");
        } else {
            System.out.println("["+entries.size()+" entries]");
        }

        System.out.println();
    }
}