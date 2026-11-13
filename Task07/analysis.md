# Task 07 – Design Challenge: Pharmacy Inventory Redesign

## 1. Problem Understanding

The design challenge focuses on identifying a limitation in an existing program and improving its performance through redesign.

Task 04 compared HashMap, TreeMap, and LinkedHashMap for pharmacy inventory management.

One limitation identified in Task 04 was that HashMap does not maintain sorted key order. Therefore, generating a sorted medicine report requires copying the keys into another collection and sorting them.

When reports are generated repeatedly, this sorting operation creates additional processing time and temporary memory usage.

To solve this problem, the original HashMap approach was redesigned using TreeMap.

## 2. Objective

- Identify a limitation in the original pharmacy inventory implementation.
- Analyse the additional work required to generate sorted reports.
- Design an improved inventory implementation using TreeMap.
- Compare the original and redesigned implementations.
- Measure construction and report-generation performance.
- Compare operation counts and temporary memory requirements.
- Validate that both implementations generate identical reports.
- Evaluate the advantages and limitations of the redesign.

## 3. Original System Analysis

### 3.1 Original Implementation

The original implementation used HashMap to store medicine records.

HashMap provides efficient average insertion and lookup operations but does not guarantee sorted key order.

Therefore, whenever a sorted report was required, the program performed these operations:

1. Copied all medicine codes into an ArrayList.
2. Sorted the medicine codes.
3. Iterated through the sorted list.
4. Generated the report.

### 3.2 Identified Limitation

The main limitation was repeated sorting.

If the pharmacy generated several reports, the same medicine codes had to be copied and sorted again and again.

For example:

- Generating a sorted report after every inventory update.
- Generating multiple sorted inventory reports during the day.

This repeated work increases execution time and requires additional temporary memory.

## 4. Proposed Redesign

### 4.1 Redesigned Implementation

The redesigned implementation uses TreeMap instead of HashMap.

TreeMap automatically maintains medicine codes in sorted order.

Therefore, the program can directly iterate through its keys without creating and sorting a separate list for every report.

### 4.2 Original and Redesigned Approaches

| Feature | Original Design | Redesigned Design |
|---|---|---|
| Collection | HashMap | TreeMap |
| Key ordering | No guaranteed order | Sorted order |
| Report generation | Copy and sort keys | Direct iteration |
| Repeated sorting | Required | Not required |
| Temporary key list | Required per report | Not required |
| Average insertion | O(1) | O(log n) |
| Sorted report generation | O(n log n) per report | O(n) per report |

### 4.3 Expected Improvement

The redesigned implementation was expected to:

1. Reduce repeated report-generation time.
2. Eliminate repeated sorting operations.
3. Reduce temporary memory allocation during report generation.
4. Maintain the same sorted report output.

However, TreeMap was expected to have slower insertion because it maintains sorted keys while adding records.

## 5. Experimental Design

Two implementations were tested.

**Original Design:**
- HashMap for medicine storage.
- ArrayList for copying medicine codes.
- Sorting before generating every report.

**Redesigned Approach:**
- TreeMap for medicine storage.
- Direct iteration through sorted keys.
- No separate sorting operation during report generation.

Three inventory sizes were tested:

- 1,000 medicines
- 10,000 medicines
- 100,000 medicines

The experiment used:

- 20 reports per measurement.
- 5 measurement repetitions.
- Average execution time in milliseconds.

The following measurements were recorded:

1. Map construction time.
2. Report-generation time.
3. Number of sorting comparisons.
4. Temporary key references per report.
5. Validation of report contents using checksums.

## 6. Experimental Results

### 6.1 Inventory Size: 1,000 Medicines

| Measurement | Original HashMap | Redesigned TreeMap |
|---|---:|---:|
| Build time (ms) | 0.3088 | 1.8982 |
| Report time (ms) | 8.1708 | 1.5217 |
| Sorting comparisons | 548,300 | 0 |
| Temporary key references per report | 1,000 | 0 |

**Analysis:**

TreeMap required more time to construct the inventory than HashMap.

However, report generation was faster because TreeMap already maintained sorted keys.

The redesigned approach reduced report-generation time from 8.1708 ms to 1.5217 ms.

### 6.2 Inventory Size: 10,000 Medicines

| Measurement | Original HashMap | Redesigned TreeMap |
|---|---:|---:|
| Build time (ms) | 2.6700 | 2.9787 |
| Report time (ms) | 45.9337 | 2.0877 |
| Sorting comparisons | 5,716,500 | 0 |
| Temporary key references per report | 10,000 | 0 |

**Analysis:**

The construction times were relatively close.

However, the original implementation required millions of sorting comparisons across repeated measurements.

TreeMap generated reports directly without sorting, reducing report-generation time from 45.9337 ms to 2.0877 ms.

### 6.3 Inventory Size: 100,000 Medicines

| Measurement | Original HashMap | Redesigned TreeMap |
|---|---:|---:|
| Build time (ms) | 7.2988 | 28.6546 |
| Report time (ms) | 503.0772 | 26.4982 |
| Sorting comparisons | 55,372,000 | 0 |
| Temporary key references per report | 100,000 | 0 |

