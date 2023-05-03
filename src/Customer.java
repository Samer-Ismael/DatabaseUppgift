import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;

public class Customer extends Person {

    private String customerNumber;
    private String id;


    public Customer(String name, String address, String age, String customerNumber) {
        super(name, address, age);
        this.customerNumber = customerNumber;
    }

    public Customer(String name, String address, String age, String customerNumber, String id) {
        super(name, address, age);
        this.customerNumber = customerNumber;
        this.id = id;
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
        Document newCustomer = new Document(customer.toDoc());
        try {
            var find = collection.find(newCustomer);
            if (find.first() != null) {
                System.out.println("Customer already exists");
            } else {
                collection.insertOne(newCustomer);
                System.out.println("Customer added");
            }
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
                Person customer1 = fromDoc(customer);
                customer1.print();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void updateDB(MongoCollection<Document> collection, Person customer) {

        Document updateCustomer = new Document("name", customer.getName())
                .append("adress", customer.getAddress())
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

        ArrayList<Customer> customerList = new ArrayList<>();
        for (Document customer : customers) {
            customerList.add(fromDoc(customer));
        }
        if (customerList.isEmpty()) {
            System.out.println("No customers found");
        } else {
            for (Customer customer : customerList) {
                customer.print();
                System.out.println("__________________________");
            }
        }
    }


    @Override
    public Customer fromJSON(String json) {

        return new Customer(
                Document.parse(json).getString("name"),
                Document.parse(json).getString("address"),
                Document.parse(json).getString("age"),
                Document.parse(json).getString("customerNumber")
        );
    }
    @Override
    public String toJSON() {

        Document customer = new Document("name", getName())
                .append("address", getAddress())
                .append("age", getAge())
                .append("customerNumber", getCustomerNumber());
        return customer.toJson();
    }
    @Override
    public Customer fromDoc(Document doc) {
        if (doc == null) {
            return new Customer("", "", "", "");
        }
        return new Customer(
                doc.getString("name"),
                doc.getString("address"),
                doc.getString("age"),
                doc.getString("customerNumber")
        );
    }
    @Override
    public Document toDoc() {
        return new Document("name", getName())
                .append("address", getAddress())
                .append("age", getAge())
                .append("customerNumber", getCustomerNumber());
    }

    @Override
    public void print( ) {

        System.out.println("Customer:");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Age: " + getAge());
        System.out.println("Customer number: " + getCustomerNumber());
    }
}