import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainManu {

    private Person customer;
    private Person worker;
    private MongoDB mongoDB;

    public void start() {

        MongoDB mongoDB = new MongoDB("", "", "");
        this.customer = new Customer("", "", 0, 0);
        this.worker = new Worker("", "", 0, 0);
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


    // Methods for asking CRUD operations
    private void workerCRUDAsking(Person worker, MongoCollection<Document> workerCollection) {
        int CRUD = askCRUD();
        if (CRUD == 1) {
            worker = getWorkerInfoFromUser();
            worker.addToDB(workerCollection, worker);
        } else if (CRUD == 2) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            worker.readFromDB(workerCollection, name);
        } else if (CRUD == 3) {
            System.out.println("To update a worker, you need to enter the name of the worker you want to update\nAnd then enter the new information");
            worker = getWorkerInfoFromUser();
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
            customer = getCustomerInfoFromUser();
            customer.addToDB(customerCollection, customer);
        } else if (CRUD == 2) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter name: ");
            String name = scanner1.nextLine();
            customer.readFromDB(customerCollection, name);
        } else if (CRUD == 3) {
            System.out.println("To update a customer, you need to enter the name of the customer you want to update\nAnd then enter the new information");
            customer = getCustomerInfoFromUser();
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
                mongoDB.advancedSearchBananas(customerCollection, workerCollection);
            }
            if (choice == 2) {
                System.out.println("----------");
                mongoDB.advancedSearchByName(customerCollection, workerCollection);
            }
            if (choice == 3) {
                System.out.println("----------");
                mongoDB.advancedSearchAdress(customerCollection, workerCollection);
            }
            if (choice == 4) {
                System.out.println("----------");
                mongoDB.advancedSearchAge(customerCollection, workerCollection);
            }
            if (choice == 5) {
                System.out.println("----------");
                mongoDB.advancedSearchCustomerNumber(customerCollection);
            }
            if (choice == 6) {
                System.out.println("----------");
                mongoDB.advancedSearchWorkerNumber(workerCollection);
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }
}