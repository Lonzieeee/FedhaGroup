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
public class FedhaLogin{
    public static void main(String[] args) {

//        The JFrame window
        JFrame frame = new JFrame("Fedha Youth Group System - Login");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //This centres the window


//      CREATING THE PANEL THAT HOLDS THE COMPONENTS


        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

//        CREATING A USERNAME LABEL AND TEXTFIELD
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 80, 25);
        panel.add(userLabel);


        JTextField userText = new JTextField(20);
        userText.setBounds(150, 50, 165, 25);
        panel.add(userText);

//        CREATING A PASSWORD LABEL AND PASSWORD FIELD

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 50, 165, 25);
        panel.add(passwordText);


//        CREATING THE LOGIN BUTTON
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 100, 80, 25);
        panel.add(loginButton);

//        LOGINBUTTON ACTION LISTENER
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());


//                LOGIN VALIDATION

                if(username.equals("Lorna") && password.equals("12345678")){
                    JOptionPane.showMessageDialog(frame, "You have successfully logged in!");
//                    AFTER SUCCESSFUL LOGIN , IT PROCEEDS TO THE DASHBOARD
                    frame.dispose(); //This closes the login window
                    new Dashboard();  //This is the placeholder for the dashboard screen
                }else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!");
                }
//                MAKING THE LOGIN FORM VISIBLE
                frame.setVisible(true);
            }
        });
    }


}
