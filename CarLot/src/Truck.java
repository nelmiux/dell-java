/**
 * @author Nelma_Perera
 *
 */
public class Truck extends Vehicle {
	private int bedSize;
	
	public Truck(String licenseNumber, String make, String model, double price, int bedSize) {
		super(licenseNumber, make, model, price);
		this.bedSize = bedSize;
	}
	
	/* 
	 * Override parent Vehicle#printDescriptionVehicle() to add
	 * this object specific description to the console printing
	 */
	@Override
	public void printDescriptionVehicle() {
		super.printDescriptionVehicle();
		System.out.println("    Bed Size: " + this.bedSize);
	}
}
