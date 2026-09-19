package step_semester_3.access_modifier.assignment;

import java.util.Arrays;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    // One-time shared system initialization block
    static {
        System.out.println("Circulation Ledger System Online.");
    }

    // Constructor enforcing input validation gates and defensive copies
    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || memberId.trim().isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        if (bookIds == null) {
            throw new IllegalArgumentException("Book IDs array cannot be null.");
        }

        // Validation: Every book ID must match "BK-" followed by exactly 3 digits
        for (String id : bookIds) {
            if (id == null || !id.matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.memberId = memberId;
        // Defensive copy on the way in to prevent external array mutation
        this.bookIds = bookIds.clone();
    }

    // Accessor using a defensive copy on the way out
    public String[] getBookIds() {
        return this.bookIds.clone();
    }

    public String getMemberId() {
        return this.memberId;
    }

    // Non-mutating with-style modifier returning an entirely new immutable object instance
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= this.bookIds.length) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        String[] updatedIds = this.bookIds.clone();
        updatedIds[index] = newId;

        // Creating a new instance automatically runs the structural validation gate on newId
        return new LoanReceipt(this.memberId, updatedIds);
    }
}

// Specialized reference-only subclass locked against modification using final
final class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }
}

// Nightly ledger analysis engine
class CirculationLedgerEngine {

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int referenceOnlyCount = 0;
        int regularCount = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        // Single-pass processing block with O(1) auxiliary telemetry counters
        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;

            // Differentiating object variants using the instanceof operator
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnlyCount++;
            } else {
                regularCount++;
            }
        }

        // Returns telemetry summary string matching the example specifications exactly
        return String.format("%d processed | %d null skipped | %d reference-only | %d regular",
                processedCount, nullSkippedCount, referenceOnlyCount, regularCount);
    }
}
