package car.app.schedule;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import car.app.db.FlywayUtil;
import car.app.entity.Car;

@Singleton
@Startup
public class TimerBean {

	Logger log = Logger.getLogger(TimerBean.class);

	@PersistenceContext(unitName = "postg")
	EntityManager em;
	
	@PostConstruct
	public void init() {
		FlywayUtil.migrate();
	}

	@Schedule(hour = "*", minute="*/30", persistent = false)
	public void automaticallyScheduled() {
		checkCars();

	}

	public void checkCars() {
		TypedQuery<Car> query = em.createQuery("from Car order by id", Car.class);
		List<Car> list = query.getResultList();
		list.forEach((car) ->{
			em.getTransaction().begin();
			car.setChecked(1);
			em.getTransaction().commit();
		});
		log.info("All cars have been checked");
	}

}
