package com.jobchumo.mddetect;

import java.util.List;

public class TimelineInfo {
    String twitterUserId;
    List<String> tweets;

    public TimelineInfo(String twitterUserId, List<String> tweets) {
        this.twitterUserId = twitterUserId;
        this.tweets = tweets;
    }

    public String getTwitterUserId() {
        return twitterUserId;
    }

    public void setTwitterUserId(String twitterUserId) {
        this.twitterUserId = twitterUserId;
    }

    public List<String> getTweets() {
        return tweets;
    }

    public void setTweets(List<String> tweets) {
        this.tweets = tweets;
    }
}
