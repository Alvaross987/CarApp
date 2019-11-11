package car.app.rest;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import car.app.entity.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

	
public interface CarResourceI {

	@GET
	@Operation(summary = "Get all Cars", description = "Get all Cars")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Cars", operationId = "getCars") }, responseCode = "200", description = "cars getted")
	@Tag(name = "/cars")
	public Response getCars(@Context HttpHeaders httpHeaders);

	@GET
	@Path("/{carId}")
	@Operation(summary = "Get specific Cars", description = "Get specific Car")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Car", operationId = "getCar", parameters = @LinkParameter(name = "CarId", expression = "$request.query.carId")) }, responseCode = "200", description = "Car getted")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "Car not found")
	@Tag(name = "/cars/id")
	public Response getCar(@Context HttpHeaders httpHeaders,
			@Parameter(description = "The Id of the Car that needs to be found", required = true) @PathParam("carId") Integer id);

	@POST
	@Operation(summary = "Add a new Car", description = "Add a new Car")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Car", operationId = "addCar") }, responseCode = "201", description = "New Car Added")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/cars")
	public Response addCar(@Context HttpHeaders httpHeaders, @RequestBody(description = "Created car object", required = true) @Valid Car car);

	@PUT
	@Path("/{CarId}")
	@Operation(summary = "Modify a Car", description = "Modify a Car")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Car", operationId = "updateCar", parameters = @LinkParameter(name = "CarId", expression = "$request.query.carId")) }, responseCode = "202", description = "Car Modified")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "Car not found")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/cars/id")
	public Response updateCar(@Context HttpHeaders httpHeaders,
			@Parameter(description = "The Id of the Car that needs to be modified", required = true) @PathParam("CarId") Integer id,
			@RequestBody(description = "Modified car object", required = true) @Valid Car car);

	@DELETE
	@Path("/{carId}")
	@Operation(summary = "Delete a Car", description = "Delete a Car")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Car", operationId = "deleteCar", parameters = @LinkParameter(name = "CarId", expression = "$request.query.carId")) }, responseCode = "202", description = "Car Deleted")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "Car not found")
	@Tag(name = "/cars/id")
	public Response deleteCar(@Context HttpHeaders httpHeaders,
			@Parameter(description = "The Id of the Car that needs to be deleted", required = true) @PathParam("carId") Integer id);

}
