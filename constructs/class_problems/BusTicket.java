package class_problems;

import java.util.HashSet;

public class BusTicket {
    private String passengerName;
    private String destination;
    private boolean isCheckedIn;

    // Parameterized constructor acting as the validation gate
    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty() ||
            destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Name and destination cannot be empty.");
        }
        
        // Validation: A name is invalid if it contains digits (e.g., "Ravi123")
        if (!passengerName.matches("^[a-zA-Z\\s]+$") || !destination.matches("^[a-zA-Z\\s]+$")) {
            throw new IllegalArgumentException("Names and destinations must contain letters only.");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.isCheckedIn = false;
    }

    // Idempotent state changer with duplicate alert
    public void markCheckedIn() {
        if (!this.isCheckedIn) {
            this.isCheckedIn = true;
            System.out.println("Ticket for " + passengerName + " successfully checked in.");
        } else {
            System.out.println("Alert: Ticket for " + passengerName + " was already checked in!");
        }
    }

    // Batch processor classifying attempts into valid, rejected, or duplicates
    public static void processBatch(String[][] rawBookings) {
        int validCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;

        if (rawBookings == null) {
            System.out.println("Valid: 0 | Rejected: 0 | Duplicates skipped: 0");
            return;
        }

        // Set to track unique passengerName + destination pairs
        HashSet<String> acceptedBookings = new HashSet<>();

        for (String[] booking : rawBookings) {
            // Structural check for malformed arrays
            if (booking == null || booking.length < 2) {
                rejectedCount++;
                continue;
            }

            String name = booking[0];
            String dest = booking[1];

            try {
                // Step 1: Validate inputs via the constructor gate
                BusTicket ticket = new BusTicket(name, dest);
                
                // Unique key combination for duplicate tracking
                String uniqueKey = name.trim().toLowerCase() + "|" + dest.trim().toLowerCase();

                // Step 2: Check for duplicate pairings
                if (acceptedBookings.contains(uniqueKey)) {
                    duplicateCount++;
                } else {
                    acceptedBookings.add(uniqueKey);
                    validCount++;
                }
            } catch (IllegalArgumentException e) {
                // Step 3: Count validation failures as rejected
                rejectedCount++;
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount + " | Duplicates skipped: " + duplicateCount);
    }
}
