/**
 * @author Nelma_Perera
 *
 */
public class Car extends Vehicle {
	private String carType;
	private int numberDoors;
	
	public Car(String licenseNumber, String make, String model, double price, String carType, int numberDoors) {
		super(licenseNumber, make, model, price);
		this.carType = carType;
		this.numberDoors = numberDoors;
	}
	
	/* 
	 * Override parent Vehicle#printDescriptionVehicle() to add
	 * this object specific description to the console printing
	 */
	@Override
	public void printDescriptionVehicle() {
		super.printDescriptionVehicle();
		System.out.println("    Car Type: " + this.carType);
		System.out.println("    Number of Doors: " + this.numberDoors);
	}
}
