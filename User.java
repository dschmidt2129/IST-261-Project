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
public class User {

    private final String lastName;
    private final String firstName;
    private final String gender;
    private final String age;
    private final String favoriteGenre;

    public User(String lastName, String firstName, String gender, String age, String favoriteGenre) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.age = age;
        this.favoriteGenre = favoriteGenre;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + ", " + gender + ", " + age + ", " + favoriteGenre;
    }

}
