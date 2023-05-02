import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Worker extends Person {

    private String workerNumber;

    public Worker(String name, String address, int age, String employeeNumber) {
        super(name, address, age);
        this.workerNumber = employeeNumber;
    }

    public String getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(String workerNumber) {
        this.workerNumber = workerNumber;
    }

    @Override
    public void addToDB(MongoCollection<Document> collection, Person worker) {

        if (worker instanceof Customer) {
            workerNumber = ((Customer) worker).getCustomerNumber();
        }
        BasicDBObject test = new BasicDBObject();
        if (collection.countDocuments(test) > 0) {
            System.out.println("Costumer already exists");
            return;
        }

        Document newWorker = new Document("name", worker.getName())
                .append("address", worker.getAddress())
                .append("age", worker.getAge())
                .append("employeeNumber", workerNumber);
        try {
            collection.insertOne(newWorker);
            System.out.println("Worker added");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void readFromDB(MongoCollection<Document> collection, String name) {

        FindIterable<Document> workers = collection.find(new Document("name", name));
        for (Document worker : workers) {
            System.out.println(worker.toJson());
        }
    }

    @Override
    public void updateDB(MongoCollection<Document> collection, Person worker) {

        Document updateWorker = new Document("name", worker.getName())
                .append("address", worker.getAddress())
                .append("age", worker.getAge())
                .append("employeeNumber", workerNumber);
        try {
            collection.updateOne(new Document("name", worker.getName()), new Document("$set", updateWorker));
            System.out.println("Worker updated");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFromDB(MongoCollection<Document> collection, String name) {

        Document deleteWorker = new Document("name", name);
        try {
            collection.deleteOne(deleteWorker);
            System.out.println("Worker deleted");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void allFromDB(MongoCollection<Document> collection) {
        FindIterable<Document> workers = collection.find();
        for (Document worker : workers) {
            System.out.println(worker.toJson());
        }
    }
}
