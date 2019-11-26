package car.app.rest;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import car.app.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface UserResourceI {
	
	@POST
	@Operation(summary = "Add a new user", description = "Add a new user")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "user", operationId = "login") }, responseCode = "201", description = "New user Added")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/user")
	public Response addUser(@Valid User user);


	@Path("/login")
	@POST
	@Operation(summary = "Add a new user", description = "Add a new user")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "login", operationId = "login") }, responseCode = "201", description = "New country Added")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/country")
	public Response login(@Context HttpHeaders httpHeaders, User user);
	
	@GET
	@Operation(summary = "Get all users", description = "Get all users")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "user", operationId = "getAllUsers") }, responseCode = "200", description = "All users getted")
	@Tag(name = "/user")
	public Response allUsers(@Context HttpHeaders httpHeaders);
	
	@Path("/admin/{id}")
	@PUT
	@Operation(summary = "Give admin to a user", description = "Give admin to a user")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "user", operationId = "giveAdmin") }, responseCode = "201", description = "New user with admin")
	@Tag(name = "/user")
	public Response giveAdmin(@Context HttpHeaders httpHeaders, @PathParam("id") Integer id);
}
