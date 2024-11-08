package ac.ku.Lorna.models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private long memberId;
    private String firstName;
    private String surname;
    private String email;
    private String phoneNumber;
    private int age;
    private double registrationFee;
    private double totalShares;
    private int consecutiveMonthsContributed;
    private boolean eligibleForLoan;

    // Fields for loan management
    private List<Loan> loans; // List to store loans borrowed by the member
    private List<FixedDeposit> fixedDeposits; // List to store fixed deposits

    // Constructor
    public Member(long memberId, String firstName, String surname, String email, String phoneNumber, int age, double registrationFee, double totalShares, int consecutiveMonthsContributed, boolean eligibleForLoan) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.registrationFee = registrationFee;
        this.totalShares = totalShares;
        this.consecutiveMonthsContributed = consecutiveMonthsContributed;
        this.eligibleForLoan = eligibleForLoan;
        this.loans = new ArrayList<>();
        this.fixedDeposits = new ArrayList<>();
    }

    // Method to get consecutive months contributed
    public int getConsecutiveMonthsContributed() {
        return this.consecutiveMonthsContributed;
    }

    // Method to set consecutive months contributed (if needed)
    public void setConsecutiveMonthsContributed(int months) {
        this.consecutiveMonthsContributed = months;
    }

    // Method to set loans for the member
    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    // Method to set fixed deposits for the member
    public void setFixedDeposits(List<FixedDeposit> fixedDeposits) {
        this.fixedDeposits = fixedDeposits;
    }

    // New Method to display loans
    public String displayLoans() {
        StringBuilder loanDetails = new StringBuilder();

        if (loans.isEmpty()) {
            loanDetails.append(firstName).append(" ").append(surname).append(" has no loans.\n");
        } else {
            loanDetails.append(firstName).append(" ").append(surname).append("'s Loans:\n");
            for (Loan loan : loans) {
                loanDetails.append("- Loan Type: ").append(loan.getLoanType())
                        .append(", Amount: ").append(loan.getLoanAmount())
                        .append(", Monthly Repayment: ").append(loan.getMonthlyRepayment())
                        .append(", Remaining Balance: ").append(loan.getRemainingBalance())
                        .append("\n");
            }
        }
        return loanDetails.toString();
    }

    // Getters for other fields
    public long getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public double getTotalShares() {
        return totalShares;
    }

    public boolean isEligibleForLoan() {
        return eligibleForLoan;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<FixedDeposit> getFixedDeposits() {
        return fixedDeposits;
    }
}
