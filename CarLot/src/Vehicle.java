/**
 * @author Nelma_Perera
 *
 */
public abstract class Vehicle {
	private String licenseNumber;
	private String make;
	private String model;
	private double price;
	
	public Vehicle(String licenseNumber, String make, String model, double price) {
		this.licenseNumber = licenseNumber;
		this.make = make;
		this.model = model;
		this.price = price;
	}
	
	/**
	 * Print the vehicle description data
	 */
	public void printDescriptionVehicle() {
		System.out.println("    LicenceNumber: " + this.licenseNumber);
		System.out.println("    Make: " + this.make);
		System.out.println("    Model: " + this.model);
		System.out.println("    Price: " + this.price);
	}
}
