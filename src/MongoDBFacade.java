import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
public class MongoDBFacade {

    MongoClient client;
    MongoDatabase db;
    MongoCollection<Document> collection;
    String conn = "mongodb://localhost:27017";

    String collectionName;
    String DBName;

    public MongoDBFacade(String DBName, String collectionName, String conn) {
        this.DBName = DBName;
        this.collectionName = collectionName;
        this.conn = conn;

    }
    public MongoDBFacade(){

        //connect();
    }

    public void insertOne(Document doc){
        collection.insertOne(doc);
    }
    public void find () {

        Document doc = new Document("name", "John");
        FindIterable<Document> result = collection.find(doc);

        ArrayList<Document> list = new ArrayList<>();
        for (Document d : result) {

        }
    }
/*
    public void Connect(){
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(conn))
                .serverApi(serverApi)
                .build();

        client = MongoClients.create(settings);
        db = client.getDatabase(DBName);
        collection = db.getCollection(collectionName);
    }

 */
}
