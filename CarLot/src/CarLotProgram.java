import java.util.Random;
import java.util.Scanner;
public class CarLotProgram {
	
	static CarLot[] carlots = { new CarLot(), new CarLot() };
	
	static Vehicle[][] vehicleList = { 
		{
			new Car("123456", "Ford", "Explorer", 40000, "suv", 5),
			new Truck("76358", "Ford", "F150", 30000, 4),
			new Car("097868", "Tesla", "S", 60000.80, "sedan", 4),
			new Truck("342342", "RAM", "1500", 31700, 2),
			new Car("757289", "NIO", "ES8", 45000.78, "sedan", 4)
		},
		{ 
			new Truck("142543", "Nissan", "Frontier", 19000.67, 4),
			new Car("109238", "Kia", "Soul EV", 20000.2, "hatchback", 5),
			new Truck("837647", "Toyota", "Tacoma", 26000, 4)
		}
	};

	public static void main(String[] args) {
		// Adding vehicles to the car lots
		for (int i = 0; i < carlots.length; ++i) {		    
		    for (int j = 0; j < vehicleList[i].length; ++j) {
		    	carlots[i].addVehicle(vehicleList[i][j]);
		    }
		}
		
		// Printing car lot information
		for (int i = 0; i < carlots.length; ++i) {
			carlots[i].printInventory();
		}
	}
}
