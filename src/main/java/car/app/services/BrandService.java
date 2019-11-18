package car.app.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import car.app.entity.Brand;
import car.app.exception.DataNotFoundException;

@Stateless
@Interceptors(UserService.class)
public class BrandService {
	Logger log = Logger.getLogger(BrandService.class);

	@PersistenceContext(unitName = "postg")
	EntityManager em;
	public List<Brand> getAllBrand() {
		TypedQuery<Brand> query = em.createQuery("from Brand order by id", Brand.class);
		List<Brand> list= query.getResultList();
		log.info("All brands getted");
		return list;
	}
	
	public Brand getBrand(int id) throws DataNotFoundException {
		Brand brand = em.find(Brand.class, id);

		if (brand == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		log.info("Brand with id= " + id + " getted");
		return brand;

	}

	public Brand addBrand(Brand brand) {
		em.persist(brand);
		log.info("Brand added");
		return brand;
	}

	public Brand updateBrand(Brand brand) throws DataNotFoundException {
		int id = brand.getId();
		Brand brand2 = em.find(Brand.class, id);
		if (brand2 == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		em.getTransaction().begin();
		brand2.setName(brand.getName());
		em.getTransaction().commit();
		log.info("Brand with id= " + id + " modified");
		return brand2;
	}

	public Brand deleteBrand(int id) throws DataNotFoundException {
		Brand brand = em.find(Brand.class, id);

		if (brand == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		em.remove(brand);
		log.info("Brand with id= " + id + " deleted");
		return brand;

	}
}

