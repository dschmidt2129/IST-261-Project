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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author david
 */
public class DeleteUserUI extends JFrame {

    private final DeleteUserCntl deleteUserCntl;

    private JPanel deletePanel;
    private JPanel buttonPanel;

    private final JTextField lastNameDisplayValue = new JTextField(15);
    private final JComboBox<User> deleteLastNameBox = new JComboBox<>();

    public DeleteUserUI(DeleteUserCntl deleteUserCntl) {
        this.deleteUserCntl = deleteUserCntl;
        initComponents();
    }

    private void initComponents() {
        setTitle("Delete User");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        deletePanel = new JPanel(new GridLayout(5, 1));
        deletePanel.add(new JLabel("User Last Name to be Deleted: "));
        deletePanel.add(lastNameDisplayValue);
        deletePanel.add(new JLabel("Select User to be Deleted: "));
        deletePanel.add(deleteLastNameBox);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton searchButton = new JButton("Search for User");
        searchButton.addActionListener(e -> searchForUser());
        buttonPanel.add(searchButton);
        JButton deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(e -> goToUserUI());
        deleteButton.addActionListener(e -> this.dispose());
        deleteButton.addActionListener(e -> deleteUser());
        buttonPanel.add(deleteButton);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(deletePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void goToUserUI() {
        UserCntl userCntl = new UserCntl();
        this.dispose();
    }

    private ArrayList<User> searchForUser() {
        ArrayList<User> userList = new ArrayList<>();
        if (deleteUserCntl.getUserList().getUserList().isEmpty()) {
            emptyListMessage();
            goToUserUI();
        }
        if (lastNameDisplayValue.getText() == null) {
            enterUserMessage();
        }
        for (User user : deleteUserCntl.getUserList().getUserList()) {
            if (lastNameDisplayValue.getText().equalsIgnoreCase(user.getLastName())) {
                deleteLastNameBox.addItem(user);
            } else {
                searchUserMessage();
            }
        }
        return userList;
    }

    private void deleteUser() {
        if (deleteUserCntl.getUserList().getUserList().isEmpty()) {
            emptyListMessage();
            goToUserUI();
        }
        if (deleteLastNameBox.getSelectedItem() == null) {
            selectUserMessage();
        } else {
            try {

                File inFile = new File("userInfo.txt");

                if (!inFile.isFile()) {
                    System.out.println("Parameter is not an existing file");
                    return;
                }

                File tempFile = new File("userInfoTemp.txt");

                try (BufferedReader br = new BufferedReader(new FileReader(inFile)); PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

                    String line;

                    String lineToRemove = deleteLastNameBox.getSelectedItem().toString().trim();

                    while ((line = br.readLine()) != null) {
                        if (!line.trim().contains(lineToRemove)) {
                            pw.println(line);
                            pw.flush();
                        }
                    }

                }
                if (!inFile.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }

                if (!tempFile.renameTo(inFile)) {
                    System.out.println("Could not rename file");
                }
                deleteUserMessage();
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    private void searchUserMessage() {
        String message = "User does not exist";
        JOptionPane.showMessageDialog(null, message, "No Such User", JOptionPane.INFORMATION_MESSAGE);
    }

    private void enterUserMessage() {
        String message = "Please Search for User";
        JOptionPane.showMessageDialog(null, message, "Search for User", JOptionPane.INFORMATION_MESSAGE);
    }

    private void selectUserMessage() {
        String message = "Select from dropdown menu";
        JOptionPane.showMessageDialog(null, message, "Select User", JOptionPane.INFORMATION_MESSAGE);
    }

    private void emptyListMessage() {
        String message = "List is empty";
        JOptionPane.showMessageDialog(null, message, "Empty List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteUserMessage() {
        String message = "User was deleted";
        JOptionPane.showMessageDialog(null, message, "Deleted User", JOptionPane.INFORMATION_MESSAGE);
    }
}
