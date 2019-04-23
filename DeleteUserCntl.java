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
public class DeleteUserCntl {

    private final UserList userList;
    private DeleteUserUI deleteUI;

    public DeleteUserCntl() {
        userList = new UserList();
        deleteUI = new DeleteUserUI(this);
        deleteUI.setVisible(true);
    }

    public User getUser(int index) {
        String[] userValues = {userList.getUserList().get(index).getFirstName(), userList.getUserList().get(index).getLastName(), userList.getUserList().get(index).getGender(),
            userList.getUserList().get(index).getAge(), userList.getUserList().get(index).getFavoriteGenre()};
        return new User(userValues[0], userValues[1], userValues[2], userValues[3], userValues[4]);
    }

    public UserList getUserList() {
        return userList;
    }

    public void getDeleteUserUI() {
        if (deleteUI.isVisible()) {
            deleteUI.setVisible(false);
        }
        deleteUI = new DeleteUserUI(this);
        deleteUI.setVisible(true);
    }
}