**Analysis:**

For 100,000 medicines, TreeMap required more time to construct the inventory.

However, the original implementation required more than 55 million sorting comparisons across the repeated measurements.

The redesigned implementation eliminated repeated sorting and reduced report-generation time from 503.0772 ms to 26.4982 ms.

This demonstrates a significant improvement when generating sorted reports repeatedly.

## 7. Performance Comparison

### 7.1 Report Generation Improvement

| Inventory Size | Original Time (ms) | Redesigned Time (ms) | Approximate Speedup |
|---:|---:|---:|---:|
| 1,000 | 8.1708 | 1.5217 | 5.37x |
| 10,000 | 45.9337 | 2.0877 | 22.00x |
| 100,000 | 503.0772 | 26.4982 | 18.99x |

The redesigned approach improved report-generation performance at all three inventory sizes.

### 7.2 Construction Time Trade-off

| Inventory Size | HashMap (ms) | TreeMap (ms) |
|---:|---:|---:|
| 1,000 | 0.3088 | 1.8982 |
| 10,000 | 2.6700 | 2.9787 |
| 100,000 | 7.2988 | 28.6546 |

TreeMap required more construction time, especially for 100,000 records.

This is the main trade-off of the redesign.

The improvement is most beneficial when the inventory is built once and sorted reports are generated repeatedly.

## 8. Operation Count and Memory Analysis

### 8.1 Sorting Comparisons

The original HashMap implementation required repeated sorting comparisons.

Observed comparison counts:

- 1,000 medicines: 548,300
- 10,000 medicines: 5,716,500
- 100,000 medicines: 55,372,000

The redesigned TreeMap implementation required zero key-sorting comparisons during report generation because the keys were already sorted.

This does not mean TreeMap performs no comparisons. It performs comparisons while inserting and maintaining its sorted structure.

### 8.2 Temporary Memory Usage

The original implementation created a temporary ArrayList containing all medicine keys for every report.

The redesigned implementation directly iterated over TreeMap keys.

| Inventory Size | Original Temporary References per Report | Redesigned Temporary References per Report |
|---:|---:|---:|
| 1,000 | 1,000 | 0 |
| 10,000 | 10,000 | 0 |
| 100,000 | 100,000 | 0 |

The redesigned approach avoids allocating a separate key list for each report.

This comparison refers to temporary key-list references, not total memory usage. TreeMap itself has additional storage overhead for maintaining its sorted structure.

## 9. Validation

Both implementations were tested using the same medicine records.

The program compared the report checksums generated by the original and redesigned implementations.

**Observed Output:**

```text
Original and redesigned report checksums: true
```

This validation was successful for all three inventory sizes.

The matching checksums demonstrate that both approaches processed the same medicine codes and generated equivalent report contents.

Therefore, the redesign improved report-generation performance without changing the required report data.

## 10. Evaluation and Trade-offs

### 10.1 Advantages of the Redesign

1. Eliminates repeated sorting during report generation.
2. Reduces report-generation time.
3. Avoids creating temporary key lists.
4. Maintains sorted medicine codes automatically.
5. Produces the same report contents as the original implementation.

### 10.2 Disadvantages of the Redesign

1. TreeMap requires more time for insertion.
2. TreeMap has additional internal storage overhead.
3. It may not be the best choice when frequent updates and fast average lookups are the main requirements.
4. The performance results depend on the system and Java runtime environment.

### 10.3 When to Use Each Approach

**HashMap is suitable when:**

- Fast average medicine lookup is the main requirement.
- Sorted reports are generated infrequently.

**TreeMap is suitable when:**

- Sorted reports are generated frequently.
- Medicine codes must always remain sorted.
- Avoiding repeated sorting is important.

## 11. Limitations

1. The experiment used generated medicine records rather than a real pharmacy database.
2. Only three inventory sizes were tested.
3. Five repetitions were used for each measurement.
4. JVM warm-up, garbage collection, and background system activity may affect timing.
5. Temporary memory usage was estimated using key references rather than measured directly in bytes.
6. The report-generation experiment used a fixed number of 20 reports.

The results demonstrate the performance difference for this workload, but they do not prove that TreeMap is faster for every pharmacy inventory operation.

## 12. Final Conclusion

The design challenge identified repeated sorting as a limitation in the original HashMap-based pharmacy inventory system.

The original approach copied and sorted medicine codes whenever a sorted report was generated. This increased execution time and temporary memory allocation.

The redesigned approach used TreeMap, which maintains medicine codes in sorted order.

The experiment showed that report generation improved by approximately 5.37 times for 1,000 medicines, 22 times for 10,000 medicines, and 18.99 times for 100,000 medicines.

Although TreeMap required more construction time, it significantly reduced repeated report-generation time.

Both implementations also produced matching report checksums, confirming that the redesign preserved the report contents.

Therefore, TreeMap is a better choice for this particular workload when sorted reports are generated frequently, while HashMap remains suitable when fast average insertion and lookup are the main priorities.
