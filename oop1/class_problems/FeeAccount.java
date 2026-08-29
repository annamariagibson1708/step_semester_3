class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        this.amountPaid += amount;
    }

    public void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }

    public double getDue() {
        return totalFee - amountPaid;
    }

    public double effectiveDue(double scholarshipPercent) {
        double due = getDue();
        return due - (due * (scholarshipPercent / 100.0));
    }
}

public class feeacc {
    public static void main(String[] args) {
        FeeAccount accA = new FeeAccount("REG01", 200000);
        accA.payInTwoInstallments(120000);
        System.out.println("Account A due: Rs " + accA.getDue());

        FeeAccount accB = new FeeAccount("REG02", 180000);
        double effDueB = accB.effectiveDue(20);
        System.out.println("Account B effective due (20% scholarship): Rs " + effDueB);
    }
}
