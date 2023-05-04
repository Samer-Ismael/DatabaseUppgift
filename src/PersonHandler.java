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

    public void advancedSearchCustomerNumber(MongoCollection<Document> customerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();

            Document doc = new Document("customerNumber", search);
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                Person customer = new Customer("", "", 0, 0, "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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

    public void advancedSearchQuestions(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Search all over the database");
            System.out.println("2. Search for name");
            System.out.println("3. Search for address");
            System.out.println("4. Search for age");
            System.out.println("5. Search for customer number");
            System.out.println("6. Search for worker number");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("----------");
                advancedSearchBananas(customerCollection, workerCollection);
            }
            if (choice == 2) {
                System.out.println("----------");
                advancedSearchByName(customerCollection, workerCollection);
            }
            if (choice == 3) {
                System.out.println("----------");
                advancedSearchAdress(customerCollection, workerCollection);
            }
            if (choice == 4) {
                System.out.println("----------");
                advancedSearchAge(customerCollection, workerCollection);
            }
            if (choice == 5) {
                System.out.println("----------");
                advancedSearchCustomerNumber(customerCollection);
            }
            if (choice == 6) {
                System.out.println("----------");
                advancedSearchWorkerNumber(workerCollection);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }


}
