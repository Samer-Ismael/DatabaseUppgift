import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;

public class Customer extends Person {

    private int customerNumber;
    private String id;

    public Customer(String name, String address, int age, int customerNumber) {
        super(name, address, age);
        this.customerNumber = customerNumber;
    }

    public Customer(String name, String address, int age, int customerNumber, String id) {
        super(name, address, age);
        this.customerNumber = customerNumber;
        this.id = id;
    }


    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }


    // Methods from the abstract class Person
    @Override
    public void moveToDB(MongoCollection<Document> collection, Person customer) {
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
    public void getFromDB(MongoCollection<Document> collection, String name) {

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
    public void updateInDB(MongoCollection<Document> collection, Person customer) {

        Document updateCustomer = new Document("name", customer.getName()).append("adress", customer.getAddress()).append("age", customer.getAge()).append("customerNumber", customerNumber);
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
        ArrayList<Customer> workerList = new ArrayList<>();
        for (Document Customer : customers) {
            workerList.add(fromDoc(Customer));
        }
        if (workerList.isEmpty()) {
            System.out.println("No workers found");
        } else {
            for (Customer customer : workerList) {
                customer.print();
                System.out.println("--------------------");
            }
        }
    }

    @Override
    public Customer fromDoc(Document doc) {
        if (doc == null) {
            return new Customer("", "", 0, 0, "");
        }
        return new Customer(doc.getString("name"), doc.getString("address"), doc.getInteger("age"), doc.getInteger("customerNumber"));
    }

    @Override
    public Document toDoc() {
        return new Document("name", getName()).append("address", getAddress()).append("age", getAge()).append("customerNumber", getCustomerNumber());
    }

    @Override
    public void print() {

        System.out.println("Customer:");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Age: " + getAge());
        System.out.println("Customer number: " + getCustomerNumber());
    }
}