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
public class Review {
    private final String firstName;
    private final String lastName;
    private final String songTitle;
    private final String review;

    public Review(String [] reviewInfo) {
        firstName = reviewInfo[0];
        lastName = reviewInfo[1];
        songTitle = reviewInfo[2];
        review = reviewInfo[3];
    }

    public String getReviewerFirstName(){
        return firstName;
    }
    
    public String getReviewerLastName(){
        return lastName;
    }
    
    public String getReviewerName(){
        return firstName + " " + lastName;
    }
    
    public String getSongTitle() {
        return songTitle;
    }

    public String getReview() {
        return review;
    }
    
}
