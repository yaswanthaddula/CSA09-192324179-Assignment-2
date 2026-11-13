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

To be completed after executing the Java program and recording the experimental results.
