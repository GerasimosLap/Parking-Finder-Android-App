package com.example.parking.domain;

public class Rating {
    private int ratingScore;
    private String comment;
    private String ratedUsername;
    private String ratingUsername;



    public Rating(int ratingScore, String comment,String ratedUsername,String ratingUsername) {
        this.ratingScore = ratingScore;
        this.comment = comment;
        this.ratedUsername=ratedUsername;
        this.ratingUsername=ratingUsername;
    }

    public Rating() {
        this.ratingScore = 0;
        this.comment = "";
        this.ratedUsername="";
        this.ratingUsername="";
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRatedUsername() {
        return ratedUsername;
    }

    public void setRatedUsername(String ratedUsername) {
        this.ratedUsername = ratedUsername;
    }

    public String getRatingUsername() {
        return ratingUsername;
    }

    public void setRatingUsername(String ratingUsername) {
        this.ratingUsername = ratingUsername;
    }



    @Override
    public String toString() {
        return  "*************************\n" +
                "Rating: " + ratingScore +" \n "+
                "Comment: " + comment + " \n "+
                "Rated User: " + ratedUsername + " \n " +
                "Rating by: " + ratingUsername + " \n " +
                "*************************";
    }


}
