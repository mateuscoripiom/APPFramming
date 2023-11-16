package com.example.framming;

public class FeedbackRequest {
    private String idMovie;
    private String feedbackText;
    private float feedbackRate;
    private String feedbackDate;

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public float getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(float feedbackRate) {
        this.feedbackRate = feedbackRate;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
