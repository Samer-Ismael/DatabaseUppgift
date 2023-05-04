import org.bson.Document;
public class Worker extends Person {

    private int workerNumber;
    private String id;

    public Worker(String name, String address, int age, int workerNumber) {
        super(name, address, age);
        this.workerNumber = workerNumber;
    }
    public Worker(String name, String address, int age, int workerNumber, String id) {
        super(name, address, age);
        this.workerNumber = workerNumber;
        this.id = id;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }
    public void setWorkerNumber(int workerNumber) {
        this.workerNumber = workerNumber;
    }


    @Override
    public Person fromDoc(Document doc) {
        if (doc == null) {
            return new Worker("", "", 0, 0);
        }
        return new Worker(doc.getString("name"), doc.getString("address"), doc.getInteger("age"), doc.getInteger("workerNumber"));
    }
    @Override
    public Document toDoc() {
        return new Document("name", getName()).append("address", getAddress()).append("age", getAge()).append("workerNumber", getWorkerNumber());
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