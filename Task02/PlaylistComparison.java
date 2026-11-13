import java.util.*;

public class PlaylistComparison {

    static final int REPEATS = 3;

    static ArrayList<String> createArrayList(int size) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add("Song" + i);
        }
        return list;
    }

    static LinkedList<String> createLinkedList(int size) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add("Song" + i);
        }
        return list;
    }

    static ArrayDeque<String> createArrayDeque(int size) {
        ArrayDeque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            deque.addLast("Song" + i);
        }
        return deque;
    }

    static void insertMiddle(ArrayDeque<String> deque, String song) {
        ArrayDeque<String> temp = new ArrayDeque<>();
        int middle = deque.size() / 2;

        for (int i = 0; i < middle; i++) {
            temp.addLast(deque.removeFirst());
        }

        deque.addFirst(song);

        while (!temp.isEmpty()) {
            deque.addFirst(temp.removeLast());
        }
    }

    static void removeMiddle(ArrayDeque<String> deque) {
        ArrayDeque<String> temp = new ArrayDeque<>();
        int middle = deque.size() / 2;

        for (int i = 0; i < middle; i++) {
            temp.addLast(deque.removeFirst());
        }

        if (!deque.isEmpty()) {
            deque.removeFirst();
        }

        while (!temp.isEmpty()) {
            deque.addFirst(temp.removeLast());
        }
    }

    static String getDequeSong(ArrayDeque<String> deque, int index) {
        int count = 0;

        for (String song : deque) {
            if (count == index) {
                return song;
            }
            count++;
        }

        return null;
    }

    static long measure(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        return System.nanoTime() - start;
    }

    static void testCollections(int size) {

        System.out.println("\n==================================");
        System.out.println("PLAYLIST SIZE: " + size);
        System.out.println("==================================");

        long arrayEnd = 0, linkedEnd = 0, dequeEnd = 0;
        long arrayFront = 0, linkedFront = 0, dequeFront = 0;
        long arrayMiddle = 0, linkedMiddle = 0, dequeMiddle = 0;
        long arrayAccess = 0, linkedAccess = 0, dequeAccess = 0;
        long arrayIteration = 0, linkedIteration = 0, dequeIteration = 0;

        long checksum = 0;
        int middle = size / 2;

        for (int r = 0; r < REPEATS; r++) {

            ArrayList<String> a = createArrayList(size);
            LinkedList<String> l = createLinkedList(size);
            ArrayDeque<String> d = createArrayDeque(size);

            arrayEnd += measure(() -> a.add("NewSong"));
            linkedEnd += measure(() -> l.add("NewSong"));
            dequeEnd += measure(() -> d.addLast("NewSong"));

            a = createArrayList(size);
            l = createLinkedList(size);
            d = createArrayDeque(size);

            ArrayList<String> a1 = a;
            LinkedList<String> l1 = l;
            ArrayDeque<String> d1 = d;

            arrayFront += measure(() -> a1.add(0, "NewSong"));
            linkedFront += measure(() -> l1.addFirst("NewSong"));
            dequeFront += measure(() -> d1.addFirst("NewSong"));

            a = createArrayList(size);
            l = createLinkedList(size);
            d = createArrayDeque(size);

            ArrayList<String> a2 = a;
            LinkedList<String> l2 = l;
            ArrayDeque<String> d2 = d;

            arrayMiddle += measure(() -> a2.add(middle, "NewSong"));
            linkedMiddle += measure(() -> l2.add(middle, "NewSong"));
            dequeMiddle += measure(() -> insertMiddle(d2, "NewSong"));

            a = createArrayList(size);
            l = createLinkedList(size);
            d = createArrayDeque(size);

            ArrayList<String> a3 = a;
            LinkedList<String> l3 = l;
            ArrayDeque<String> d3 = d;

            arrayAccess += measure(() -> checksumHolder(a3.get(middle)));
            linkedAccess += measure(() -> checksumHolder(l3.get(middle)));
            dequeAccess += measure(() -> checksumHolder(getDequeSong(d3, middle)));

            a = createArrayList(size);
            l = createLinkedList(size);
            d = createArrayDeque(size);

            ArrayList<String> a4 = a;
            LinkedList<String> l4 = l;
            ArrayDeque<String> d4 = d;

            arrayIteration += measure(() -> checksumHolder(iterateList(a4)));
            linkedIteration += measure(() -> checksumHolder(iterateList(l4)));
            dequeIteration += measure(() -> checksumHolder(iterateDeque(d4)));

            // Remove the first song
            a = createArrayList(size);
            l = createLinkedList(size);
            d = createArrayDeque(size);

            ArrayList<String> a5 = a;
            LinkedList<String> l5 = l;
            ArrayDeque<String> d5 = d;

            measure(() -> a5.remove(0));
            measure(() -> l5.removeFirst());
            measure(() -> d5.removeFirst());

            // Remove the middle song
            a = createArrayList(size);
            l = createLinkedList(size);
            d = createArrayDeque(size);

            ArrayList<String> a6 = a;
            LinkedList<String> l6 = l;
            ArrayDeque<String> d6 = d;

            measure(() -> a6.remove(middle));
            measure(() -> l6.remove(middle));
            measure(() -> removeMiddle(d6));
        }

        System.out.println("\nAVERAGE TIME IN MILLISECONDS");
        System.out.println("Operation       ArrayList    LinkedList    ArrayDeque");

        printRow("Add at end", arrayEnd, linkedEnd, dequeEnd);
        printRow("Add at front", arrayFront, linkedFront, dequeFront);
        printRow("Add middle", arrayMiddle, linkedMiddle, dequeMiddle);
        printRow("Access kth", arrayAccess, linkedAccess, dequeAccess);
        printRow("Iteration", arrayIteration, linkedIteration, dequeIteration);

        System.out.println("\nRemoval operations were also tested.");
        System.out.println("Checksum: " + checksum);
    }

    static long checksum = 0;

    static void checksumHolder(String value) {
        if (value != null) {
            checksum += value.length();
        }
    }

    static String iterateList(List<String> list) {
        String last = "";
        for (String song : list) {
            last = song;
        }
        return last;
    }

    static String iterateDeque(ArrayDeque<String> deque) {
        String last = "";
        for (String song : deque) {
            last = song;
        }
        return last;
    }

    static void printRow(String name, long a, long l, long d) {
        System.out.printf("%-15s %-12.4f %-13.4f %.4f%n",
                name,
                a / (double) REPEATS / 1_000_000.0,
                l / (double) REPEATS / 1_000_000.0,
                d / (double) REPEATS / 1_000_000.0);
    }

    public static void main(String[] args) {

        System.out.println("PLAYLIST COLLECTION PERFORMANCE COMPARISON");

        testCollections(1000);
        testCollections(10000);
        testCollections(100000);

        System.out.println("\nComparison completed.");
    }
}
