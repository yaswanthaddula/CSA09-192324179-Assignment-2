# Task 03 – Event Attendance De-duplication

## 1. Problem Understanding

The task is to remove duplicate student records using Java's `HashSet`.

Each student has a registration number and name. Two student objects with the same registration number should be treated as the same student.

The main issue is that `HashSet` uses both `hashCode()` and `equals()` to identify duplicate objects. If these methods are not implemented correctly, duplicate records may be stored.

## 2. Objective

- Understand how `HashSet` stores unique objects.
- Study the relationship between `equals()` and `hashCode()`.
- Identify the problems caused by missing or incorrect `hashCode()`.
- Understand the effect of modifying an object's hash-related field after insertion.
- Implement a corrected solution.
- Compare `HashSet`, `TreeSet`, and `HashMap` approaches.

## 3. Predictions

### Test 1: Same Object Added Twice

**Prediction:** The set should contain only one copy of the same object.

### Test 2: Separate Objects with the Same Registration Number

**Prediction:** The set should contain only one student if duplicate identification works correctly.

However, if `hashCode()` is not overridden, separate objects may have different hash codes and both may be stored.

### Test 3: Changing Registration Number After Insertion

**Prediction:** Changing the registration number may affect the object's hash code. The `HashSet` may fail to locate the object using `contains()`.

## 4. Experimental Design

Three experiments were performed.

1. Added the same object twice to a `HashSet`.
2. Added separate objects with the same registration number.
3. Modified the registration number after inserting an object into a `HashSet`.

After identifying the problems, a corrected `Student` class was implemented by overriding both `equals()` and `hashCode()`.

Alternative solutions using `TreeSet` and `HashMap` were also tested.

## 5. Experimental Results

### 5.1 Test 1 – Same Object Added Twice

**Expected Result:** 1 unique object.

**Observed Output:**

```text
TEST 1: Same object scanned twice
Expected unique students: 2
Actual set size: 2
```

**Analysis:**

The same object was added twice, but the set did not store another copy of it.

The actual set size was 2 because the test included two distinct student records. This demonstrates that adding the same object again does not create another entry.

### 5.2 Test 2 – Separate Objects with the Same ID

**Expected Result:** Duplicate student objects should be identified as one student.

**Observed Output:**

```text
TEST 2: Separate objects with the same ID
Are duplicate objects equal? true
First object hashCode: 2124308362
Second object hashCode: 1482968390
Expected unique students: 2
Actual set size: 3
```

**Analysis:**

The two student objects were logically equal because their registration numbers were the same.

However, their hash codes were different because the `hashCode()` method was not overridden.

As a result, the `HashSet` stored both objects, producing 3 entries instead of the expected 2.

This demonstrates why overriding `equals()` alone is not sufficient.

### 5.3 Test 3 – Changing Registration Number

**Observed Output:**

```text
TEST 3: Changing registration number
Before change, contains: true
After change, contains: false
Set size: 1
```

**Analysis:**

Before changing the registration number, the `HashSet` successfully found the object.

After modification, the hash code changed. The object remained stored in its original hash bucket, but the search used the new hash code.

Therefore, `contains()` returned `false`.

This shows why fields used in `equals()` and `hashCode()` should not be modified after inserting an object into a hash-based collection.

## 6. Corrected Implementation

The corrected `Student` class overrides both `equals()` and `hashCode()`.

- `equals()` compares students using their registration numbers.
- `hashCode()` generates the hash code using the registration number.
- The registration number is declared `final` to prevent modification.

**Corrected Output:**

```text
CORRECTED HASHSET SOLUTION
192311001 Arun
192311002 Divya
Unique students: 2
```

**Analysis:**

The corrected implementation successfully removed duplicate student records.

Although two student objects may be created with the same registration number, the `HashSet` treats them as equal and stores only one of them.

## 7. Alternative Approaches

### 7.1 TreeSet

**Observed Output:**

```text
TREESET UNIQUE STUDENTS: 2
```

**Analysis:**

`TreeSet` stores unique elements in sorted order.

It uses comparison logic to identify duplicate elements. A comparator based on registration number can ensure that students with the same registration number are treated as duplicates.

Its insertion and search operations generally take O(log n) time.

### 7.2 HashMap

**Observed Output:**

```text
HASHMAP UNIQUE STUDENTS: 2
```

**Analysis:**

A `HashMap` can use the registration number as the key and the student object as the value.

Since map keys must be unique, adding another student with the same registration number replaces the previous value.

Its insertion and lookup operations generally take O(1) average time.

## 8. Comparison of Approaches

| Approach | Duplicate Identification | Ordering | Average/Typical Operation Cost |
|---|---|---|---|
| HashSet | Using `equals()` and `hashCode()` | No guaranteed order | O(1) |
| TreeSet | Using comparison logic | Sorted order | O(log n) |
| HashMap | Using registration number as key | No guaranteed order | O(1) |

## 9. Important Observations

1. `HashSet` uses `hashCode()` to identify the bucket and `equals()` to compare objects within that bucket.
2. If two objects are equal according to `equals()`, they must return the same hash code.
3. Overriding only `equals()` can cause duplicate objects to be stored.
4. Changing fields used in `hashCode()` after insertion can make an object difficult to find.
5. Immutable fields are safer for objects stored in hash-based collections.

## 10. Conclusion

The experiment demonstrated how Java collections identify duplicate objects.

The initial implementation showed that overriding only `equals()` is insufficient. Different hash codes allowed logically equal student objects to be stored separately in the observed test.

The corrected implementation successfully removed duplicate student records by overriding both `equals()` and `hashCode()`.

The experiments also demonstrated the effect of modifying an object's registration number after insertion and compared alternative solutions using `TreeSet` and `HashMap`.

Therefore, implementing the `equals()` and `hashCode()` contract correctly is essential when storing custom objects in a `HashSet`.
