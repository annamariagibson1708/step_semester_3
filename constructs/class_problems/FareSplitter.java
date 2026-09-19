package class_problems;

public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    // 1. Primary parameterized constructor performing all validation gates
    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative.");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be greater than zero.");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // 2. Chained constructor defaulting to a standard group size of 2 passengers
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    // 3. Chained constructor for provisional splits defaulting to a 0.0 fare
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    // Distribution breakdown logic handling uneven division safely down to the exact paisa/cent
    public double[] fareBreakdown() {
        double[] breakdown = new double[passengerCount];
        
        // Convert total fare to absolute cents to eliminate floating-point truncation issues
        long totalCents = Math.round(totalFare * 100);
        long baseCents = totalCents / passengerCount;
        long remainderCents = totalCents % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            long individualCents = baseCents;
            
            // Distribute leftover cents consistently starting from the end of the group
            if (i >= passengerCount - remainderCents) {
                individualCents += 1;
            }
            
            breakdown[i] = individualCents / 100.0;
        }

        return breakdown;
    }

    // Verifies confirmation status and rejects invalid tracking configurations
    public boolean isConfirmationOverdue(int confirmed, int expected) {
        // Validate inputs to reject a tracking matrix that was never valid to begin with
        if (expected <= 0 || confirmed < 0 || confirmed > expected) {
            throw new IllegalArgumentException("Invalid confirmation metrics provided.");
        }
        
        // Group is behind schedule if the confirmed count falls short of expectations
        return confirmed < expected;
    }
}
