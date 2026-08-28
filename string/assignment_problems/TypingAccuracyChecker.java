
import java.util.Scanner;

public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int totalChars = original.length();
        int matchedCount = 0;
        int firstMismatchPos = -1;

        for (int i = 0; i < totalChars; i++) {
            char origChar = original.charAt(i);
            char typedChar = typed.charAt(i);

            if (origChar == typedChar) {
                matchedCount++;
            } else {
                if (firstMismatchPos == -1) {
                    firstMismatchPos = i + 1;
                }
            }
        }

        double accuracy = ((double) matchedCount / totalChars) * 100;

        String outputStr = "Matched: " + matchedCount + "/" + totalChars +
                " | Accuracy: " + String.format("%.2f%%", accuracy) + " | ";

        if (firstMismatchPos == -1) {
            outputStr += "No Mismatches";
        } else {
            char origC = original.charAt(firstMismatchPos - 1);
            char typedC = typed.charAt(firstMismatchPos - 1);
            outputStr += "First Mismatch at position " + firstMismatchPos + " ('" + origC + "' vs '" + typedC + "')";
        }

        System.out.println(outputStr);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();

        System.out.print("Enter your typed text: ");
        String typed = scanner.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Error: Both texts must have exactly the same length for comparison!");
        } else {
            System.out.println("\nOutput:");
            checkTypingAccuracy(original, typed);
        }

        scanner.close();
    }
}
