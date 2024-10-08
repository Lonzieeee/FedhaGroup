package ac.ku.Lorna.userInterface;

/**
 * FedhaGroup (ac.ku.Lorna.userInterface)
 * Created by: user
 * On: 10/5/2024 8:00 AM
 * Description:
 **/
import ac.ku.Lorna.models.Member;
import ac.ku.Lorna.services.MemberService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MemberManagement extends JFrame {
    private JTable membersTable;
    private DefaultTableModel tableModel;

    public MemberManagement() {
        setTitle("Manage Members - Fedha Youth Group");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Table setup
        String[] columnNames = {"Member Name", "Age", "Shares", "Registration Fee"};
        tableModel = new DefaultTableModel(columnNames, 0);
        membersTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(membersTable);

        // Button setup
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Member");
        JButton editButton = new JButton("Edit Member");
        JButton deleteButton = new JButton("Delete Member");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add components to main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);
        add(panel);

        // Button action listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddMemberForm();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    editMember(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(MemberManagement.this, "Please select a member to edit.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = membersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(MemberManagement.this, "Please select a member to delete.");
                }
            }
        });

        setVisible(true);
    }

    private void showAddMemberForm() {
        JDialog addMemberDialog = new JDialog(this, "Add New Member", true);
        addMemberDialog.setSize(300, 200);
        addMemberDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField sharesField = new JTextField();
        JTextField registrationFeeField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Shares:"));
        formPanel.add(sharesField);
        formPanel.add(new JLabel("Registration Fee:"));
        formPanel.add(registrationFeeField);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        addMemberDialog.add(formPanel);

        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double shares = Double.parseDouble(sharesField.getText());
                double registrationFee = Double.parseDouble(registrationFeeField.getText());

                Member newMember = new Member(name, age, shares, registrationFee);
                tableModel.addRow(new Object[]{newMember.getName(), newMember.getAge(), newMember.getShares(), newMember.getRegistrationFee()});

                addMemberDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MemberManagement.this, "Please enter a valid number.");
            }
        });

        cancelButton.addActionListener(e -> addMemberDialog.dispose());

        addMemberDialog.setVisible(true);
    }

    private void editMember(int rowIndex) {
        String currentName = tableModel.getValueAt(rowIndex, 0).toString();
        String currentAge = tableModel.getValueAt(rowIndex, 1).toString();
        String currentShares = tableModel.getValueAt(rowIndex, 2).toString();
        String currentRegistrationFee = tableModel.getValueAt(rowIndex, 3).toString();

        JDialog editMemberDialog = new JDialog(this, "Edit Member", true);
        editMemberDialog.setSize(300, 200);
        editMemberDialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField(currentName);
        JTextField ageField = new JTextField(currentAge);
        JTextField sharesField = new JTextField(currentShares);
        JTextField registrationFeeField = new JTextField(currentRegistrationFee);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Shares:"));
        formPanel.add(sharesField);
        formPanel.add(new JLabel("Registration Fee:"));
        formPanel.add(registrationFeeField);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        editMemberDialog.add(formPanel);

        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double shares = Double.parseDouble(sharesField.getText());
                double registrationFee = Double.parseDouble(registrationFeeField.getText());

                tableModel.setValueAt(name, rowIndex, 0);
                tableModel.setValueAt(age, rowIndex, 1);
                tableModel.setValueAt(shares, rowIndex, 2);
                tableModel.setValueAt(registrationFee, rowIndex, 3);

                editMemberDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MemberManagement.this, "Please enter a valid number.");
            }
        });

        cancelButton.addActionListener(e -> editMemberDialog.dispose());

        editMemberDialog.setVisible(true);
    }

    public static void main(String[] args) {
        new MemberManagement();
    }
}

