import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class PersonHandler {


    // Abstract methods for the subclasses to implement
    public abstract void moveToDB(MongoCollection<Document> collection, Person person);

    public abstract void getFromDB(MongoCollection<Document> collection, String name);

    public abstract void updateInDB(MongoCollection<Document> collection, Person person);

    public abstract void deleteFromDB(MongoCollection<Document> collection, String name);

    public abstract void allFromDB(MongoCollection<Document> collection);





    public void advancedSearchAdress(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("adress", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                Person customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                Person worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchAge(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();

            Document doc = new Document("age", search);

            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                Person customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                Person worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchByName(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {
        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("name", pattern)
            ));

            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                Person customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                Person worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void advancedSearchBananas(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("name", pattern),
                    new Document("adress", pattern),
                    new Document("email", pattern),
                    new Document("age", pattern),
                    new Document("customerNumber", pattern),
                    new Document("workerNumber", pattern)
            ));

            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                Person customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                Person worker = new Worker("", "", 0, 0, "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
