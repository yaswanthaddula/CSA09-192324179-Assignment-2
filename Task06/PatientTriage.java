
import java.util.*;

class Patient {
    String name;
    int severity;
    long arrival;

    Patient(String name, int severity, long arrival) {
        this.name = name;
        this.severity = severity;
        this.arrival = arrival;
    }

    public String toString() {
        return name + "(S" + severity + ")";
    }
}

public class PatientTriage {

    static Comparator<Patient> patientOrder =
        Comparator.comparingInt((Patient p) -> p.severity)
                  .thenComparingLong(p -> p.arrival);

    static final int REPEATS = 5;

    static void displayQueue(PriorityQueue<Patient> queue) {

        PriorityQueue<Patient> copy =
            new PriorityQueue<>(patientOrder);

        copy.addAll(queue);

        while (!copy.isEmpty()) {
            System.out.print(copy.poll() + " ");
        }

        System.out.println();
    }

    static double measureAdd(int size) {

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {

            PriorityQueue<Patient> queue =
                new PriorityQueue<>(patientOrder);

            long start = System.nanoTime();

            for (int i = 0; i < size; i++) {
                queue.add(new Patient(
                    "Patient" + i,
                    (i % 5) + 1,
                    i
                ));
            }

            long end = System.nanoTime();
            total += end - start;
        }

        return total / (REPEATS * 1000000.0);
    }

    static double measureRemove(int size) {

        long total = 0;

        for (int r = 0; r < REPEATS; r++) {

            PriorityQueue<Patient> queue =
                new PriorityQueue<>(patientOrder);

            for (int i = 0; i < size; i++) {
                queue.add(new Patient(
                    "Patient" + i,
                    (i % 5) + 1,
                    i
                ));
            }

            long start = System.nanoTime();

            while (!queue.isEmpty()) {
                queue.poll();
            }

            long end = System.nanoTime();
            total += end - start;
        }

        return total / (REPEATS * 1000000.0);
    }

    static void demonstrateFIFO() {

        Queue<Patient> fifoQueue = new ArrayDeque<>();

        fifoQueue.add(new Patient("PatientA", 5, 1));
        fifoQueue.add(new Patient("PatientB", 1, 2));
        fifoQueue.add(new Patient("PatientC", 3, 3));

        System.out.println("\nNORMAL FIFO QUEUE:");

        while (!fifoQueue.isEmpty()) {
            System.out.print(fifoQueue.poll() + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("HOSPITAL PATIENT TRIAGE SYSTEM");

        PriorityQueue<Patient> queue =
            new PriorityQueue<>(patientOrder);

        queue.add(new Patient("Meena", 1, 1));
        queue.add(new Patient("Kumar", 1, 2));
        queue.add(new Patient("Lata", 2, 3));
        queue.add(new Patient("Ravi", 3, 4));
        queue.add(new Patient("John", 3, 5));
        queue.add(new Patient("Asha", 5, 6));
        queue.add(new Patient("Priya", 4, 7));
        queue.add(new Patient("Dev", 2, 8));

        System.out.println("\nTOTAL PATIENTS: " + queue.size());

        System.out.println("\nDIRECT PRIORITYQUEUE PRINT:");
        System.out.println(queue);

        System.out.println("\nPATIENT SERVICE ORDER:");
        displayQueue(queue);

        System.out.println("\nSERVING PATIENTS:");

        while (!queue.isEmpty()) {
            System.out.println("Serving: " + queue.poll());
        }

        demonstrateFIFO();

        System.out.println("\nPERFORMANCE MEASUREMENT");
        System.out.println("Average time in milliseconds");
        System.out.println("Repetitions: " + REPEATS);

        int[] sizes = {1000, 10000, 100000};

        System.out.printf("%-15s %-20s %-20s%n",
            "Queue Size", "Add Time", "Remove Time");

        for (int size : sizes) {

            double addTime = measureAdd(size);
            double removeTime = measureRemove(size);

            System.out.printf("%-15d %-20.4f %-20.4f%n",
                size, addTime, removeTime);
        }

        System.out.println("\nFAIRNESS AND STARVATION");

        System.out.println("Priority 1 = Highest priority");
        System.out.println("Priority 5 = Lowest priority");
        System.out.println("Equal severity patients follow FIFO order.");

        System.out.println(
            "Aging can improve fairness by increasing the priority "
            + "of patients who wait too long."
        );

        System.out.println("\nEXPERIMENT COMPLETED");
    }
}

