package ac.ku.Lorna.userInterface;

import ac.ku.Lorna.dao.UsersDAO;

import javax.swing.*;
import java.awt.*;

public class RegistrationScreen extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private UsersDAO usersDAO;

    public RegistrationScreen(MainFrame mainFrame) {
        setLayout(new GridBagLayout());
        usersDAO = new UsersDAO();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(passwordField, gbc);

        // Role Selection Dropdown
        JLabel roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(new String[]{"Select Role", "Admin", "User"});

        gbc.gridx = 0; gbc.gridy = 2;
        add(roleLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(roleComboBox, gbc);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> registerUser(mainFrame));

        gbc.gridx = 1; gbc.gridy = 3;
        add(registerButton, gbc);
    }

    private void registerUser(MainFrame mainFrame) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || "Select Role".equals(selectedRole)) {
            JOptionPane.showMessageDialog(this, "Please fill all fields correctly", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = usersDAO.registerUser(username, password, selectedRole);
        if (success) {
            JOptionPane.showMessageDialog(this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.showScreen("LoginScreen");
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
