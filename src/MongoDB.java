import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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

    public void advancedSearchWorkerNumber(MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("workerNumber", pattern)
            ));
            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchCustomerNumber(MongoCollection<Document> customerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("customerNumber", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchAdress(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("adress", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchAge(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("age", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchByName(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {
        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("name", pattern)
            ));

            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchBananas(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("name", pattern),
                    new Document("adress", pattern),
                    new Document("email", pattern),
                    new Document("age", pattern),
                    new Document("customerNumber", pattern),
                    new Document("workerNumber", pattern)
            ));

            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchQuestions(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Search all over the database");
            System.out.println("2. Search for name");
            System.out.println("3. Search for address");
            System.out.println("4. Search for age");
            System.out.println("5. Search for customer number");
            System.out.println("6. Search for worker number");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("----------");
                advancedSearchBananas(customerCollection, workerCollection);
            }
            if (choice == 2) {
                System.out.println("----------");
                advancedSearchByName(customerCollection, workerCollection);
            }
            if (choice == 3) {
                System.out.println("----------");
                advancedSearchAdress(customerCollection, workerCollection);
            }
            if (choice == 4) {
                System.out.println("----------");
                advancedSearchAge(customerCollection, workerCollection);
            }
            if (choice == 5) {
                System.out.println("----------");
                advancedSearchCustomerNumber(customerCollection);
            }
            if (choice == 6) {
                System.out.println("----------");
                advancedSearchWorkerNumber(workerCollection);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }


}