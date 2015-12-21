package controller.server;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import controller.service.filters.CORSFilter;
import controller.service.filters.SecurityFilter;

public class GrizzlyServer {
	
    private static HttpServer webServer;

    public static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/").port(8081).build();
    }

	public static void start() {
        Logger.getLogger("org.glassfish.grizzly.http.server").setLevel(Level.OFF); 

        ResourceConfig rc = new ResourceConfig();
        rc.packages("controller.service");
        rc.registerClasses(CORSFilter.class, SecurityFilter.class);
        
        try {
            webServer = GrizzlyHttpServerFactory.createHttpServer(getBaseURI(), rc);
            webServer.start();
            System.out.println("[i] Server started");
            System.out.println("[i] WADL: " + getBaseURI() + "application.wadl");
        } catch (Exception e) {
            System.out.println("[e] " + e.getMessage());
        }
    }
    
    public static void stop() {
        webServer.shutdownNow();
		System.out.println("[i] Server stopped");
    }

    
}
