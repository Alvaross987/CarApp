package car.app.rest;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import car.app.entity.User;
import car.app.filter.AdminFilter;
import car.app.services.UserService;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	@EJB(name = "userService")
	UserService userService;
	
	
@POST
	public Response addUser(@Valid User user) {
		return Response.status(Status.CREATED).entity(userService.addUser(user)).build();
	}


	@Path("/login")
	@POST
	public Response login(@Context HttpHeaders httpHeaders, User user) {
		boolean status = userService.login(user.getUsername(), user.getPassword());
		if(status) {
			String username = user.getUsername();
			String[] token = new String[1];
			token[0] = userService.generateToken(username);
			
			return Response.status(Status.ACCEPTED).entity(token).build();
		}
		
		return Response.status(Status.UNAUTHORIZED).build();

	}
	
	
	@GET
	@Interceptors(AdminFilter.class)
	public Response allUsers() {
		return Response.status(Status.OK).entity(userService.getAllUsers()).build();
	}
	
	@Path("/admin/{id}")
	@PUT
	@Interceptors(AdminFilter.class)
	public Response giveAdmin(@Context HttpHeaders httpHeaders, @PathParam("id") Integer id) {
		return Response.status(Status.OK).entity(userService.giveAdmin(id)).build();
	}
}
