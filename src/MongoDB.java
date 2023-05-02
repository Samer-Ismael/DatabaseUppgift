import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {

    private MongoDatabase db;
    private MongoClient client;
    private String DBName;
    private String server;
    private String port;
    private MongoCollection<Document> collection;

    // Constructor will set default values if user does not provide them
    public MongoDB(String DBName, String server, String port) {
        if (server.isEmpty()) {
            this.server = "localhost";
        }else {
            this.server = server;
        }
        if (port.isEmpty()) {
            this.port = "27017";
        }else {
            this.port = port;
        }
        if (DBName.isEmpty()) {
            this.DBName = "MongoDB";
        }else {
            this.DBName = DBName;
        }
        connect(); // Make connection when object is created
    }
    // Make connection to MongoDB, private method because we only use it in this class
    private void connect() {
        String connection = "mongodb://" + server + ":" + port;
        try {
            MongoClientURI clientURI = new MongoClientURI(connection);
            this.client = new MongoClient(clientURI);
            this.db = client.getDatabase(DBName);
            System.out.println("Connected to MongoDB");
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }
    public MongoDatabase getDb() {
        return db;
    }
    public MongoCollection<Document> createCollection(String collectionName) {
        try {
            db.createCollection(collectionName);
            System.out.println("Collection " + collectionName + " created successfully");
        } catch (Exception e) {
            // if collection already exists, we do nothing so no one gets hurt :)
        }
        return this.collection = db.getCollection(collectionName);
    }
}
