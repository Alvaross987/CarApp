package car.app.rest;

import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
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
public class UserResource implements UserResourceI {
	
	@EJB(name = "userService")
	UserService userService;
	


	public Response addUser(@Valid User user) {
		return Response.status(Status.CREATED).entity(userService.addUser(user)).build();
	}


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
	
	@Interceptors(AdminFilter.class)
	public Response allUsers(HttpHeaders httpHeader) {
		return Response.status(Status.OK).entity(userService.getAllUsers()).build();
	}

	@Interceptors(AdminFilter.class)
	public Response giveAdmin(@Context HttpHeaders httpHeaders, @PathParam("id") Integer id) {
		return Response.status(Status.OK).entity(userService.giveAdmin(id)).build();
	}
}
