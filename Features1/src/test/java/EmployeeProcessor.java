import java.util.*;
import java.util.stream.*;
import java.text.DecimalFormat;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary + '}';
    }
}

public class EmployeeProcessor {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "Engineering", 90000),
                new Employee(2, "Bob", "Engineering", 75000),
                new Employee(3, "Charlie", "Marketing", 85000),
                new Employee(4, "David", "Engineering", 120000),
                new Employee(5, "Eva", "Sales", 95000),
                new Employee(6, "Frank", "Engineering", 82000)
        );

        List<Employee> filteredAndSorted = employees.stream()
                .filter(e -> e.getDepartment().equals("Engineering") && e.getSalary() > 80000)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());

        System.out.println("Filtered and Sorted Employees (Engineering & salary > 80000):");
        filteredAndSorted.forEach(System.out::println);

        Map<String, List<Employee>> groupedByDept = filteredAndSorted.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("\n Grouped by Department:");
        groupedByDept.forEach((dept, list) -> {
            System.out.println(dept + ":");
            list.forEach(emp -> System.out.println("  " + emp));
        });

        Map<String, Double> averageSalary = filteredAndSorted.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        DecimalFormat df = new DecimalFormat("#.00");

        System.out.println("\n Average Salary by Department:");
        averageSalary.forEach((dept, avgSal) ->
                System.out.println(dept + ": Rs" + df.format(avgSal)));
    }
}

