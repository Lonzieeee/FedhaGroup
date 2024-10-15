package ac.ku.Lorna.models;

/**
 * FedhaGroup (models)
 * Created by: user
 * On: 10/5/2024 8:14 AM
 * Description:
 **/
public class Loan {
    private String memberName; //Name of the member that took the loan
    private double loanAmount; //Amount of money borrowed
    private String loanType; // Type of loan
    private int repaymentPeriod; // Time taken to repay the loan
    private double interestRate; // Interest rate applied to the loan
    private double monthlyRepayment; //Monthly repaymentt amount based on the loan details
    private double balance; //Remaining balance after payments

    //Constructor

    public Loan (String memberName,double loanAmount,String loanType,int repaymentPeriod, double interestRate,double monthlyRepayment,double balance) {
        this.memberName = memberName;
        this.loanAmount = loanAmount;
        this.loanType = loanType;
        this.repaymentPeriod = repaymentPeriod;
        this.interestRate = interestRate;
        this.monthlyRepayment = monthlyRepayment;
        this.balance = balance;


    }
    //GETTTERS AND SETTERSS
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    public String getLoanType() {
        return loanType;
    }
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
    public int getRepaymentPeriod() {
        return repaymentPeriod;
    }
    public void setRepaymentPeriod(int repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }
    public void setMonthlyRepayment(double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
