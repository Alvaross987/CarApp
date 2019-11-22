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

import car.app.entity.Country;
import car.app.filter.AdminFilter;
import car.app.filter.AuthFilter;
import car.app.services.CountryService;;

@Stateless
@Path("/country")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Interceptors(AuthFilter.class)
public class CountryResource implements CountryResourceI{
	final Logger logger = Logger.getLogger(CountryResource.class);
	@EJB(name = "countryService	")
	CountryService countryService;

	
	public Response getAllCountries(HttpHeaders httpHeaders) {
		return Response.status(Status.OK).entity(countryService.getAllCountries()).build();
	}

	
	public Response getCountry(HttpHeaders httpHeaders, Integer id) {
		Country country = countryService.getCountry(id);
		List<Country> list = new ArrayList<Country>();
		list.add(country);
		Response result =  Response.status(Status.OK).entity(list).build();
		return result;
	}

	@Interceptors(AdminFilter.class)
	public Response addCountry(HttpHeaders httpHeaders, Country country) {

		Country newcountry = countryService.addCountry(country);
		return Response.status(Status.CREATED).entity(newcountry).build();
	}

	@Interceptors(AdminFilter.class)
	public Response updateCountry(HttpHeaders httpHeaders, Integer id, Country country) {

		country.setId(id);

		return Response.accepted(countryService.updateCountry(country)).build();
	}

	@Interceptors(AdminFilter.class)
	public Response deleteCountry(HttpHeaders httpHeaders, Integer id) {

		return Response.accepted(countryService.deleteCountry(id)).build();
	}
}
