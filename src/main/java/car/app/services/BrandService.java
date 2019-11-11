package car.app.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import car.app.entity.Brand;
import car.app.exception.DataNotFoundException;

@Stateless
@Interceptors(UserService.class)
public class BrandService {

	@PersistenceContext(unitName = "postg")
	EntityManager em;
	public List<Brand> getAllBrand() {
		TypedQuery<Brand> query = em.createQuery("from Brand order by id", Brand.class);
		List<Brand> list= query.getResultList();

		return list;
	}
	
	public Brand getBrand(int id) throws DataNotFoundException {
		Brand brand = em.find(Brand.class, id);

		if (brand == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		return brand;

	}

	public Brand addBrand(Brand brand) {
		em.persist(brand);
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

		return brand2;
	}

	public Brand deleteBrand(int id) throws DataNotFoundException {
		Brand brand = em.find(Brand.class, id);

		if (brand == null) {
			throw new DataNotFoundException("BRAND WITH ID " + id + " NOT FOUND");
		}
		em.remove(brand);

		return brand;

	}
}

