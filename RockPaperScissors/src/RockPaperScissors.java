import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class RockPaperScissors {
	
	private static final Map<Character, String> OPTION_NAMES = createMap();

    private static Map<Character, String> createMap() {
        Map<Character, String> result = new HashMap<Character, String>();
        result.put(new Character('r'), "Rock");
        result.put(new Character('p'), "Paper");
        result.put(new Character('s'), "Scissors");
        return Collections.unmodifiableMap(result);
    }
    
    private static Scanner reader = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean play = true;
		do {
			String longUserOption = readUserOption();
			
			char userOption = getValidOption(longUserOption);
			
			if (userOption == '\0') {
				System.out.println();
				System.out.println(longUserOption + " is Invalid, please try again");
				System.out.println();
				continue;
			}
			
			Game game = new Game(userOption);
		    
			System.out.println();
			System.out.println("User got: " + OPTION_NAMES.get(userOption) + "(" + userOption + ")");
		    System.out.println("PC got: " + OPTION_NAMES.get(game.getPcOption()) + "(" + game.getPcOption() + ")");
		    System.out.println();
		    System.out.println(game.finalResult());
		    System.out.println();
		    
		    play = isPlayAgain();
			    
		} while (play);
		
		reader.close();
		
		System.exit(0);
	}
	
	private static String readUserOption() {
		String options = "";
		for (char k: OPTION_NAMES.keySet()) {
			options += OPTION_NAMES.get(k) + "(" + k + "), ";
		}
		options = options.replaceAll(", $", "");
		System.out.print("Enter your option " + options + ": ");
		String userOption = reader.nextLine();
		return userOption;
	}
	
	private static char getValidOption(String longUserOption) {
		for (Entry<Character, String> entry: OPTION_NAMES.entrySet()) {
            if (longUserOption.equalsIgnoreCase(entry.getValue()) || 
            		(longUserOption.length() == 1 && entry.getKey() == longUserOption.toLowerCase().charAt(0))) {
                return entry.getKey();
            }
        }
		return '\0';
	}
	
	private static boolean isPlayAgain() {
		boolean valid = false;
		boolean play = false;
		
		do {
			System.out.println("Would you like to play again y/n? ");
			String userOptionPlayAgain = reader.nextLine();
			System.out.println();

			if(userOptionPlayAgain.equalsIgnoreCase("y") || userOptionPlayAgain.equalsIgnoreCase("yes")) {
				valid = true;
			    play = true;
			    System.out.println();
			} 
			
			if(userOptionPlayAgain.equalsIgnoreCase("n") || userOptionPlayAgain.equalsIgnoreCase("no")) {
				valid = true;
				play = false;
			}
			
			if (!valid) {
				System.out.println("Invalid entry, please try again");
				System.out.println();
			}
		} while (!valid);
		
		return play;
	}
}
