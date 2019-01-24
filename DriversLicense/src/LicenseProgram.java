import java.util.Date;

public class LicenseProgram {
	/**
	 * 	List of 3 DriverLicence instances
	 */
	private static final DriversLicense[] driversLicenses = 
		{
			new DriversLicense("Nelma", "Perera", "02/02/2002", 5.5, 'F'), 
			new DriversLicense("Charles", "Babbage", "12/26/1791", 5.8, 'M'),
			new DriversLicense("Ada", "Lovelace", "12/10/1815", 6.2, 'F')
		};
	
	public static void main(String[] args) throws ClassNotFoundException {
	    
		/**
		 * 	Loop through the driversLicenses list and
		 *  print FullName and Age of each driver license 
		 */
		for (int i = 0; i < driversLicenses.length; ++i)
			System.out.println((i + 1) + " - FullName: " + driversLicenses[i].getFullName() + ", Age: " + driversLicenses[i].getAge());
	}
}
