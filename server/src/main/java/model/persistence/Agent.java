package model.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Agent {
    private static MongoClientURI uri  = new MongoClientURI("mongodb://pimusicfest:pimusicfest@ds038888.mongolab.com:38888/pimusicfest"); 
	private static MongoClient mongoClient = null;
	private static MongoDatabase database = null;

	private Agent() {}
	
    public static synchronized MongoDatabase getInstance() {
        if (database == null) {
            Logger.getLogger("org.mongodb.driver").setLevel(Level.OFF); 
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase("pimusicfest");
        }
        return database;
    }
    
}
