import java.util.Scanner;

public class atmpin {

    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            checkPinLength(input);
        }
        scanner.close();
    }
}
