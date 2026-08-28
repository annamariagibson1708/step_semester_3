import java.util.Scanner;

public class wordrev {

    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) {
                wordBuilder.append(words[i].charAt(j));
            }
            result.append(wordBuilder);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println(reverseEachWord(input));
        }
        scanner.close();
    }
}
