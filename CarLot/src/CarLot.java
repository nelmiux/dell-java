import java.util.ArrayList;
import java.util.List;

/**
 * @author Nelma_Perera
 *
 */
public class CarLot {
	private String name;
	private List<Vehicle> vehicles =  new ArrayList<Vehicle>();
	
	/**
	 * Add new vehicle to the parking lot
	 * @param vehicle
	 */
	public void addVehicle(Vehicle vehicle) {
		this.vehicles.add(vehicle);
	}
	
	/**
	 * Print Parking lot inventory
	 */
	public void printInventory() {
		int numberOfVehicles = this.vehicles.size();
		System.out.println("CarLot " + this.hashCode() + ":");
		System.out.println("  Number of Vehicles: " + numberOfVehicles);
		for (int i = 0; i < numberOfVehicles; ++i) {
			System.out.println("  Vehicle " + i + ":");
			this.vehicles.get(i).printDescriptionVehicle();
			System.out.println();
		}
		System.out.println();
	}
}
