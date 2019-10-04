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

import com.google.gson.Gson;

import car.app.entity.Car;
import car.app.services.CarService;

@Path("/cars")

public class CarResource {
	
	@EJB
	CarService carService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	  public Response getCars() {
			
		String json = new Gson().toJson(carService.getCars());
		return Response.status(Status.OK).entity(json).build();
	  }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/car/{carId}")
	  public Response getCar(@PathParam("carId") long id) {
		String json = new Gson().toJson(carService.getCar(id));
		return Response.ok(json).build();
	  }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addCar")
	  public Response addCar(String car) {
		
		Car car2 = new Gson().fromJson(car, Car.class);
		String json = new Gson().toJson(carService.addCar(car2));
		
		return Response.ok(json).build();
	  }
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateCar/{CarId}")
	  public Response updateCar(@PathParam("CarId") int id, String car) {
		Car car2 = new Gson().fromJson(car, Car.class);
		car2.setId(id);
		String json = new Gson().toJson(carService.updateCar(car2));
		
		return Response.ok(json).build();
	  }
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteCar/{carId}")
	  public Response deleteCar(@PathParam("carId") int id) {
		String json = new Gson().toJson(carService.deleteCar(id));
		
		return Response.ok(json).build();
	  }
}
