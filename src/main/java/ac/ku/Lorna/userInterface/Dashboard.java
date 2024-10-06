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
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  //To centre the window


//        CREATING A PANEL USING A GRID LAYOUT TO ORGANIZE BUTTONS

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2,10,10));
        JButton membersButton = new JButton("Manage Members");
        JButton loansButton = new JButton("View Loans");
        JButton sharesButton = new JButton("Manage Shares");
        JButton FixedDepositsButton = new JButton("FixedDeposits");
        JButton reportsButton = new JButton("Generate Reports");
        JButton logoutButton = new JButton("Logout");

        //ADD BUTTONS TO PANEL
        panel.add(membersButton);
        panel.add(loansButton);
        panel.add(sharesButton);
        panel.add(FixedDepositsButton);
        panel.add(reportsButton);
        panel.add(logoutButton);


        add(panel);  //THIS ADDS THE WHOLE PANEL TO THE FRAME


        //ADDING ACTION LISTENERS TO ACH BUTTON(WHAT HAPPENS TO THE BUTTON WHEN CLICKED



        membersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "You clicked manage members!"); //This opens the manage members(To be implemented)

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
//        LOGOUT BUTTON FUNCTIONALITY. WHEN CLICKED,IT ASKS THE USER FOR CONFIRMATION AND RETURNS TO THE LOGIN PAGE WHEN CLICKED YES
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Dashboard.this , "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();// This closes the dashboard
                    new FedhaLogin(); //This opens the login screen
                }
            }
        });
        setVisible(true); //THIS DISPLAYS THE DASHBOARD

    }
    public static void main(String[] args) {
        new Dashboard();
    }
}
