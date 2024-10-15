package ac.ku.Lorna.userInterface;

/**
 * FedhaGroup (ac.ku.Lorna.userInterface)
 * Created by: user
 * On: 10/5/2024 7:59 AM
 * Description:
 **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("Fedha Youth Group System - Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // To centre the

        getContentPane().setBackground(new Color(230, 240, 255));

        // Creating a panel using a gridbaglayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15, 15, 15, 15);  //Padding around each button
        panel.setBackground(new Color(230, 240, 255));

        //TITLE LABEL WITH STYLING

        JLabel titleLabel = new JLabel("Fedha Youth Group Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2; //Title spans 2 columns
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, constraints);

        JButton membersButton = createStyledButton("Manage Members");
        JButton loansButton = createStyledButton("View Loans");
        JButton sharesButton = createStyledButton("Manage Shares");
        JButton fixedDepositsButton = createStyledButton("Fixed Deposits");
        JButton reportsButton = createStyledButton("Generate Reports");
        JButton logoutButton = createStyledButton("Logout");

        //Adding buttons to panel with proper positioning
        constraints.gridwidth=1;
        constraints.anchor = GridBagConstraints.CENTER;  //Centres all buttons

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(membersButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(loansButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(sharesButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(fixedDepositsButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(reportsButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(logoutButton, constraints);


        add(panel); // This adds the whole panel to the frame

        // Adding action listeners to each button
        membersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             new MemberManagement();
            }
        });

        loansButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new LoanManagement();
            }
        });

        sharesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "You clicked view shares!");
            }
        });

        // Logout button functionality
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Dashboard.this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose(); // This closes the dashboard
                    new FedhaLogin(); // This opens the login screen
                }
            }
        });

        setVisible(true); // This displays the dashboard
    }


    //METHOD TO CREATE A BUTTON WITH CUSTOM STYLING
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 153, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(180, 40)); //Fixed size for Button
        return button;

    }

    public static void main(String[] args) {

        new Dashboard();
    }
}
