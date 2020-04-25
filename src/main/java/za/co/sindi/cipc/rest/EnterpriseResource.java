package za.co.sindi.cipc.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import za.co.sindi.cipc.rest.model.EnterpriseDetails;
import za.co.sindi.cipc.rest.model.EnterpriseNameSearchResult;
import za.co.sindi.cipc.rest.service.EnterpriseService;

@Path("/enterprise")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class EnterpriseResource {
	
	@Inject
	private EnterpriseService enterpriseService;

	@GET
	@Path("/{enterprise-number}") ///{enterprise-number:[a-zA-Z][0-9]{10}}
	@APIResponse(
	        responseCode = "200",
	        description = "The Enterprise Details object in respected media type.",
	        content = @Content(
	            schema = @Schema(
	                type = SchemaType.OBJECT,
	                implementation = EnterpriseDetails.class)))
	@Operation(
	        summary = "Get the enterprise details based on the provided CIPC enterprise number.",
	        description = "Returns the enterprise details as recorded on CPIC database.")
	public EnterpriseDetails getEnterpriseDetailsByEnterpriseNumber(
			@Parameter(
					description = "The CIPC Enterprise Number.",
					required = true,
					example = "K012345678, 2020/0909/02",
			schema = @Schema(type = SchemaType.STRING))
			@PathParam("enterprise-number") final String enterpriseNumber) {
		return enterpriseService.getEntepriseDetailsByEnterpriseNumber(enterpriseNumber);
	}
	
	@GET
	@Path("/search")
	@APIResponse(
	        responseCode = "200",
	        description = "The Enterprise Details object in respected media type.",
	        content = @Content(
	            schema = @Schema(
	                type = SchemaType.ARRAY,
	                implementation = EnterpriseNameSearchResult.class)))
	@Operation(
	        summary = "Get the enterprise name search resutls based on the provided CIPC enterprise name.",
	        description = "Returns the enterprise name search results as recorded on CPIC database.")
	public EnterpriseNameSearchResult[] getEnterpriseDetailsByEnterpriseName(
			@Parameter(
					description = "The CIPC Enterprise Name.",
					required = true,
					example = "<ENTER YOUR COMPANY NAME HERE>",
			schema = @Schema(type = SchemaType.STRING))
			@QueryParam("name") final String enterpriseName) {
		return enterpriseService.getEntepriseDetailsByEnterpriseName(enterpriseName);
	}
}