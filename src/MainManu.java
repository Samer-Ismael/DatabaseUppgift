import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Scanner;

public class MainManu {

    MongoDB mongoDB;

    public void start() {

        MongoDB mongoDB = new MongoDB("", "", "");
        Person customer = new Customer("", "", 0, 0);
        Person worker = new Worker("", "", 0, 0);
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
                mongoDB.advancedSearchQuestions(customerCollection, workerCollection);
            }
            if (choice == 4) {
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }

    // Methods for asking CRUD operations
    private void workerCRUDAsking(Person worker, MongoCollection<Document> workerCollection) {
        int CRUD = askCRUD();
        if (CRUD == 1) {
            worker = getWorkerInfoFromUser();
            worker.moveToDB(workerCollection, worker);
        } else if (CRUD == 2) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            worker.getFromDB(workerCollection, name);
        } else if (CRUD == 3) {
            System.out.println("To update a worker, you need to enter the name of the worker you want to update\nAnd then enter the new information");
            worker = getWorkerInfoFromUser();
            worker.updateInDB(workerCollection, worker);
        } else if (CRUD == 4) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            worker.getFromDB(workerCollection, name);
            worker.deleteFromDB(workerCollection, name);
        } else if (CRUD == 5) {
            worker.allFromDB(workerCollection);
        }
    }

    // Methods for asking CRUD operations
    private void customerCRUDAsking(Person customer, MongoCollection<Document> customerCollection) {
        int CRUD = askCRUD();
        if (CRUD == 1) {
            customer = getCustomerInfoFromUser();
            customer.moveToDB(customerCollection, customer);
        } else if (CRUD == 2) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            customer.getFromDB(customerCollection, name);
        } else if (CRUD == 3) {
            System.out.println("To update a customer, you need to enter the name of the customer you want to update\nAnd then enter the new information");
            customer = getCustomerInfoFromUser();
            customer.updateInDB(customerCollection, customer);
        } else if (CRUD == 4) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            customer.getFromDB(customerCollection, name);
            customer.deleteFromDB(customerCollection, name);
        } else if (CRUD == 5) {
            customer.allFromDB(customerCollection);
        }
    }

    // Method for building object of Customer class from user input
    public Customer getCustomerInfoFromUser() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter address: ");
            String address = scanner.nextLine();
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            System.out.println("Enter customer number: ");
            int customerNumber = scanner.nextInt();
            return new Customer(name, address, age, customerNumber);
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }

    // Method for building object of Worker class from user input
    public Worker getWorkerInfoFromUser() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter address: ");
            String address = scanner.nextLine();
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            System.out.println("Enter worker number: ");
            int workerNumber = scanner.nextInt();
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

}