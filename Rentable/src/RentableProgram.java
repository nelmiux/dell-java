import java.text.DecimalFormat;
import java.util.Scanner;
public class RentableProgram {
	private static Scanner reader = new Scanner(System.in);
	private static IRentable[] rentables = {
			new Room(77.55),
			new Condo(410.45),
			new Tool(6.80),
			new Room(100.30),
			new Condo(284.23),
			new Tool(11.48),
			new Room(397.62),
			new Condo(734.76),
			new Tool(25.87),
	};

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("###.##");
		for (IRentable rentable: rentables) {
			System.out.println("Rentable " + rentable.hashCode() + ":");
			System.out.println();
			System.out.println("  Description: " + rentable.getDescription());
			System.out.println();
			System.out.println("  Daily Rate: " + df.format(rentable.getDailyRate()));
			System.out.println();
			
			boolean valid;
			
			do {
				valid = true;
				try {
					System.out.print("  Enter number of days: ");
					String daysStr = reader.nextLine();
					double days = Double.parseDouble(daysStr);
					System.out.println();
					System.out.println("  The total price is " + df.format(rentable.getPrice(days)));
				} catch (Exception e) {
					System.out.println();
					System.out.println("  Invalid entry, please try again");
					valid = false;
				}
				System.out.println();
			} while (!valid);
			
			System.out.println();
		}
		
		reader.close();
	}
}
