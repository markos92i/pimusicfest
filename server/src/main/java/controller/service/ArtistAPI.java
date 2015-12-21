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
import model.persistence.ArtistDAO;

@Path("/artist")
public class ArtistAPI {
	@GET
	@RolesAllowed({"admin", "staff"})
	public Response artistList() {
		Response r;
		ArtistDAO artistDAO = new ArtistDAO();
		try {
			JsonNode artist = artistDAO.readAll();
			if (artist.size()>0) { r = Response.status(Response.Status.OK).entity(artist).build(); }
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
	public Response artistCreate(JsonNode artistJSON) {
		Response r;
		ArtistDAO artistDAO = new ArtistDAO();
		try {
			artistDAO.create(artistJSON);
			r = Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@GET
	@Path("/read/{_id}")
	@Produces("application/json")
	public Response artistRead(@PathParam("_id") String _id) {
		Response r;
		ArtistDAO artistDAO = new ArtistDAO();
		try {
			if(artistDAO.exist(_id)) {
				JsonNode artist = artistDAO.read(_id);
				r = Response.status(Response.Status.OK).entity(artist).build();
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
	public Response artistUpdate(JsonNode artistJSON) {
		Response r;
		ArtistDAO artistDAO = new ArtistDAO();
		try {
			if(artistDAO.update(artistJSON)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@DELETE
	@Path("/delete/{_id}")
	@RolesAllowed({"admin", "staff"})
	public Response artistDelete(@PathParam("_id") String _id) {
		Response r;
		ArtistDAO artistDAO = new ArtistDAO();
		try {
			if(artistDAO.delete(_id)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

}
