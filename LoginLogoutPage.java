import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginLogoutPage extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginLogoutPage() {
        setTitle("Marine Life Management System - Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginLogoutPage.this, "Login successful!");

                    // Open the main application interface
                    openMainApplicationInterface(username);
                } else {
                    JOptionPane.showMessageDialog(LoginLogoutPage.this, "Invalid username or password. Please try again.");
                }
            }
        });
    }

    private boolean authenticate(String username, String password) {
        // Implement your authentication logic here
        // For simplicity, this example uses hardcoded username and password
        return username.equals("admin") && password.equals("admin");
    }

    private void openMainApplicationInterface(String username) {
        JFrame mainFrame = new JFrame("Marine Life Management System - Main Interface");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        Map<String, String> speciesHabitatMap = new HashMap<>();
        speciesHabitatMap.put("leapord", "subtropical and tropical regions, savannas, grasslands, deserts, and rocky and mountainous regions");
        speciesHabitatMap.put("Box turtles", "open woodlands, marshy meadows, floodplains, scrub forests and brushy grasslands");
        speciesHabitatMap.put("Rhinoceros", "tropical and subtropical grasslands, savannas and shrublands, tropical moist forests, deserts and shrublands");

        Map<String, String> speciesImageMap = new HashMap<>();
        speciesImageMap.put("leapord", "leapord.png");
        speciesImageMap.put("Box turtles", "turtle.jpg");
        speciesImageMap.put("Rhinoceros", "rhino.jpg");

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        panel.add(welcomeLabel);

        JLabel speciesLabel = new JLabel("Select Species:");
        JComboBox<String> speciesComboBox = new JComboBox<>(speciesHabitatMap.keySet().toArray(new String[0]));
        panel.add(speciesLabel);
        panel.add(speciesComboBox);

        JLabel habitatLabel = new JLabel("Habitat:");
        JLabel selectedHabitatLabel = new JLabel();
        panel.add(habitatLabel);
        panel.add(selectedHabitatLabel);

        JLabel imageLabel = new JLabel();
        panel.add(new JLabel("Image:"));
        panel.add(imageLabel);

        speciesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSpecies = (String) speciesComboBox.getSelectedItem();
                String habitat = speciesHabitatMap.get(selectedSpecies);
                selectedHabitatLabel.setText(habitat);

                // Load and display the image
                String imagePath = speciesImageMap.get(selectedSpecies);
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(image));
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose(); // Close the main frame
                new LoginLogoutPage().setVisible(true); // Open the login page
            }
        });
        panel.add(logoutButton);

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginLogoutPage().setVisible(true);
            }
        });
    }
}
