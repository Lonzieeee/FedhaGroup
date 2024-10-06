package ac.ku.Lorna.userInterface;

/**
 * FedhaGroup (ac.ku.Lorna.userInterface)
 * Created by: user
 * On: 10/5/2024 8:17 AM
 * Description:
 **/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FedhaLogin {
    public FedhaLogin() {
        // The JFrame window
        JFrame frame = new JFrame("Fedha Youth Group System - Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // This centres the window

        // Creating the panel that holds the components
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        // Creating a username label and textfield
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 50, 165, 25);
        panel.add(userText);

        // Creating a password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 90, 165, 25);
        panel.add(passwordText);

        // Creating the login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 80, 25);
        panel.add(loginButton);

        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // Login validation
                if (username.equals("Lorna") && password.equals("12345678")) {
                    JOptionPane.showMessageDialog(frame, "You have successfully logged in!");
                    // After successful login, it proceeds to the dashboard
                    frame.dispose(); // This closes the login window
                    new Dashboard(); // This is the placeholder for the dashboard screen
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!");
                }
            }
        });

        // Making the login form visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FedhaLogin();
    }
}
