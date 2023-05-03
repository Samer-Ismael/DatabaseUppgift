import com.mongodb.client.MongoCollection;
import org.bson.Document;

public abstract class Person {

    private String name;
    private String address;
    private int age;

    public Person(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // Abstract methods for the subclasses to implement
    public abstract void addToDB(MongoCollection<Document> collection, Person person);
    public abstract void readFromDB(MongoCollection<Document> collection, String name);
    public abstract void updateDB(MongoCollection<Document> collection, Person person);
    public abstract void deleteFromDB(MongoCollection<Document> collection, String name);
    public abstract void allFromDB(MongoCollection<Document> collection);
    public abstract Person fromJSON(String json);
    public abstract String toJSON();
    public abstract Person fromDoc (Document doc);
    public abstract Document toDoc ();
    public abstract void print ();

}
