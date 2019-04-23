/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg261.project;

import java.util.ArrayList;

/**
 *
 * @author david
 */
public class ReviewList {
    private final ArrayList<Review> reviewList;

    public ReviewList() {
        reviewList = new ArrayList<>();   
    }
    
    public ArrayList<Review> getReviewList(){
        return reviewList;
    }
    
}
