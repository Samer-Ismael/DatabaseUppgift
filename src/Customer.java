import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

public class Customer extends Person {

    private String customerNumber;

    public Customer(String name, String address, int age, String customerNumber) {
        super(name, address, age);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }


    // Methods from the abstract class Person
    @Override
    public void addToDB(MongoCollection<Document> collection, Person customer) {

        if (customer instanceof Customer) {
            customerNumber = ((Customer) customer).getCustomerNumber();
        }

        Document newCustomer = new Document("name", customer.getName())
                .append("address", customer.getAddress())
                .append("age", customer.getAge())
                .append("customerNumber", customerNumber);
        try {
            collection.createIndex(new Document("name", 1), new IndexOptions().unique(true));
            collection.insertOne(newCustomer);
            System.out.println("Customer added");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Customer already exists");
        }
    }
    @Override
    public void readFromDB(MongoCollection<Document> collection, String name) {

        try {
            FindIterable<Document> customers = collection.find(new Document("name", name));
            if (collection.countDocuments() == 0) {
                System.out.println("No customers found");
                return;
            }
            for (Document customer : customers) {
                System.out.println(customer.toJson());
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void updateDB(MongoCollection<Document> collection, Person customer) {

        Document updateCustomer = new Document("name", customer.getName())
                .append("address", customer.getAddress())
                .append("age", customer.getAge())
                .append("customerNumber", customerNumber);
        try {
            collection.updateOne(new Document("name", customer.getName()), new Document("$set", updateCustomer));
            System.out.println("Customer updated");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void deleteFromDB(MongoCollection<Document> collection, String name) {

        Document deleteCustomer = new Document("name", name);
        try {
            collection.deleteOne(deleteCustomer);
            System.out.println("Customer deleted");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void allFromDB(MongoCollection<Document> collection) {
        FindIterable<Document> customers = collection.find();
        for (Document customer : customers) {
            System.out.println(customer.toJson());
        }

    }
}