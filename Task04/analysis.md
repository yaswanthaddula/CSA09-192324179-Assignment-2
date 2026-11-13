# Task 04 – Pharmacy Inventory Management

## 1. Problem Understanding

A pharmacy needs to maintain medicine inventory records using Java Map collections.

Each medicine contains:
- Medicine code
- Medicine name
- Available quantity

Java provides different Map implementations such as `HashMap`, `TreeMap`, and `LinkedHashMap`. Each implementation stores and retrieves data differently.

This experiment compares their performance and ordering behaviour.

## 2. Objective

- Implement pharmacy inventory using `HashMap`, `TreeMap`, and `LinkedHashMap`.
- Compare insertion, lookup, and iteration performance.
- Measure the performance using different inventory sizes.
- Understand the ordering behaviour of different Map implementations.
- Identify the most suitable Map for different inventory requirements.
- Study alternative approaches for generating sorted reports.

## 3. Predictions

### 3.1 HashMap

**Prediction:** HashMap should provide fast insertion and lookup because it uses hashing.

### 3.2 TreeMap

**Prediction:** TreeMap should maintain medicine codes in sorted order. Its insertion and lookup operations may take more time because it maintains sorted keys.

### 3.3 LinkedHashMap

**Prediction:** LinkedHashMap should maintain insertion order while providing efficient average lookup performance.

## 4. Experimental Design

Three Map implementations were tested:

1. HashMap
2. TreeMap
3. LinkedHashMap

The experiment used three different inventory sizes:

- 1,000 medicines
- 10,000 medicines
- 100,000 medicines

The following operations were measured:

- Insertion of medicine records.
- 100,000 random medicine lookups.
- Iteration through all medicine records.

Each measurement was repeated five times, and the average execution time was calculated in milliseconds.

The experiment also compared the ordering of medicine codes in the three Map implementations.

## 5. Experimental Results

### 5.1 Performance Comparison

All measurements are average execution times in milliseconds.

**Inventory Size: 1,000 Medicines**

| Operation | HashMap | TreeMap | LinkedHashMap |
|---|---:|---:|---:|
| Insertion | 11.6050 | 5.5604 | 6.0411 |
| 100,000 Lookups | 21.3155 | 26.9433 | 18.0955 |
| Iteration | 0.2172 | 0.2951 | 0.2546 |

**Observation:**

LinkedHashMap recorded the fastest lookup time. HashMap recorded the fastest iteration time. TreeMap recorded the lowest insertion time in this measurement.

**Inventory Size: 10,000 Medicines**

| Operation | HashMap | TreeMap | LinkedHashMap |
|---|---:|---:|---:|
| Insertion | 2.4816 | 4.2251 | 3.9373 |
| 100,000 Lookups | 20.6194 | 36.7138 | 21.9544 |
| Iteration | 0.9289 | 0.6342 | 0.2435 |

**Observation:**

HashMap recorded the fastest insertion and lookup times. LinkedHashMap recorded the fastest iteration time. TreeMap lookup was slower than the other two implementations.

**Inventory Size: 100,000 Medicines**

| Operation | HashMap | TreeMap | LinkedHashMap |
|---|---:|---:|---:|
| Insertion | 34.2034 | 41.0979 | 44.7488 |
| 100,000 Lookups | 57.7053 | 80.1102 | 38.7061 |
| Iteration | 9.0892 | 2.6025 | 3.2856 |

**Observation:**

HashMap recorded the fastest insertion time. LinkedHashMap recorded the fastest lookup time. TreeMap recorded the fastest iteration time.

### 5.2 Overall Performance Observations

1. HashMap generally provides efficient insertion and lookup operations.
2. TreeMap maintains sorted keys but lookup operations can be slower.
3. LinkedHashMap maintains insertion order and showed good lookup performance in these measurements.
4. Performance differences depend on inventory size and runtime conditions.

The measurements are specific to this execution environment and should not be treated as guaranteed performance rankings.

## 6. Map Ordering Comparison

The program inserted medicine codes in this order:

```text
MED000003
MED000001
MED000005
MED000002
MED000004
```

### 6.1 HashMap Output

```text
MED000005
MED000004
MED000001
MED000003
MED000002
```

**Analysis:**

HashMap does not guarantee insertion order or sorted order. The displayed order may vary depending on implementation and runtime conditions.

### 6.2 TreeMap Output

```text
MED000001
MED000002
MED000003
MED000004
MED000005
```

**Analysis:**

TreeMap automatically stores keys in sorted order. This makes it suitable for generating medicine reports based on medicine codes.

### 6.3 LinkedHashMap Output

```text
MED000003
MED000001
MED000005
MED000002
MED000004
```

**Analysis:**

LinkedHashMap preserves the insertion order of medicine records. It is useful when the original order of adding records needs to be maintained.

## 7. Generating a Sorted Report from HashMap

HashMap does not automatically sort its keys. Therefore, the program copied its keys into an ArrayList and sorted them using `Collections.sort()`.

**Observed Output:**

```text
MED000001
MED000002
MED000003
MED000004
MED000005
```

**Analysis:**

The output shows that a sorted report can be generated from HashMap by sorting its keys separately.

However, this requires additional processing.

## 8. Complexity Comparison

| Operation | HashMap | TreeMap | LinkedHashMap |
|---|---|---|---|
| Insertion | O(1) average | O(log n) | O(1) average |
| Lookup | O(1) average | O(log n) | O(1) average |
| Iteration | O(n) | O(n) | O(n) |
| Ordering | No guaranteed order | Sorted order | Insertion order |

## 9. Practical Evaluation

### 9.1 Suitable Map for Pharmacy Inventory

HashMap is suitable when fast medicine-code lookup is the main requirement.

Example 1: Finding the available quantity of a medicine using its medicine code.

Example 2: Updating the quantity of a medicine after a purchase.

### 9.2 Suitable Map for Sorted Reports

TreeMap is suitable when medicine records must be displayed in sorted order.

Example 1: Displaying medicines alphabetically by medicine code.

Example 2: Generating an inventory report in ascending medicine-code order.

### 9.3 Suitable Map for Insertion Order

LinkedHashMap is suitable when medicine records must be displayed in the same order in which they were added.

Example 1: Displaying newly registered medicines in their insertion order.

Example 2: Maintaining the order of inventory records during processing.

### 9.4 Acceptable Lookup Response Time

For an interactive pharmacy inventory system, lookup operations should respond quickly. A practical target could be below 100 milliseconds for an individual lookup under normal load.

The experiment measured 100,000 lookups together, not one lookup individually. Therefore, these total timings cannot directly prove that every individual lookup meets the target.

## 10. Alternative Approaches

### 10.1 Hashtable

Hashtable is another Java Map implementation.

However, it is a legacy synchronized collection. It does not allow null keys or null values. Modern applications generally prefer HashMap for ordinary single-threaded use or suitable concurrent collections when thread safety is required.

### 10.2 Sorted Array with Binary Search

Another approach is to store medicine records in a sorted array and use `Arrays.binarySearch()` to find a medicine.

- Searching: O(log n)
- Accessing by index: O(1)
- Inserting while maintaining sorted order: O(n)

This approach can be useful when the inventory is mostly read-only and changes infrequently.

## 11. Limitations

1. Execution times may vary due to JVM warm-up and garbage collection.
2. Background system activity may affect measurements.
3. Only five repetitions were used.
4. The experiment uses
