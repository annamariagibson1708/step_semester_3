public class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    // Primary constructor resolving parameter naming clashes with 'this'
    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    // Chained constructor defaulting the trust score to 3
    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    // Comparison logic following Java's signed result convention
    public int compareTo(Canteen other) {
        // 1. Higher trust score comes first (descending order)
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore, this.trustScore);
        }

        // 2. Tie-breaker: Case-insensitive canteen code (ascending order)
        int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeCompare != 0) {
            return codeCompare;
        }

        // 3. Strict case tie-breaker for identical letters with different cases
        int strictCodeCompare = this.canteenCode.compareTo(other.canteenCode);
        if (strictCodeCompare != 0) {
            return strictCodeCompare;
        }

        // 4. Tie-breaker: Name length (ascending order)
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    // In-place manual selection sort algorithm (O(n²))
    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length == 0) {
            return canteens;
        }

        int n = canteens.length;
        for (int i = 0; i < n - 1; i++) {
            int bestIndex = i;
            for (int j = i + 1; j < n; j++) {
                // If canteens[j] should precede canteens[bestIndex]
                if (canteens[j].compareTo(canteens[bestIndex]) < 0) {
                    bestIndex = j;
                }
            }
            // Swap elements
            Canteen temp = canteens[bestIndex];
            canteens[bestIndex] = canteens[i];
            canteens[i] = temp;
        }
        return canteens;
    }
}
