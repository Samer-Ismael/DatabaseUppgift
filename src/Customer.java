import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Customer extends Person {

    private String customerNumber;

    public Customer(String name, String address, int age, String customerNumber) {
        super(name, address, age);
        this.customerNumber = customerNumber;
        super.setCollectionName("customers");
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public void addToDB(MongoCollection<Document> collection, Person customer) {
        Document doc = new Document("name", customer.getName())
                .append("adress", customer.getAddress())
                .append("age", customer.getAge())
                .append("customerNumber", getCustomerNumber());
        collection.insertOne(doc);

    }

    @Override
    public void readFromDB(MongoCollection<Document> collection, String customer) {

        try {
            Document filter = new Document("name", customer);
            FindIterable<Document> iterable = collection.find(filter);
            Document customerDocument = iterable.first();

            if (customerDocument != null) {
                String customerName = customerDocument.getString("name");
                int customerAge = customerDocument.getInteger("age");
                String customerAddress = customerDocument.getString("adress");
                String customerNumber = customerDocument.getString("customerNumber");

                System.out.println("Name: " + customerName);
                System.out.println("Age: " + customerAge);
                System.out.println("Address: " + customerAddress);
                System.out.println("Customer number: " + customerNumber);
            }
        }catch (Exception e){
            System.out.println("Customer not found");
        }
    }
    @Override
    public void updateDB(MongoCollection<Document> collection, Person customer) {
        Document filter = new Document("name", customer.getName());
        Document update = new Document("$set", new Document("name", customer.getName())
                .append("adress", customer.getAddress())
                .append("age", customer.getAge())
                .append("employeeNumber", getCustomerNumber()));
        collection.updateOne(filter, update);
    }

    @Override
    public void deleteFromDB(MongoCollection<Document> collection, Person customer) {

        Document filter = new Document("name", customer.getName());
        collection.deleteOne(filter);
    }
}
