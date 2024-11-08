package ac.ku.Lorna.dao;

import ac.ku.Lorna.models.Member;
import ac.ku.Lorna.models.Loan;
import ac.ku.Lorna.models.FixedDeposit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembersDAO {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fedha_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Lorna_2005";

    // SQL queries for Members
    private static final String INSERT_MEMBER_SQL = "INSERT INTO Members (first_name, surname, email, phone_number, age, registration_fee, total_shares, consecutive_months_contributed, eligible_for_loan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_MEMBERS_SQL = "SELECT * FROM Members";
    private static final String SELECT_MEMBER_BY_ID_SQL = "SELECT * FROM Members WHERE member_id = ?";
    private static final String UPDATE_MEMBER_SQL = "UPDATE Members SET first_name = ?, surname = ?, email = ?, phone_number = ?, age = ?, total_shares = ?, consecutive_months_contributed = ?, eligible_for_loan = ? WHERE member_id = ?";
    private static final String DELETE_MEMBER_SQL = "DELETE FROM Members WHERE member_id = ?";

    // SQL queries for Loans and Fixed Deposits
    private static final String SELECT_LOANS_BY_MEMBER_ID_SQL = "SELECT * FROM Loans WHERE member_id = ?";
    private static final String SELECT_FIXED_DEPOSITS_BY_MEMBER_ID_SQL = "SELECT * FROM FixedDeposits WHERE member_id = ?";

    // Method to establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to add a new member
    public void addMember(Member member) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER_SQL)) {

            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getSurname());
            preparedStatement.setString(3, member.getEmail());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setInt(5, member.getAge());
            preparedStatement.setDouble(6, member.getRegistrationFee());
            preparedStatement.setDouble(7, member.getTotalShares());
            preparedStatement.setInt(8, member.getConsecutiveMonthsContributed());
            preparedStatement.setBoolean(9, member.isEligibleForLoan());

            preparedStatement.executeUpdate();
            System.out.println("Member added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all members
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_MEMBERS_SQL)) {

            while (resultSet.next()) {
                Member member = new Member(
                        resultSet.getLong("member_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("registration_fee"),
                        resultSet.getDouble("total_shares"),
                        resultSet.getInt("consecutive_months_contributed"),
                        resultSet.getBoolean("eligible_for_loan")
                );
                // Fetch loans and fixed deposits for each member
                member.setLoans(getLoansByMemberId(member.getMemberId()));
                member.setFixedDeposits(getFixedDepositsByMemberId(member.getMemberId()));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    // Method to retrieve a member by ID
    public Member getMemberById(long memberId) {
        Member member = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MEMBER_BY_ID_SQL)) {

            preparedStatement.setLong(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                member = new Member(
                        resultSet.getLong("member_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("registration_fee"),
                        resultSet.getDouble("total_shares"),
                        resultSet.getInt("consecutive_months_contributed"),
                        resultSet.getBoolean("eligible_for_loan")
                );
                // Fetch loans and fixed deposits for this member
                member.setLoans(getLoansByMemberId(member.getMemberId()));
                member.setFixedDeposits(getFixedDepositsByMemberId(member.getMemberId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    // Method to retrieve loans by member ID
    public List<Loan> getLoansByMemberId(long memberId) {
        List<Loan> loans = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOANS_BY_MEMBER_ID_SQL)) {

            preparedStatement.setLong(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Loan loan = new Loan(
                        resultSet.getLong("loan_id"),
                        resultSet.getString("loan_type"),
                        resultSet.getDouble("loan_amount"),
                        resultSet.getDouble("interest_rate"),
                        resultSet.getInt("repayment_period"),
                        resultSet.getDouble("monthly_repayment"),
                        resultSet.getDouble("remaining_balance")
                );
                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    // Method to retrieve fixed deposits by member ID
    public List<FixedDeposit> getFixedDepositsByMemberId(long memberId) {
        List<FixedDeposit> fixedDeposits = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FIXED_DEPOSITS_BY_MEMBER_ID_SQL)) {

            preparedStatement.setLong(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FixedDeposit deposit = new FixedDeposit(
                        resultSet.getLong("fixed_deposit_id"),
                        resultSet.getDouble("deposit_amount"),
                        resultSet.getDouble("interest_rate")
                );
                fixedDeposits.add(deposit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fixedDeposits;
    }

    // Method to update a member's information
    public void updateMember(Member member) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MEMBER_SQL)) {

            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getSurname());
            preparedStatement.setString(3, member.getEmail());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setInt(5, member.getAge());
            preparedStatement.setDouble(6, member.getTotalShares());
            preparedStatement.setInt(7, member.getConsecutiveMonthsContributed());
            preparedStatement.setBoolean(8, member.isEligibleForLoan());
            preparedStatement.setLong(9, member.getMemberId());

            preparedStatement.executeUpdate();
            System.out.println("Member updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a member by ID
    public void deleteMember(long memberId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEMBER_SQL)) {

            preparedStatement.setLong(1, memberId);
            preparedStatement.executeUpdate();
            System.out.println("Member deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
