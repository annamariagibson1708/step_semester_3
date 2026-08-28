import java.util.Scanner;

public class ReverseCustomerName {


    public static String reverseCustomerName(String customerName) {
        String reversed = "";

        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed = reversed + customerName.charAt(i);
        }

        return reversed;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String originalName = scanner.nextLine().trim();

        String reversedName = reverseCustomerName(originalName);

        System.out.println("\nOutput:");
        System.out.println("Original Name: " + originalName);
        System.out.println("Reversed Name: " + reversedName);

        scanner.close();
    }
}
