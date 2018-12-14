import java.util.Scanner;
public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello World!");
	    System.out.println("Please type something into the console.");
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		System.out.println("You typed: " + input);
		reader.close();
	}
}
