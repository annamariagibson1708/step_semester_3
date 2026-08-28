import java.util.Scanner;

public class transacvalid {

    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        // Avoid index out of bounds if input is too short
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        // Uppercase only the first 3 characters, leave the rest untouched
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        // 1. Check length
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // 2. Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: non-letter bank code";
            }
        }

        // 3. Validate remaining 11 characters are digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        // 4. Extract segments for formatting (ddMMmmyy -> dd/MM/yy)
        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequence = reference.substring(9, 14);

        // 5. Build output using StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
                .append(day).append("/").append(month).append("/").append(year)
                .append(" | SEQ: ").append(sequence);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Bank Transaction Reference (or 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            // Multi-stage process: Normalize first, then validate/format
            String normalized = normalizeReference(input);
            String result = validateAndFormat(normalized);

            System.out.println("Output: " + result);
            System.out.println();
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}
