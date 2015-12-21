package controller.service.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
 
public class CORSFilter implements ContainerResponseFilter {
	
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "API, GET, POST, DELETE, PUT, OPTIONS");
		responseContext.getHeaders().add("Access-Control-Max-Age", "151200");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
		String requestHeader = requestContext.getHeaderString("Access-Control-Request-Headers");
		if (null != requestHeader && !requestHeader.equals(null)) {
			responseContext.getHeaders().add("Access-Control-Allow-Headers", requestHeader);
		}		
	}
	
}