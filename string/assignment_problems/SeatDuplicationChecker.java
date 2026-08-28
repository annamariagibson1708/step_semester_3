
import java.util.Scanner;

public class SeatDuplicationChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    duplicateFound = true;
                    break; // Break internal loop to avoid printing the same pair multiple times
                }
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of total seats to check: ");
        int totalSeats = scanner.nextInt();

        int[] seatNumbers = new int[totalSeats];

        System.out.println("Enter the " + totalSeats + " seat numbers one by one:");
        for (int i = 0; i < totalSeats; i++) {
            System.out.print("Seat " + (i + 1) + ": ");
            seatNumbers[i] = scanner.nextInt();
        }

        System.out.println("\nOutput:");
        checkDuplicateSeats(seatNumbers);

        scanner.close();
    }
}
