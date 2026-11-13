
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

