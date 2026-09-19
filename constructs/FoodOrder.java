public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean isDelivered;

    // Parameterized constructor validating fields
    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() || 
            dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name or dish name.");
        }
        this.studentName = studentName;
        this.dishName = dishName;
        this.isDelivered = false;
    }

    // Handles delivery flags and duplicate calls
    public void markDelivered() {
        if (!this.isDelivered) {
            this.isDelivered = true;
            System.out.println("Order for " + studentName + " delivered.");
        } else {
            System.out.println("Alert: Order for " + studentName + " was already delivered!");
        }
    }

    // Processes a batch of raw text inputs
    public static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;

        if (rawOrders == null) {
            System.out.println("Valid: 0 | Rejected: 0");
            return;
        }

        for (String[] order : rawOrders) {
            // Check if the inner array contains both name and dish
            if (order != null && order.length >= 2) {
                try {
                    new FoodOrder(order[0], order[1]);
                    validCount++;
                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            } else {
                rejectedCount++;
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }
}
