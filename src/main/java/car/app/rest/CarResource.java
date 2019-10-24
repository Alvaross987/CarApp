package car.app.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import car.app.entity.Car;
import car.app.services.CarService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;


@OpenAPIDefinition(info = 
@Info(
        title = "Car App",
        version = "0.3.4",
        description = "My API",
        license = @License(name = "Apache 2.0", url = "http://foo.bar"),
        contact = @Contact(url = "http://example.com", name = "Alvaro", email = "Example@example.com")
),
tags = {
        @Tag(name = "/cars", description = "Car Operations")
})
@Stateless
@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource implements ICarResource{
	static final Logger logger = Logger.getLogger(CarResource.class);
	@EJB
	CarService carService;

	
	public Response getCars() {
		logger.info("Started");
		return Response.status(Status.OK).entity(carService.getCars()).build();
	}


	public Response getCar(Integer id) {
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + id);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + id);
		}
		
		logger.warn("This is warn : " + id);
		logger.error("This is error : " + id);
		logger.fatal("This is fatal : " + id);
		return Response.ok(carService.getCar(id)).build();
	}

	
	public Response addCar(Car car) {

		Car newCar = carService.addCar(car);
		return Response.status(Status.CREATED).entity(newCar).build();
	}


	public Response updateCar(Integer id, Car car) {

		car.setId(id);

		return Response.accepted(carService.updateCar(car)).build();
	}


	public Response deleteCar(Integer id) {

		return Response.accepted(carService.deleteCar(id)).build();
	}
	
}
