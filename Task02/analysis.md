# Q2: Playlist Management Using Java Collections

## 1. Problem Understanding

A music playlist application needs to store and manage songs. Different collection classes provide different performance for adding, removing, accessing and iterating through songs.

This experiment compares ArrayList, LinkedList and ArrayDeque.

## 2. Objective

To compare the performance of ArrayList, LinkedList and ArrayDeque for different playlist operations and identify the most suitable collection for each operation.

## 3. Prediction Before Implementation

- ArrayList: Expected to provide fast random access and efficient insertion at the end.
- LinkedList: Expected to support efficient insertion and removal at the beginning and end.
- ArrayDeque: Expected to perform efficiently for adding and removing elements at both ends.

## 4. Experimental Workloads

Three collection sizes will be tested:

1. 1,000 songs
2. 10,000 songs
3. 100,000 songs

## 5. Operations to Compare

1. Adding songs at the end.
2. Inserting and removing songs at the beginning.
3. Inserting and removing songs in the middle.
4. Accessing the kth song.
5. Iterating through all songs.

## 6. Playlist Queue Design

ArrayDeque is considered for the Up Next queue because it supports adding and removing songs efficiently at both ends.

## 7. Results and Performance Analysis

The program was executed using three playlist sizes: 1,000, 10,000 and 100,000 songs. The average execution time was measured in milliseconds for each operation.

### 7.1 Results for 1,000 Songs

| Operation | ArrayList (ms) | LinkedList (ms) | ArrayDeque (ms) |
|---|---:|---:|---:|
| Add at end | 0.0018 | 0.0011 | 0.0014 |
| Add at front | 0.0041 | 0.0032 | 0.0016 |
| Add middle | 0.0031 | 0.0122 | 0.1935 |
| Access kth | 0.0046 | 0.0096 | 0.2141 |
| Iteration | 0.1610 | 0.1505 | 0.0342 |
| Remove front | 0.0049 | 0.0030 | 0.0062 |
| Remove middle | 0.0043 | 0.0107 | 0.0941 |

### 7.2 Results for 10,000 Songs

| Operation | ArrayList (ms) | LinkedList (ms) | ArrayDeque (ms) |
|---|---:|---:|---:|
| Add at end | 0.0019 | 0.0007 | 0.0008 |
| Add at front | 0.0107 | 0.0029 | 0.0005 |
| Add middle | 0.0091 | 0.0698 | 1.2093 |
| Access kth | 0.0060 | 0.0553 | 0.4653 |
| Iteration | 0.6714 | 0.9026 | 0.5368 |
| Remove front | 0.0152 | 0.0018 | 0.0009 |
| Remove middle | 0.0127 | 0.0733 | 0.6627 |

### 7.3 Results for 100,000 Songs

| Operation | ArrayList (ms) | LinkedList (ms) | ArrayDeque (ms) |
|---|---:|---:|---:|
| Add at end | 0.0026 | 0.0013 | 0.0010 |
| Add at front | 0.0571 | 0.0038 | 0.0014 |
| Add middle | 0.0305 | 0.4925 | 1.8472 |
| Access kth | 0.0108 | 0.6341 | 1.5353 |
| Iteration | 2.6529 | 1.1475 | 1.2650 |
| Remove front | 0.1580 | 0.0019 | 0.0009 |
| Remove middle | 0.0808 | 0.3692 | 2.4076 |

### 7.4 Performance Analysis

1. **Add at end:** All three collections performed quickly in the tested workloads.
2. **Add at front:** ArrayDeque performed best for larger playlists because it supports efficient insertion at the front.
3. **Add middle:** ArrayList performed better in these measurements. ArrayDeque was slower because reaching the middle requires traversal.
4. **Access kth song:** ArrayList was the fastest because it supports direct index-based access.
5. **Iteration:** The results varied with playlist size. All three collections support iteration in O(n) time.
6. **Remove front:** LinkedList and ArrayDeque performed efficiently for larger playlists, while ArrayList required shifting elements.
7. **Remove middle:** ArrayList performed best in these measurements. LinkedList and ArrayDeque required traversal to reach the middle position.

### 7.5 Overall Observation

No single collection is the best choice for every playlist operation. ArrayList is suitable for frequent random access, LinkedList supports efficient operations at both ends, and ArrayDeque is suitable for an Up Next queue.

## 8. Time Complexity Comparison

| Operation | ArrayList | LinkedList | ArrayDeque |
|---|---|---|---|
| Add at end | O(1) amortized | O(1) | O(1) amortized |
| Add at front | O(n) | O(1) | O(1) amortized |
| Add middle | O(n) | O(n) | O(n) |
| Access kth | O(1) | O(n) | O(n) |
| Iteration | O(n) | O(n) | O(n) |
| Remove front | O(n) | O(1) | O(1) amortized |
| Remove middle | O(n) | O(n) | O(n) |

### 8.1 Space Complexity

All three collections require O(n) space to store n songs.

- **ArrayList:** Uses a resizable array.
- **LinkedList:** Uses nodes that store song data and links to other nodes.
- **ArrayDeque:** Uses a resizable circular array.

### 8.2 Plain Array Alternative

A plain array can also store playlist songs, but its size is fixed after creation.

- Accessing the kth song takes O(1) time.
- Inserting or removing a song in the middle takes O(n) time because elements must be shifted.
- Iterating through all songs takes O(n) time.

Therefore, a plain array is less flexible when the playlist size changes frequently.

## 9. Up Next Queue Design Using ArrayDeque

ArrayDeque is suitable for implementing the Up Next queue because it supports efficient insertion and removal at both ends.

The following operations can be used:

- `addFirst(song)` – Adds a song at the front.
- `addLast(song)` – Adds a song at the end.
- `removeFirst()` – Removes the next song from the front.
- `removeLast()` – Removes a song from the end.

For example:

1. Add a priority song at the front using `addFirst()`.
2. Add upcoming songs at the end using `addLast()`.

This makes ArrayDeque a suitable choice for managing the Up Next queue.

## 10. Final Conclusion

The experiment compared ArrayList, LinkedList and ArrayDeque for different playlist operations using three collection sizes.

- ArrayList is suitable when frequent random access and middle operations are required.
- LinkedList is suitable when frequent insertion and removal at the beginning or end are required.
- ArrayDeque is suitable for an Up Next queue where songs are mainly added and removed from both ends.

Based on the experiment, ArrayList is a good choice for general playlist storage, while ArrayDeque is a better choice for the Up Next queue.

The final collection should be selected based on the operations performed most frequently in the application.
