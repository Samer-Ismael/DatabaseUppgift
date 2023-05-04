import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoDB {

    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private String collectionName;
    private MongoClient client;
    private final String DBName;
    private final String server;
    private final String port;

    private Person customer;
    private Person worker;


    // Constructor with default values
    public MongoDB(String DBName, String server, String port) {
        if (server.isEmpty()) {
            this.server = "localhost";
        } else {
            this.server = server;
        }
        if (port.isEmpty()) {
            this.port = "27017";
        } else {
            this.port = port;
        }
        if (DBName.isEmpty()) {
            this.DBName = "MongoDB";
        } else {
            this.DBName = DBName;
        }
        connect(); // Make connection when object is created
    }


    // Getters and setters for the class
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public MongoDatabase getDb() {
        return db;
    }

    // Method to connect to the database
    private void connect() {
        String connection = "mongodb://" + server + ":" + port;
        try {
            MongoClientURI clientURI = new MongoClientURI(connection);
            this.client = new MongoClient(clientURI);
            this.db = client.getDatabase(DBName);
        } catch (MongoException e) {
            System.out.println("Error!" + e.getMessage());
        }
    }

    // Method to create a collection in the database
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