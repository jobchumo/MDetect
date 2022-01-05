package com.jobchumo.mddetect;

public class TimelineInfo {
    String tweetID;
    String tweetText;

    public TimelineInfo(String tweetID, String tweetText) {
        this.tweetID = tweetID;
        this.tweetText = tweetText;
    }

    public String getTweetID() {
        return tweetID;
    }

    public void setTweetID(String tweetID) {
        this.tweetID = tweetID;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }
}
