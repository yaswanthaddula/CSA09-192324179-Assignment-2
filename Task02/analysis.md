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

## 7. Results and Conclusion
### 7.1 Experimental Results

The Java program was executed successfully using three different playlist sizes: 1,000, 10,000 and 100,000 songs.

The following observations were recorded:

| Operation | Best Performing Collection (Observed) |
|---|---|
| Add at end | LinkedList / ArrayDeque |
| Add at front | ArrayDeque |
| Add in middle | ArrayList |
| Access kth song | ArrayList |
| Iteration | ArrayDeque for 1,000 and 10,000; LinkedList for 100,000 |

### 7.2 Analysis

- ArrayList performed well in accessing songs by index.
- LinkedList provided efficient insertion at the beginning.
- ArrayDeque performed well when adding songs at the front and end.
- ArrayDeque was slower for middle insertion and indexed access because traversal was required.
- Performance varied with playlist size and execution conditions.

### 7.3 Conclusion

The experiment demonstrates that no single collection is best for every playlist operation.

ArrayList is suitable for fast random access, LinkedList is useful for insertion and removal at the ends, and ArrayDeque is suitable for managing an Up Next queue.

Therefore, the choice of collection depends on the application's requirements and the operations performed frequently.
