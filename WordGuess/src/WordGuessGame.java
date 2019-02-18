import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Nelma_Perera
 *
 */
public class WordGuessGame {
	private static Scanner reader = new Scanner(System.in);
	private static Map<Character, List<Integer>> charIndexes = new HashMap<Character, List<Integer>>();
	private static String originalWord;
	private static String outWord;

	/**
	 * Driver main
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			originalWord = randomWord().toLowerCase();
			setCharIndexs();
			outWord = originalWord.replaceAll(".", "-");
			boolean invalid =  false;
			String input;
			do {
				do {
					printOutWord();
					input = inputLetter();
					if (input.length() > 1 || !Character.isLetter(input.charAt(0))) {
						printInvalidEntry();
						invalid = true;
					}		
				} while (invalid);
				char inputChar = input.charAt(0);
				char[] outWordChars = outWord.toCharArray();
				if (charIndexes.containsKey(inputChar)) {
					for (int index: charIndexes.get(inputChar))
						outWordChars[index] = inputChar;
					outWord = String.valueOf(outWordChars);
				}
			} while (outWord.contains("-"));
			printEndMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();
	}
	
	
	/**
	 * @return a random word from the file
	 * @throws IOException
	 */
	private static String randomWord() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./words_alpha.txt"));
		Random randNumGenerator = new Random();
	    int randomLine = randNumGenerator.nextInt(lines.size());
	    return lines.get(randomLine);
	}
	
	/**
	 * Sets the map between each character and its indexes from the original word
	 */
	private static void setCharIndexs() {
		for (int i = 0; i < originalWord.length(); i++) {
			char letter = originalWord.charAt(i);
			if (!charIndexes.containsKey(letter)) {
				charIndexes.put(letter, new ArrayList<Integer>());
			}
			List<Integer> indexes = charIndexes.get(letter);
			indexes.add(i);
			charIndexes.put(letter, indexes);
		}
	}
	
	/**
	 * Print to console the current guessed word
	 */
	private static void printOutWord() {
		System.out.println("The guessed word is " + outWord);
	}
	
	/**
	 * @return the input from console
	 */
	private static String inputLetter() {
		System.out.print("Enter a letter (case insensitive): ");
		return reader.nextLine().toLowerCase();
	}
	
	
	/**
	 * Print to console the message when the entry is invalid
	 */
	private static void printInvalidEntry() {
		System.out.println();
		System.out.println("Invalid entry, try again");
		System.out.println();
	}
	
	
	/**
	 * Print to console the final message after the word was 
	 * finally completely guessed
	 */
	private static void printEndMessage() {
		System.out.println();
		System.out.println("You nailed it, the word is " + outWord);
	}
}