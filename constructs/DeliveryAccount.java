// Base class for delivery accounts
public class DeliveryAccount {
    private String studentId;
    private double orderValue;
    
    // One-time, class-level state initialization using a static block
    static {
        // Sets up any system-wide configurations or logging for the engine
        System.out.println("DeliveryAccount system state initialized successfully.");
    }

    // Full constructor
    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    // Provisional constructor chaining to the full constructor
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    // Reusing the final tiered surge fee logic from Problem 4
    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay minutes cannot be negative.");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        // Tiered breakdown: Bracket 1 (1-5m), Bracket 2 (6-15m), Bracket 3 (16m+)
        int bracket1Minutes = Math.min(delayMinutes, 5);
        int bracket2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        int bracket3Minutes = Math.max(0, delayMinutes - 15);

        double totalTieredPercent = (bracket1Minutes * 0.5) + (bracket2Minutes * 1.0) + (bracket3Minutes * 2.0);
        double tieredFee = (totalTieredPercent / 100.0) * this.orderValue;

        // Minimum floor rule of 1.5% for any delayed order
        double floorFee = (1.5 / 100.0) * this.orderValue;

        return Math.max(tieredFee, floorFee);
    }

    public String getStudentId() { return studentId; }
    public double getOrderValue() { return orderValue; }
}

// Subclass representing Premium members who get settled differently
class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
    public Premium(String studentId) {
        super(studentId);
    }
}

// The core reconciliation engine
class ReconciliationEngine {
    // Running summary counters (O(1) extra space strategy)
    private static int processedCount = 0;
    private static int nullSkippedCount = 0;
    private static int premiumCount = 0;
    private static int regularCount = 0;
    private static double grandTotalSurgeFees = 0.0;

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        // Safe check preventing uncaught NullPointerExceptions
        if (account == null) {
            nullSkippedCount++;
            return;
        }

        processedCount++;
        double baseSurgeFee = account.calculateSurgeFee(delayMinutes);

        // Using instanceof to decide how different accounts are settled
        if (account instanceof Premium) {
            premiumCount++;
            // Premium perk: 50% discount on final calculated surge fees
            grandTotalSurgeFees += (baseSurgeFee * 0.5);
        } else {
            regularCount++;
            // Regular accounts pay the standard calculated fee
            grandTotalSurgeFees += baseSurgeFee;
        }
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        // Early structural integrity verification
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            throw new IllegalArgumentException("Input arrays cannot be null.");
        }
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Parallel arrays length mismatch! Batch rejected for data safety.");
        }

        // Reset counters for a fresh run execution
        processedCount = 0;
        nullSkippedCount = 0;
        premiumCount = 0;
        regularCount = 0;
        grandTotalSurgeFees = 0.0;

        // Single pass execution over the batch array constraint
        for (int i = 0; i < accounts.length; i++) {
            processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
        }

        // Output summary visualization matching format guidelines
        System.out.printf("%d processed | %d null skipped | %d premium | %d regular | grand total surge fees = %.2f\n",
                processedCount, nullSkippedCount, premiumCount, regularCount, grandTotalSurgeFees);
    }
}
