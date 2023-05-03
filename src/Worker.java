import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;

public class Worker extends Person {

    private String workerNumber;
    private String id;


    public Worker(String name, String address, String age, String employeeNumber) {
        super(name, address, age);
        this.workerNumber = employeeNumber;
    }

    public Worker(String name, String address, String age, String employeeNumber, String id) {
        super(name, address, age);
        this.workerNumber = employeeNumber;
        this.id = id;
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
    public Worker fromJSON(String json) {

        return new Worker(
                Document.parse(json).getString("name"),
                Document.parse(json).getString("address"),
                Document.parse(json).getString("age"),
                Document.parse(json).getString("workerNumber")
                );
    }
    @Override
    public String toJSON() {
        Document worker = new Document("name", getName())
                .append("address", getAddress())
                .append("age", getAge())
                .append("workerNumber", getWorkerNumber());
        return worker.toJson();
    }
    @Override
    public Person fromDoc(Document doc) {
        if (doc == null) {
            return new Worker("", "", "", "", "");
        }
        return new Worker(
                doc.getString("name"),
                doc.getString("address"),
                doc.getString("age"),
                doc.getString("workerNumber"));
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