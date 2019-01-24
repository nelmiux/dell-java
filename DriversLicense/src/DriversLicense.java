import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DriversLicense {
	private String firstName;
	private String lastName;
	private Date dob;
	private double height;
	private char gender;
	
	/**
	 * 	Initializing object properties on the constructor
	 */
	public DriversLicense(String firstName, String lastName, String dob, Double height, char gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		try {
			this.dob = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(dob);
		} catch (ParseException e) {
			this.dob = new Date();
		}
		this.height = height;
		this.gender = gender;
	}
	
	/**
	 * 	Return "FirstName LastName"
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	/**
	 * 	Return the age of the person based on DOB
	 */
	public int getAge() {
		Date now = new Date();
		long timeRange = now.getTime() - dob.getTime();
		return (int) Math.floor(timeRange / 3.15576e+10);
	}
}
