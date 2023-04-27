import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
public class MongoDB {

    private String DBName;
    private String server;
    private String port;

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
    // Make connection to MongoDB
    private void connect() {
        String uri = "mongodb://" + server + ":" + port;



    }
    // CRUD methods
    public void addToDB() {


    }
    public void readFromDB() {


    }
    public void updateDB() {


    }
    public void deleteFromDB() {


    }




}
