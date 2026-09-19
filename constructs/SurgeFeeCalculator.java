// Class locked against modification using 'final'
public final class SurgeFeeCalculator {
    // Calculation field locked against modification using 'final'
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    // Method locked against overriding using 'final'
    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        // Validation: Reject negative values at the point of calculation
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes cannot be negative.");
        }

        // On-time delivery incurs zero surge fee (floor never applies)
        if (delayMinutes == 0) {
            return 0.0;
        }

        // O(1) Closed-form allocation into tiered minute brackets
        int bracket1Minutes = Math.min(delayMinutes, 5);
        int bracket2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        int bracket3Minutes = Math.max(0, delayMinutes - 15);

        // Calculate rate contribution from each bracket
        double totalTieredPercent = (bracket1Minutes * 0.5) + (bracket2Minutes * 1.0) + (bracket3Minutes * 2.0);
        double tieredFee = (totalTieredPercent / 100.0) * orderValue;

        // Apply minimum floor rule for genuinely delayed orders
        double floorFee = (this.minimumSurgePercent / 100.0) * orderValue;

        return Math.max(tieredFee, floorFee);
    }
}
