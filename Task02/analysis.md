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

The program was executed successfully using three playlist sizes: 1,000, 10,000 and 100,000 songs.

The following results were observed:

**Playlist Size: 1,000**

| Operation | ArrayList (ms) | LinkedList (ms) | ArrayDeque (ms) |
|---|---:|---:|---:|
| Add at end | 0.0026 | 0.0012 | 0.0012 |
| Add at front | 0.0038 | 0.0037 | 0.0024 |
| Add middle | 0.0036 | 0.0110 | 0.2404 |
| Access kth | 0.0044 | 0.0094 | 0.1228 |
| Iteration | 0.1374 | 0.1931 | 0.0637 |

**Playlist Size: 10,000**

| Operation | ArrayList (ms) | LinkedList (ms) | ArrayDeque (ms) |
|---|---:|---:|---:|
| Add at end | 0.0034 | 0.0008 | 0.0010 |
| Add at front | 0.0095 | 0.0026 | 0.0005 |
| Add middle | 0.0062 | 0.0437 | 0.8306 |
| Access kth | 0.0045 | 0.0403 | 0.4250 |
| Iteration | 0.6576 | 0.7822 | 0.6056 |

**Playlist Size: 100,000**

| Operation | ArrayList (ms) | LinkedList (ms) | ArrayDeque (ms) |
|---|---:|---:|---:|
| Add at end | 0.0027 | 0.0011 | 0.0012 |
| Add at front | 0.0587 | 0.0043 | 0.0014 |
| Add middle | 0.0670 | 0.5400 | 1.8263 |
| Access kth | 0.0095 | 0.4830 | 1.4336 |
| Iteration | 5.7424 | 1.0216 | 1.4690 |

### 7.2 Performance Analysis

- ArrayList performed well in middle insertion and indexed access in the observed experiment.
- LinkedList performed better for front insertion than ArrayList at larger playlist sizes.
- ArrayDeque performed well for front insertion and end insertion.
- ArrayDeque was slower for middle insertion and indexed access because it requires traversal.
- Iteration performance varied with collection size.

### 7.3 Conclusion

ArrayList is a reasonable choice when a playlist requires frequent indexed access, iteration and adding songs at the end.

LinkedList can be considered when frequent insertions and removals at the beginning are required.

ArrayDeque is more suitable for an Up Next queue where songs are frequently added at both ends and removed from the front.

## 8. Changed Condition – Up Next Queue

ArrayDeque is suitable for the Up Next queue because it supports efficient insertion and removal at both ends.

For example:

- addFirst() adds an urgent song at the beginning.
- addLast() adds a song at the end.
- removeFirst() plays and removes the next song.

Therefore, ArrayDeque is a better choice for managing the Up Next queue than ArrayList.

## 9. Alternative Concept and Complexity Analysis

| Operation | ArrayList | LinkedList | ArrayDeque |
|---|---|---|---|
| Add at end | O(1) amortized | O(1) | O(1) amortized |
| Insert at front | O(n) | O(1) | O(1) amortized |
| Insert in middle | O(n) | O(n) | O(n) |
| Access kth element | O(1) | O(n) | O(n) |
| Iteration | O(n) | O(n) | O(n) |

### Space Complexity

- ArrayList stores elements in a resizable array, requiring O(n) space.
- LinkedList stores elements in separate nodes containing references to neighbouring nodes, requiring O(n) space with additional node overhead.
- ArrayDeque stores elements in a resizable circular array, requiring O(n) space.

### Plain Array Alternative

A plain array can store playlist songs, but inserting or removing an element requires manually shifting elements.

For example, inserting a song at the beginning requires shifting all existing elements one position to the right.

Its insertion and removal operations take O(n) time, while accessing the kth song takes O(1) time.

## 10. Final Engineering Recommendation

Based on the experiment and complexity analysis:

1. ArrayList is recommended for normal playlists that require frequent indexed access.
2. LinkedList is useful when frequent insertion and removal at the ends are required.
3. ArrayDeque is recommended for the Up Next queue because it efficiently supports operations at both ends.

The experiment shows that collection selection should depend on the application's usage pattern rather than choosing one collection for every operation.


, the choice of collection depends on the application's requirements and the operations performed frequently.
