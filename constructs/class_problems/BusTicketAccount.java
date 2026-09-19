package class_problems;
// Base class for managing passenger ticket financial routing
public class BusTicketAccount {
    private String bookingId;
    private double ticketFare;

    // Static block for handling system-wide, class-level state initialization
    static {
        System.out.println("BusTicketAccount fleet management system initialized.");
    }

    // Full parameterized constructor
    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking ID cannot be null or blank.");
        }
        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative.");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    // Provisional chained constructor defaulting fare to 0.0
    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    // Final method reusing the O(1) tiered boarding penalty system from Problem 4
    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative.");
        }
        if (minutesLate == 0) {
            return 0.0;
        }

        // Bracket limits: 1-5 mins (0.5%), 6-15 mins (1.0%), 16+ mins (2.0%)
        int bracket1 = Math.min(minutesLate, 5);
        int bracket2 = Math.max(0, Math.min(minutesLate - 5, 10));
        int bracket3 = Math.max(0, minutesLate - 15);

        double totalPercent = (bracket1 * 0.5) + (bracket2 * 1.0) + (bracket3 * 2.0);
        double tieredPenalty = (totalPercent / 100.0) * this.ticketFare;

        // Standard 1.0% minimum penalty flat-fee floor for delayed passengers
        double floorPenalty = (1.0 / 100.0) * this.ticketFare;

        return Math.max(tieredPenalty, floorPenalty);
    }

    public String getBookingId() { return bookingId; }
    public double getTicketFare() { return ticketFare; }
}

// Specialized subclass settling under custom high-tier passenger rules
class Sleeper extends BusTicketAccount {
    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}

// Core reconciliation processor
class NightlyReconciliationEngine {
    // Shared running counters optimized for single-pass O(1) auxiliary memory
    private static int processedCount = 0;
    private static int nullSkippedCount = 0;
    private static int sleeperCount = 0;
    private static int regularCount = 0;
    private static double grandTotalPenalties = 0.0;

    // Evaluates a single record using safe instance type checks
    public static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        // Prevent uncaught NullPointerExceptions from upstream processing defects
        if (account == null) {
            nullSkippedCount++;
            return;
        }

        processedCount++;
        double penalty = account.calculatePenalty(minutesLate);

        // Polymorphic separation utilizing the instanceof operator
        if (account instanceof Sleeper) {
            sleeperCount++;
            // Sleeper Benefit: 50% discount waiver applied directly at settlement
            grandTotalPenalties += (penalty * 0.5);
        } else {
            regularCount++;
            grandTotalPenalties += penalty;
        }
    }

    // Handles parallel array structures safely inside a single pass loop
    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        // Early defensive structural verification
        if (accounts == null || amounts == null || minutesLateArray == null) {
            throw new IllegalArgumentException("Batch source references cannot be null.");
        }
        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Parallel array structure misalignment. Processing terminated.");
        }

        // Reset system context state for the current run iteration
        processedCount = 0;
        nullSkippedCount = 0;
        sleeperCount = 0;
        regularCount = 0;
        grandTotalPenalties = 0.0;

        // O(N) execution pass checking every element context
        for (int i = 0; i < accounts.length; i++) {
            processAccount(accounts[i], amounts[i], minutesLateArray[i]);
        }

        // Print output formatted to match system telemetry specifications
        System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = %.1f\n",
                processedCount, nullSkippedCount, sleeperCount, regularCount, grandTotalPenalties);
    }
}
