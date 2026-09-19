package class_problems;

// Class locked against modification using 'final'
public final class BoardingPenaltyCalculator {
    
    // Configured state field locked against modification using 'final'
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    // Calculation method locked against overriding using 'final'
    public final double calculatePenalty(double ticketFare, int minutesLate) {
        // Validation: Reject negative values at the exact point of calculation
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late cannot be negative.");
        }

        // On-time passengers incur zero penalty (the minimum floor never applies)
        if (minutesLate == 0) {
            return 0.0;
        }

        // O(1) Closed-form division into non-overlapping tiered time brackets
        int bracket1Minutes = Math.min(minutesLate, 5);
        int bracket2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        int bracket3Minutes = Math.max(0, minutesLate - 15);

        // Calculate total tiered accumulation percentage rate
        double totalTieredPercent = (bracket1Minutes * 0.5) + (bracket2Minutes * 1.0) + (bracket3Minutes * 2.0);
        double tieredPenalty = (totalTieredPercent / 100.0) * ticketFare;

        // Apply configured minimum percentage flat-fee floor rule for delayed trips
        double floorPenalty = (this.minimumPenaltyPercent / 100.0) * ticketFare;

        // Charge whichever value is larger
        return Math.max(tieredPenalty, floorPenalty);
    }
}
