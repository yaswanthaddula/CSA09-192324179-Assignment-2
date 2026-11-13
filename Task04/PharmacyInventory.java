
import java.util.*;

class Medicine {
    String code;
    String name;
    int quantity;

    Medicine(String code, String name, int quantity) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public String toString() {
        return code + " - " + name + " - Quantity: " + quantity;
    }
}

public class PharmacyInventory {

    static final int LOOKUPS = 100000;
    static final int REPEATS = 5;

    static HashMap<String, Medicine> hashMap;
    static TreeMap<String, Medicine> treeMap;
    static LinkedHashMap<String, Medicine> linkedMap;

    static String getCode(int i) {
        return String.format("MED%06d", i);
    }

    static Medicine createMedicine(int i) {
        return new Medicine(getCode(i), "Medicine" + i, 100 + i);
    }

    static void fillHashMap(int size) {
        hashMap = new HashMap<>();

        for (int i = 0; i < size; i++) {
            Medicine m = createMedicine(i);
            hashMap.put(m.code, m);
        }
    }

    static void fillTreeMap(int size) {
        treeMap = new TreeMap<>();

        for (int i = 0; i < size; i++) {
            Medicine m = createMedicine(i);
            treeMap.put(m.code, m);
        }
    }

    static void fillLinkedMap(int size) {
        linkedMap = new LinkedHashMap<>();

        for (int i = 0; i < size; i++) {
            Medicine m = createMedicine(i);
            linkedMap.put(m.code, m);
        }
    }

    static double measureInsertion(int type, int size) {
        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            if (type == 1) {
                fillHashMap(size);
            } else if (type == 2) {
                fillTreeMap(size);
            } else {
                fillLinkedMap(size);
            }

            long end = System.nanoTime();
            total += end - start;
        }

        return total / (REPEATS * 1000000.0);
    }

    static double measureLookup(int type, int size) {
        fillHashMap(size);
        fillTreeMap(size);
        fillLinkedMap(size);

        Random random = new Random(10);
        int[] indexes = new int[LOOKUPS];

        for (int i = 0; i < LOOKUPS; i++) {
            indexes[i] = random.nextInt(size);
        }

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            for (int index : indexes) {
                String code = getCode(index);

                if (type == 1) {
                    hashMap.get(code);
                } else if (type == 2) {
                    treeMap.get(code);
                } else {
                    linkedMap.get(code);
                }
            }

            long end = System.nanoTime();
            total += end - start;
        }

        return total / (REPEATS * 1000000.0);
    }

    static double measureIteration(int type, int size) {
        fillHashMap(size);
        fillTreeMap(size);
        fillLinkedMap(size);

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();
            int count = 0;

            if (type == 1) {
                for (Medicine m : hashMap.values()) {
                    count++;
                }
            } else if (type == 2) {
                for (Medicine m : treeMap.values()) {
                    count++;
                }
            } else {
                for (Medicine m : linkedMap.values()) {
                    count++;
                }
            }

            long end = System.nanoTime();
            total += end - start;

            if (count != size) {
                System.out.println("Iteration count error");
            }
        }

        return total / (REPEATS * 1000000.0);
    }

    static void displayOrdering() {

        HashMap<String, Medicine> h = new HashMap<>();
        TreeMap<String, Medicine> t = new TreeMap<>();
        LinkedHashMap<String, Medicine> l = new LinkedHashMap<>();

        int[] order = {3, 1, 5, 2, 4};

        for (int i : order) {
            Medicine m = new Medicine(
                getCode(i),
                "Medicine" + i,
                100 + i
            );

            h.put(m.code, m);
            t.put(m.code, m);
            l.put(m.code, m);
        }

        System.out.println("\nMAP ORDERING COMPARISON");

        System.out.println("\nHashMap (no guaranteed order):");
        for (String key : h.keySet()) {
            System.out.println(key);
        }

        System.out.println("\nTreeMap (sorted order):");
        for (String key : t.keySet()) {
            System.out.println(key);
        }

        System.out.println("\nLinkedHashMap (insertion order):");
        for (String key : l.keySet()) {
            System.out.println(key);
        }

        System.out.println("\nSORTED REPORT FROM HASHMAP:");
        List<String> sortedKeys = new ArrayList<>(h.keySet());
        Collections.sort(sortedKeys);

        for (String key : sortedKeys) {
            System.out.println(key);
        }
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 10000, 100000};

        System.out.println("PHARMACY INVENTORY MANAGEMENT");
        System.out.println("Number of repetitions: " + REPEATS);
        System.out.println("Lookups per measurement: " + LOOKUPS);

        System.out.println("\nPERFORMANCE COMPARISON");
        System.out.println("Times are average milliseconds.");

        for (int size : sizes) {

            System.out.println("\n--------------------------------");
            System.out.println("NUMBER OF MEDICINES: " + size);
            System.out.println("--------------------------------");

            System.out.printf("%-20s %-15s %-15s %-15s%n",
                "Operation", "HashMap", "TreeMap", "LinkedHashMap");

            double hPut = measureInsertion(1, size);
            double tPut = measureInsertion(2, size);
            double lPut = measureInsertion(3, size);

            System.out.printf("%-20s %-15.4f %-15.4f %-15.4f%n",
                "Insertion", hPut, tPut, lPut);

            double hGet = measureLookup(1, size);
            double tGet = measureLookup(2, size);
            double lGet = measureLookup(3, size);

            System.out.printf("%-20s %-15.4f %-15.4f %-15.4f%n",
                "100000 Lookups", hGet, tGet, lGet);

            double hIteration = measureIteration(1, size);
            double tIteration = measureIteration(2, size);
            double lIteration = measureIteration(3, size);

            System.out.printf("%-20s %-15.4f %-15.4f %-15.4f%n",
                "Iteration", hIteration, tIteration, lIteration);
        }

        displayOrdering();

        System.out.println("\nEXPERIMENT COMPLETED");
    }
}

