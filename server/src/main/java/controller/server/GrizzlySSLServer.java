package controller.server;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import controller.service.filters.CORSFilter;
import controller.service.filters.SecurityFilter;

public class GrizzlySSLServer {
	
    private static final String KEYSTORE_SERVER_FILE = "./keystore_server";
    private static final String KEYSTORE_SERVER_PWD = "asdfgh";
    private static final String TRUSTORE_SERVER_FILE = "./truststore_server";
    private static final String TRUSTORE_SERVER_PWD = "asdfgh";

    private static HttpServer webServer;

    public static URI getBaseURI() {
        return UriBuilder.fromUri("https://0.0.0.0/").port(8443).build();
    }

    public static void start() {
        Logger.getLogger("org.glassfish.grizzly.http.server").setLevel(Level.OFF); 

        ResourceConfig rc = new ResourceConfig();
        rc.packages("controller.service");
        rc.registerClasses(CORSFilter.class, SecurityFilter.class);

        // Grizzly ssl configuration
        SSLContextConfigurator sslContext = new SSLContextConfigurator();
        
        // set up security context
        sslContext.setKeyStoreFile(KEYSTORE_SERVER_FILE); // contains server keypair (jks?)
        sslContext.setKeyStorePass(KEYSTORE_SERVER_PWD);
        sslContext.setTrustStoreFile(TRUSTORE_SERVER_FILE); // contains client certificate
        sslContext.setTrustStorePass(TRUSTORE_SERVER_PWD);

        try {
            webServer = GrizzlyHttpServerFactory.createHttpServer(
                    getBaseURI(),
                    rc,
                    true,
                    new SSLEngineConfigurator(sslContext).setClientMode(false).setNeedClientAuth(true)
            );
            webServer.start();
            System.out.println("[i] Secure Server started");
            System.out.println("[i] WADL: " + getBaseURI() + "application.wadl");
        } catch (Exception e) {
            System.out.println("[e] " + e.getMessage());
        }
    }
    
    public static void stop() {
        webServer.shutdownNow();
		System.out.println("[i] Secure Server stopped");
    }

    
}