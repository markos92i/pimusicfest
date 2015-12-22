package controller.client;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;

public class SecureConnector {
    private static final String TRUSTORE_CLIENT_FILE = "./truststore_client";
    private static final String TRUSTORE_CLIENT_PWD = "asdfgh";
    private static final String KEYSTORE_CLIENT_FILE = "./keystore_client";
    private static final String KEYSTORE_CLIENT_PWD = "asdfgh";

	private static SecureConnector instance;
	//private final static String server = "http://markcloud.dynamic-dns.net:8081/";
	private final static String secureserver = "https://markcloud.dynamic-dns.net:8443/";
	private static Client client;
	private static String token;
	
	private SecureConnector() { }
	
    public static SecureConnector getInstance() {
        if (instance == null) {
        	instance = new SecureConnector();
        	ClientConfig clientConfig = new ClientConfig();
    		//clientConfig.register(Filter.class);
			try {
				client = ClientBuilder.newBuilder().withConfig(clientConfig).sslContext(getSSLContext().createSSLContext()).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
		return instance;
    }
    	
    public static SslConfigurator getSSLContext() throws NoSuchAlgorithmException, KeyManagementException { 
        SslConfigurator sslConfig = SslConfigurator.newInstance()
                .trustStoreFile(TRUSTORE_CLIENT_FILE)
                .trustStorePassword(TRUSTORE_CLIENT_PWD)
                .keyStoreFile(KEYSTORE_CLIENT_FILE)
                .keyPassword(KEYSTORE_CLIENT_PWD);

        return sslConfig;
    }

    public void setToken(String auth) {
    	token = auth;
    }
    
	public Builder request(WebTarget target) {
	    Builder builder = target.request()
	    		.header("Content-Type", "application/json;charset=UTF-8")
	    	    .header("Accept-Charset", "UTF-8")
	    	    .header("Accept", "application/json")
		    	.header("Authorization", token);
		return builder;
	}
	
	public Response get(String path) {
		WebTarget target = client.target(secureserver).path(path);
	    Builder builder = request(target);
	    Response response = builder.get(Response.class);
		return response;
	}
	
	public Response put(String path, Object object) {
		WebTarget target = client.target(secureserver).path(path);
	    Builder builder = request(target);
	    Response response = builder.put(Entity.json(object), Response.class);
		return response;
	}
	
	public Response post(String path, Object object) {
		WebTarget target = client.target(secureserver).path(path);
	    Builder builder = request(target);
	    Response response = builder.post(Entity.json(object), Response.class);
		return response;
	}

	public Response delete(String path) {
		WebTarget target = client.target(secureserver).path(path);
	    Builder builder = request(target);
	    Response response = builder.delete(Response.class);
		return response;
	}

}
