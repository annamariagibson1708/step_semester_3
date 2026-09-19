package step_semester_3.access_modifier.assignment;

public class BookInventory {
    // Encapsulated data fields locked to private visibility
    private final int copiesTotal;
    private int copiesAvailable;

    // Parameterized constructor acting as the initial validation gate
    public BookInventory(int copiesTotal) {
        // Reject zero or negative totals outright
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.copiesTotal = copiesTotal;
        // Initially, all copies are available in circulation
        this.copiesAvailable = copiesTotal;
    }

    // Decrements available stock while preventing negative bounds underflow
    public void checkOut() {
        if (this.copiesAvailable > 0) {
            this.copiesAvailable--;
        }
        // Silently rejects transitions that would push the count below 0
    }

    // Increments available stock while keeping the invariant capped below total capacity
    public void checkIn() {
        if (this.copiesAvailable < this.copiesTotal) {
            this.copiesAvailable++;
        }
        // Silently rejects transitions that exceed the structural maximum capacity
    }

    // Public accessor to read the protected state safely
    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }
}
