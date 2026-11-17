import java.util.ArrayDeque;
import java.util.Deque;

public class HackathonBannerPrinter {

    // Expands the compressed banner design code iteratively using two stacks
    public static String expandBanner(String code) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : code.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Build multi-digit repeat count k
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Push the repeat count and current string built so far onto their stacks
                countStack.push(k);
                stringStack.push(currentString);
                
                // Reset for the new inner bracket scope
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // Pop the repeat count and previous string builder
                int repeatTimes = countStack.pop();
                StringBuilder decodedString = stringStack.pop();
                
                // Append the current bracket's expanded text repeatTimes times
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // Regular character: append directly to the current builder
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        // Sample 1
        String code1 = "3[ab]2[c]";
        System.out.println("Sample 1 Output: " + expandBanner(code1));
        // Expected: abababcc

        // Sample 2
        String code2 = "2[x3[y]]z";
        System.out.println("Sample 2 Output: " + expandBanner(code2));
        // Expected: xyyyxyyyz
    }
}