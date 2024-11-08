package ac.ku.Lorna.userInterface;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoanApplicationScreen extends JPanel {   //This defines the class as a JPanel


    // Defining loan types with their interest rates, max loan multiplier, and repayment periods

    private final Map<String, LoanDetails> loanDetailsMap = new HashMap<>(); // Creating a HashMap to store details for each loan type.
    // The key will be a String (loan type), and the value is a LoanDetails object that contains  interest rates, multipliers, and repayment periods.

    public LoanApplicationScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout());  //Use a BorderLayout to arrange components.
        setBackground(new Color(245, 245, 245));

        // Initialize loan details


        //         Each loan type is added to the loanDetailsMap with its corresponding  -Max Multiplier ( this is how many times the amount of shares can be loaned),
        //            Interest Rate (per month),
        //            Repayment Period (in months).
        loanDetailsMap.put("Emergency Loan", new LoanDetails(1.0, 0.3, 12));
        loanDetailsMap.put("Short Loan", new LoanDetails(2.0, 0.6, 24));
        loanDetailsMap.put("Normal Loan", new LoanDetails(3.0, 1.0, 36));
        loanDetailsMap.put("Development Loan", new LoanDetails(5.0, 1.4, 48));

        // TITLE
        JLabel titleLabel = new JLabel("Loan Application", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // FORM PANEL
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));    // GridLayout with 6 rows and 2 columns, with 10px horizontal and vertical gaps between components.
        formPanel.setBackground(new Color(245, 245, 245));

        // FORM FIELDS
        JLabel loanTypeLabel = new JLabel("Loan Type:");
        JComboBox<String> loanTypeComboBox = new JComboBox<>(loanDetailsMap.keySet().toArray(new String[0]));

        JLabel sharesLabel = new JLabel("Total Shares:");
        JTextField sharesField = new JTextField();

        JLabel loanAmountLabel = new JLabel("Loan Amount:");
        JTextField loanAmountField = new JTextField();

        JLabel interestRateLabel = new JLabel("Interest Rate (%):");
        JTextField interestRateField = new JTextField();
        interestRateField.setEditable(false); //This is auto calculated

        JLabel repaymentPeriodLabel = new JLabel("Repayment Period (Months):");
        JTextField repaymentPeriodField = new JTextField();
        repaymentPeriodField.setEditable(false);//This is auto calculated

        JLabel maxLoanLabel = new JLabel("Maximum Loan Amount:");
        JTextField maxLoanField = new JTextField();
        maxLoanField.setEditable(false);//This is auto calculated

        // Updating the  fields based on loan type selection


        loanTypeComboBox.addActionListener(e -> {
            String selectedLoanType = (String) loanTypeComboBox.getSelectedItem();
            if (selectedLoanType != null) {
                LoanDetails details = loanDetailsMap.get(selectedLoanType);
                if (details != null) {
                    interestRateField.setText(String.valueOf(details.interestRate));
                    repaymentPeriodField.setText(String.valueOf(details.repaymentPeriod));

                    // Calculate maximum loan amount based on shares
                    try {
                        double shares = Double.parseDouble(sharesField.getText());
                        double maxLoanAmount = shares * details.maxMultiplier;
                        maxLoanField.setText(String.valueOf(maxLoanAmount));
                    } catch (NumberFormatException ex) {
                        maxLoanField.setText("Invalid Shares");
                    }
                }
            }
        });

        // APPLY LOAN BUTTON
        JButton applyLoanButton = new JButton("Apply For Loan");
        applyLoanButton.setPreferredSize(new Dimension(180, 40));
        applyLoanButton.setBackground(new Color(0, 102, 204));
        applyLoanButton.setForeground(Color.white);
        applyLoanButton.setFocusPainted(false);

        // ACTION LISTENER FOR APPLY LOAN BUTTON
        applyLoanButton.addActionListener(e -> {
            String loanType = (String) loanTypeComboBox.getSelectedItem();
            String loanAmountText = loanAmountField.getText();
            String sharesText = sharesField.getText();
            String maxLoanText = maxLoanField.getText();

            // VALIDATION CHECK
            if (loanType == null || loanAmountText.isEmpty() || sharesText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double loanAmount = Double.parseDouble(loanAmountText);
                    double maxLoanAmount = Double.parseDouble(maxLoanText);

                    // Check if loan amount exceeds maximum limit
                    if (loanAmount > maxLoanAmount) {
                        JOptionPane.showMessageDialog(this, "Loan amount exceeds the maximum limit based on shares.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Loan application submitted:\n" +
                                "Type: " + loanType + "\n" +
                                "Amount: " + loanAmount + "\n" +
                                "Repayment Period: " + repaymentPeriodField.getText() + " Months");

                        // Clear fields after submission
                        sharesField.setText("");
                        loanAmountField.setText("");
                        maxLoanField.setText("");
                        loanTypeComboBox.setSelectedIndex(0);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for amount or shares.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Adding components to the form panel
        formPanel.add(loanTypeLabel);
        formPanel.add(loanTypeComboBox);
        formPanel.add(sharesLabel);
        formPanel.add(sharesField);
        formPanel.add(maxLoanLabel);
        formPanel.add(maxLoanField);
        formPanel.add(loanAmountLabel);
        formPanel.add(loanAmountField);
        formPanel.add(interestRateLabel);
        formPanel.add(interestRateField);
        formPanel.add(repaymentPeriodLabel);
        formPanel.add(repaymentPeriodField);

        // Adding form panel to the center
        add(formPanel, BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        buttonPanel.add(applyLoanButton);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.setPreferredSize(new Dimension(180, 40));
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.white);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mainFrame.showScreen("Dashboard"));

        buttonPanel.add(backButton);

        // Adding button panel to the bottom
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Inner class for Loan Details


    private static class LoanDetails {
        double maxMultiplier;
        double interestRate;
        int repaymentPeriod;

        LoanDetails(double maxMultiplier, double interestRate, int repaymentPeriod) {
            this.maxMultiplier = maxMultiplier;
            this.interestRate = interestRate;
            this.repaymentPeriod = repaymentPeriod;
        }
    }
}
