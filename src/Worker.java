public class Worker extends Person {

    private String employeeNumber;

    public Worker(String name, String address, int age, String employeeNumber) {
        super(name, address, age);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

}
