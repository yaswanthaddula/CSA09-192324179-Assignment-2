
public class StringComparison {

    static volatile long resultCheck = 0;

    // Build ticket using String
    static String buildUsingString(String[] fields) {
        String ticket = "";

        for (int i = 0; i < fields.length; i++) {
            ticket = ticket + fields[i];
        }

        return ticket;
    }

    // Build ticket using StringBuilder
    static String buildUsingStringBuilder(String[] fields) {
        StringBuilder ticket = new StringBuilder();

        for (int i = 0; i < fields.length; i++) {
            ticket.append(fields[i]);
        }

        return ticket.toString();
    }

    // Build ticket using StringBuffer
    static String buildUsingStringBuffer(String[] fields) {
        StringBuffer ticket = new StringBuffer();

        for (int i = 0; i < fields.length; i++) {
            ticket.append(fields[i]);
        }

        return ticket.toString();
    }

    // Generate sample ticket fields
    static String[] createFields(int fieldCount) {
        String[] fields = new String[fieldCount];

        for (int i = 0; i < fieldCount; i++) {
            fields[i] = "Field" + i + " ";
        }

        return fields;
    }

    // Measure execution time
    static void test(String method, String[] fields,
                     int ticketCount, int repetitions) {

        long totalTime = 0;
        long checksum = 0;

        // Warm-up
        for (int i = 0; i < 100; i++) {
            if (method.equals("String")) {
                checksum += buildUsingString(fields).length();
            } else if (method.equals("StringBuilder")) {
                checksum += buildUsingStringBuilder(fields).length();
            } else {
                checksum += buildUsingStringBuffer(fields).length();
            }
        }

        for (int r = 0; r < repetitions; r++) {

            long start = System.nanoTime();

            for (int i = 0; i < ticketCount; i++) {

                String ticket;

                if (method.equals("String")) {
                    ticket = buildUsingString(fields);
                } else if (method.equals("StringBuilder")) {
                    ticket = buildUsingStringBuilder(fields);
                } else {
                    ticket = buildUsingStringBuffer(fields);
                }

                checksum += ticket.length();
            }

            long end = System.nanoTime();
            totalTime += (end - start);
        }

        resultCheck += checksum;

        double averageTime =
                totalTime / (double) repetitions / 1_000_000.0;

        System.out.printf("%-15s : %.4f ms%n",
                          method, averageTime);
    }

    // Approximate memory measurement
    static long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    static void runWorkload(String name, int ticketCount,
                            int fieldCount, int repetitions) {

        System.out.println("\n" + name);
        System.out.println("Tickets: " + ticketCount);
        System.out.println("Fields per ticket: " + fieldCount);

        String[] fields = createFields(fieldCount);

        test("String", fields, ticketCount, repetitions);
        test("StringBuilder", fields, ticketCount, repetitions);
        test("StringBuffer", fields, ticketCount, repetitions);

        System.out.println("Estimated intermediate String objects:");
        System.out.println("String: " +
                (long) ticketCount * fieldCount);
        System.out.println("StringBuilder: Approximately " +
                ticketCount + " final Strings");
        System.out.println("StringBuffer: Approximately " +
                ticketCount + " final Strings");
    }

    public static void main(String[] args) {

        System.out.println("RAILWAY E-TICKET PERFORMANCE COMPARISON");
        System.out.println("----------------------------------------");

        // Workload 1
        runWorkload("WORKLOAD 1", 1, 6, 10000);

        // Workload 2
        runWorkload("WORKLOAD 2", 10000, 50, 5);

        // Workload 3
        runWorkload("WORKLOAD 3", 1000, 100, 5);

        System.out.println("\nApproximate memory measurement");

        System.gc();

        long before = usedMemory();

        String[] fields = createFields(100);
        String ticket = buildUsingStringBuilder(fields);

        long after = usedMemory();

        System.out.println("Memory before: " + before / 1024 + " KB");
        System.out.println("Memory after : " + after / 1024 + " KB");
        System.out.println("Memory change: " +
                (after - before) / 1024 + " KB");

        System.out.println("\nChecksum: " + resultCheck);
        System.out.println("Comparison completed.");
    }
}

