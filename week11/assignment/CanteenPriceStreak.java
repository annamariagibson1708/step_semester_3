import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CanteenPriceStreak {

    // Calculates the price streak for each day in O(n) time using a monotonic stack
    public static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] streak = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // Stores indices of days

        for (int i = 0; i < n; i++) {
            // Pop all previous days whose price is less than or equal to today's price
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            // If stack is empty, all previous days had prices <= today's price
            // Otherwise, the top of the stack is the most recent day with a higher price
            streak[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());

            // Push today's index onto the stack
            stack.push(i);
        }

        return streak;
    }

    public static void main(String[] args) {
        // Sample 1
        int[] price1 = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Sample 1 Output: " + Arrays.toString(priceStreak(price1)));
        // Expected: [1, 1, 1, 2, 1, 4, 6]

        // Sample 2
        int[] price2 = {10, 20, 30};
        System.out.println("Sample 2 Output: " + Arrays.toString(priceStreak(price2)));
        // Expected: [1, 2, 3]
    }
}
