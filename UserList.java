/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg261.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class UserList {

    private ArrayList<User> userList;

    public UserList() {
        this.userList = userList;
        addUserToList();
    }

    public final void addUserToList() {
        userList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("userInfo.txt");
            Scanner scnr = new Scanner(fileReader);
            while (scnr.hasNextLine()) {
                String[] arraySplit = scnr.nextLine().split(", ");
                if (arraySplit.length == 5) {
                    String lastName = arraySplit[0];
                    String firstName = arraySplit[1];
                    String gender = arraySplit[2];
                    String age = arraySplit[3];
                    String genre = arraySplit[4];
                    userList.add(new User(lastName, firstName, gender, age, genre));
                }
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);;
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

}
