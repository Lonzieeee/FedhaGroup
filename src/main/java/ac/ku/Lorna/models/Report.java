package ac.ku.Lorna.models;

import java.util.List;

public class Report {

    // Method to calculate total registration fees collected from all members
    public static double totalRegistrationFees(List<Member> members) {
        return members.size() * 1000.0; // Each member pays a fixed registration fee of 1000
    }

    // Method to generate each member's share contribution report
    public static String shareContributions(List<Member> members) {
        StringBuilder report = new StringBuilder("Share Contributions by Members:\n");
        for (Member member : members) {
            report.append("- ").append(member.getFirstName())
                    .append(" ").append(member.getSurname())
                    .append(": ").append(member.getTotalShares()).append("\n");
        }
        return report.toString();
    }

    // Method to generate loan report for each member
    public static String loanReport(List<Member> members) {
        StringBuilder report = new StringBuilder("Loan Report:\n");
        for (Member member : members) {
            report.append(member.getFirstName())
                    .append(" ").append(member.getSurname()).append("'s Loans:\n");
            report.append(member.displayLoans()).append("\n");
        }
        return report.toString();
    }

    // Method to calculate dividends based on shares (assuming fixed total revenue)
    public static String calculateDividends(List<Member> members, double totalRevenue) {
        double totalShares = 0;
        for (Member member : members) {
            totalShares += member.getTotalShares();
        }

        StringBuilder report = new StringBuilder("Dividends for Members:\n");
        for (Member member : members) {
            double dividend = (member.getTotalShares() / totalShares) * totalRevenue;
            report.append("- ").append(member.getFirstName())
                    .append(" ").append(member.getSurname())
                    .append(" receives dividend: ").append(String.format("%.2f", dividend)).append("\n");
        }
        return report.toString();
    }
}
