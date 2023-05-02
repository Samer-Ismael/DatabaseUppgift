import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Collection;

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



    @Override
    public void addToDB(MongoCollection<Document> collection, Person customer) {

        Document newCustomer = new Document("name", customer.getName())
                .append("address", customer.getAddress())
                .append("age", customer.getAge())
                .append("customerNumber", customerNumber);

        try {
            collection.insertOne(newCustomer);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void readFromDB(MongoCollection<Document> collection, String name) {

        FindIterable<Document> customers = collection.find(new Document("name", name));
        for (Document customer : customers) {
            System.out.println(customer.toJson());
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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFromDB(MongoCollection<Document> collection, String name) {

        Document deleteCustomer = new Document("name", name);
        try {
            collection.deleteOne(deleteCustomer);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }



}
