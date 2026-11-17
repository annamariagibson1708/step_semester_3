public class StudentMarksGrid {

    private String[] subjects;
    private String[] students;
    private Integer[][] marks; // Rows = students, Columns = subjects (null = missing mark)

    public StudentMarksGrid(String[] subjects, String[] students, Integer[][] marks) {
        this.subjects = subjects;
        this.students = students;
        this.marks = marks;
    }

    // Calculates and prints total marks for each student
    public void printTotals() {
        StringBuilder sb = new StringBuilder("Totals ");
        for (int i = 0; i < students.length; i++) {
            int total = 0;
            for (int j = 0; j < subjects.length; j++) {
                // Handle jagged rows or null entries for missing subjects
                if (j < marks[i].length && marks[i][j] != null) {
                    total += marks[i][j];
                }
            }
            sb.append(students[i]).append(" ").append(total);
            if (i < students.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }

    // Identifies and prints the highest scorer for each subject
    public void printToppers() {
        StringBuilder sb = new StringBuilder("toppers ");
        for (int j = 0; j < subjects.length; j++) {
            int maxMark = -1;
            String topperName = "None";

            for (int i = 0; i < students.length; i++) {
                if (j < marks[i].length && marks[i][j] != null) {
                    if (marks[i][j] > maxMark) {
                        maxMark = marks[i][j];
                        topperName = students[i];
                    }
                }
            }

            sb.append(subjects[j]).append(" ").append(topperName).append(" ").append(maxMark);
            if (j < subjects.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        String[] students = {"Anu", "Ravi", "Meena"};

        // null indicates Ravi is missing English marks
        Integer[][] marks = {
            {80, 90, 70},
            {60, 85, null},
            {95, 75, 88}
        };

        StudentMarksGrid grid = new StudentMarksGrid(subjects, students, marks);
        grid.printTotals();  // Output: Totals Anu 240, Ravi 145, Meena 258
        grid.printToppers(); // Output: toppers Math Meena 95, Sci Anu 90, Eng Meena 88
    }
}