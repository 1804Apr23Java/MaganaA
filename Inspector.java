public class Inspector extends Employee {
    
    private final static int baseSalary = 35000;
    
    public Inspector() {
        
    }
    
    public Inspector(String name, int employeeNumber) {
        super(name, employeeNumber);
    }
    
    public double getSalary() {
        return baseSalary;
    }

}