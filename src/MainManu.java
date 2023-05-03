import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainManu {

    private Person customer;
    private Person worker;

    public void start() {

        MongoDB mongoDB = new MongoDB("", "", "");
        this.customer = new Customer("", "", "", "");
        this.worker = new Worker("", "", "", "");
        MongoCollection<Document> customerCollection = mongoDB.createCollection("customer");
        MongoCollection<Document> workerCollection = mongoDB.createCollection("worker");

        System.out.println("Do you want to work with customers or workers?");
        System.out.println("1. Customers");
        System.out.println("2. Workers");
        System.out.println("3. Advanced search");
        System.out.println("4. Exit");
        System.out.println("------------------------------------------");
        try {
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                customerCRUDAsking(customer, customerCollection);
            }
            if (choice == 2) {
                workerCRUDAsking(worker, workerCollection);
            }
            if (choice == 3) {
                advancedSearchQuestions(customerCollection, workerCollection);
            }
            if (choice == 4) {
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }

    private void advancedSearchWorkerNumber(MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("workerNumber", pattern)
            ));
            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", "", "", "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void advancedSearchCustomerNumber(MongoCollection<Document> customerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("customerNumber", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", "", "", "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void advancedSearchAdress(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

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
                customer = new Customer("", "", "", "", "");                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", "", "", "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void advancedSearchAge(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            int search = scan.nextInt();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("age", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", "", "", "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", "", "", "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void advancedSearchEmail(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

        try {
            Scanner scan = new Scanner(System.in);
            String search = scan.nextLine();
            Pattern pattern = Pattern.compile(".*" + search + ".*", Pattern.CASE_INSENSITIVE);

            Document doc = new Document();
            doc.put("$or", List.of(
                    new Document("email", pattern)
            ));
            FindIterable<Document> result = customerCollection.find(doc);
            for (Document obj : result) {
                customer = new Customer("", "", "", "", "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }
            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", "", "", "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void advancedSearchByName(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {
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
                customer = new Customer("", "", "", "", "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", "", "", "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void advancedSearchBananas(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {

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
                customer = new Customer("", "", "", "");
                customer = customer.fromDoc(obj);
                customer.print();
                System.out.println("------------------------------------------");
            }

            FindIterable<Document> result2 = workerCollection.find(doc);
            for (Document obj : result2) {
                worker = new Worker("", "", "", "", "");
                worker = worker.fromDoc(obj);
                worker.print();
                System.out.println("------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Methods for asking CRUD operations
    private void workerCRUDAsking(Person worker, MongoCollection<Document> workerCollection) {
        int CRUD = askCRUD();
        if (CRUD == 1) {
            worker = getWorkerInfo();
            worker.addToDB(workerCollection, worker);
        } else if (CRUD == 2) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            worker.readFromDB(workerCollection, name);
        } else if (CRUD == 3) {
            System.out.println("To update a worker, you need to enter the name of the worker you want to update\nAnd then enter the new information");
            worker = getWorkerInfo();
            worker.updateDB(workerCollection, worker);
        } else if (CRUD == 4) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            worker.readFromDB(workerCollection, name);
            worker.deleteFromDB(workerCollection, name);
        } else if (CRUD == 5) {
            worker.allFromDB(workerCollection);
        }
    }

    // Methods for asking CRUD operations
    private void customerCRUDAsking(Person customer, MongoCollection<Document> customerCollection) {
        int CRUD = askCRUD();
        if (CRUD == 1) {
            customer = getCustomerInfo();
            customer.addToDB(customerCollection, customer);
        } else if (CRUD == 2) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            customer.readFromDB(customerCollection, name);
        } else if (CRUD == 3) {
            System.out.println("To update a customer, you need to enter the name of the customer you want to update\nAnd then enter the new information");
            customer = getCustomerInfo();
            customer.updateDB(customerCollection, customer);
        } else if (CRUD == 4) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            customer.readFromDB(customerCollection, name);
            customer.deleteFromDB(customerCollection, name);
        } else if (CRUD == 5) {
            customer.allFromDB(customerCollection);
        }
    }

    // Method for building object of Customer class from user input
    public Customer getCustomerInfo() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter address: ");
            String address = scanner.nextLine();
            System.out.println("Enter age: ");
            String age = scanner.nextLine();
            System.out.println("Enter customer number: ");
            String customerNumber = scanner.nextLine();
            return new Customer(name, address, age, customerNumber);
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }

    // Method for building object of Worker class from user input
    public Worker getWorkerInfo() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter address: ");
            String address = scanner.nextLine();
            System.out.println("Enter age: ");
            String age = scanner.nextLine();
            System.out.println("Enter employee number: ");
            String workerNumber = scanner.nextLine();
            return new Worker(name, address, age, workerNumber);
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }

    // Method for asking user which CRUD operation the user wants to do
    public int askCRUD() {
        int choice = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Add");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Show all");

            return choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong input");
            return 0;
        }
    }

    public void advancedSearchQuestions(MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Search all over the database");
            System.out.println("2. Search for name");
            System.out.println("3. Search for address");
            System.out.println("4. Search for age");
            System.out.println("5. Search for email");
            System.out.println("6. Search for customer number");
            System.out.println("7. Search for worker number");
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
                advancedSearchEmail(customerCollection, workerCollection);
            }
            if (choice == 6) {
                System.out.println("----------");
                advancedSearchCustomerNumber(customerCollection);
            }
            if (choice == 7) {
                System.out.println("----------");
                advancedSearchWorkerNumber(workerCollection);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }
}