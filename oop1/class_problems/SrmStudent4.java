class SrmStudent4 {
    String name;
    String regNo;
    int attendance;
    static String university = "SRM Institute of Science and Technology";
    static int admissionCount = 0;

    public SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + (10 + admissionCount);
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo + " | " + university);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class srmsys {
    public static void main(String[] args) {
        SrmStudent s1 = new SrmStudent("Ravi", 85);
        SrmStudent s2 = new SrmStudent("Meera", 90);
        SrmStudent s3 = new SrmStudent("Karthik", 78);

        s1.printIdCard();
        s2.printIdCard();
        s3.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}
