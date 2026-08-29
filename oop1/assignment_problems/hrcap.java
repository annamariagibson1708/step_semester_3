public class hrcap {
    public static void main(String[] args) {
        ParkingSlot s1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot s2 = new ParkingSlot("A2", 5, 4);

        Employee e1 = new ManagerEmployee("M01", "Divya", 70000, 8000);
        Employee e2 = new Employee("E01", "Karan", 40000);
        Employee e3 = new Employee("E02", "Meera", 10000);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "M01", e1, s1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E01", e2, s2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E02", e3, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
