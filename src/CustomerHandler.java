import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;

public class CustomerHandler extends PersonHandler{

    private int customerNumber;

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
                Person customer1 = new Customer("", "", 0, 0);
                customer1.fromDoc(customer);
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
        ArrayList<Customer> List = new ArrayList<>();
        for (Document people : customers) {
            Person customer= new Worker("", "", 0, 0);
            List.add((Customer) customer.fromDoc(people));
        }
        if (List.isEmpty()) {
            System.out.println("No workers found");
        } else {
            for (Customer customer : List) {
                customer.print();
                System.out.println("--------------------");
            }
        }
    }



}
