package car.app.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import car.app.entity.Brand;
import car.app.services.BrandService;

@RunWith(MockitoJUnitRunner.class)
public class BrandResourceTest {
	
	@InjectMocks
	BrandResource brandResource;

	@Mock BrandService brandService;

	@Test
	public void whenGettingAllBrands_shouldReturnOKandBrandCollection() {
		// Mocks
		final List<Brand> brands = new ArrayList<>();
		final Brand brand = new Brand("BMW");
		brands.add(brand);
		Mockito.when(brandService.getAllBrand()).thenReturn(brands);

		// Method call
		final Response response = brandResource.getAllBrand(null);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(brands, response.getEntity());
	}

	
	
	@Test
	public void whenGettingBrand_shouldReturnOKandBrand() {
		// Mocks
		final Brand brand = new Brand("Brand");
		final List<Brand> lc = new ArrayList<Brand>();
		lc.add(brand);
		Mockito.when(brandService.getBrand(1)).thenReturn(brand);

		// Method call
		final Response response = brandResource.getBrand(null, 1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(lc, response.getEntity());
	}
	
	
	@Test
	public void whenAddingCar_shouldReturnCREATEDandCar() {
		// Mocks
		final Brand brand = new Brand("Brand");
		Mockito.when(brandService.addBrand(brand)).thenReturn(brand);

		// Method call
		final Response response = brandResource.addBrand(null, brand);

		// Assertions
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		assertEquals(brand, response.getEntity());
	}
	
	@Test
	public void whenUpdatingCar_shouldReturnOKandCar() {
		// Mocks
		final Brand brand = new Brand("Brand");
		Mockito.when(brandService.updateBrand(brand)).thenReturn(brand);

		// Method call
		final Response response = brandResource.updateBrand(null, 1,brand);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(brand, response.getEntity());
	}
	
	
	@Test
	public void whenDeletingCar_shouldReturnOKandCar() {
		// Mocks
		final Brand brand = new Brand("Brand");
		Mockito.when(brandService.deleteBrand(1)).thenReturn(brand);

		// Method call
		final Response response = brandResource.deleteBrand(null, 1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(brand, response.getEntity());
	}

}
