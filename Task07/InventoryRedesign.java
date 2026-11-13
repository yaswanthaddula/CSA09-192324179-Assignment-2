
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
}

public class InventoryRedesign {

    static long comparisons = 0;
    static final int REPORTS = 20;
    static final int REPEATS = 5;

    static Comparator<String> countingComparator = (a, b) -> {
        comparisons++;
        return a.compareTo(b);
    };

    static List<Medicine> createData(int size) {

        List<Medicine> medicines = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String code = String.format("MED%06d", i);
            medicines.add(
                new Medicine(code, "Medicine" + i, 100 + i)
            );
        }

        return medicines;
    }

    // Original design: HashMap and sorting for every report
    static HashMap<String, Medicine> buildOriginal(
        List<Medicine> medicines) {

        HashMap<String, Medicine> map = new HashMap<>();

        for (Medicine m : medicines) {
            map.put(m.code, m);
        }

        return map;
    }

    static long generateOriginalReports(
        HashMap<String, Medicine> map) {

        long checksum = 0;

        for (int r = 0; r < REPORTS; r++) {

            List<String> keys = new ArrayList<>(map.keySet());

            keys.sort(countingComparator);

            for (String key : keys) {
                checksum += key.length();
            }
        }

        return checksum;
    }

    // Redesigned approach: TreeMap maintains sorted keys
    static TreeMap<String, Medicine> buildRedesigned(
        List<Medicine> medicines) {

        TreeMap<String, Medicine> map =
            new TreeMap<>(countingComparator);

        for (Medicine m : medicines) {
            map.put(m.code, m);
        }

        return map;
    }

    static long generateRedesignedReports(
        TreeMap<String, Medicine> map) {

        long checksum = 0;

        for (int r = 0; r < REPORTS; r++) {

            for (String key : map.keySet()) {
                checksum += key.length();
            }
        }

        return checksum;
    }

    static double measureOriginalBuild(
        List<Medicine> medicines) {

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            HashMap<String, Medicine> map =
                buildOriginal(medicines);

            long end = System.nanoTime();
            total += end - start;
        }

        return total / (REPEATS * 1000000.0);
    }

    static double measureRedesignedBuild(
        List<Medicine> medicines) {

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {
            long start = System.nanoTime();

            TreeMap<String, Medicine> map =
                buildRedesigned(medicines);

            long end = System.nanoTime();
            total += end - start;
        }

        return total / (REPEATS * 1000000.0);
    }

    static double measureOriginalReports(
        HashMap<String, Medicine> map) {

        long total = 0;
        long checksum = 0;

        for (int r = 0; r < REPEATS; r++) {

            long start = System.nanoTime();

            checksum += generateOriginalReports(map);

            long end = System.nanoTime();
            total += end - start;
        }

        if (checksum == 0) {
            System.out.println("Invalid checksum");
        }

        return total / (REPEATS * 1000000.0);
    }

    static double measureRedesignedReports(
        TreeMap<String, Medicine> map) {

        long total = 0;
        long checksum = 0;

        for (int r = 0; r < REPEATS; r++) {

            long start = System.nanoTime();

            checksum += generateRedesignedReports(map);

            long end = System.nanoTime();
            total += end - start;
        }

        if (checksum == 0) {
            System.out.println("Invalid checksum");
        }

        return total / (REPEATS * 1000000.0);
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 10000, 100000};

        System.out.println("TASK 07 - INVENTORY REDESIGN");
        System.out.println("Original: HashMap + repeated sorting");
        System.out.println("Redesigned: TreeMap + direct iteration");
        System.out.println("Reports per measurement: " + REPORTS);
        System.out.println("Measurement repetitions: " + REPEATS);

        for (int size : sizes) {

            System.out.println("\n--------------------------------");
            System.out.println("MEDICINE COUNT: " + size);
            System.out.println("--------------------------------");

            List<Medicine> medicines = createData(size);

            double originalBuild =
                measureOriginalBuild(medicines);

            double redesignedBuild =
                measureRedesignedBuild(medicines);

            HashMap<String, Medicine> original =
                buildOriginal(medicines);

            TreeMap<String, Medicine> redesigned =
                buildRedesigned(medicines);

            comparisons = 0;

            double originalReport =
                measureOriginalReports(original);

            long originalComparisons = comparisons;

            comparisons = 0;

            double redesignedReport =
                measureRedesignedReports(redesigned);

            long redesignedReportComparisons = comparisons;

            System.out.printf(
                "%-25s %-15.4f %-15.4f%n",
                "Operation",
                originalBuild,
                redesignedBuild
            );

            System.out.printf(
                "%-25s %-15.4f %-15.4f%n",
                "Build time (ms)",
                originalBuild,
                redesignedBuild
            );

            System.out.printf(
                "%-25s %-15.4f %-15.4f%n",
                "Report time (ms)",
                originalReport,
                redesignedReport
            );

            System.out.println(
                "Original sorting comparisons: " +
                originalComparisons
            );

            System.out.println(
                "Redesigned report comparisons: " +
                redesignedReportComparisons
            );

            System.out.println(
                "Original temporary key references per report: "
                + size
            );

            System.out.println(
                "Redesigned temporary key references per report: 0"
            );

            System.out.println(
                "Medicine objects in each design: " + size
            );

            System.out.println(
                "Original and redesigned report checksums: " +
                (generateOriginalReports(original) == 
                 generateRedesignedReports(redesigned))
            );
        }

        System.out.println("\nEXPERIMENT COMPLETED");
    }
}

