package car.app.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import car.app.entity.Car;
import car.app.exception.DataNotFoundException;

@Stateless
public class CarService {

	@PersistenceContext(unitName = "postg")
	EntityManager em;

	public List<Car> getCars() {
		TypedQuery<Car> query = em.createQuery("from Car order by id", Car.class);
		List<Car> list= query.getResultList();

		return list;
	}
	
	public Car getCar(int id) throws DataNotFoundException {
		Car car = em.find(Car.class, id);

		if (car == null) {
			throw new DataNotFoundException("CAR WITH ID " + id + " NOT FOUND");
		}
		return car;

	}

	public Car addCar(Car car) {
		em.persist(car);
		return car;
	}

	public Car updateCar(Car car) throws DataNotFoundException {
		int id = car.getId();
		Car car2 = em.find(Car.class, id);
		if (car2 == null) {
			throw new DataNotFoundException("CAR WITH ID " + id + " NOT FOUND");
		}
		
		em.getTransaction().begin();
		car2.setBrand(car.getBrand());
		car2.setModel(car.getModel());
		car2.setColor(car.getColor());
		car2.setCountry(car.getCountry());
		car2.setRegistration(car.getRegistration());
		em.getTransaction().commit();

		return car2;
	}

	public Car deleteCar(int id) throws DataNotFoundException {
		Car car = em.find(Car.class, id);

		if (car == null) {
			throw new DataNotFoundException("CAR WITH ID " + id + " NOT FOUND");
		}
		em.remove(car);

		return car;

	}

}
