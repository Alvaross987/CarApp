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

import car.app.entity.Country;
import car.app.services.CountryService;


@RunWith(MockitoJUnitRunner.class)
public class CountryResourceTest {

	@InjectMocks
	CountryResource countryResource;

	@Mock CountryService countryService;

	@Test
	public void whenGettingAllCountries_shouldReturnOKandCountryCollection() {
		// Mocks
		final List<Country> countries = new ArrayList<>();
		final Country country = new Country("SPAIN");
		countries.add(country);
		Mockito.when(countryService.getAllCountries()).thenReturn(countries);

		// Method call
		final Response response = countryResource.getAllCountries(null);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(countries, response.getEntity());
	}

	
	
	@Test
	public void whenGettingCountry_shouldReturnOKandCountry() {
		// Mocks
		final Country country = new Country("SPAIN");
		final List<Country> countries = new ArrayList<Country>();
		countries.add(country);
		Mockito.when(countryService.getCountry(1)).thenReturn(country);

		// Method call
		final Response response = countryResource.getCountry(null, 1);

		// Assertions
		assertEquals(response.getStatus(), Status.OK.getStatusCode());
		assertEquals(countries, response.getEntity());
	}
	
	
	@Test
	public void whenAddingCountry_shouldReturnCREATEDandCountry() {
		// Mocks
		final Country country = new Country("SPAIN");
		Mockito.when(countryService.addCountry(country)).thenReturn(country);

		// Method call
		final Response response = countryResource.addCountry(null, country);

		// Assertions
		assertEquals(response.getStatus(), Status.CREATED.getStatusCode());
		assertEquals(country, response.getEntity());
	}
	
	@Test
	public void whenUpdatingCountry_shouldReturnACCEPTEDandCountry() {
		// Mocks
		final Country country = new Country("SPAIN");
		Mockito.when(countryService.updateCountry(country)).thenReturn(country);

		// Method call
		final Response response = countryResource.updateCountry(null, 1,country);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(country, response.getEntity());
	}
	
	
	@Test
	public void whenDeletingCountry_shouldReturnACCEPTEDandCountry() {
		// Mocks
		final Country country = new Country("SPAIN");
		Mockito.when(countryService.deleteCountry(1)).thenReturn(country);

		// Method call
		final Response response = countryResource.deleteCountry(null, 1);

		// Assertions
		assertEquals(response.getStatus(), Status.ACCEPTED.getStatusCode());
		assertEquals(country, response.getEntity());
	}

}
