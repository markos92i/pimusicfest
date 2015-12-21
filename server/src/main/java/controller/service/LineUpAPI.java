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
import model.persistence.LineUpDAO;

@Path("/lineup")
public class LineUpAPI {
	@GET
	@Produces("application/json")
	public Response lineupList() {
		Response r;
		LineUpDAO lineupDAO = new LineUpDAO();
		try {
			JsonNode lineup = lineupDAO.readAll();
			if (lineup.size()>0) { r = Response.status(Response.Status.OK).entity(lineup).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@PUT
	@Path("create")
	@Consumes("application/json")
	@RolesAllowed({"admin", "staff"})
	public Response lineupCreate(JsonNode lineupJSON) {
		Response r;
		LineUpDAO lineupDAO = new LineUpDAO();
		try {
			lineupDAO.create(lineupJSON);
			r = Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@GET
	@Path("read/{_id}")
	@Produces("application/json")
	public Response lineupRead(@PathParam("_id") String _id) {
		Response r;
		LineUpDAO lineupDAO = new LineUpDAO();
		try {
			if(lineupDAO.exist("_id")) { r = Response.status(Response.Status.OK).entity(lineupDAO.read(_id)).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@PUT
	@Path("/update")
	@Consumes("application/json")
	@RolesAllowed({"admin", "staff"})
	public Response lineupUpdate(JsonNode lineupJSON) {
		Response r;
		LineUpDAO lineupDAO = new LineUpDAO();
		try {
			if(lineupDAO.update(lineupJSON)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@DELETE
	@Path("/delete/{_id}")
	@RolesAllowed({"admin", "staff"})
	public Response lineupDelete(@PathParam("_id") String _id) {
		Response r;
		LineUpDAO lineupDAO = new LineUpDAO();
		try {
			if(lineupDAO.delete(_id)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

}
