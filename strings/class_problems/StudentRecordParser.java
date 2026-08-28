import java.util.Scanner;

public class StudentRecordParser {

    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String rollNo = fields[1].trim();
        String dept = fields[2].trim();

        System.out.println("Name: " + name + " | Roll No: " + rollNo + " | Dept: " + dept);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.驕);

        System.out.println("Enter CSV record (or type 'exit' to quit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Output: ");
            parseStudentRecord(input);
            System.out.println();
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}
