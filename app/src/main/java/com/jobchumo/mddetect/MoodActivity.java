package com.jobchumo.mddetect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.models.security.SecurityScheme;
import kotlin.jvm.internal.CollectionToArray;


public class MoodActivity extends AppCompatActivity {

    protected String url = "https://tryflaskdetect.herokuapp.com/predict";
    protected FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    protected FirebaseDatabase firebaseDatabase;
    protected DatabaseReference databaseReference;
    protected DatabaseReference mUser;
    protected TextView timeView, dateView, moodDesc, adviseMood;
    protected ImageView emojiMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        firebaseAuth = FirebaseAuth.getInstance();
        dateView = findViewById(R.id.dateDesc);
        timeView = findViewById(R.id.timeDesc);
        moodDesc = findViewById(R.id.mood_Desc);
        adviseMood = findViewById(R.id.advice_when_sad);
        emojiMood = findViewById(R.id.emoji_mood);

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(MoodActivity.this, Login.class));
        }

        SimpleDateFormat simpleDateFormat;
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("h:mm aa");
        String currentTime = simpleTimeFormat.format(new Date());
        timeView.setText(currentTime);


        determineMood();
    }
    private void determineMood() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting your mood...");
        progressDialog.show();

        final String[] tweet_arr = {""};
        databaseReference = FirebaseDatabase.getInstance().getReference("Timeline");
        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference.child(firebaseUser.getUid()).child("tweets").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d("ErrorDatabase", task.getException().toString());
            }
            else {
                Log.d("firebase_tweet", String.valueOf(task.getResult().getValue()));
                tweet_arr[0] = String.valueOf(task.getResult().getValue());
                String tweets = Arrays.toString(tweet_arr);
                Log.d("tweets_to_array", tweets);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                String result = response.toString();
                                Log.d("Depression Prediction", result);
                                if (result.equals("0")) {
                                    String mood = "Happy";
                                    String adviceWhenHappy = "Keep it up";
                                    moodDesc.setText(mood);
                                    adviseMood.setText(adviceWhenHappy);
                                    emojiMood.setImageResource(R.drawable.happy_emoji);
                                    progressDialog.dismiss();
                                    Log.d("Depression Status", "You are not depressed");
                                }
                                else {
                                    String moodSad = "Sad";
                                    String adviceWhenSad = "You might want to check out the resources on the next page";
                                    moodDesc.setText(moodSad);
                                    adviseMood.setText(adviceWhenSad);
                                    emojiMood.setImageResource(R.drawable.sad_emoji);
                                    progressDialog.dismiss();
                                    Log.d("Depression Status", "You are depressed");
                                }
                            }
                        },
                        error -> {
                            String request_error = error.getMessage();
                            Log.d("Request Error", request_error);

                        }){
                    @Override
                    protected Map getParams(){
                        Map params = new HashMap();
                        params.put("mood_pred",tweets);
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(MoodActivity.this);
                queue.add(stringRequest);
            }
        });

    }

    public void continueAction(View view) {
        startActivity(new Intent(MoodActivity.this, HealthResourcesActivity.class));
    }

    public void backAction(View view) {
        startActivity(new Intent(MoodActivity.this, TwitterActivity.class));
    }
}