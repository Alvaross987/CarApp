package car.app.rest;

import javax.ejb.EJB;
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
	public Response getCar(@PathParam("carId") long id) {
		return Response.ok(carService.getCar(id)).build();
	}

	@POST
	public Response addCar(Car car) {

		carService.addCar(car);

		return Response.ok().build();
	}

	@PUT
	@Path("/{CarId}")
	public Response updateCar(@PathParam("CarId") int id, Car car) {
		car.setId(id);
		carService.updateCar(car);

		return Response.ok().build();
	}

	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") int id) {
		carService.deleteCar(id);

		return Response.ok().build();
	}
}
