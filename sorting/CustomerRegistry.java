import java.util.*;

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ")";
    }
}

public class CustomerRegistry {
    private Set<Customer> customers = new HashSet<>();

    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    public boolean containsCustomer(Customer customer) {
        return customers.contains(customer);
    }

    public int getUniqueCount() {
        return customers.size();
    }

    public static void main(String[] args) {
        CustomerRegistry registry = new CustomerRegistry();

        // Sample Input operations
        System.out.println(registry.addCustomer(new Customer(101, "Asha"))); // true
        System.out.println(registry.addCustomer(new Customer(101, "Asha"))); // false
        System.out.println(registry.addCustomer(new Customer(102, "Ravi"))); // true

        System.out.println("unique count " + registry.getUniqueCount());     // 2
        System.out.println("contains: " + registry.containsCustomer(new Customer(101, "Asha"))); // true
    }
}