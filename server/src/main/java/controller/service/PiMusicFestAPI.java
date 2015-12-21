package controller.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api")
public class PiMusicFestAPI {
  
	/*
	Create = PUT    Idempodente;    S�lo si va a enviar todo el contenido del recurso especificado (en la URL) 
	Create = POST   No Idempodente; Si va a enviar un comando que mediante alg�n algoritmo del lado del servidor (Enfocado a consultas tales como busquedas...)
	Read   = GET    Idempodente;
	Update = PUT    Idempodente;    S�lo si va a actualizar el contenido completo del recurso especificado
	Update = POST   No Idempodente; Si usted est� solicitando el servidor para actualizar uno o m�s subordinados del recurso especificado (Enfocado a operaciones multiples...)
	Delete = DELETE Idempodente;
	 */
	 
	@GET
	@Path("/about")
	@Produces("text/html")
	public Response about() {
		return Response.status(200).entity("PiMusicFest's API v1.0").build();
	}
	
	@GET
	@Path("/xml")
	@Produces("application/xml")
	public Response xml() {
		return Response.status(200).entity("<info>PiMusicFest's API v1.0</info>").build();
	}
 		
}