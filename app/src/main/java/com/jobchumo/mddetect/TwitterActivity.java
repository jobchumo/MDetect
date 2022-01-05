package com.jobchumo.mddetect;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twitter.clientlib.JSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
                .bearerToken("AAAAAAAAAAAAAAAAAAAAAKzISwEAAAAArZZ4ThC0joFGnlENHNZPVHZfrdo%3DJc8MadyRgarKojzrhcaH0pJS2w8oBXT6u1IDvAcaDfjW0ILqG4")
                .build());

        username = findViewById(R.id.twitterUsername);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


    }

    private class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            String usernam = username.getText().toString();
            try {
                User user = twitterClient.getUserFromUserName(usernam);
                String id = user.getId();
                Log.d("UserID", id);

                Tweet tweet = twitterClient.getTweet("1478303491177259008");
                System.out.println(tweet.getText());
                String tweetText = tweet.getText();
                Log.d("Tweet", tweetText);

                TweetList userTimeline = twitterClient.getUserTimeline(id);
                System.out.println(userTimeline.getData());
            } catch (NetworkOnMainThreadException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
        }
    }

    private void twitterTweets(String userName) {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//
//        StrictMode.setThreadPolicy(policy);
//
//        try {
//            User user = twitterClient.getUserFromUserName(userName);
//            String id = user.getId();
//
//            Tweet tweet = twitterClient.getTweet("1478303491177259008");
//            System.out.println(tweet.getText());
//
//            TweetList userTimeline = twitterClient.getUserTimeline(id);
//            System.out.println(userTimeline.getData());
//        } catch (NetworkOnMainThreadException e) {
//            Log.d("Network Exception", e.getMessage());
//        }


    }

    public void userTimeline(View view) {
        String userName = username.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Your Timeline...");
        progressDialog.show();
        //String url = "https://api.twitter.com/2/users/by/username/jobchumo\" -H \"Authorization: Bearer $AAAAAAAAAAAAAAAAAAAAAKzISwEAAAAArZZ4ThC0joFGnlENHNZPVHZfrdo%3DJc8MadyRgarKojzrhcaH0pJS2w8oBXT6u1IDvAcaDfjW0ILqG4";

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(TwitterActivity.this, "Empty Field! \nPlease Enter Your Username", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            new MyTask().execute();
            progressDialog.hide();
        }

    }
}