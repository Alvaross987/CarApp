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

import car.app.entity.Brand;
import car.app.filter.AdminFilter;
import car.app.filter.AuthFilter;
import car.app.services.BrandService;

@Path("/brand")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Interceptors(AuthFilter.class)
public class BrandResource implements BrandResourceI {
	final Logger logger = Logger.getLogger(BrandResource.class);
	@EJB(name = "brandService")
	BrandService brandService;

	
	public Response getAllBrand(HttpHeaders httpHeaders) {
		return Response.status(Status.OK).entity(brandService.getAllBrand()).build();
	}

	
	public Response getBrand(HttpHeaders httpHeaders, Integer id) {
		Brand brand = brandService.getBrand(id);
		List<Brand> list = new ArrayList<Brand>();
		list.add(brand);
		Response result =  Response.status(Status.OK).entity(list).build();
		return result;
	}

	@Interceptors(AdminFilter.class)
	public Response addBrand(HttpHeaders httpHeaders, Brand brand) {

		Brand newBrand = brandService.addBrand(brand);
		return Response.status(Status.CREATED).entity(newBrand).build();
	}

	@Interceptors(AdminFilter.class)
	public Response updateBrand(HttpHeaders httpHeaders, Integer id, Brand brand) {

		brand.setId(id);

		return Response.status(Status.OK).entity(brandService.updateBrand(brand)).build();
	}

	@Interceptors(AdminFilter.class)
	public Response deleteBrand(HttpHeaders httpHeaders, Integer id) {

		return Response.status(Status.OK).entity(brandService.deleteBrand(id)).build();
	}

}
