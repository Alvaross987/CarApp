package car.app.rest;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import car.app.entity.Brand;
import car.app.entity.Car;
import car.app.entity.Country;
import car.app.services.CarService;

@RunWith(MockitoJUnitRunner.class)
public class CarResourceTest {

	@InjectMocks
	CarResource carResource;

	@Mock CarService carService;
	
	
	@Test
	public void whenGettingCars_shouldReturnOKandCarCollection() {
		// Mocks
		final List<Car> cars = new ArrayList<>();
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		cars.add(car);
		Mockito.when(carService.getCars()).thenReturn(cars);

		// Method call
		final Response response = carResource.getCars(null);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(cars, response.getEntity());
	}

	
	
	@Test
	public void whenGettingCar_shouldReturnOKandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		final List<Car> lc = new ArrayList<Car>();
		lc.add(car);
		Mockito.when(carService.getCar(1)).thenReturn(car);

		// Method call
		final Response response = carResource.getCar(null, 1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(lc, response.getEntity());
	}
	
	
	@Test
	public void whenAddingCar_shouldReturnCREATEDandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(carService.addCar(car)).thenReturn(car);

		// Method call
		final Response response = carResource.addCar(null, car);

		// Assertions
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		assertEquals(car, response.getEntity());
	}
	
	@Test
	public void whenUpdatingCar_shouldReturnACCEPTEDandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(carService.updateCar(car)).thenReturn(car);

		// Method call
		final Response response = carResource.updateCar(null, 1,car);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(car, response.getEntity());
	}
	
	
	@Test
	public void whenDeletingCar_shouldReturnACCEPTEDandCar() {
		// Mocks
		final Car car = new Car(1, new Brand("bmw"), new Timestamp(1), new Country("Spain"), new Timestamp(1), new Timestamp(1));
		Mockito.when(carService.deleteCar(1)).thenReturn(car);

		// Method call
		final Response response = carResource.deleteCar(null, 1);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(car, response.getEntity());
	}
}