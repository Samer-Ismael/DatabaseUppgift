import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
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


    // Methods from the abstract class Person
    @Override
    public void addToDB(MongoCollection<Document> collection, Person worker) {

        if (worker instanceof Worker) {
            workerNumber = ((Worker) worker).getWorkerNumber();
        }

        Document newWorker = new Document("name", worker.getName())
                .append("address", worker.getAddress())
                .append("age", worker.getAge())
                .append("workerNumber", workerNumber);
        try {
            collection.createIndex(new Document("name", 1), new IndexOptions().unique(true));
            collection.insertOne(newWorker);
            System.out.println("Worker added");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void readFromDB(MongoCollection<Document> collection, String name) {

        try {
            FindIterable<Document> workers = collection.find(new Document("name", name));
            if (collection.countDocuments() == 0) {
                System.out.println("No workers found");
                return;
            }
            for (Document worker : workers) {
                System.out.println(worker.toJson());
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Worker not found");
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