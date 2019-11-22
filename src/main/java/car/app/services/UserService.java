package car.app.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import car.app.entity.User;

@Stateless
public class UserService {

	Algorithm algorithm = Algorithm.HMAC256("secret");
	@PersistenceContext(unitName = "postg")
	EntityManager em;

	public List<User> getAllUsers() {
		TypedQuery<User> query = em.createQuery("from User order by id", User.class);
		List<User> list = query.getResultList();

		return list;
	}

	public boolean login(String username, String password) {
		try {
		TypedQuery<User> query = em.createQuery("from User where username = :name and password = :passw", User.class);
		query.setParameter("name", username).setParameter("passw", password).getSingleResult();
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public User giveAdmin(Integer id) {
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		user.setIsAdmin(1);
		em.getTransaction().commit();
		return user;
	}
	
	public String generateToken(String username) {
		TypedQuery<User> query = em.createQuery("from User where username = :name", User.class);
		User user = query.setParameter("name", username).getSingleResult();
		String token = "";
		Calendar cal = Calendar.getInstance();
		long t = cal.getTimeInMillis();
		cal.setTimeInMillis(t+2250000);
		Date later = cal.getTime();
		
		try {

			token = JWT.create()
					.withIssuer(username)
					.withClaim("isadmin", user.getIsAdmin())
					.withExpiresAt(later)
					.sign(algorithm);
			em.getTransaction().begin();
			user.setToken(token);
			em.getTransaction().commit();
		} catch (JWTCreationException exception) {
			
		}
		return token;
	}

	public User addUser(User user) {
		em.persist(user);
		return user;
	}

}
