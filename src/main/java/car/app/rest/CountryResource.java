package car.app.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import car.app.entity.Country;
import car.app.services.CountryService;;

@Stateless
@Path("/country")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryResource implements CountryResourceI{
	final Logger logger = Logger.getLogger(CountryResource.class);
	@EJB(name = "countryService	")
	CountryService countryService;

	
	public Response getAllCountries() {
		return Response.status(Status.OK).entity(countryService.getAllCountries()).build();
	}

	
	public Response getCountry(Integer id) {
		Country country = countryService.getCountry(id);
		List<Country> list = new ArrayList<Country>();
		list.add(country);
		Response result =  Response.status(Status.OK).entity(list).build();
		return result;
	}

	
	public Response addCountry(Country country) {

		Country newcountry = countryService.addCountry(country);
		return Response.status(Status.CREATED).entity(newcountry).build();
	}


	public Response updateCountry(Integer id, Country country) {

		country.setId(id);

		return Response.accepted(countryService.updateCountry(country)).build();
	}


	public Response deleteCountry(Integer id) {

		return Response.accepted(countryService.deleteCountry(id)).build();
	}
}
