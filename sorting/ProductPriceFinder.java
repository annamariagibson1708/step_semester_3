public class ProductPriceFinder {

    // Finds the first index where prices[index] >= target
    public static int lowerBound(int[] prices, int target) {
        int low = 0, high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Finds the first index where prices[index] > target
    public static int upperBound(int[] prices, int target) {
        int low = 0, high = prices.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prices[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void countProductsInRange(int[] prices, int minPrice, int maxPrice) {
        int lowerIdx = lowerBound(prices, minPrice);
        int upperIdx = upperBound(prices, maxPrice);
        int count = Math.max(0, upperIdx - lowerIdx);

        System.out.println("Lower bound index " + lowerIdx);
        System.out.println("Upper bound index " + upperIdx);
        System.out.println("Count " + count);
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        countProductsInRange(prices, 150, 300);
        // Expected Output:
        // Lower bound index 1
        // Upper bound index 5
        // Count 4
    }
}