
import java.util.Scanner;

public class BMICalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("  WELLNESS CAMP REPORT  ");
        System.out.printf("%-10s | %-12s | %-12s | %-8s | %-12s\n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");

        for (int i = 0; i < heights.length; i++) {
            double h = heights[i];
            double w = weights[i];

            double bmi = w / (h * h);

            String status = getBmiStatus(bmi);

            System.out.printf("Person %-3d | %-12.2f | %-12.2f | %-8.2f | %-12s\n",
                    (i + 1), h, w, bmi, status);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of people in the team: ");
        int totalPeople = scanner.nextInt();

        double[] heights = new double[totalPeople];
        double[] weights = new double[totalPeople];

        for (int i = 0; i < totalPeople; i++) {
            System.out.println("\n--- Entering details for Person " + (i + 1) + " ---");

            System.out.print("Enter height in meters (e.g., 1.75): ");
            heights[i] = scanner.nextDouble();

            System.out.print("Enter weight in kg (e.g., 70): ");
            weights[i] = scanner.nextDouble();
        }

        printWellnessReport(heights, weights);

        scanner.close();
    }
}
