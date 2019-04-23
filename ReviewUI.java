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
public class ReviewUI extends JFrame {

    private final ReviewCntl reviewCntl;

    private final JTextField songTitleDisplayValue = new JTextField(15);
    private final JTextField reviewDisplayValue = new JTextField(15);
    private final JTextField firstNameDisplayValue = new JTextField(15);
    private final JTextField lastNameDisplayValue = new JTextField(15);

    private JPanel reviewPanel;
    private JPanel buttonPanel;

    public ReviewUI(ReviewCntl reviewCntl) {
        this.reviewCntl = reviewCntl;
        initComponents();
    }

    private void initComponents() {
        setTitle("Review Viewer");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        reviewPanel = new JPanel(new GridLayout(5, 1));
        reviewPanel.add(new JLabel("User First Name: "));
        reviewPanel.add(firstNameDisplayValue);
        reviewPanel.add(new JLabel("User Last Name: "));
        reviewPanel.add(lastNameDisplayValue);
        reviewPanel.add(new JLabel("Song Title: "));
        reviewPanel.add(songTitleDisplayValue);
        reviewPanel.add(new JLabel("Song Review: "));
        reviewPanel.add(reviewDisplayValue);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitReviewButton = new JButton("Submit Review");

        submitReviewButton.addActionListener(e -> createAndWriteReviewFile());
        submitReviewButton.addActionListener(e -> this.dispose());
        buttonPanel.add(submitReviewButton);

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(reviewPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }

    private void createAndWriteReviewFile() {

        String fileName = "reviewInfo.txt";
        String fileName2 = "tempReview.txt";
        File file = new File(fileName);
        File file2 = new File(fileName2);
        Map<String, String> reviewMap = addReviewsToMap(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (file.exists()) {
                try (FileWriter fileWriter = new FileWriter(file, true); BufferedWriter buffWriter = new BufferedWriter(fileWriter)) {

                    if ((firstNameDisplayValue.getText().isEmpty()) || (lastNameDisplayValue.getText().isEmpty())
                            || (songTitleDisplayValue.getText().isEmpty()) || (reviewDisplayValue.getText().isEmpty())) {
                        fillInReviewPopup();
                    } else {
                        String userAndSongName = firstNameDisplayValue.getText().toLowerCase() + " "
                                + lastNameDisplayValue.getText().toLowerCase() + "; " + songTitleDisplayValue.getText().toLowerCase() + "; ";
                        String review = reviewDisplayValue.getText().toLowerCase();

                        reviewMap.put(userAndSongName, review);
                        buffWriter.write(userAndSongName + review + "\n");
                        buffWriter.newLine();

                        addReviewMessage();

                        }
                    buffWriter.close();
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private Map<String, String> addReviewsToMap(String fileName) {
        Map<String, String> reviewMap = new HashMap<>();
        File file = new File(fileName);
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String[] review = line.split("; ", 3);
                if (review.length >= 3) {
                    String userAndSongName = review[0] + "; " + review[1] + "; ";
                    String songReview = review[2];
                    reviewMap.put(userAndSongName, songReview);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return reviewMap;
    }

    private void addReviewMessage() {
        String message = "Review has been added to reviewInfo.txt";
        JOptionPane.showMessageDialog(null, message, "Added Review", JOptionPane.INFORMATION_MESSAGE);
    }

    private void fillInReviewPopup() {
        String message = "Please fill in the required fields";
        JOptionPane.showMessageDialog(null, message, "Review Data Needed to Add", JOptionPane.INFORMATION_MESSAGE);
    }

}
