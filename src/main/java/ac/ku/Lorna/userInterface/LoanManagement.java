package ac.ku.Lorna.userInterface;

/**
 * FedhaGroup (ac.ku.Lorna.userInterface)
 * Created by: user
 * On: 10/5/2024 8:00 AM
 * Description:
 **/
import ac.ku.Lorna.models.Loan;
import ac.ku.Lorna.services.LoanService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class LoanManagement extends JFrame {
    private JTable loansTable;
    private DefaultTableModel tableModel;
    private LoanService loanService;

    public LoanManagement() {
        loanService = new LoanService();
        setTitle("Loan Management - Fedha youth group");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);


         JPanel mainpanel = new JPanel(new BorderLayout());
         mainpanel.setBackground(new Color(240, 248, 255));
         mainpanel.setBorder(new EmptyBorder(20, 20, 20, 20)); //padding around the main panel

        //TITLE LABEL
        JLabel titleLabel = new JLabel("Loan Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102 ,204));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

         //SETTING UP THE TABLE

        String[] columnNames = {"Member Name" , "Loan Amount", "Loan Type", "Repayment Period", "Interest Rate", "Monthly Repayment","Balance"};
        tableModel = new DefaultTableModel(columnNames,0);
        loansTable = new JTable(tableModel);
        loansTable.setRowHeight(25);
        loansTable.setFont(new Font("Arial", Font.PLAIN, 14));
        loansTable.setBackground(new Color(245, 245, 245));
        loansTable.setForeground(Color.BLACK);


        JScrollPane tableScrollPane = new JScrollPane(loansTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(0,102,204),2));

        //Button Setup

        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        ButtonPanel.setBackground(new Color(240, 248, 255));

        JButton addButton = createStyledButton("Add Loan");
        ButtonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit Loan");
        ButtonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete Loan");
        ButtonPanel.add(deleteButton);

        //Adding stuff to the main panel
        mainpanel.add(titleLabel, BorderLayout.NORTH);

mainpanel.add(tableScrollPane, BorderLayout.CENTER);
mainpanel.add(ButtonPanel, BorderLayout.SOUTH);
        add(mainpanel);

        loadLoans(); //Load initial loans

        // ACTION LISTNERS FOR THE 3 BUTTONS
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddLoanForm();
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int SelectedRow = loansTable.getSelectedRow();
                if (SelectedRow >= 0) {
                    editLoan (SelectedRow);
                }else {
                    JOptionPane.showMessageDialog(LoanManagement.this, "Please select a loan to edit");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int SelectedRow = loansTable.getSelectedRow();
                if (SelectedRow >= 0) {
                    String memberName = tableModel.getValueAt(SelectedRow, 0).toString();
                    loadLoans();// Reload loans after Deletion
                }else {
                    JOptionPane.showMessageDialog(LoanManagement.this, "Please select a loan to delete");
                }
            }
        });
        setVisible(true);

    }
    //METHOD FOR CREATING STYLED BUTTONS
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //ADDING A HOVER EFFECT TO THE BUTTONS
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
             button.setBackground(new Color(0, 102, 204));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 153, 255));
            }
        });
        return button;
    }


    private void loadLoans() {
        List<Loan> loans = loanService.loadloans();
        if(loans != null) {
            tableModel.setRowCount(0);//This clears the table
            for(Loan loan : loans) {
                tableModel.addRow(new Object[]{
                        loan.getMemberName(),
                        loan.getLoanAmount(),
                        loan.getLoanType(),
                        loan.getRepaymentPeriod(),
                        loan.getInterestRate(),
                        loan.getMonthlyRepayment(),
                        loan.getBalance()
                });
            }
        }

    }

    private void showAddLoanForm(){
        JDialog addLoanDialog = new JDialog(this, "Add Loan", true);
        addLoanDialog.setSize(350,350);

        //CENTER THE DIALOG BOX
        addLoanDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(8,2,10,10));
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextField memberNameField = new JTextField();
        JTextField loanAmountField = new JTextField();
        JTextField loanTypeField = new JTextField();
        JTextField repaymentPeriodField = new JTextField();
        JTextField interestRateField = new JTextField();
        JTextField monthlyRepaymentField = new JTextField();
        JTextField balanceField = new JTextField();

        formPanel.add(new JLabel("Member Name:"));
        formPanel.add(memberNameField);
        formPanel.add(new JLabel("Loan Amount:"));
        formPanel.add(loanAmountField);
        formPanel.add(new JLabel("LoanType:"));
        formPanel.add(loanTypeField);
        formPanel.add(new JLabel("Repayment Period:"));
        formPanel.add(repaymentPeriodField);
        formPanel.add(new JLabel("Interest Rate:"));
        formPanel.add(interestRateField);
        formPanel.add(new JLabel("Monthly Repayment:"));
        formPanel.add(monthlyRepaymentField);
        formPanel.add(new JLabel("Balance:"));
        formPanel.add(balanceField);

        JButton saveButton = createStyledButton("Save");
        formPanel.add(saveButton);
        JButton cancelButton = createStyledButton("Cancel");
        formPanel.add(cancelButton);
        addLoanDialog.add(formPanel);

        saveButton.addActionListener(e -> {
            try{
                String memberName = memberNameField.getText();
                double loanAmount = Double.parseDouble(loanAmountField.getText());
                String loanType = loanTypeField.getText();
                int repaymentPeriod = Integer.parseInt(repaymentPeriodField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText());
                double monthlyRepayment = Double.parseDouble(monthlyRepaymentField.getText());
                double balance = Double.parseDouble(balanceField.getText());

                Loan newloan = new Loan(memberName,loanAmount,loanType,repaymentPeriod,interestRate,monthlyRepayment,balance);
                loanService.addloan(newloan);

                loadLoans();//Reloads loans after adding
                addLoanDialog.dispose();

            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(LoanManagement.this, "Please enter a valid number");
            }
        });
        cancelButton.addActionListener(e -> addLoanDialog.dispose());
        addLoanDialog.setVisible(true);
    }
    private void editLoan(int rowIndex) {
        //Fetch existing data
        String currentMemberName = tableModel.getValueAt(rowIndex, 0).toString();
        String currentLoanAmount = tableModel.getValueAt(rowIndex, 1).toString();
        String currentLoanType = tableModel.getValueAt(rowIndex, 2).toString();
        String currentRepaymentPeriod = tableModel.getValueAt(rowIndex, 3).toString();
        String currentInterestRate = tableModel.getValueAt(rowIndex, 4).toString();
        String currentMonthlyRepayment = tableModel.getValueAt(rowIndex, 5).toString();
        String currentBalance = tableModel.getValueAt(rowIndex, 6).toString();


    }
    private void deleteLoan(int rowIndex) {
        String currentMemberName = tableModel.getValueAt(rowIndex, 0).toString();
        String currentLoanAmount = tableModel.getValueAt(rowIndex, 1).toString();
        String currentLoanType = tableModel.getValueAt(rowIndex, 2).toString();
        String currentRepaymentPeriod = tableModel.getValueAt(rowIndex, 3).toString();
        String currentInterestRate = tableModel.getValueAt(rowIndex, 4).toString();
        String currentMonthlyRepayment = tableModel.getValueAt(rowIndex, 5).toString();
        String currentBalance = tableModel.getValueAt(rowIndex, 6).toString();

    }
    public static void main(String[] args) {
        new LoanManagement();
    }
}
