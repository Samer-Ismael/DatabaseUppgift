import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {


        MongoDB mongoDB = new MongoDB("","","");
        Person customer = new Customer("John", "Street 1", 25, "123456789");
        Person worker = new Worker("Jane", "Street 2", 30, "987654321");

        MongoCollection<Document> customerCollection = mongoDB.createCollection(customer.getCollectionName());
        MongoCollection<Document> workerCollection = mongoDB.createCollection(worker.getCollectionName());

        customer.addToDB(customerCollection, customer);
        worker.addToDB(workerCollection, worker);

        customer.readFromDB(customerCollection, "John" );
        worker.readFromDB(workerCollection, "Jane");
        System.out.println("Added");
        System.out.println("-------------------------------------------------");

        customer.deleteFromDB(customerCollection, customer);
        worker.deleteFromDB(workerCollection, worker);

        customer.readFromDB(customerCollection, "John" );
        worker.readFromDB(workerCollection, "Jane");
        System.out.println("Deleted");
        System.out.println("-------------------------------------------------");

    }
}