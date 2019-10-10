package car.app.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import car.app.entity.Car;
import car.app.entity.ErrorMessage;

@Stateless
public class CarService {

	@PersistenceContext(unitName = "postg")
	EntityManager em;


	public List<Car> getCars() {
		String q = "from Car";
		return em.createQuery(q, Car.class).getResultList();
	}

	public Car getCar(int id) {

		ErrorMessage errorMessage = new ErrorMessage("CAR WITH ID " + id + " NOT FOUND", 404, "");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();

		// Car car = Cars.get(CarId);
		Car car = em.find(Car.class, id);

		if (car == null) {
			throw new WebApplicationException(response);
		}
		return car;

	}

	public Car addCar(@Valid Car car) {
		em.persist(car);
		return car;
	}

	public Car updateCar(@Valid Car car) {
		int id = car.getId();
		ErrorMessage errorMessage = new ErrorMessage("CAR WITH ID " + id + " NOT FOUND", 404, "");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		Car car2 = em.find(Car.class, id);
		if (car2 == null) {
			throw new WebApplicationException(response);
		}
		em.getTransaction().begin();
		car2.setName(car.getName());
		car2.setCountry(car.getCountry());
		if (car.getCreated_at() != null) {
			car2.setCreated_at(car.getCreated_at());
		}
		if (car.getRegistration() != null) {
			car2.setRegistration(car.getRegistration());
		}

		em.getTransaction().commit();
		return car;
	}

	public Car deleteCar(int id) {
		ErrorMessage errorMessage = new ErrorMessage("CAR WITH ID " + id + " NOT FOUND", 404, "");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).build();

		Car car = em.find(Car.class, id);

		if (car == null) {
			throw new WebApplicationException(response);
		}
		em.remove(car);
		return car;

	}

}
