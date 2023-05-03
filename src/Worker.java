import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class Worker extends Person {

    private int workerNumber;
    private String id;


    public Worker(String name, String address, int age, int workerNumber) {
        super(name, address, age);
        this.workerNumber = workerNumber;
    }

    public Worker(String name, String address, int age, int workerNumber, String id) {
        super(name, address, age);
        this.workerNumber = workerNumber;
        this.id = id;
    }


    public int getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }


    // Methods from the abstract class Person
    @Override
    public void addToDB(MongoCollection<Document> collection, Person worker) {
        Document newWorker = new Document(worker.toDoc());
        try {
            var find = collection.find(newWorker);
            if (find.first() != null) {
                System.out.println("Worker already exists");
            } else {
                collection.insertOne(newWorker);
                System.out.println("Worker added");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Worker already exists");
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
        } catch (Exception e) {
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
        ArrayList<Worker> workerList = new ArrayList<>();
        for (Document worker : workers) {
            workerList.add((Worker) fromDoc(worker));
        }
        if (workerList.isEmpty()) {
            System.out.println("No workers found");
        } else {
            for (Worker worker : workerList) {
                worker.print();
                System.out.println("--------------------");
            }
        }
    }
    @Override
    public Person fromDoc(Document doc) {
        if (doc == null) {
            return new Worker("", "", 0, 0);
        }
        return new Worker(
                doc.getString("name"),
                doc.getString("address"),
                doc.getInteger("age"),
                doc.getInteger("workerNumber"));
    }
    @Override
    public Document toDoc() {
        return new Document("name", getName())
                .append("address", getAddress())
                .append("age", getAge())
                .append("workerNumber", getWorkerNumber());
    }

    @Override
    public void print() {
        System.out.println("Worker:");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Age: " + getAge());
        System.out.println("Worker number: " + getWorkerNumber());
    }
}