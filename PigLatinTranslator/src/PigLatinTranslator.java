import java.util.Scanner;
public class PigLatinTranslator {

	public static void main(String[] args) {
	    System.out.print("Enter a word: ");
		Scanner reader = new Scanner(System.in);
		String word = reader.nextLine();
		System.out.println();
		
		Translator translator = new Translator();
		
		System.out.print("Translated word: " + translator.translate(word));
		
		reader.close();
	}
}
