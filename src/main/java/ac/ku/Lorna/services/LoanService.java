package ac.ku.Lorna.services;
import ac.ku.Lorna.models.Loan;
import ac.ku.Lorna.utilities.QueryExecutor;  //Utility class that helps execute SQL Queries
import java.sql.SQLException; //Exception thrown when there is an error during interaction with the database
import java.util.List;

public class LoanService {
    //Load loans from the database
    //The loadloans method retrieves all the loans from the database and converts them to loan "objects" returning a list of loans
    public List<Loan>loadloans(){
        String query = "SELECT * FROM loan";  //SQL query that selcts all columns from the loans table in the database
        try {
            //The result set (rs) is processed for each row, and a new Loan object is created for every row in the result set.
            return QueryExecutor.executeQuery(query, rs ->
                    new Loan(
                            rs.getString("member_name"), //.i.e Retrieves the member_name column value from the database result and passes it as a parameter to the Loan constructor.
                            rs.getDouble("loan_amount"),
                            rs.getString("loan_type"),
                            rs.getInt("repayment_period"),
                            rs.getDouble("interest_rate"),
                            rs.getDouble("monthly_repayment"),
                            rs.getDouble("balance")

                    )
            );


        }catch (SQLException e){
            e.printStackTrace();
            return null;  // The method returns a List<Loan>, which is a collection of all loans fetched from the database.
            // in case an exception occurs, it prints the error and returns null.

        }
    }
    //Add or modify loan
    //Th below method inserts a new loan record into the loans table in the database, using the data from a Loan object.

    public void addloan(Loan loan){
        //This is the SQL query to insert a new row into the loans table.
        // The ? symbols are placeholders for the actual loan data that will be provided.
        String insertQuery = "INSERT INTO loans(member_name,loan_amount,loan_type,repayment_period,interest_rate,monthly_repayment,balance) VALUES(?,?,?,?,?,?,?)";
        try{
            QueryExecutor.executeUpdate(insertQuery,loan.getMemberName(),loan.getLoanAmount(),loan.getLoanType(),loan.getRepaymentPeriod(),loan.getInterestRate(),loan.getMonthlyRepayment(),loan.getBalance());

        }catch (SQLException e){
            e.printStackTrace();
            return;
        }
    }
}
