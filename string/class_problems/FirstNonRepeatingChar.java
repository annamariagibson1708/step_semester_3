
import java.util.Scanner;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        int[] charCounts = new int[256];

        for (int i = 0; i < text.length(); i++) {
            char currentBreak = text.charAt(i);
            charCounts[currentBreak]++;
        }

        for (int i = 0; i < text.length(); i++) {
            char currentBreak = text.charAt(i);
            if (charCounts[currentBreak] == 1) {
                return currentBreak; // Early-exit as soon as we find it
            }
        }

        return '\0';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or sentence: ");
        String input = scanner.nextLine();

        char result = findFirstNonRepeatingChar(input);

        System.out.println("\nOutput:");
        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        scanner.close();
    }
}
