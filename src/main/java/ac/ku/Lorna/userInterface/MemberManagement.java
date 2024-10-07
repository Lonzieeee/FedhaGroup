package ac.ku.Lorna.userInterface;

/**
 * FedhaGroup (ac.ku.Lorna.userInterface)
 * Created by: user
 * On: 10/5/2024 8:00 AM
 * Description:
 **/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MemberManagement extends JFrame {
    private JTable membersTable;  // Table where members information is displayed
    private DefaultTableModel tableModel; // This allows us to add rows,remove rows and update data dynamically
    public MemberManagement() {
        setTitle("Manage Members - Fedha Youth Grouo");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// Ensures when the user closes the window, only the window closes and not the application
        setLocationRelativeTo(null); //Centres the window
        JPanel panel = new JPanel(new BorderLayout()); //Creates a panel that uses a border layout which divides the window into 5 parts.This panel is used to add components i.e tables


//        SETTING UP THE TABLE

        String[] columnNames = {"Member Name" ,  "Age" , "Shares" ,  "Registration Fee"}; //Array to hold the column names for the table
        tableModel = new DefaultTableModel(columnNames, 0); //Initializes an empty table
        membersTable = new JTable(tableModel); //Creates a table and links it to tableModel so that when any changes in the model,it is reflected on the table
        JScrollPane tableScrollPane = new JScrollPane(membersTable); //Adds the table to a scroll pane so that when the table becomes too long, it is scrollable

//        SETTING UP THE BUTTON PANEL

        JPanel buttonPanel = new JPanel(); //Creates a panel to hold the buttons
        JButton addButton = new JButton("Add Member");
        JButton editButton = new JButton("Edit Member");
        JButton deleteButton = new JButton("Delete Member");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

//        ADDING THE COMPONENTS TO THE MAIN PANEL
        panel.add(buttonPanel, BorderLayout.SOUTH); //Adds the button panel to the bottom of the main panel
        panel.add(tableScrollPane, BorderLayout.CENTER); //Adds the table inside the scrollpane to the centre of the panel

        add(panel); //Adds the panel to the window

        //ACTION LISTENERS FOR THE BUTTONS
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddMemberForm();
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             int selectedRow = membersTable.getSelectedRow(); //gts the selected row
                if (selectedRow >=0) {
                    editMember(selectedRow); //calls the editmethod  if a row is selected
                }else {
                    JOptionPane.showMessageDialog(MemberManagement.this, "Please select a member to edit.");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   int selectedRow = membersTable.getSelectedRow();
                   if (selectedRow >= 0) {
                       tableModel.removeRow(selectedRow); //rmoves the selected row
                   }else {
                       JOptionPane.showMessageDialog(MemberManagement.this, "Please select a member to delete.");
                   }
            }
        });

      setVisible(true);
    }
    private void   showAddMemberForm(){
        JDialog addMemberDialog = new JDialog(this, "Add New Member", true); //Creates a new dialog window called "Add New Member".  (true) makes the dialog modal,this means that you can't interact with the main window until this dialog is closed
        addMemberDialog.setSize(300,200);
        addMemberDialog.setLocationRelativeTo(this); //Centres it relative to the main window


//        FORM FIELDS
        JPanel formPanel = new JPanel(new GridLayout(5,2));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel sharesLabel = new JLabel("Shares:");
        JTextField sharesField = new JTextField();
        JLabel registrationFeeLabel = new JLabel("Registration Fee:");
        JTextField registrationFeeField = new JTextField();

        JButton saveButton = new JButton("Save");  //Save button
        JButton cancelButton = new JButton("Cancel"); //cancel  button

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(ageLabel);
        formPanel.add(ageField);
        formPanel.add(sharesLabel);
        formPanel.add(sharesField);
        formPanel.add(registrationFeeLabel);
        formPanel.add(registrationFeeField);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);

        addMemberDialog.add(formPanel);

//        ACTION LISTNER FOR THE SAVE BUTTON
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { try{
                String name = nameField.getText();
                int age =Integer.parseInt(ageField.getText()) ;  //Convrts age from string to numbers
                double shares = Double.parseDouble(sharesField.getText()); //Converts shares from string to numbrs
                double registrationFee = Double.parseDouble(registrationFeeField.getText());

                tableModel.addRow(new Object[]{name,age,shares,registrationFee}); //Adds the new member's data to the table
                addMemberDialog.dispose(); //Closes the dialog after saving the data
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(MemberManagement.this, "Please enter a valid number.");
            }

            }
        });

//        ACTION LISTENER FOR THE CANCEL BUTTON
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMemberDialog.dispose();  //This closes the dialog is clicked without saving anything
            }
        });
addMemberDialog.setVisible(true);
//        MAIN METHOD THAT OPNES MemberManagement window when the program starts

    }


//    METHOD TO EDIT THE SELECTED MEMBER5
    private void editMember(int rowIndex) {

//        FETCH CURRENT DATA FROM THE SELECTED ROW
        String currentName = tableModel.getValueAt(rowIndex, 0).toString();
        String currentAge = tableModel.getValueAt(rowIndex, 1).toString();
        String currentShares = tableModel.getValueAt(rowIndex, 2).toString();
        String currentRegistrationFee = tableModel.getValueAt(rowIndex, 3).toString();

// CREATE A NEW DIALOG WINDOW FOR EDITING THE MEMBER

        JDialog editMemberDialog = new JDialog(this, "Edit Member", true);
        editMemberDialog.setSize(300,200);
        editMemberDialog.setLocationRelativeTo(this);

        //FORM FIELDS
        JPanel formPanel = new JPanel(new GridLayout(5,2));
        JTextField nameField = new JTextField(currentName);
        JTextField ageField = new JTextField(currentAge);
        JTextField sharesField = new JTextField(currentShares);
        JTextField registrationFeeField = new JTextField(currentRegistrationFee);
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

//        ADDING COMPONENTS TO THE FORM PANEL
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageField);
        formPanel.add(new JLabel("Shares:"));
        formPanel.add(sharesField);
        formPanel.add(new JLabel("Registration Fee:"));
        formPanel.add(registrationFeeField);
        formPanel.add(saveButton);
        formPanel.add(cancelButton);



        editMemberDialog.add(formPanel);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

                }catch (NumberFormatException ex){{
                JOptionPane.showMessageDialog(MemberManagement.this, "Please enter a valid number.");}
                }
            }
        });

        //CANCEL BUTTON LOGIC
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editMemberDialog.dispose();
            }
        });

        editMemberDialog.setVisible(true);

    }



    public static void main(String[] args) {
        new MemberManagement();
    }
}
