package com.jobchumo.mddetect;

public class TweetDetails {
    String tweetId;
    String text;

    public TweetDetails(String tweetId, String text) {
        this.tweetId = tweetId;
        this.text = text;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
