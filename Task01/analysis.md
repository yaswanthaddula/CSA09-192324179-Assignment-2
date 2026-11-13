
# Q1: Building Text Under Load
## String, StringBuilder or StringBuffer?

### 1. Problem Understanding

A railway reservation system generates an e-ticket for every booking. The ticket contains details such as PNR, passenger name, coach, berth, fare and journey information.

The ticket is created by combining multiple fields into a single text block.

This question investigates the performance of String, StringBuilder and StringBuffer when generating railway tickets.

### 2. Objective

To compare String, StringBuilder and StringBuffer based on:

1. Execution time.
2. Estimated intermediate String object creation.
3. Memory usage.
4. Behaviour when the number of fields and tickets increases.

### 3. Prediction Before Implementation

#### 3.1 String

String is immutable. Whenever a new value is assigned through concatenation, a new String may be created.

Prediction: Repeated String concatenation inside a loop may take more time and create more intermediate objects as the ticket size increases.

#### 3.2 StringBuilder

StringBuilder is mutable. It modifies the same character sequence when append() is called.

Prediction: StringBuilder should perform better for repeated text construction because it reduces unnecessary intermediate String creation.

#### 3.3 StringBuffer

StringBuffer is also mutable, but its methods are synchronized.

Prediction: StringBuffer may be slower than StringBuilder in a single-threaded benchmark because of synchronization overhead.

### 4. Designed Workloads

Three workloads will be used to compare the approaches.

| Workload | Number of Tickets | Fields per Ticket |
|---|---:|---:|
| Workload 1 | 1 | 6 |
| Workload 2 | 10,000 | 50 |
| Workload 3 | 1,000 | 100 |

Workload 1 represents a small ticket-generation operation.

Workload 2 represents a large number of tickets with multiple fields.

Workload 3 represents tickets containing a larger number of fields.

### 5. Measurement Plan

The following measurements will be collected for all three approaches:

1. Execution time using System.nanoTime().
2. Estimated intermediate String object creation.
3. Approximate memory usage before and after execution.
4. Average execution time over repeated runs.

No measured results are assumed in advance. Actual results will be recorded after executing the Java program.

### 6. Initial Prediction

StringBuilder is expected to perform better than repeated String concatenation for large workloads.

StringBuffer is expected to have some synchronization overhead compared with StringBuilder in a single-threaded test.

The actual results will be compared with these predictions after execution.

### 7. Conclusion

The final conclusion will be written after collecting and analysing the experimental results.


## 8. Design Decision for a Multithreaded Booking Server

The railway booking server may handle multiple booking requests at the same time.

### 8.1 Per-Ticket Text

StringBuilder is recommended for creating individual ticket text.

Reason:
- It supports efficient repeated append operations.
- It avoids creating a new String for every concatenation.
- Each booking thread can use its own StringBuilder object.

After the ticket is generated, toString() can convert it into an immutable String.

### 8.2 Shared Booking Log

StringBuffer can be used when multiple threads append to the same shared log because its append methods are synchronized.

However, synchronization may reduce performance when many threads access the same log.

An alternative is to use a dedicated logging mechanism or synchronize access to a shared StringBuilder.

### 8.3 Final Recommendation

Use StringBuilder for individual ticket generation and a thread-safe logging approach for the shared booking log.

## 9. Alternative Concept and Complexity Analysis

### 9.1 Using String.join()

String.join() combines multiple strings using a specified delimiter.

Example:

String ticket = String.join(" | ", fields);

It is useful when all fields are already available as strings.

### 9.2 Using StringJoiner

StringJoiner can combine multiple strings with a delimiter, prefix and suffix.

Example:

StringJoiner ticket = new StringJoiner(" | ", "[", "]");
for (String field : fields) {
    ticket.add(field);
}
String result = ticket.toString();

It is useful when a ticket needs a consistent format.

### 9.3 Using String.format()

String.format() creates formatted text using placeholders.

Example:

String ticket = String.format("PNR: %s | Name: %s",
                              "123456", "Arun");

It is useful for formatted output, but repeated formatting can involve additional processing.

### 9.4 Time Complexity Comparison

Let n represent the number of fields and L represent the average length of each field.

| Approach | Time Complexity |
|---|---|
| Repeated String concatenation | O(n² × L) |
| StringBuilder | O(n × L) amortized |
| StringBuffer | O(n × L) amortized |
| String.join() | O(n × L) |
| StringJoiner | O(n × L) |
| String.format() | Depends on formatting and input size; generally has extra formatting overhead |

The assignment describes repeated String concatenation as O(n²) character copying and StringBuilder as O(n).

### 9.5 Space Complexity

- String concatenation can create multiple intermediate String objects, increasing temporary memory usage.
- StringBuilder uses a mutable character buffer and creates a final String when toString() is called.
- StringBuffer also uses a mutable character buffer and creates a final String when toString() is called.
- String.join() and StringJoiner create the resulting combined text, with temporary processing and result storage.
- String.format() creates a formatted result String and may use additional formatting resources.

### 9.6 Final Conclusion

StringBuilder is suitable for building individual railway tickets because it reduces repeated intermediate String creation.


## 10. Experimental Results

### 10.1 Execution Time Comparison

The following results were obtained by executing the Java program.

| Workload | String (ms) | StringBuilder (ms) | StringBuffer (ms) |
|---|---:|---:|---:|
| Workload 1 | 0.0010 | 0.0006 | 0.0008 |
| Workload 2 | 29.7056 | 8.2927 | 5.1517 |
| Workload 3 | 7.1609 | 1.0456 | 1.6478 |

### 10.2 Observations

1. In Workload 1, all three approaches showed very small execution times.
2. In Workload 2, StringBuffer recorded the lowest execution time of 5.1517 ms.
3. In Workload 3, StringBuilder recorded the lowest execution time of 1.0456 ms.
4. Repeated String concatenation took more time in the larger workloads.
5. StringBuilder and StringBuffer created approximately one final String per ticket, while repeated String concatenation created additional intermediate String objects.

### 10.3 Memory Measurement

| Measurement | Result |
|---|---:|
| Memory before | 2606 KB |
| Memory after | 2606 KB |
| Memory change | 0 KB |

The measured memory change was 0 KB at the displayed precision. This is an approximate measurement and does not prove that no additional memory was allocated.

### 10.4 Limitations

The execution times may vary depending on JVM warm-up, garbage collection, system load and measurement conditions. Only five measurement repetitions were used for Workloads 2 and 3. Therefore, the results should be treated as observations from this execution rather than universal performance guarantees.

### 10.5 Final Conclusion

Repeated String concatenation showed higher execution time in the larger workloads. StringBuilder performed best in Workload 3, while StringBuffer performed best in Workload 2. StringBuilder is a suitable default choice for constructing individual e-tickets because it supports mutable text construction without synchronization overhead. The final choice should consider the application's threading requirements.
StringBuffer is an option for shared text modification when synchronized append operations are required.

The final performance decision will be based on the actual execution-time and memory measurements collected from the experiments.


