import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Main {
	public static void main(String[] args) {
	    System.out.print("Enter expresion to calculate (eg. 10 * ( 7 + 30 )): ");
	    Scanner reader = new Scanner(System.in);
	    String expression = reader.nextLine();
	    Calculator calculator = new Calculator(); 
	    try {
	    	System.out.println("Result: " + calculator.calculate(expression));
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
    		reader.close();
        }
	    System.exit(0);
	}
}
