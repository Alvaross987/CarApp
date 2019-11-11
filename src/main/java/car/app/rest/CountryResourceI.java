package car.app.rest;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import car.app.entity.Country;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface CountryResourceI {
	@GET
	@Operation(summary = "Get all countries", description = "Get all countries")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "country", operationId = "getAllcountries") }, responseCode = "200", description = "All countries getted")
	@Tag(name = "/country")
	public Response getAllCountries();

	@GET
	@Path("/{countryId}")
	@Operation(summary = "Get specific countrys", description = "Get specific country")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "country", operationId = "getCountry", parameters = @LinkParameter(name = "countryId", expression = "$request.query.countryId")) }, responseCode = "200", description = "country getted")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "country not found")
	@Tag(name = "/country/id")
	public Response getCountry(
			@Parameter(description = "The Id of the country that needs to be found", required = true) @PathParam("countryId") Integer id);

	@POST
	@Operation(summary = "Add a new country", description = "Add a new country")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "country", operationId = "addCountry") }, responseCode = "201", description = "New country Added")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/country")
	public Response addCountry(@RequestBody(description = "Created country object", required = true) @Valid Country country);

	@PUT
	@Path("/{countryId}")
	@Operation(summary = "Modify a country", description = "Modify a country")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "country", operationId = "updateCountry", parameters = @LinkParameter(name = "countryId", expression = "$request.query.countryId")) }, responseCode = "202", description = "country Modified")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "country not found")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/country/id")
	public Response updateCountry(
			@Parameter(description = "The Id of the country that needs to be modified", required = true) @PathParam("countryId") Integer id,
			@RequestBody(description = "Modified country object", required = true) @Valid Country country);

	@DELETE
	@Path("/{countryId}")
	@Operation(summary = "Delete a country", description = "Delete a country")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "country", operationId = "deleteCountry", parameters = @LinkParameter(name = "countryId", expression = "$request.query.countryId")) }, responseCode = "202", description = "country Deleted")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "country not found")
	@Tag(name = "/country")
	public Response deleteCountry(
			@Parameter(description = "The Id of the country that needs to be deleted", required = true) @PathParam("countryId") Integer id);

}
