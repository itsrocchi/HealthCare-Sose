package it.univaq.sose.doctorRecordService;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import it.univaq.sose.doctorRecordService.model.Doctor;

@Path("/drs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface DoctorRecord {
	
	@Operation( 
		       description = "Get the details of a specific doctor",
				responses = {
			       @ApiResponse(
			          description = "Return the details of a doctor as Json response",
			          content = {
			                 @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Doctor.class))
			          }
			       )
			    }
			 )	
	
	@GET
    @Path("/doctorData/{id}")
    public Response getDoctorById(@PathParam("id") String id);
	
	@Operation( 
		       description = "Get the list of doctor",
				responses = {
			       @ApiResponse(
			          description = "Return all the doctors as Json response",
			          content = {
			                 @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Doctor.class))
			          }
			       )
			    }
			 )
	
    @GET
    @Path("/doctorData")
    public Response getAllDoctors();
	
	@Operation( 
		       description = "Update of a doctor",
				responses = {
			       @ApiResponse(
			          description = "Return the updated doctor",
			          content = {
			                 @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = Doctor.class)))
			          }
			       )
			    }
			 )

    @PUT
    @Path("/doctorData/{id}")
    public Response updateDoctor(@PathParam("id") String id, Doctor doctor);

	@Operation( 
		       description = "Delete of a doctor",
				responses = {
			       @ApiResponse(
			          description = "Return an empty response",
			          content = {
			                 @Content(mediaType = MediaType.APPLICATION_JSON)
			          }
			       )
			    }
			 )
	
    @DELETE
    @Path("/doctorData/{id}")
    public Response deleteDoctor(@PathParam("id") String id);

	@Operation( 
		       description = "Create a new doctor",
				responses = {
			       @ApiResponse(
			          description = "Return the created doctor",
			          content = {
			                 @Content(mediaType = MediaType.APPLICATION_JSON)
			          }
			       )
			    }
			 )
	
    @POST
    @Path("/doctorData")
    public Response createDoctor(Doctor doctor);

}
