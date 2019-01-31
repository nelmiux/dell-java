import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ParkingGarage {
	private Car[] cars;
	private int capacity;

	public ParkingGarage(int capacity) {
		this.capacity = capacity;
		cars = new Car[capacity];
	}
	
	/**
	 * Check if spot which is the index on cars is in range
	 * @param spot
	 * @return if spot is valid value
	 */
	private boolean isValid(int spot) {
		return spot < this.cars.length;
	}
	
	/** 
	 * Add the car to a parking spot
	 * @param car
	 * @param spot
	 * @return false when entry out of range, null when the spot is already taken, or true when the adding was successful; 
	 */
	public Boolean park(Car car, int spot) {
		
		if (!this.isValid(spot))
			return false;

		if (cars[spot] != null)
			return null;
		
		this.cars[spot] = car;
		return true;
	}
	
	/**
	 * Remove the car from the specified spot
	 * @param spot
	 * @return false when entry out of range, null when the spot is null, or true when the removing was successful;
	 */
	public Boolean vacate(int spot) {
		
		if (!this.isValid(spot))
			return false;
		
		if (cars[spot] == null)
			return null;
		
		this.cars[spot] = null;
		return true;
	}
	/**
	 * Prints out to the console the the listing of all the cars
	 * Using reflection to get field names and values to print
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public void printInventory() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("  Garage " + this.hashCode() + ": ");
		for (int i = 0; i < this.cars.length; ++i) {
			if (this.cars[i] == null)
				continue;
			System.out.println("    Car " + i + ": ");
			System.out.println("      Spot Number: " + i); 
			Field[] fields = cars[i].getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					System.out.println("      " + field.getName() + ": " + cars[i].getClass().getDeclaredMethod("get" + field.getName()).invoke(cars[i]));
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (SecurityException e) {
					
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Get park garage capacity
	 * @return capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
}
