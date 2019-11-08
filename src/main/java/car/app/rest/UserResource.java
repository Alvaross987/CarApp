package car.app.rest;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import car.app.entity.User;
import car.app.services.UserService;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	@EJB(name = "userService")
	UserService userService;

	@Path("/login")
	@POST
	public Response login(User user) {
		boolean status = userService.login(user.getUsername(), user.getPassword());
		if(status) {
			String username = user.getUsername();
			return Response.status(Status.ACCEPTED).entity(userService.generateToken(username)).build();
		}
		
		return Response.status(Status.UNAUTHORIZED).build();

	}
	
	@GET
	public Response val() {
		return Response.status(Status.OK).entity(userService.getAllUsers()).build();
	}
	
	@POST
	public Response addUser(@Valid User user) {
		return Response.status(Status.CREATED).entity(userService.addUser(user)).build();
	}
}
