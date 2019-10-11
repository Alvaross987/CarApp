package car.app.rest;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import car.app.entity.Car;
import car.app.services.CarService;

@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {

	@EJB
	CarService carService;

	@GET
	public Response getCars() {
		return Response.status(Status.OK).entity(carService.getCars()).build();
	}

	@GET
	@Path("/{carId}")
	public Response getCar(@PathParam("carId") int id) {

		return Response.ok(carService.getCar(id)).build();
	}

	@POST
	public Response addCar(@Valid Car car) {

		Car newCar = carService.addCar(car);
		return Response.status(Status.CREATED).entity(newCar).build();
	}

	@PUT
	@Path("/{CarId}")
	public Response updateCar(@PathParam("CarId") int id, @Valid Car car) {

		car.setId(id);

		return Response.accepted(carService.updateCar(car)).build();
	}

	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") int id) {

		return Response.accepted(carService.deleteCar(id)).build();
	}
}
