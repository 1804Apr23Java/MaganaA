public abstract class Employee {
    String name;
    protected int employeeNumber;
    
    
    public Employee() {
        
    }
    
    public Employee(String name, int employeeNumber) {
        System.out.println("Hiring New Employee: " + name + ", Employee Number: " + employeeNumber);
        this.name = name;
        this.employeeNumber = employeeNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public int getEmployeeNumber() {
        return employeeNumber;
    }
    
}


