# Task 06 – Priority, Order and Fairness in a Queue

## 1. Problem Understanding

A hospital needs a patient triage system to decide which patient should receive treatment first.

Patients have different severity levels. Patients with more serious conditions must receive treatment before patients with less serious conditions.

A normal FIFO queue serves patients in arrival order, whereas a PriorityQueue serves patients according to their priority.

This experiment uses Java Queue and PriorityQueue to demonstrate patient management, priority ordering, fairness, and performance.

## 2. Objective

- Understand the Java Queue interface.
- Implement a normal FIFO queue.
- Implement a PriorityQueue for hospital patient triage.
- Serve patients according to severity.
- Maintain FIFO order for patients with equal severity.
- Measure insertion and removal performance.
- Understand fairness and starvation in priority-based queues.

## 3. Predictions

### 3.1 Normal FIFO Queue

**Prediction:** Patients will be served in the same order in which they arrive, regardless of severity.

### 3.2 PriorityQueue

**Prediction:** Patients with severity 1 will be served first, followed by severity 2, 3, 4, and 5.

### 3.3 Equal Priority

**Prediction:** Patients with equal severity will be served in their arrival order.

### 3.4 Performance

**Prediction:** Insertion and removal times will increase as the number of patients increases.

## 4. Experimental Design

The experiment used two queue implementations:

1. `ArrayDeque` for normal FIFO queue operations.
2. `PriorityQueue` for priority-based patient management.

Each patient record contains:

- Patient name
- Severity level
- Arrival order

Severity levels were defined as follows:

| Severity | Priority |
|---|---|
| 1 | Highest |
| 2 | High |
| 3 | Medium |
| 4 | Low |
| 5 | Lowest |

A comparator was used to sort patients first by severity and then by arrival order.

Performance was measured using three queue sizes:

- 1,000 patients
- 10,000 patients
- 100,000 patients

Each measurement was repeated five times, and average execution time was calculated in milliseconds.

## 5. Experimental Results

### 5.1 Total Patients

**Observed Output:**

```text id="0s5d2h"
TOTAL PATIENTS: 8
```

**Analysis:**

The PriorityQueue successfully stored all eight patient records.

### 5.2 Direct PriorityQueue Printing

**Observed Output:**

```text id="c4d8as"
DIRECT PRIORITYQUEUE PRINT:
[Meena(S1), Kumar(S1), Lata(S2), Dev(S2), John(S3), Asha(S5), Priya(S4), Ravi(S3)]
```

**Analysis:**

Directly printing a PriorityQueue does not guarantee fully sorted output.

The PriorityQueue maintains its internal heap structure, where the highest-priority element is available at the head. The remaining elements are not necessarily displayed in sorted order.

Therefore, repeated `poll()` operations are required to obtain the actual service order.

### 5.3 Patient Service Order

**Observed Output:**

```text id="b0qz4m"
PATIENT SERVICE ORDER:
Meena(S1) Kumar(S1) Lata(S2) Dev(S2) Ravi(S3) John(S3) Priya(S4) Asha(S5)
```

**Analysis:**

The patients were served in ascending severity order.

Patients with severity 1 were served first, followed by severity 2, severity 3, severity 4, and severity 5.

Patients with the same severity were served according to arrival order.

For example:

- Meena arrived before Kumar, and both had severity 1.
- Ravi arrived before John, and both had severity 3.

This confirms that the comparator correctly handled both priority and FIFO ordering.

### 5.4 Serving Patients

**Observed Output:**

```text id="m9a1kw"
SERVING PATIENTS:
Serving: Meena(S1)
Serving: Kumar(S1)
Serving: Lata(S2)
Serving: Dev(S2)
Serving: Ravi(S3)
Serving: John(S3)
Serving: Priya(S4)
Serving: Asha(S5)
```

**Analysis:**

The `poll()` method removed and returned the highest-priority patient from the queue.

All eight patients were served in the expected order, and the queue became empty after processing.

### 5.5 Normal FIFO Queue

**Observed Output:**

```text id="6js5dh"
NORMAL FIFO QUEUE:
PatientA(S5) PatientB(S1) PatientC(S3)
```

**Analysis:**

The normal FIFO queue served patients according to their arrival order.

Although PatientB had the highest severity, PatientA was served first because PatientA entered the queue earlier.

This demonstrates the difference between a normal queue and a priority-based queue.

## 6. Performance Measurements

All execution times are average milliseconds over five repetitions.

| Queue Size | Add Time (ms) | Remove Time (ms) |
|---:|---:|---:|
| 1,000 | 0.8673 | 1.8816 |
| 10,000 | 1.3146 | 2.6642 |
| 100,000 | 8.0878 | 28.4623 |

### 6.1 Insertion Analysis

The insertion time increased as the number of patients increased.

- 1,000 patients: 0.8673 ms
- 10,000 patients: 1.3146 ms
- 100,000 patients: 8.0878 ms

This shows that adding more patients requires more processing.


