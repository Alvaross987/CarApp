package car.app.schedule;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import car.app.entity.Car;

@Stateless
public class CarCheck {
	
	Logger log = Logger.getLogger(CarCheck.class);
	@PersistenceContext(unitName = "postg")
	EntityManager em;
	public List<Car> checkCars() {
		TypedQuery<Car> query = em.createQuery("from Car order by id", Car.class);
		List<Car> list = query.getResultList();
		//list.forEach(car );
		log.info("All cars have been checked");
		return list;
	}
}
