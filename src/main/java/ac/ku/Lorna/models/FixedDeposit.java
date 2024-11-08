package ac.ku.Lorna.models;

public class FixedDeposit {
    private long fixedDepositId; // Added this field
    private double depositAmount;
    private double interestRate; // Monthly interest rate

    // Constructor to initialize the fixed deposit with an ID, deposit amount, and interest rate
    public FixedDeposit(long fixedDepositId, double depositAmount, double interestRate) {
        this.fixedDepositId = fixedDepositId;
        this.depositAmount = depositAmount;
        this.interestRate = interestRate;
    }

    // Constructor to initialize only with deposit amount
    public FixedDeposit(double depositAmount) {
        this.depositAmount = depositAmount;
        this.interestRate = 0.6; // Default interest rate
    }

    // Getters
    public long getFixedDepositId() {
        return fixedDepositId;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
