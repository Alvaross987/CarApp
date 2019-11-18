package car.app.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import car.app.entity.Country;
import car.app.exception.DataNotFoundException;

@Stateless
public class CountryService {

	Logger log = Logger.getLogger(CountryService.class);
	
	@PersistenceContext(unitName = "postg")
	EntityManager em;

	public List<Country> getAllCountries() {
		TypedQuery<Country> query = em.createQuery("from Country order by id", Country.class);
		List<Country> list= query.getResultList();
		log.info("All counties getted");
		return list;
	}
	
	public Country getCountry(int id) throws DataNotFoundException {
		Country country = em.find(Country.class, id);

		if (country == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		log.info("Country with id= " + id + " getted");
		return country;

	}

	public Country addCountry(Country country) {
		em.persist(country);
		log.info("Country added");
		return country;
	}

	public Country updateCountry(Country country) throws DataNotFoundException {
		int id = country.getId();
		Country country2 = em.find(Country.class, id);
		if (country2 == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		em.getTransaction().begin();
		country2.setName(country.getName());
		em.getTransaction().commit();
		log.info("Country with id= " + id + " modified");
		return country2;
	}

	public Country deleteCountry(int id) throws DataNotFoundException {
		Country country = em.find(Country.class, id);

		if (country == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		em.remove(country);
		log.info("Country with id= " + id + " deleted");
		return country;

	}
}
