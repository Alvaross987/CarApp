package car.app.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import car.app.database.CarDatabase;
import car.app.entity.Car;

@Stateless
public class CarService {

	private Map<Long, Car> Cars = CarDatabase.getCars();

	public CarService() {
		Timestamp tm = new Timestamp(new Date().getTime());
		Cars.put(1L, new Car(1, "coche1", tm, "España", tm, tm));
		Cars.put(2L, new Car(2, "coche5", tm, "Alemania", tm, tm));
	}
	
	private void actualizafecha(Car car) {
		car.setLast_Updated(new Timestamp(new Date().getTime()));
	}

	public List<Car> getCars() {
		return new ArrayList<>(Cars.values());
	}

	public Car getCar(long CarId) {
		return Cars.get(CarId);

	}

	public Car addCar(Car car) {
		car.setId(Cars.size() + 1);
		actualizafecha(car);
		Cars.put((long) car.getId(), car);
		return car;
	}

	public Car updateCar(Car car) {
		int id = car.getId();
		if (id == 0) {
			return null;
		}
		actualizafecha(car);
		Cars.put((long) car.getId(), car);
		return car;
	}

	public Car deleteCar(int CarId) {
		return Cars.remove((long) CarId);

	}

}
