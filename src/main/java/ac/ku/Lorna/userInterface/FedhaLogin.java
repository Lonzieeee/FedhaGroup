package ac.ku.Lorna.userInterface;

/**
 * FedhaGroup (ac.ku.Lorna.userInterface)
 * Created by: user
 * On: 10/5/2024 8:17 AM
 * Description:
 **/

import javax.swing.*;
import java.awt.*;
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
        panel.setLayout(new GridBagLayout()); //For better alignment
        panel.setBackground(new Color(240, 240, 240));


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);  //To add padding


        //TITLE LABEL FOR THE LOGIN FORM

        JLabel titlelabel = new JLabel("Login To Fedha System");
        titlelabel.setFont(new Font("Arial", Font.BOLD, 20)); //Larger font
        titlelabel.setForeground(new Color(0, 102, 204)); //Colour for the title
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titlelabel, constraints);

        // Creating a username label and textfield


        JLabel userLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_END;  //Aligns label to the right

        panel.add(userLabel , constraints);

        JTextField userText = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_START;  //Alligns textfield to the left
        panel.add(userText , constraints);


        // Creating a password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(passwordLabel , constraints);

        JPasswordField passwordText = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(passwordText , constraints);

        // Creating the login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);   //This removes the button focus border

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;  //Spans 2 columns making it centred
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

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
