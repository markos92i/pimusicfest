package controller.service;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;

import model.persistence.SponsorDAO;

@Path("/sponsor")
public class SponsorAPI {
	@GET
	@Produces("application/json")
	public Response sponsorList() {
		Response r;
		SponsorDAO sponsorDAO = new SponsorDAO();
		try {
			JsonNode sponsor = sponsorDAO.readAll();
			if (sponsor.size()>0) { r = Response.status(Response.Status.OK).entity(sponsor).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@PUT
	@Path("/create")
	@Consumes("application/json")
	@RolesAllowed({"admin", "staff"})
	public Response sponsorCreate(JsonNode sponsorJSON) {
		Response r;
		SponsorDAO sponsorDAO = new SponsorDAO();
		try {
			sponsorDAO.create(sponsorJSON);
			r = Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@GET
	@Path("/read/{_id}")
	@Produces("application/json")
	@RolesAllowed({"admin", "staff"})
	public Response sponsorRead(@PathParam("_id") String _id) {
		Response r;
		SponsorDAO sponsorDAO = new SponsorDAO();
		try {
			if (sponsorDAO.exist(_id)) {
				JsonNode sponsor = sponsorDAO.read(_id);
				r = Response.status(Response.Status.OK).entity(sponsor).build();
			} else {
				r = Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	@RolesAllowed({"admin", "staff"})
	public Response sponsorUpdate(JsonNode sponsorJSON) {
		Response r;
		SponsorDAO sponsorDAO = new SponsorDAO();
		try {
			if(sponsorDAO.update(sponsorJSON)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@DELETE
	@Path("/delete/{_id}")
	@RolesAllowed({"admin", "staff"})
	public Response sponsorDelete(@PathParam("_id") String _id) {
		Response r;
		SponsorDAO sponsorDAO = new SponsorDAO();
		try {
			if(sponsorDAO.delete(_id)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

}
