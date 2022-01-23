package com.jobchumo.mddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.v3.oas.models.security.SecurityScheme;


public class MoodActivity extends AppCompatActivity {

    protected String url = "";
    protected FirebaseAuth firebaseAuth;
    protected TextView timeView, dateView, moodDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        firebaseAuth = FirebaseAuth.getInstance();
        dateView = findViewById(R.id.dateDesc);
        timeView = findViewById(R.id.timeDesc);

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

    }

    public void continueAction(View view) {
        startActivity(new Intent(MoodActivity.this, HealthResourcesActivity.class));
    }

    public void backAction(View view) {
        startActivity(new Intent(MoodActivity.this, TwitterActivity.class));
    }
}