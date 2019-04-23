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
public class ReviewCntl {
    private final ReviewList reviewList;
    private final ReviewUI reviewUI;
    
    public ReviewCntl(){
        reviewList = new ReviewList();
        reviewUI = new ReviewUI(this);
        reviewUI.setVisible(true);    
    }
    
    public Review getReview(int index){
        String [] reviews = {reviewList.getReviewList().get(index).getSongTitle(), reviewList.getReviewList().get(index).getReview()};
        return new Review(reviews);
    }
    
}