//
//
//public class MemberManagement extends JFrame {
//    private JTable membersTable;
//    private DefaultTableModel tableModel;
//    private MemberService memberService;
//
//    private int currentPage = 1;
//    private int rowsPerPage = 10;
//
//    public MemberManagement() {
//        memberService = new MemberService();
//
//        setTitle("Manage Members - Fedha Youth Group");
//        setSize(600, 400);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel(new BorderLayout());
//
//        // Table setup
//        String[] columnNames = {"Member Name", "Age", "Shares", "Registration Fee"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        membersTable = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(membersTable);
//
//        // Pagination control buttons
//        JPanel paginationPanel = new JPanel();
//        JButton previousButton = new JButton("Previous");
//        JButton nextButton = new JButton("Next");
//        paginationPanel.add(previousButton);
//        paginationPanel.add(nextButton);
//
//        // Button setup
//        JPanel buttonPanel = new JPanel();
//        JButton addButton = new JButton("Add Member");
//        JButton editButton = new JButton("Edit Member");
//        JButton deleteButton = new JButton("Delete Member");
//        buttonPanel.add(addButton);
//        buttonPanel.add(editButton);
//        buttonPanel.add(deleteButton);
//
//        // Add components to main panel
//        panel.add(buttonPanel, BorderLayout.NORTH);
//        panel.add(tableScrollPane, BorderLayout.CENTER);
//        panel.add(paginationPanel, BorderLayout.SOUTH);
//        add(panel);
//
//        // Load initial page of members
//        loadMembers();
//
//        // Pagination button actions
//        previousButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (currentPage > 1) {
//                    currentPage--;
//                    loadMembers();
//                }
//            }
//        });
//
//        nextButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                currentPage++;
//                loadMembers();
//            }
//        });
//
//        // Button action listeners
//        addButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                showAddMemberForm();
//            }
//        });
//
//        editButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = membersTable.getSelectedRow();
//                if (selectedRow >= 0) {
//                    editMember(selectedRow);
//                } else {
//                    JOptionPane.showMessageDialog(MemberManagement.this, "Please select a member to edit.");
//                }
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = membersTable.getSelectedRow();
//                if (selectedRow >= 0) {
//                    tableModel.removeRow(selectedRow);
//                } else {
//                    JOptionPane.showMessageDialog(MemberManagement.this, "Please select a member to delete.");
//                }
//            }
//        });
//
//        setVisible(true);
//    }
//
//    private void loadMembers() {
//        int offset = (currentPage - 1) * rowsPerPage;
//        List<Member> members = memberService.loadMembers(rowsPerPage, offset);
//
//        if (members != null) {
//            tableModel.setRowCount(0);  // Clear the table before adding new data
//            for (Member member : members) {
//                tableModel.addRow(new Object[]{member.getName(), member.getAge(), member.getShares(), member.getRegistrationFee()});
//            }
//        }
//    }
//
//    private void showAddMemberForm() {
//        JDialog addMemberDialog = new JDialog(this, "Add New Member", true);
//        addMemberDialog.setSize(300, 200);
//        addMemberDialog.setLocationRelativeTo(this);
//
//        JPanel formPanel = new JPanel(new GridLayout(5, 2));
//        JTextField nameField = new JTextField();
//        JTextField ageField = new JTextField();
//        JTextField sharesField = new JTextField();
//        JTextField registrationFeeField = new JTextField();
//
//        formPanel.add(new JLabel("Name:"));
//        formPanel.add(nameField);
//        formPanel.add(new JLabel("Age:"));
//        formPanel.add(ageField);
//        formPanel.add(new JLabel("Shares:"));
//        formPanel.add(sharesField);
//        formPanel.add(new JLabel("Registration Fee:"));
//        formPanel.add(registrationFeeField);
//
//        JButton saveButton = new JButton("Save");
//        JButton cancelButton = new JButton("Cancel");
//        formPanel.add(saveButton);
//        formPanel.add(cancelButton);
//
//        addMemberDialog.add(formPanel);
//
//        saveButton.addActionListener(e -> {
//            try {
//                String name = nameField.getText();
//                int age = Integer.parseInt(ageField.getText());
//                double shares = Double.parseDouble(sharesField.getText());
//                double registrationFee = Double.parseDouble(registrationFeeField.getText());
//
//                Member newMember = new Member(name, age, shares, registrationFee);
//                memberService.addMember(newMember);  // Add member to database
//                loadMembers();  // Reload members after adding
//                addMemberDialog.dispose();
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(MemberManagement.this, "Please enter a valid number.");
//            }
//        });
//
//        cancelButton.addActionListener(e -> addMemberDialog.dispose());
//
//        addMemberDialog.setVisible(true);
//    }
//
//    private void editMember(int rowIndex) {
//        String currentName = tableModel.getValueAt(rowIndex, 0).toString();
//        String currentAge = tableModel.getValueAt(rowIndex, 1).toString();
//        String currentShares = tableModel.getValueAt(rowIndex, 2).toString();
//        String currentRegistrationFee = tableModel.getValueAt(rowIndex, 3).toString();
//
//        JDialog editMemberDialog = new JDialog(this, "Edit Member", true);
//        editMemberDialog.setSize(300, 200);
//        editMemberDialog.setLocationRelativeTo(this);
//
//        JPanel formPanel = new JPanel(new GridLayout(5, 2));
//        JTextField nameField = new JTextField(currentName);
//        JTextField ageField = new JTextField(currentAge);
//        JTextField sharesField = new JTextField(currentShares);
//        JTextField registrationFeeField = new JTextField(currentRegistrationFee);
//
//        formPanel.add(new JLabel("Name:"));
//        formPanel.add(nameField);
//        formPanel.add(new JLabel("Age:"));
//        formPanel.add(ageField);
//        formPanel.add(new JLabel("Shares:"));
//        formPanel.add(sharesField);
//        formPanel.add(new JLabel("Registration Fee:"));
//        formPanel.add(registrationFeeField);
//
//        JButton saveButton = new JButton("Save");
//        JButton cancelButton = new JButton("Cancel");
//        formPanel.add(saveButton);
//        formPanel.add(cancelButton);
//
//        editMemberDialog.add(formPanel);
//
//        saveButton.addActionListener(e -> {
//            try {
//                String name = nameField.getText();
//                int age = Integer.parseInt(ageField.getText());
//                double shares = Double.parseDouble(sharesField.getText());
//                double registrationFee = Double.parseDouble(registrationFeeField.getText());
//
//                Member updatedMember = new Member(name, age, shares, registrationFee);
//                memberService.editMember(updatedMember, rowIndex + 1);  // Update in database
//
//                loadMembers();  // Reload members after editing
//                editMemberDialog.dispose();
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(MemberManagement.this, "Please enter a valid number.");
//            }
//        });
//
//        cancelButton.addActionListener(e -> editMemberDialog.dispose());
//
//        editMemberDialog.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        new MemberManagement();
//    }
//}
