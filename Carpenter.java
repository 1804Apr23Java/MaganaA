import java.util.Random;

public class Carpenter extends Employee implements EmployeeWork{
    
    private final static int baseSalary = 60000;
    private double personalSalary;
    private int yearsWorked;
    Random years = new Random();
    
    public Carpenter() {
        
    }
    
    public Carpenter(String name, int employeeNumber) {
        super(name, employeeNumber);
        yearsWorked = years.nextInt(50);
    }

    @Override
    public void calculateSalary() {
        personalSalary = ( yearsWorked * 1.05 ) * baseSalary;
        
    }
    
    public double getSalary() {
        return personalSalary;
    }
    
}
