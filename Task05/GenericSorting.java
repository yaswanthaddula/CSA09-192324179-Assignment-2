
import java.util.*;

class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    void display() {
        System.out.println("Stored value: " + value);
    }
}

class Employee implements Comparable<Employee> {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Natural sorting order: Employee ID
    public int compareTo(Employee e) {
        return Integer.compare(this.id, e.id);
    }

    public String toString() {
        return id + " - " + name + " - " + salary;
    }
}

public class GenericSorting {

    // Generic method to display array elements
    static <T> void displayArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

    // Generic method to find the maximum value
    static <T extends Comparable<T>> T findMaximum(T a, T b) {
        if (a.compareTo(b) > 0) {
            return a;
        }
        return b;
    }

    public static void main(String[] args) {

        System.out.println("1. GENERIC CLASS");

        Box<Integer> intBox = new Box<>(100);
        Box<String> stringBox = new Box<>("Java Generics");

        intBox.display();
        stringBox.display();

        System.out.println("\n2. GENERIC METHOD");

        Integer[] numbers = {10, 40, 20, 50, 30};

        System.out.println("Integer Array:");
        displayArray(numbers);

        System.out.println("Maximum number: " +
            findMaximum(40, 80));

        String[] names = {"Arun", "Divya", "Kiran"};

        System.out.println("String Array:");
        displayArray(names);

        System.out.println("\n3. COMPARABLE - SORT BY EMPLOYEE ID");

        Employee[] employees = {
            new Employee(103, "Arun", 45000),
            new Employee(101, "Divya", 55000),
            new Employee(104, "Kiran", 40000),
            new Employee(102, "Ravi", 60000)
        };

        Arrays.sort(employees);

        displayArray(employees);

        System.out.println("\n4. COMPARATOR - SORT BY SALARY");

        Arrays.sort(employees,
            Comparator.comparingDouble(e -> e.salary));

        displayArray(employees);

        System.out.println("\n5. COMPARATOR - SORT BY NAME");

        Arrays.sort(employees,
            Comparator.comparing(e -> e.name));

        displayArray(employees);

        System.out.println("\nEXPERIMENT COMPLETED");
    }
}

