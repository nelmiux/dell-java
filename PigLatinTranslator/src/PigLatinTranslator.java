import java.util.Scanner;
public class PigLatinTranslator {

	public static void main(String[] args) {
	    System.out.print("Enter a sentence: ");
		Scanner reader = new Scanner(System.in);
		String sentence = reader.nextLine();
		System.out.println();
		
		String[] splittedSentence = sentence.split(" ");
		String resultSentence = "";
		Translator translator = new Translator();	
		
		for (int i = 0; i < splittedSentence.length; ++i) {
			resultSentence += " " + translator.translate(validWord(splittedSentence[i]));
		}
		
		System.out.println("Translated sentence:" + resultSentence);
		
		reader.close();
	}
	
	// Returns true if the word contains only letters, false otherwise
	private static String validWord(String word) {
		String validWord = "";
		for (int i = 0; i < word.length(); ++i) {
			Character character = new Character(word.charAt(i));
			if (Character.isLetter(character)) validWord += character;
		}
		return validWord;
	}
}
