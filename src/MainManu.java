import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Scanner;

public class MainManu {


    public void start (){

        MongoDB mongoDB = new MongoDB("","","");
        Person customer = new Customer("", "", 0, "");
        Person worker = new Worker("", "", 0, "");
        MongoCollection<Document> customerCollection = mongoDB.createCollection("customer");
        MongoCollection<Document> workerCollection = mongoDB.createCollection("worker");

        System.out.println("Welcome to the Main Menu");
        System.out.println("Please choose an option");
        System.out.println("1. Add a new customer");
        System.out.println("2. Add a new worker");
        System.out.println("3. Read a customer");
        System.out.println("4. Read a worker");
        System.out.println("5. Update a customer");
        System.out.println("6. Update a worker");
        System.out.println("7. Delete a customer");
        System.out.println("8. Delete a worker");
        System.out.println("9. Exit");


        try {Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {

                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Please enter the name of the customer");
                String name = scanner1.nextLine();
                customer.setName(name);
                System.out.println("Please enter the address of the customer");
                String address = scanner1.nextLine();
                customer.setAddress(address);
                System.out.println("Please enter the customer number");
                String customerNumber = scanner1.nextLine();
                System.out.println("Please enter the age of the customer");
                int age = scanner1.nextInt();
                customer.setAge(age);
                ((Customer) customer).setCustomerNumber(customerNumber);

                customer.addToDB(customerCollection, customer);


            } else if (choice == 2) {

                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Please enter the name of the worker");
                String name = scanner2.nextLine();
                worker.setName(name);
                System.out.println("Please enter the address of the worker");
                String address = scanner2.nextLine();
                worker.setAddress(address);
                System.out.println("Please enter the employee number");
                String employeeNumber = scanner2.nextLine();
                System.out.println("Please enter the age of the worker");
                int age = scanner2.nextInt();
                worker.setAge(age);

                ((Worker) worker).setEmployeeNumber(employeeNumber);


                worker.addToDB(workerCollection, worker);


            } else if (choice == 3) {

                Scanner scanner3 = new Scanner(System.in);
                System.out.println("Please enter the name of the customer");
                String name = scanner3.nextLine();
                customer.readFromDB(customerCollection, name);


            } else if (choice == 4) {

                Scanner scanner4 = new Scanner(System.in);
                System.out.println("Please enter the name of the worker");
                String name = scanner4.nextLine();
                worker.readFromDB(customerCollection, name);



            } else if (choice == 5) {

                Scanner scanner5 = new Scanner(System.in);
                System.out.println("Please enter the name of the customer");
                String name = scanner5.nextLine();
                customer.setName(name);
                System.out.println("Please enter the address of the customer");
                String address = scanner5.nextLine();
                customer.setAddress(address);
                System.out.println("Please enter the customer number");
                String customerNumber = scanner5.nextLine();
                System.out.println("Please enter the age of the customer");
                int age = scanner5.nextInt();
                customer.setAge(age);

                ((Customer) customer).setCustomerNumber(customerNumber);

                customer.updateDB(customerCollection, customer);
                System.out.println("Customer updated");


            } else if (choice == 6) {

                Scanner scanner6 = new Scanner(System.in);
                System.out.println("Please enter the name of the worker");
                String name = scanner6.nextLine();
                worker.setName(name);
                System.out.println("Please enter the address of the worker");
                String address = scanner6.nextLine();
                worker.setAddress(address);
                System.out.println("Please enter the employee number");
                String employeeNumber = scanner6.nextLine();
                System.out.println("Please enter the age of the worker");
                int age = scanner6.nextInt();
                worker.setAge(age);

                ((Worker) worker).setEmployeeNumber(employeeNumber);

                worker.updateDB(workerCollection, worker);
                System.out.println("Worker updated");



            } else if (choice == 7) {

                Scanner scanner7 = new Scanner(System.in);
                System.out.println("Please enter the name of the customer");
                String name = scanner7.nextLine();
                customer.readFromDB(customerCollection, name);
                customer.deleteFromDB(customerCollection, name);
                System.out.println("Customer deleted");

            } else if (choice == 8) {

                Scanner scanner8 = new Scanner(System.in);
                System.out.println("Please enter the name of the worker");
                String name = scanner8.nextLine();
                worker.readFromDB(customerCollection, name);
                worker.deleteFromDB(workerCollection, name);
                System.out.println("Worker deleted");

            } else if (choice == 9) {
                System.out.println("Goodbye");
                System.exit(0);
            } else {
                System.out.println("Please enter a number between 1 and 9");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a number");
        }
    }

}
