package com.example.parking.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RatingTest {
    private Rating rating;
    private int ratingScore;
    private String comment;

    @Before
    public void setup(){
        rating = new Rating();
        ratingScore = 5;
        comment = "Good";
        rating.setRatingScore(ratingScore);
        rating.setComment(comment);
    }

    @Test
    public void FullConTest() {
        Rating r = new Rating(5,"Awesome!","user1","user2");
        assertNotNull(r);
    }

    @Test
    public void getRatingScoreTest(){
        Assert.assertEquals(ratingScore,rating.getRatingScore());
    }

    @Test
    public void getCommentTest(){
        Assert.assertEquals(comment,rating.getComment());
    }

//    @Test
//    public void toStringTest() {
//        String str="Rating{" +
//                "ratingScore=" + ratingScore +
//                ", Comment='" + comment + '\'' +
//                '}';
//        assertEquals(str,rating.toString());
//    }
}