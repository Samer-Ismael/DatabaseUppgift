import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sun.security.jgss.GSSUtil;
import org.bson.Document;

public class Worker extends Person {

    private String employeeNumber;

    public Worker(String name, String address, int age, String employeeNumber) {
        super(name, address, age);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public void addToDB(MongoCollection<Document> collection, Person worker) {

        Document newWorker = new Document("name", worker.getName())
                .append("address", worker.getAddress())
                .append("age", worker.getAge())
                .append("employeeNumber", employeeNumber);
        try {
            collection.insertOne(newWorker);
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
                .append("employeeNumber", employeeNumber);
        try {
            collection.updateOne(new Document("name", worker.getName()), new Document("$set", updateWorker));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFromDB(MongoCollection<Document> collection, String name) {

        Document deleteWorker = new Document("name", name);
        try {
            collection.deleteOne(deleteWorker);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
