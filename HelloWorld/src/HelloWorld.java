import java.util.Scanner;
public class HelloWorld {

	public static void main(String[] args) {
	    System.out.println("Hi, I am Nelma, what is your name?");
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		System.out.println("Hi " + input + ", hope you are doing well.");
		reader.close();
	}
}
