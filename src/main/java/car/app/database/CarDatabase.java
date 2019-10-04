package car.app.database;

import java.util.HashMap;
import java.util.Map;

import car.app.entity.Car;

public class CarDatabase {

	private static Map<Long, Car> cars = new HashMap<>();
	
	public static Map<Long, Car> getCars(){
		return cars;
	}
}
