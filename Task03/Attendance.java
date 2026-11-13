
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

// Original Student class: equals() overridden, hashCode() missing
class BrokenStudent {
    String regNo;
    String name;

    BrokenStudent(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrokenStudent)) return false;

        BrokenStudent other = (BrokenStudent) o;
        return regNo.equals(other.regNo);
    }

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

// Student class with mutable registration number for Test 3
class MutableStudent {
    String regNo;
    String name;

    MutableStudent(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MutableStudent)) return false;

        MutableStudent other = (MutableStudent) o;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

// Correct Student class
class Student {
    private final String regNo;
    private final String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student other = (Student) o;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

public class Attendance {

    public static void main(String[] args) {

        // TEST 1: Same object scanned more than once
        Set<BrokenStudent> test1 = new HashSet<>();

        BrokenStudent s1 = new BrokenStudent("ID101", "Arun");
        BrokenStudent s2 = new BrokenStudent("ID102", "Divya");

        test1.add(s1);
        test1.add(s2);
        test1.add(s1);

        System.out.println("TEST 1: Same object scanned twice");
        System.out.println("Expected unique students: 2");
        System.out.println("Actual set size: " + test1.size());

        // TEST 2: Different objects with the same registration number
        Set<BrokenStudent> test2 = new HashSet<>();

        BrokenStudent s3 = new BrokenStudent("ID201", "Ravi");
        BrokenStudent s4 = new BrokenStudent("ID202", "Meena");
        BrokenStudent s5 = new BrokenStudent("ID201", "Ravi");

        test2.add(s3);
        test2.add(s4);
        test2.add(s5);

        System.out.println("\nTEST 2: Separate objects with the same ID");
        System.out.println("Are duplicate objects equal? " + s3.equals(s5));
        System.out.println("First object hashCode: " + s3.hashCode());
        System.out.println("Second object hashCode: " + s5.hashCode());
        System.out.println("Expected unique students: 2");
        System.out.println("Actual set size: " + test2.size());

        // TEST 3: Changing a field used by hashCode after insertion
        Set<MutableStudent> test3 = new HashSet<>();

        MutableStudent s6 = new MutableStudent("ID301", "Kumar");
        test3.add(s6);

        System.out.println("\nTEST 3: Changing registration number");
        System.out.println("Before change, contains: " + test3.contains(s6));

        s6.regNo = "ID999";

        System.out.println("After change, contains: " + test3.contains(s6));
        System.out.println("Set size: " + test3.size());

        // CORRECTED SOLUTION: Complete the supplied TODO
        Set<Student> present = new HashSet<>();

        present.add(new Student("192311001", "Arun"));
        present.add(new Student("192311002", "Divya"));
        present.add(new Student("192311001", "Arun"));

        System.out.println("\nCORRECTED HASHSET SOLUTION");

        for (Student student : present) {
            System.out.println(student);
        }

        System.out.println("Unique students: " + present.size());

        // ALTERNATIVE 1: TreeSet with Comparator
        Set<Student> treeSet = new TreeSet<>(
            Comparator.comparing(student -> student.toString().split(" ")[0])
        );

        treeSet.add(new Student("ID401", "Ravi"));
        treeSet.add(new Student("ID402", "Meena"));
        treeSet.add(new Student("ID401", "Ravi"));

        System.out.println("\nTREESET UNIQUE STUDENTS: " + treeSet.size());

        // ALTERNATIVE 2: HashMap using registration number as key
        Map<String, String> studentMap = new HashMap<>();

        studentMap.put("ID501", "Kumar");
        studentMap.put("ID502", "Latha");
        studentMap.put("ID501", "Kumar");

        System.out.println("HASHMAP UNIQUE STUDENTS: " + studentMap.size());
    }
}

