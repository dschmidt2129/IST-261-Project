/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg261.project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class UserUI extends JFrame {

    public final JTextField firstNameDisplayValue = new JTextField(15);//used in the review UI to write reviews to text file
    public final JTextField lastNameDisplayValue = new JTextField(15);//used in the review UI to write reviews to text file
    private final JTextField genderDisplayValue = new JTextField(15);
    private final JTextField ageDisplayValue = new JTextField(15);
    private final JTextField favoriteGenreDisplayValue = new JTextField(15);

    private JPanel userPanel;
    private JPanel buttonPanel;
    private JPanel labelPanel;

    private final UserCntl userCntl;

    public UserUI(UserCntl userCntl) {
        this.userCntl = userCntl;
        initComponents();
    }

    private void initComponents() {
        setTitle("User Viewer");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("*fields with asterisks(*) are required*");
        labelPanel.add(label);

        userPanel = new JPanel(new GridLayout(5, 1));
        userPanel.add(new JLabel("First Name* "));
        userPanel.add(firstNameDisplayValue);
        userPanel.add(new JLabel("Last Name* "));
        userPanel.add(lastNameDisplayValue);
        userPanel.add(new JLabel("Gender* "));
        userPanel.add(genderDisplayValue);
        userPanel.add(new JLabel("Age* "));
        userPanel.add(ageDisplayValue);
        userPanel.add(new JLabel("Favorite Genre* "));
        userPanel.add(favoriteGenreDisplayValue);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitInfoButton = new JButton("Go to Review Song Page");
        JButton addUserButton = new JButton("Add New User");
        JButton deleteUserButton = new JButton("Go to Delete User Page");
        submitInfoButton.addActionListener(e -> this.dispose());
        submitInfoButton.addActionListener(e -> goToReviewUI());
        addUserButton.addActionListener(e -> createAndWriteUserFile());
        deleteUserButton.addActionListener(e -> this.dispose());
        deleteUserButton.addActionListener(e -> goToDeleteUI());
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(addUserButton);
        buttonPanel.add(submitInfoButton);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(labelPanel, BorderLayout.NORTH);
        getContentPane().add(userPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void goToReviewUI() {
        ReviewCntl reviewCntl = new ReviewCntl();
    }

    private void goToDeleteUI() {
        DeleteUserCntl deleteUserCntl = new DeleteUserCntl();
    }

    private void createAndWriteUserFile() {
        String fileName = "userInfo.txt";
        File file = new File(fileName);
        Map<String, String> fileMap = addUsersToMap();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                try (FileWriter fileWriter = new FileWriter(file, true); BufferedWriter buffWriter = new BufferedWriter(fileWriter)) {
                    try (FileWriter fileWriter1 = new FileWriter(file, false)) {
                        fileWriter1.write("");
                    }
                    if ((lastNameDisplayValue.getText().isEmpty()) || (firstNameDisplayValue.getText().isEmpty()) || (genderDisplayValue.getText().isEmpty())
                            || (ageDisplayValue.getText().isEmpty()) || (favoriteGenreDisplayValue.getText().isEmpty())) {
                        fillInDataPopup();
                    } else {
                        String userName = lastNameDisplayValue.getText().toLowerCase() + ", " + firstNameDisplayValue.getText().toLowerCase() + ", ";
                        String info = genderDisplayValue.getText().toLowerCase() + ", " + ageDisplayValue.getText().toLowerCase() + ", " + favoriteGenreDisplayValue.getText().toLowerCase();

                        if (!fileMap.containsKey(userName)) {
                            fileMap.put(userName, info);
                            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                                buffWriter.write(entry.getKey() + entry.getValue() + "\n");
                                buffWriter.newLine();
                                buffWriter.flush();
                            }
                            addUserMessage();
                        } else {
                            didNotAddMessage();
                        }
                    }
                    buffWriter.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private Map<String, String> addUsersToMap() {
        Map<String, String> userMap = new HashMap<>();
        File file = new File("userInfo.txt");
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String[] users = line.split(", ", 5);
                if (users.length >= 5) {
                    String name = users[0] + ", " + users[1] + ", ";
                    String info = users[2] + ", " + users[3] + ", " + users[4];
                    userMap.put(name, info);
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return userMap;
    }
    
    public String getFirstName(){
        return this.firstNameDisplayValue.getText();
    }
    
    public String getLastName(){
        return this.lastNameDisplayValue.getText();
    }

    private void fillInDataPopup() {
        String message = "Please fill in the required fields";
        JOptionPane.showMessageDialog(null, message, "Info Needed to Add", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addUserMessage() {
        String message = "User has been added to userInfo.txt";
        JOptionPane.showMessageDialog(null, message, "Added User", JOptionPane.INFORMATION_MESSAGE);
    }

    private void didNotAddMessage() {
        String message = "User has already been entered into the system.  Please enter a different first and last name pairing";
        JOptionPane.showMessageDialog(null, message, "Add New User Deficiency", JOptionPane.INFORMATION_MESSAGE);
    }

}
