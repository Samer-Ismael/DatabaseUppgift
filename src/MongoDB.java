import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
public class MongoDB {

    private String DBName;
    private String server;
    private String port;

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

        String url = "mongodb://" + server + ":" + port + "/" + DBName;



    }

    public String getDBName() {
        return DBName;
    }
    public void setDBName(String DBName) {
        this.DBName = DBName;
    }
    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
}
