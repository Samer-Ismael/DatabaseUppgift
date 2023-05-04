import org.bson.Document;

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


    @Override
    public Customer fromDoc(Document doc) {
        if (doc == null) {
            return new Customer("", "", 0, 0, "");
        }
        return new Customer(doc.getString("name"),
                doc.getString("address"),
                doc.getInteger("age"),
                doc.getInteger("customerNumber"));
    }

    @Override
    public Document toDoc() {
        return new Document("name", getName())
                .append("address", getAddress())
                .append("age", getAge())
                .append("customerNumber", getCustomerNumber());
    }

    @Override
    public void print() {

        System.out.println("MongoDBFacade.Customer:");
        System.out.println("Name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Age: " + getAge());
        System.out.println("MongoDBFacade.Customer number: " + getCustomerNumber());
    }
}

