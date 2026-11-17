import java.util.Scanner;

public class PlacementDriveShortlist {

    static class Candidate {
        String name;
        int[] scores;
        int total;
        int bestGain;
        int startTest; // 1-based index
        int endTest;   // 1-based index

        Candidate(String name, int[] scores) {
            this.name = name;
            this.scores = scores;
            this.total = 0;
            for (int s : scores) {
                this.total += s;
            }
            computeBestClimb();
        }

        // Kadane's algorithm on the 5 consecutive weekly differences
        private void computeBestClimb() {
            int maxGain = Integer.MIN_VALUE;
            int currentGain = 0;
            int currentStart = 1; // 1-based test index

            this.startTest = 1;
            this.endTest = 2;

            for (int i = 0; i < scores.length - 1; i++) {
                int diff = scores[i + 1] - scores[i];

                if (currentGain + diff < diff) {
                    currentGain = diff;
                    currentStart = i + 1; // Start of this change is test (i + 1)
                } else {
                    currentGain += diff;
                }

                // Strictly greater ('>') ensures we keep the earliest run on ties
                if (currentGain > maxGain) {
                    maxGain = currentGain;
                    this.startTest = currentStart;
                    this.endTest = i + 2; // End of this change is test (i + 2)
                }
            }
            this.bestGain = maxGain;
        }
    }

    // Custom stable Merge Sort (descending by total; preserves registration order on ties)
    public static void mergeSort(Candidate[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Candidate[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Candidate[] L = new Candidate[n1];
        Candidate[] R = new Candidate[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            // Using '>=' guarantees stability: left element wins when totals are equal
            if (L[i].total >= R[j].total) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Binary search on descending array to count candidates with total >= x
    public static int countAtLeast(Candidate[] arr, int x) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].total >= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // Index of first element < x equals the count of elements >= x
    }

    // Two-pointer search on descending array for two distinct candidates summing to t
    public static String findPair(Candidate[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left].total + arr[right].total;
            if (sum == target) {
                return arr[left].name + " + " + arr[right].name;
            } else if (sum > target) {
                left++;  // Need a smaller sum; move left pointer toward lower scores
            } else {
                right--; // Need a larger sum; move right pointer toward higher scores
            }
        }
        return "no pair";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;

        int n = sc.nextInt();
        Candidate[] candidates = new Candidate[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int[] scores = new int[6];
            for (int j = 0; j < 6; j++) {
                scores[j] = sc.nextInt();
            }
            candidates[i] = new Candidate(name, scores);
        }

        // 1. Sort candidates by total score descending (stable)
        mergeSort(candidates, 0, n - 1);

        // 2. Print shortlist with best climb
        System.out.println("Shortlist:");
        for (int i = 0; i < n; i++) {
            Candidate c = candidates[i];
            String sign = c.bestGain >= 0 ? "+" : "";
            System.out.printf("%d. %s %d | best climb %s%d (test %d to %d)%n",
                    (i + 1), c.name, c.total, sign, c.bestGain, c.startTest, c.endTest);
        }

        // 3. Process CUTOFF and PAIR queries
        while (sc.hasNext()) {
            String queryType = sc.next();
            if (queryType.equals("CUTOFF")) {
                int x = sc.nextInt();
                int count = countAtLeast(candidates, x);
                System.out.println("CUTOFF " + x + ": " + count + " candidates");
            } else if (queryType.equals("PAIR")) {
                int t = sc.nextInt();
                System.out.println("PAIR " + t + ": " + findPair(candidates, t));
            }
        }
        sc.close();
    }
}