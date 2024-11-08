package ac.ku.Lorna.models;

public class Loan {
    private long loanId;
    private String loanType;
    private double loanAmount;
    private double interestRate;
    private int repaymentPeriod;
    private double monthlyRepayment;
    private double remainingBalance;

    // Updated Constructor with loanId
    public Loan(long loanId, String loanType, double loanAmount, double interestRate, int repaymentPeriod, double monthlyRepayment, double remainingBalance) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.repaymentPeriod = repaymentPeriod;
        this.monthlyRepayment = monthlyRepayment;
        this.remainingBalance = remainingBalance;
    }

    // Getters
    public long getLoanId() {
        return loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }
}
