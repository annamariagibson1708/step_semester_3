
import java.util.Scanner;

public class TrafficSignalStreak {

    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.length() == 0) {
            System.out.println("No signals recorded in log.");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int longestStreak = 1;

        char currentColor = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            char nextChar = signalLog.charAt(i);

            if (nextChar == currentColor) {
                currentStreak++;
            } else {
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                    longestColor = currentColor;
                }
                currentColor = nextChar;
                currentStreak = 1;
            }
        }

        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
            longestColor = currentColor;
        }

        System.out.println("Longest Streak: '" + longestColor + "' repeated " + longestStreak + " times");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter traffic signal readings log (e.g., RRGGGYRR): ");
        String input = scanner.nextLine().trim();

        System.out.println("\nOutput:");
        findLongestStreak(input);

        scanner.close();
    }
}
