/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg261.project;

/**
 *
 * @author david
 */
public class UserCntl {

    private final UserList userList;
    private final UserUI userUI;

    public UserCntl() {
        userList = new UserList();
        userUI = new UserUI(this);
        userUI.setVisible(true);
    }

    public User getUser(int index) {
        String[] userValues = {userList.getUserList().get(index).getFirstName(), userList.getUserList().get(index).getLastName(), userList.getUserList().get(index).getGender(),
            userList.getUserList().get(index).getAge(), userList.getUserList().get(index).getFavoriteGenre()};
        return new User(userValues[0], userValues[1], userValues[2], userValues[3], userValues[4]);
    }
}
