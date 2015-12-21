package controller.service;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.authentication.Authentication;
import model.utils.Base64Util;

@Path("/auth")
@PermitAll
public class AuthenticationAPI {
	@POST
	@Path("/signup")
	@Produces("application/json")
	public Response register(@HeaderParam("Authorization") String auth) {
		Response r;
		try {
			String[] authValues = Base64Util.Decode(auth).split(":");
			Authentication.register(authValues[0], authValues[1]);
			r = Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			r = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return r;
	}

	@GET
	@Path("/signin")
	@Produces("application/json")
	public Response login(@HeaderParam("Authorization") String auth) {
		Response r;
		String token = null;
		try {
			String[] authValues = Base64Util.Decode(auth).split(":");
			token = Authentication.login(authValues[0], authValues[1]);
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode result = mapper.createObjectNode();
			if (token.length()!=0) {
				result.put("access", true);
				result.put("token", token);
				r = Response.status(Response.Status.OK).entity(result).build();
			} else {
				result.put("access", false);
				result.put("token", token);
				r = Response.status(Response.Status.UNAUTHORIZED).entity(result).build();
			}
		} catch (Exception e) {
			r = Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

		return r;
	}

	@DELETE
	@Path("/signout")
	@Produces("application/json")
	public Response logout(@HeaderParam("Authorization") String token) {
		Response r;
		boolean logout = Authentication.logout(token);
		if (logout) { r = Response.status(Response.Status.OK).build(); }
		else { r = Response.status(Response.Status.NOT_FOUND).build(); }
		return r;
	}
}
