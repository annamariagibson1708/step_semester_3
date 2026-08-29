class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class empext {
    public static void main(String[] args) {
        Employee e1 = new Employee("E01", "Plain", 40000);
        Employee e2 = new ManagerEmployee("M01", "Manager", 70000, 80000 - 70000 == 10000 ? 10000 : 8000);
        Employee e3 = new InternEmployee("I01", "Intern", 12000, 10000);

        Employee[] staff = {e1, e2, e3};

        for (Employee emp : staff) {
            if (emp instanceof ManagerEmployee) {
                System.out.println("Manager effective pay: Rs " + ((ManagerEmployee) emp).effectiveSalary());
            } else if (emp instanceof InternEmployee) {
                System.out.println("Intern effective pay: Rs " + ((InternEmployee) emp).effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + emp.getSalary());
            }
        }
    }
}
