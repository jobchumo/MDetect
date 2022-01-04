package com.jobchumo.mddetect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.GenericTweetsTimelineResponse;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.signature.TwitterCredentials;

public class TwitterActivity extends AppCompatActivity {

    protected  static TwitterClient twitterClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken("717751924129472512-wyxzUq8Os3IgMprko2pAkkFLSEQ59q2")
                .accessTokenSecret("4Tq0uQXt95eKxrmr6RA7hQP40SPTzpG7Hujek5ftZT3ZY")
                .apiKey("24xvUJF9KUdjseqCY2FTkVCc5")
                .apiSecretKey("RzPVUxgeXYzgUfR8vrAsdRC0yEZqCa4KJkK9DXaNtOlXB0GV24")
                .build());

        twitterTweets();
    }

    private void twitterTweets() {

        Tweet tweet = twitterClient.getTweet("1478303491177259008");
        System.out.println(tweet.getText());

        TweetList userTimeline = twitterClient.getUserTimeline("717751924129472512");
        System.out.println(userTimeline.getData());
    }
}