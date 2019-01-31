import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class GarageManager {
	static ParkingGarage[] parkingGarages = {
			new ParkingGarage(5),
			new ParkingGarage(9),
			new ParkingGarage(7),
			new ParkingGarage(10)
	};
	
	static Car[] carsList = {
		new Car("Blue", "123456", "Ford", "Explorer"), 
		new Car("Red", "6789", "Tesla", "S"), 
		new Car("Black", "09876", "NIO", "ES8"),
		new Car("Green", "45678", "Chevrolet", "Bolt"),
		new Car("Gray", "109238", "Kia", "Soul EV"), 
		new Car("Orange", "76358", "Hyundai", "Kona"), 
		new Car("Blue", "2345", "BMW", "i3"),
		new Car("White", "987667", "Nissan", "Leaf"), 
		new Car("Green", "42355", "Jaguar", "i-Pace"), 
		new Car("Red", "34567", "Renault", "Zoe"),
		new Car("Black", "25367", "VW", "e-Golf"),
		new Car("Gray", "0987", "Smart", "Fortwo Electric Drive"),
		new Car("Black", "8273468", "Hyundai", "Ioniq Plug-In Hybrid"), 
		new Car("White", "345098", "Tesla", "3"), 
		new Car("Red", "645099", "BMW", "7 Series"),
		new Car("Orange", "0987777", "Audi", "Sportback e-tron"),
		new Car("Red", "0987555", "Cadillac", "CT6 Hybrid"), 
		new Car("White", "4566333", "Mercedes-Benz", "C-Class Hybrid"), 
		new Car("Green", "25673", "Porsche", "Cayenne Hybrid"),
	};
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String invalidSpotMessage = "Index out of bound, this parking spot does not exist";
		
		// Parking random cars on ParkingGarage
		System.out.println("Parking!");
		for (int i = 0; i < parkingGarages.length; ++i) {
			// Generate random cars from carsList to park on parking garages
			Random randNumGenerator = new Random();
		    int randomCarListIndex = randNumGenerator.nextInt(carsList.length);
		    for (int j = 0; j < parkingGarages[i].getCapacity(); ++j) {
		    	Boolean validPark = parkingGarages[i].park(carsList[randomCarListIndex], j);
		    	if (validPark == null)
		    		System.out.println("The spot you are trying to park is not empty");
		    	if (!validPark)
		    		System.out.println(invalidSpotMessage);
		    }
		    
		    parkingGarages[i].printInventory();
		}
		
		// Vacating all cars from ParkingGarage
		System.out.println("Vacating!");
		for (int i = 0; i < parkingGarages.length; ++i) {
			for (int j = 0; j < parkingGarages[i].getCapacity(); ++j) {
		    	Boolean validVacate = parkingGarages[i].vacate(j);
		    	if (validVacate == null)
		    		System.out.println("The spot you are trying to vacate is already empty");
		    	if (!validVacate)
		    		System.out.println(invalidSpotMessage);
		    }
		    
		    parkingGarages[i].printInventory();
		}
	}
}
