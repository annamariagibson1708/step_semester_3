public class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;
    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay = employee.getSalary();
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        }

        String slotText = (slot == null) ? "no parking assigned" : slot.slotNo;
        return name + " | Pay: Rs " + pay + " | Slot: " + slotText;
    }
}
