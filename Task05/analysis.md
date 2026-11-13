# Task 05 – Generics, Comparable and Comparator

## 1. Problem Understanding

Java Generics allow classes, interfaces, and methods to work with different data types while maintaining type safety.

Java also provides two interfaces for sorting objects:

- `Comparable` defines the natural sorting order of objects.
- `Comparator` defines additional sorting orders based on different requirements.

This experiment demonstrates these concepts using generic classes, generic methods, and employee records.

## 2. Objective

- Understand the concept of Java Generics.
- Create a generic class that accepts different data types.
- Implement generic methods.
- Find the maximum value using a generic method.
- Implement the `Comparable` interface.
- Use the `Comparator` interface for different sorting operations.
- Compare natural sorting and custom sorting.

## 3. Predictions

### 3.1 Generic Class

**Prediction:** A generic class should store and display values of different data types.

### 3.2 Generic Method

**Prediction:** A generic method should work with different types of arrays and display their elements.

### 3.3 Comparable

**Prediction:** Implementing `Comparable` should allow employee records to be sorted by employee ID.

### 3.4 Comparator

**Prediction:** Using different comparators should allow employee records to be sorted by salary and name.

## 4. Experimental Design

The experiment was divided into five parts.

1. Created a generic `Box<T>` class to store integer and string values.
2. Created a generic method to display array elements.
3. Created another generic method to find the maximum value.
4. Implemented `Comparable<Employee>` to sort employees by ID.
5. Used `Comparator` to sort employees by salary and name.

Four employee records were used for the sorting experiments.

## 5. Experimental Results

### 5.1 Generic Class

**Observed Output:**

```text
1. GENERIC CLASS
Stored value: 100
Stored value: Java Generics
```

**Analysis:**

The generic `Box<T>` class successfully stored and displayed both integer and string values.

This demonstrates that one generic class can work with different data types without creating separate classes for each type.

### 5.2 Generic Method

**Observed Output:**

```text
2. GENERIC METHOD
Integer Array:
10
40
20
50
30
Maximum number: 80
String Array:
Arun
Divya
Kiran
```

**Analysis:**

The generic `displayArray()` method successfully displayed both integer and string arrays.

The `findMaximum()` method compared two integer values and returned 80 as the maximum value.

This demonstrates that generic methods can be reused with different compatible data types.

### 5.3 Comparable – Sorting by Employee ID

**Observed Output:**

```text
3. COMPARABLE - SORT BY EMPLOYEE ID
101 - Divya - 55000.0
102 - Ravi - 60000.0
103 - Arun - 45000.0
104 - Kiran - 40000.0
```

**Analysis:**

The `Employee` class implemented the `Comparable<Employee>` interface.

The `compareTo()` method compared employee IDs.

After using `Arrays.sort()`, the employee records were arranged in ascending order of employee ID.

Therefore, `Comparable` defines the natural sorting order of employee objects.

### 5.4 Comparator – Sorting by Salary

**Observed Output:**

```text
4. COMPARATOR - SORT BY SALARY
104 - Kiran - 40000.0
103 - Arun - 45000.0
101 - Divya - 55000.0
102 - Ravi - 60000.0
```

**Analysis:**

The `Comparator` interface was used to sort employee records based on salary.

The records were arranged in ascending order, from the lowest salary to the highest salary.

This demonstrates that Comparator can define a custom sorting order without changing the natural sorting logic in the Employee class.

### 5.5 Comparator – Sorting by Name

**Observed Output:**

```text
5. COMPARATOR - SORT BY NAME
103 - Arun - 45000.0
101 - Divya - 55000.0
104 - Kiran - 40000.0
102 - Ravi - 60000.0
```

**Analysis:**

The employee records were sorted alphabetically using their names.

The output shows the order Arun, Divya, Kiran, and Ravi.

This demonstrates that multiple sorting conditions can be created using Comparator.

## 6. Comparison Between Comparable and Comparator

| Comparable | Comparator |
|---|---|
| Defines natural sorting order | Defines custom sorting order |
| Uses `compareTo()` | Uses `compare()` |
| Implemented inside the class | Can be created separately |
| Generally provides one natural order | Supports multiple sorting orders |
| Used for employee ID sorting | Used for salary and name sorting |

## 7. Important Observations

1. Generics allow reusable code for different data types.
2. Generic classes reduce the need to create separate classes for each data type.
3. Generic methods can accept different compatible data types.
4. Comparable defines the natural sorting order of objects.
5. Comparator allows sorting based on different fields.
6. `Arrays.sort()` can use either natural ordering or a custom comparator.

## 8. Practical Applications

### 8.1 Generics

Generics are useful when developing reusable data structures.

Examples:

- Creating a reusable Box class for different data types.
- Creating a generic list to store different types of objects.

### 8.2 Comparable

Comparable is useful when an object has one natural sorting order.

Examples:

- Sorting employees by employee ID.
- Sorting student records by registration number.

### 8.3 Comparator

Comparator is useful when the same objects need different sorting orders.

Examples:

- Sorting employees by salary.
- Sorting employees alphabetically by name.

## 9. Conclusion

The experiment successfully demonstrated Java Generics, Comparable, and Comparator.

The generic class stored different data types, and generic methods successfully displayed arrays and found the maximum value.

The Comparable interface sorted employee records by employee ID. The Comparator interface sorted the same records by salary and name.

Therefore, Generics improve code reusability and type safety, while Comparable and Comparator provide flexible object-sorting capabilities in Java.
