import java.util.Scanner;

public class ExtensionValid {

    public static String validateFileExtension(String filename) {
        // Find the position of the last '.' character
        int lastDotIndex = filename.lastIndexOf('.');

        // Ensure a dot exists and it is not the very last character of the string
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }

        // Extract the extension using substring()
        String extension = filename.substring(lastDotIndex + 1);

        // Compare case-insensitively using equalsIgnoreCase()
        if (extension.equalsIgnoreCase("pdf") ||
                extension.equalsIgnoreCase("docx") ||
                extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a filename to validate (or type 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            // Terminate program if user types exit
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String result = validateFileExtension(input);
            System.out.println("Output: " + result);
            System.out.println();
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}
