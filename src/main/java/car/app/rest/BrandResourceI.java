package car.app.rest;

import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import car.app.entity.Brand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface BrandResourceI {
	@GET
	@Operation(summary = "Get all brands", description = "Get all brands")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Brands", operationId = "getAllBrand") }, responseCode = "200", description = "brands getted")
	@Tag(name = "/brand")
	public Response getAllBrand();

	@GET
	@Path("/{brandId}")
	@Operation(summary = "Get specific brand", description = "Get specific brand")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "brand", operationId = "getBrand", parameters = @LinkParameter(name = "brandId", expression = "$request.query.brandId")) }, responseCode = "200", description = "brand getted")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "brand not found")
	@Tag(name = "/brand/id")
	public Response getBrand(
			@Parameter(description = "The Id of the Brand that needs to be found", required = true) @PathParam("brandId") Integer id);

	@POST
	@Operation(summary = "Add a new brand", description = "Add a new brand")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "brand", operationId = "addBrand") }, responseCode = "201", description = "New brand Added")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/brand")
	public Response addBrand(@RequestBody(description = "Created Brand object", required = true) @Valid Brand brand);

	@PUT
	@Path("/{brandId}")
	@Operation(summary = "Modify a Brand", description = "Modify a Brand")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Brand", operationId = "updateBrand", parameters = @LinkParameter(name = "BrandId", expression = "$request.query.brandId")) }, responseCode = "202", description = "Brand Modified")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "Brand not found")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "400", description = "Parameters cannot be null")
	@Tag(name = "/brand")
	public Response updateBrand(
			@Parameter(description = "The Id of the Brand that needs to be modified", required = true) @PathParam("brandId") Integer id,
			@RequestBody(description = "Modified Brand object", required = true) @Valid Brand brand);

	@DELETE
	@Path("/{brandId}")
	@Operation(summary = "Delete a Brand", description = "Delete a Brand")
	@ApiResponse(content = @Content(mediaType = "application/json"), links = {
			@Link(name = "Brand", operationId = "deleteBrand", parameters = @LinkParameter(name = "BrandId", expression = "$request.query.B"
					+ "brandId")) }, responseCode = "202", description = "Brand Deleted")
	@ApiResponse(content = @Content(mediaType = "application/json"), responseCode = "404", description = "Brand not found")
	@Tag(name = "/brand")
	public Response deleteBrand(
			@Parameter(description = "The Id of Brand that needs to be deleted", required = true) @PathParam("brandId") Integer id);
}
