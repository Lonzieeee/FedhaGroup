package ac.ku.Lorna.userInterface;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Fedha Youth Group System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Initializing each screen as a panel and adding them to cardLayout
        LoginScreen loginScreen = new LoginScreen(this);
        ac.ku.Lorna.userInterface.RegisterScreen registerScreen = new ac.ku.Lorna.userInterface.RegisterScreen(this);
        Dashboard dashboard = new Dashboard(this);
        MemberManagementScreen memberManagement = new MemberManagementScreen(this);
        LoanApplicationScreen loanApplication = new LoanApplicationScreen(this);
        SavingsScreen savingsScreen = new SavingsScreen(this);
        FixedDepositScreen fixedDepositScreen = new FixedDepositScreen(this);
        ReportsScreen reportsScreen = new ReportsScreen(this);

        // Adding screens to cardLayout
        add(loginScreen, "Login");
        add(registerScreen, "Register");
        add(dashboard, "Dashboard");
        add(memberManagement, "MemberManagement");
        add(loanApplication, "LoanApplication");
        add(savingsScreen, "SavingsScreen");
        add(fixedDepositScreen, "FixedDeposit");
        add(reportsScreen, "ReportsScreen");

        // Show the login screen by default
        cardLayout.show(getContentPane(), "Login");
    }

    // Method to show a specific panel
    public void showScreen(String screenName) {
        cardLayout.show(getContentPane(), screenName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
