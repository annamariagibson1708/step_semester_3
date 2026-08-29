public class capstone {
    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 2);
        HostelRoom r2 = new HostelRoom("C-507", 2);
        HostelRoom r3 = new HostelRoom("C-309", 2);

        FeeAccount f1 = new FeeAccount("REG01", 200000);
        FeeAccount f2 = new FeeAccount("REG02", 180000);
        FeeAccount f3 = new FeeAccount("REG03", 240000);

        f1.payInTwoInstallments(120000);
        f3.pay(96000);

        SrmStudent s1 = new SrmStudent("Ravi", "REG01", f1, r1);
        SrmStudent s2 = new SrmStudent("Anitha", "REG02", f2, r2);
        SrmStudent s3 = new SrmStudent("Karthik", "REG03", f3, r3);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
