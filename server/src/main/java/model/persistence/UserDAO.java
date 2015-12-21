package model.persistence;

import java.io.IOException;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class UserDAO {
    private MongoCollection<Document> coll;
    
    public UserDAO() {
    	MongoDatabase database = Agent.getInstance();
        this.coll = database.getCollection("Users");
    }
	
    public JsonNode readAll() {
        FindIterable<Document> iterable = coll.find();
        MongoCursor<Document> cursor = iterable.iterator();
		ObjectMapper mapper = new ObjectMapper();
        ArrayNode list = mapper.createArrayNode();
        while (cursor.hasNext()) { list.add(cursor.next().toJson().toString()); }
        return list;
    }

	public void create(JsonNode artist) {
		Document doc = Document.parse(artist.toString());
        coll.insertOne(doc);
    }
 
    public JsonNode read(String _id) throws JsonProcessingException, IOException {
    	Document doc = new Document("_id", _id);
        FindIterable<Document> data = coll.find(doc);
		ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(data.first().toJson().toString());
    }

    public boolean update(JsonNode artist) {
    	Document doc = new Document("_id", artist.get("_id"));
        UpdateResult data = coll.updateOne(doc, Document.parse(artist.toString()));
        return data.getModifiedCount()>0;
    }
    
    public boolean delete(String _id) {
    	Document doc = new Document("_id", _id);
        DeleteResult data = coll.deleteOne(doc);
        return data.getDeletedCount()>0;
    }
    
    public boolean exist(String _id) {
    	Document doc = new Document("_id", _id);
        FindIterable<Document> data = coll.find(doc);
        return data.iterator().hasNext();
    }

}
