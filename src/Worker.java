import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sun.security.jgss.GSSUtil;
import org.bson.Document;

public class Worker extends Person {

    private String employeeNumber;

    public Worker(String name, String address, int age, String employeeNumber) {
        super(name, address, age);
        this.employeeNumber = employeeNumber;
        super.setCollectionName("workers");
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public void addToDB(MongoCollection<Document> collection, Person worker) {
        Document doc = new Document("name", worker.getName())
                .append("adress", worker.getAddress())
                .append("age", worker.getAge())
                .append("employeeNumber", getEmployeeNumber());
        collection.insertOne(doc);

    }

    @Override
    public void readFromDB(MongoCollection<Document> collection, String worker) {

        try{
            Document filter = new Document("name", worker);
            FindIterable<Document> iterable = collection.find(filter);
            Document workerDocument = iterable.first();

            if (workerDocument != null) {
                String workerName = workerDocument.getString("name");
                int workerAge = workerDocument.getInteger("age");
                String workerAddress = workerDocument.getString("adress");
                String EmployeeNumber = workerDocument.getString("employeeNumber");

                System.out.println("Name: " + workerName);
                System.out.println("Age: " + workerAge);
                System.out.println("Address: " + workerAddress);
                System.out.println("Worker number: " + EmployeeNumber);
            }
        }catch (Exception e) {
            System.out.println("Worker not found");
        }
    }

    @Override
    public void updateDB(MongoCollection<Document> collection, Person worker) {
        Document filter = new Document("name", worker.getName());
        Document update = new Document("$set", new Document("name", worker.getName())
                .append("address", worker.getAddress())
                .append("age", worker.getAge())
                .append("employeeNumber", getEmployeeNumber())); // corrected here
        collection.updateOne(filter, update);
    }

    @Override
    public void deleteFromDB(MongoCollection<Document> collection, Person worker) {
        Document filter = new Document("name", worker.getName());
        collection.deleteOne(filter);
    }
}