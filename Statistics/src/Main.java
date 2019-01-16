import java.text.DecimalFormat;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
	    System.out.print("Please, enter comma separated numbers: ");
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		reader.close();
		input = input.replaceAll(" ", "");
		String[] strArray = input.split(",");
		Statistics statistics = new Statistics(strArray);
		
		DecimalFormat df = new DecimalFormat("###.##");
		System.out.println("The Minimum is " + df.format(statistics.getMin()));
		System.out.println("The Maximum is " + df.format(statistics.getMax()));
		System.out.println("The Number of Elements is " + statistics.getNumberElements());
		System.out.println("The Average is " + df.format(statistics.getAverage()));
		System.out.println("The Sum is " + df.format(statistics.getSum()));
		
		System.exit(0);
	}
}
