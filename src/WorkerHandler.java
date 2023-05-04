import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkerHandler extends PersonHandler {

    private int workerNumber;
    @Override
    public void moveToDB(MongoCollection<Document> collection, Person worker) {
        Document newWorker = new Document(worker.toDoc());
        try {
            var find = collection.find(newWorker);
            if (find.first() != null) {
                System.out.println("Worker already exists");
            } else {
                collection.insertOne(newWorker);
                System.out.println("Worker added");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Worker already exists");
        }
    }

    @Override
    public void getFromDB(MongoCollection<Document> collection, String name) {

        try {
            FindIterable<Document> workers = collection.find(new Document("name", name));
            if (collection.countDocuments() == 0) {
                System.out.println("No workers found");
                return;
            }
            for (Document worker : workers) {
                System.out.println(worker.toJson());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Worker not found");
        }
    }

    @Override
    public void updateInDB(MongoCollection<Document> collection, Person worker) {

        Document updateWorker = new Document("name", worker.getName())
                .append("address", worker.getAddress())
                .append("age", worker.getAge())
                .append("workerNumber", workerNumber);
        try {
            collection.updateOne(new Document("name", worker.getName()), new Document("$set", updateWorker));
            System.out.println("Worker updated");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteFromDB(MongoCollection<Document> collection, String name) {

        Document deleteWorker = new Document("name", name);
        try {
            collection.deleteOne(deleteWorker);
            System.out.println("Worker deleted");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void allFromDB(MongoCollection<Document> collection) {
        FindIterable<Document> workers = collection.find();
        ArrayList<Worker> workerList = new ArrayList<>();
        for (Document people : workers) {
            Person worker= new Worker("", "", 0, 0);
            workerList.add((Worker) worker.fromDoc(people));
        }
        if (workerList.isEmpty()) {
            System.out.println("No workers found");
        } else {
            for (Worker customer : workerList) {
                customer.print();
                System.out.println("--------------------");
            }
        }
    }


    public void advancedSearchWorkerNumber(MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();
            Document doc = new Document("workerNumber",search);

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