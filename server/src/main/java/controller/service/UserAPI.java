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
import model.persistence.UserDAO;

@Path("/user")
public class UserAPI {
	@GET
	@Produces("application/json")
	@RolesAllowed({"admin"})
	public Response userList() {
		Response r;
		UserDAO userDAO = new UserDAO();
		try {
			JsonNode user = userDAO.readAll();
			if (user.size()>0) { r = Response.status(Response.Status.OK).entity(user).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@PUT
	@Path("/create")
	@Consumes("application/json")
	@RolesAllowed({"admin"})
	public Response userCreate(JsonNode userJSON) {
		Response r;
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.create(userJSON);
			r = Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@GET
	@Path("/read/{_id}")
	@Produces("application/json")
	@RolesAllowed({"admin"})
	public Response userRead(@PathParam("_id") String _id) {
		Response r;
		UserDAO userDAO = new UserDAO();
		try {
			if(userDAO.exist("_id")) {
				JsonNode user = userDAO.read(_id);
				r = Response.status(Response.Status.OK).entity(user).build();
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
	@RolesAllowed({"admin"})
	public Response userUpdate(JsonNode userJSON) {
		Response r;
		UserDAO userDAO = new UserDAO();
		try {
			if(userDAO.update(userJSON)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

	@DELETE
	@Path("/delete/{_id}")
	@RolesAllowed({"admin"})
	public Response userDelete(@PathParam("_id") String _id) {
		Response r;
		UserDAO userDAO = new UserDAO();
		try {
			if(userDAO.delete(_id)) { r = Response.status(Response.Status.OK).build(); }
			else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		} catch (Exception e) {
			r = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return r;
	}

}
