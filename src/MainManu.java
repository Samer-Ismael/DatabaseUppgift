import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Scanner;

public class MainManu {


    public void start() {

        MongoDB mongoDB = new MongoDB("", "", "");
        Person customer = new Customer("", "", 0, "");
        Person worker = new Worker("", "", 0, "");
        MongoCollection<Document> customerCollection = mongoDB.createCollection("customer");
        MongoCollection<Document> workerCollection = mongoDB.createCollection("worker");


        System.out.println("Do you want to work with customers or workers?");
        System.out.println("1. Customers");
        System.out.println("2. Workers");
        System.out.println("3. Advanced search");
        System.out.println("4. Exit");
        try { Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                customerCRUDAsking(customer, customerCollection);
            }
            if (choice == 2) {
                workerCRUDAsking(worker, workerCollection);
            }
            if (choice == 3) {
                advancedSearch(customer, worker, customerCollection, workerCollection);
            }
            if (choice == 4) {
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }

    private void advancedSearch(Person customer, Person worker, MongoCollection<Document> customerCollection, MongoCollection<Document> workerCollection) {



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
            worker = getWorkerInfo();
            worker.updateDB(workerCollection, worker);
        } else if (CRUD == 4) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
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
            customer = getCustomerInfo();
            customer.updateDB(customerCollection, customer);
        } else if (CRUD == 4) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            customer.deleteFromDB(customerCollection, name);
        } else if (CRUD == 5) {
            customer.allFromDB(customerCollection);
        }
    }

    // Method for building object of Customer class from user input
    public Customer getCustomerInfo() {
        try {Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        System.out.println("Enter customer number: ");
        String customerNumber = scanner.nextLine();
        customerNumber = scanner.nextLine();
        Person customer = new Customer(name, address, age, customerNumber);
        return (Customer) customer;
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }
    // Method for building object of Worker class from user input
    public Worker getWorkerInfo() {
        try {Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        System.out.println("Enter employee number: ");
        String workerNumber = scanner.nextLine();
        workerNumber = scanner.nextLine();
        Person worker = new Worker(name, address, age, workerNumber);
        return (Worker) worker;
        } catch (Exception e) {
            System.out.println("Wrong input");
            return null;
        }
    }
    // Method for asking user which CRUD operation the user wants to do
    public int askCRUD() {
        try {Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Show all");
        int choice = scanner.nextInt();
        return choice;
        } catch (Exception e) {
            System.out.println("Wrong input");
            return 0;
        }
    }
}
