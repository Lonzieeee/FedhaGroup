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
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // To centre the window

        // Creating a panel using a grid layout to organize buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        JButton membersButton = new JButton("Manage Members");
        JButton loansButton = new JButton("View Loans");
        JButton sharesButton = new JButton("Manage Shares");
        JButton fixedDepositsButton = new JButton("Fixed Deposits");
        JButton reportsButton = new JButton("Generate Reports");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to panel
        panel.add(membersButton);
        panel.add(loansButton);
        panel.add(sharesButton);
        panel.add(fixedDepositsButton);
        panel.add(reportsButton);
        panel.add(logoutButton);

        add(panel); // This adds the whole panel to the frame

        // Adding action listeners to each button
        membersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             new MemberManagement();
            }
        });

        loansButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "You clicked view loans!");
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

    public static void main(String[] args) {
        new Dashboard();
    }
}
