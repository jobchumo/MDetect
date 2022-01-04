package com.jobchumo.mddetect;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import io.github.redouane59.twitter.dto.user.User;
import io.github.redouane59.twitter.signature.TwitterCredentials;

public class TwitterActivity extends AppCompatActivity {

    protected  static TwitterClient twitterClient;
    protected EditText username;
    protected FirebaseUser firebaseUser;
    protected FirebaseAuth firebaseAuth;

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

        username = findViewById(R.id.twitterUsername);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

    }

    private void twitterTweets(String userName) {

        User user = twitterClient.getUserFromUserName(userName);
        String id = user.getId();

        Tweet tweet = twitterClient.getTweet("1478303491177259008");
        System.out.println(tweet.getText());

        TweetList userTimeline = twitterClient.getUserTimeline(id);
        System.out.println(userTimeline.getData());
    }

    public void userTimeline(View view) {
        String userName = username.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(TwitterActivity.this, "Empty Field! \nPlease Enter Your Username", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            twitterTweets(userName);
        }

    }
}