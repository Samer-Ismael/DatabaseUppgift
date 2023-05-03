import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
public class MongoDBFacade {

    // Jag försökte att koppla till databas på detta sätt men jag lyckades inte få det fungera.
    // Jag har kommenterat ut koden för att inte få error när jag kör programmet.
    // Problemet är på
    //        MongoClientSettings settings = MongoClientSettings.builder()
    //                .applyConnectionString(new ConnectionString(conn))
    //                .serverApi(serverApi)
    //                .build();
    //som vill inte fungera och har testat alla möjliga drivers.

    // Jag kopplar istället till local databasen för att få programmet att fungera.


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
