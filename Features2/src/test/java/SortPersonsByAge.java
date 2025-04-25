import java.util.*;

class Person {
    private String name;
    private int age;
    private double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return name + " - Age: " + age + ", Salary: $" + salary;
    }
}

public class SortPersonsByAge {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alice", 28, 50000));
        persons.add(new Person("Bob", 35, 60000));
        persons.add(new Person("Charlie", 22, 45000));
        persons.add(new Person("Diana", 30, 70000));

        persons.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        System.out.println("Sorted List by Age:");
        persons.forEach(System.out::println);
    }
}
