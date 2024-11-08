package ac.ku.Lorna.userInterface;

import javax.swing.*;
import java.awt.*;

public class MemberManagementScreen extends JPanel {

    public MemberManagementScreen(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // TITLE
        JLabel titleLabel = new JLabel("Member Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // FORM PANEL
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // padding between components
        gbc.anchor = GridBagConstraints.WEST; // Align all labels to the left

        // Create labels and text fields
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(250, 30)); // Increase size

        JLabel surNameLabel = new JLabel("Surname:");
        JTextField surNameField = new JTextField();
        surNameField.setPreferredSize(new Dimension(250, 30)); // Increase size

        JLabel emailLabel = new JLabel("Email Address:");
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(250, 30)); // Increase size

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(250, 30)); // Increase size

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(250, 30)); // Increase size

        JLabel registrationFeeLabel = new JLabel("Registration Fee:");
        JTextField registrationFeeField = new JTextField();
        registrationFeeField.setPreferredSize(new Dimension(250, 30)); // Increase size

        // Setting fonts for labels
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        firstNameLabel.setFont(labelFont);
        surNameLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        registrationFeeLabel.setFont(labelFont);

        // Adding components to form panel with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(surNameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(surNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(registrationFeeLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(registrationFeeField, gbc);

        // Form panel at the center of the screen
        add(formPanel, BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        // Add Member Button
        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setPreferredSize(new Dimension(180, 40));
        addMemberButton.setFont(new Font("Arial", Font.PLAIN, 16));
        addMemberButton.setBackground(new Color(0, 102, 204));
        addMemberButton.setForeground(Color.WHITE);
        addMemberButton.setFocusPainted(false);
        addMemberButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        addMemberButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String surName = surNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String ageText = ageField.getText();
            String feeText = registrationFeeField.getText();

            // Validate fields
            if (firstName.isEmpty() || surName.isEmpty() || email.isEmpty() || phone.isEmpty() || ageText.isEmpty() || feeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int age = Integer.parseInt(ageText);
                    double fee = Double.parseDouble(feeText);

                    if (age < 18 || age > 35) {
                        JOptionPane.showMessageDialog(this, "Age must be between 18 and 35", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (fee != 1000) {
                        JOptionPane.showMessageDialog(this, "Registration fee must be 1000 sh", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    JOptionPane.showMessageDialog(this, "Member successfully added!\n" +
                            "Name: " + firstName + " " + surName + "\n" +
                            "Email: " + email + "\n" +
                            "Phone: " + phone + "\n" +
                            "Age: " + age + "\n" +
                            "Fee: " + fee, "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields after adding
                    firstNameField.setText("");
                    surNameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    ageField.setText("");
                    registrationFeeField.setText("");

                    // Show next action dialog
                    showNextActionDialog(mainFrame);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid age or registration fee input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back to Dashboard Button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setPreferredSize(new Dimension(180, 40));
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(0, 102, 204));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> mainFrame.showScreen("Dashboard"));

        buttonPanel.add(addMemberButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method for showing next action dialog
    private void showNextActionDialog(MainFrame mainFrame) {
        int option = JOptionPane.showOptionDialog(this,
                "What would you like to do next?", "Choose Next Action",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Apply for Loan", "Go to Savings", "Return to Dashboard"},
                "Apply for Loan");

        switch (option) {
            case JOptionPane.YES_OPTION -> mainFrame.showScreen("LoanApplication");
            case JOptionPane.NO_OPTION -> mainFrame.showScreen("SavingsScreen");
            default -> mainFrame.showScreen("Dashboard");
        }
    }
}
