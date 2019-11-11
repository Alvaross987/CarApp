package car.app.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import car.app.entity.Brand;
import car.app.entity.Car;
import car.app.entity.Country;
import car.app.exception.DataNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

	@InjectMocks
	CarService carService;
	
	@Mock
	EntityManager em;
	
	@Mock
	TypedQuery<Object> query;
	
	
	@Test
	public void whenGettingCars_shouldReturnListOfCars() {
		// Mocks
		final List<Object> cars = new ArrayList<>();
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		cars.add(car);
		Mockito.when(em.createQuery(Mockito.anyString(), Mockito.any())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(cars);
		
		// Method call
		final List<Car> list = carService.getCars();
		
		// Assertions
		assertFalse(list.isEmpty());
		
	}
	
	
	@Test
	public void whenGettingCar_shouldReturnCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(em.find(Car.class, car.getId())).thenReturn(car);
		
		// Method call
		final Car c = carService.getCar(car.getId());

		// Assertions
		assertNotNull(c);
		
	}
	
	
	@Test(expected = DataNotFoundException.class)
	public void whenGettingCar_shouldReturnDataNotFoundException() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(em.find(Car.class, car.getId())).thenThrow(DataNotFoundException.class);
		
		// Method call
		carService.getCar(car.getId());
		
		
	}
	
	
	@Test
	public void whenUpdatingCar_shouldReturnUpdatedCar() {
		// Mocks
		EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("russia"), new Timestamp(1), new Timestamp(1));
		final Car car2 = new Car(1, new Brand("audi"), new Timestamp(5), new Country("spain"), new Timestamp(5), new Timestamp(5));
		Mockito.when(em.find(Car.class, car.getId())).thenReturn(car);
		Mockito.when(em.getTransaction()).thenReturn(transaction);
		
		// Method call
		final Car c = carService.updateCar(car2);
		Mockito.verify(transaction).begin();
		Mockito.verify(transaction).commit();
		// Assertions
		assertSame(c, car2);
		
	}
	
	
	@Test(expected = DataNotFoundException.class)
	public void whenUpdatingCar_shouldReturnDataNotFoundException() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("spain"), new Timestamp(1), new Timestamp(1));
		final Car car2 = new Car(1, new Brand("audi"), new Timestamp(5), new Country("russia"), new Timestamp(5), new Timestamp(5));
		Mockito.when(em.find(Car.class, car.getId())).thenThrow(DataNotFoundException.class);
		
		// Method call
		carService.updateCar(car2);
		
		
	}
	
	
	@Test
	public void whenDeletingCar_shouldReturnDeletedCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(em.find(Car.class, car.getId())).thenReturn(car);
		
		// Method call
		final Car c = carService.deleteCar(1);
		Mockito.verify(em, Mockito.never()).remove(car);
		
		//Assertion
		assertEquals(car, c);
		
	}
	
	
	@Test(expected = DataNotFoundException.class)
	public void whenDeletingCar_shouldReturnDataNotFoundException() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(em.find(Car.class, car.getId())).thenThrow(DataNotFoundException.class);
		
		// Method call
		carService.deleteCar(car.getId());
		Mockito.verify(em, Mockito.never()).remove(car);
		
		
	}


}
