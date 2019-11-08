package car.app.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import car.app.entity.Car;
import car.app.filter.AuthFilter;
import car.app.services.CarService;

@Stateless
@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource implements CarResourceI {
	final Logger logger = Logger.getLogger(CarResource.class);
	@EJB(name = "carService	")
	CarService carService;
	
	@Interceptors(AuthFilter.class)
	public Response getCars(HttpHeaders httpHeaders) {
		return Response.status(Status.OK).entity(carService.getCars()).build();
	}

	public Response getCar(HttpHeaders httpHeaders, Integer id) {
		Car car = carService.getCar(id);
		List<Car> list = new ArrayList<Car>();
		list.add(car);
		Response result = Response.status(Status.OK).entity(list).build();
		return result;
	}

	
	public Response addCar(HttpHeaders httpHeaders, Car car) {

		Car newCar = carService.addCar(car);
		return Response.status(Status.CREATED).entity(newCar).build();
	}

	public Response updateCar(HttpHeaders httpHeaders, Integer id, Car car) {

		car.setId(id);

		return Response.accepted(carService.updateCar(car)).build();
	}

	public Response deleteCar(HttpHeaders httpHeaders, Integer id) {

		return Response.accepted(carService.deleteCar(id)).build();
	}

}
