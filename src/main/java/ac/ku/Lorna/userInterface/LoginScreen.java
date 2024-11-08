package ac.ku.Lorna.userInterface;

import ac.ku.Lorna.dao.UsersDAO;
import ac.ku.Lorna.models.User;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private UsersDAO usersDAO;

    public LoginScreen(MainFrame mainFrame) {
        usersDAO = new UsersDAO();
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Fedha Youth Group System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Role
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Role:"), gbc);

        roleComboBox = new JComboBox<>(new String[]{"Admin", "User"});
        gbc.gridx = 1;
        add(roleComboBox, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Action Listeners
        loginButton.addActionListener(e -> authenticateUser(mainFrame));
        registerButton.addActionListener(e -> mainFrame.showScreen("Register"));
    }

    private void authenticateUser(MainFrame mainFrame) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = (String) roleComboBox.getSelectedItem();

        User user = usersDAO.authenticateUser(username, password);

        if (user != null && user.getRole().equalsIgnoreCase(role)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.showScreen("Dashboard");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username, password, or role.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
