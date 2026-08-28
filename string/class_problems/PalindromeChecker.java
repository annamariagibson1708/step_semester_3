//package string.class_problems;

import java.util.Scanner;

public class PalindromeChecker {


    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false; // Found a mismatch
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true; // Single letters or empty texts are palindromes
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false; // First and last letters don't match
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] letters = text.toCharArray();
        String reversedText = "";

        for (int i = letters.length - 1; i >= 0; i--) {
            reversedText = reversedText + letters[i];
        }

        return text.equals(reversedText);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String input = scanner.nextLine().trim();

        boolean res1 = isPalindromeIterative(input);
        boolean res2 = isPalindromeRecursive(input);
        boolean res3 = isPalindromeArrayReversal(input);

        String out1 = res1 ? "Palindrome" : "Not Palindrome";
        String out2 = res2 ? "Palindrome" : "Not Palindrome";
        String out3 = res3 ? "Palindrome" : "Not Palindrome";

        System.out.println("\nOutput:");
        System.out.println("Iterative: " + out1 + " | Recursive: " + out2 + " | Array Reversal: " + out3);

        scanner.close();
    }
}
