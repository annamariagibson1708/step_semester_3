import java.util.Scanner;

public class WarehouseInventory {

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        int highestQty = Integer.MIN_VALUE;
        String highestSection = "";
        int highestIndex = -1;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];

            if (sectionA[i] > highestQty) {
                highestQty = sectionA[i];
                highestSection = "Section A";
                highestIndex = i + 1; // 1-based indexing for display (Item 1, Item 2...)
            }

            if (sectionB[i] > highestQty) {
                highestQty = sectionB[i];
                highestSection = "Section B";
                highestIndex = i + 1;
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)\n",
                totalA, totalB, status, highestQty, highestSection, highestIndex);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of item categories: ");
        int categories = scanner.nextInt();

        int[] sectionA = new int[categories];
        int[] sectionB = new int[categories];

        System.out.println("\nEnter quantities for Section A:");
        for (int i = 0; i < categories; i++) {
            System.out.print("Category " + (i + 1) + ": ");
            sectionA[i] = scanner.nextInt();
        }

        System.out.println("\nEnter quantities for Section B:");
        for (int i = 0; i < categories; i++) {
            System.out.print("Category " + (i + 1) + ": ");
            sectionB[i] = scanner.nextInt();
        }

        System.out.println("\nOutput:");
        analyzeInventory(sectionA, sectionB);

        scanner.close();
    }
}
