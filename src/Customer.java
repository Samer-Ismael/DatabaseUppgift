public class Customer extends Person {

    private String customerNumber;

    public Customer(String name, String address, int age, String customerNumber) {
        super(name, address, age);
        this.customerNumber = customerNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
