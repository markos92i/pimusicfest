package controller.service.filters;

import java.io.IOException;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.User;
import model.authentication.Authentication;

public class SecurityFilter implements ContainerRequestFilter {
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private ResourceInfo resourceInfo;

	public void filter(ContainerRequestContext requestContext) throws IOException {		
        User user = authenticate(requestContext);

        boolean rolesOnMethod = resourceInfo.getResourceMethod().isAnnotationPresent(RolesAllowed.class);
		boolean rolesOnClass = resourceInfo.getResourceClass().isAnnotationPresent(RolesAllowed.class);
		boolean permitAllOnMethod = resourceInfo.getResourceMethod().isAnnotationPresent(PermitAll.class);

		boolean loginRequired = rolesOnMethod || (rolesOnClass && !permitAllOnMethod);

		if (loginRequired && user == null) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		} else if (loginRequired) {
			String[] allowedRoles;
			if (rolesOnMethod) { allowedRoles = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class).value(); }
			else { allowedRoles = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class).value(); }
			
			if (!roleMatch(user.getRole(), allowedRoles)) { requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build()); }
		}

	}
	
	private User authenticate(ContainerRequestContext requestContext) {
		String token = requestContext.getHeaderString("Authorization");
		User user = Authentication.getUser(token);
        return user;
    }

	private boolean roleMatch(String userRole, String[] roles) {
		boolean match = false;
		for (int i=0; i<roles.length; i++) {
			if (roles[i].equals(userRole)) { match = true; }
		}
		return match;
	}

}