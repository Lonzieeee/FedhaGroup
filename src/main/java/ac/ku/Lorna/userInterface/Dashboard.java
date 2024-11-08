package ac.ku.Lorna.userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JPanel {

    public Dashboard(MainFrame mainFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));  // Light background color

        // Title label with a simple fade-in effect
        JLabel titleLabel = new JLabel("Welcome to Fedha Youth Group System", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));  // Dark blue for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));  // Padding around the title

        // Add title label
        add(titleLabel, BorderLayout.NORTH);

        // Create button panel with slide-in effect from left
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 20, 20));  // 2x2 grid with 20px spacing between buttons
        buttonPanel.setBackground(new Color(240, 240, 240));  // Transparent background for the panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));  // Padding for button panel

        // Create and add buttons
        JButton memberManagementButton = createStyledButton("Member Management", mainFrame, "MemberManagement");
        JButton loanApplicationButton = createStyledButton("Loan Application", mainFrame, "LoanApplication");
        JButton fixedDepositButton = createStyledButton("Fixed Deposit", mainFrame, "FixedDeposit");
        JButton reportsButton = createStyledButton("Reports", mainFrame, "ReportsScreen");

        // Add buttons to the panel
        buttonPanel.add(memberManagementButton);
        buttonPanel.add(loanApplicationButton);
        buttonPanel.add(fixedDepositButton);
        buttonPanel.add(reportsButton);

        // Add button panel to the main layout
        add(buttonPanel, BorderLayout.CENTER);

        // Slide-in effect for the button panel
        slideIn(buttonPanel);

        // Button fade-in effect one by one
        fadeInButtons(buttonPanel);
    }

    private JButton createStyledButton(String text, MainFrame mainFrame, String screenName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(0, 102, 204));  // Same blue color for all buttons
        button.setForeground(Color.WHITE);  // White text color
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 200));  // Larger button size (200x200)
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // Pointer cursor on hover

        // Apply rounded corners using an empty border
        button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Padding around text

        // Action listener for screen transition
        button.addActionListener(e -> {
            System.out.println("Switching to " + screenName);
            mainFrame.showScreen(screenName);  // Assuming showScreen() is implemented in MainFrame
        });

        // Hover effect: Bounce animation and color change
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Timer timer = new Timer(10, new ActionListener() {
                    int yOffset = 0;
                    public void actionPerformed(ActionEvent e) {
                        if (yOffset < 10) {  // Bounce effect by moving the button up and down
                            yOffset++;
                            button.setLocation(button.getX(), button.getY() - 1);  // Move button up
                        } else if (yOffset < 20) {
                            yOffset++;
                            button.setLocation(button.getX(), button.getY() + 1);  // Move button down
                        }
                    }
                });
                timer.start();

                // Change button color on hover
                button.setBackground(new Color(0, 153, 255));  // Lighter blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setLocation(button.getX(), button.getY());  // Reset to original position

                // Revert button color to original on mouse exit
                button.setBackground(new Color(0, 102, 204));  // Original blue color
            }
        });

        return button;
    }

    // Slide-in effect for the button panel
    private void slideIn(JPanel panel) {
        Timer timer = new Timer(10, new ActionListener() {
            int xOffset = -panel.getWidth();  // Start with the panel off-screen
            public void actionPerformed(ActionEvent e) {
                if (xOffset < 0) {
                    xOffset += 10;  // Gradually move the panel to the right
                    panel.setLocation(xOffset, panel.getY());
                } else {
                    ((Timer)e.getSource()).stop();  // Stop the timer when the panel is fully visible
                }
            }
        });
        timer.start();
    }

    // Fade-in effect for the buttons one by one
    private void fadeInButtons(JPanel panel) {
        Component[] components = panel.getComponents();
        Timer timer = new Timer(200, new ActionListener() {
            int index = 0;  // Start with the first button
            public void actionPerformed(ActionEvent e) {
                if (index < components.length) {
                    JButton button = (JButton) components[index];
                    button.setOpaque(true);  // Make button visible
                    button.setBackground(new Color(0, 102, 204));  // Set the background to blue
                    index++;
                } else {
                    ((Timer)e.getSource()).stop();  // Stop the timer when all buttons are visible
                }
            }
        });
        timer.start();
    }
}
