import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;
public class Main {
	static final Scanner reader = new Scanner(System.in);
	static Map<String, String> studentGrades = new HashMap();

	public static void main(String[] args) {
		boolean isValid = true;
		do {
			try {
				getEntryPerStudent(getNumberOfStudents());
				Gradebook gradebook = new Gradebook(studentGrades);
				printResult(gradebook.getResult());
			} catch (NumberFormatException e) {
				System.out.println();
				System.out.println("Invalid Entry, please try again");
				System.out.println();
				isValid = false;
			}
		} while (!isValid);

		reader.close();
		System.exit(0);
	}
	
	// Read number of students
	private static int getNumberOfStudents() {
		System.out.print("Please enter the number of students on the gradebook: ");
		String numberStudents = reader.nextLine();
		int nstudents = Integer.parseInt(numberStudents);
		return nstudents;
	}
	
	// Read entry per student
	private static void getEntryPerStudent(int nstudents) {	
		for (int i = 0; i < nstudents; ++i) {
			int studentNumber = i + 1;
			System.out.print("Please enter the student " + studentNumber + " name: ");
			String name = reader.nextLine();
			
			System.out.print("Please enter grades of " + name + " as a comma separated values: ");
			String grades = reader.nextLine();
			
			studentGrades.put(name, grades.replaceAll(" ", ""));
		}
	}
	
	// Print the final map result
	private static void printResult(Map<String, Double> result) {
		System.out.println();
		DecimalFormat df = new DecimalFormat("###.##");
		for (Entry<String, Double> entry: result.entrySet()) {
			System.out.println("The average grade of " + entry.getKey() + " is " + df.format(entry.getValue()));
		}
	}
}
