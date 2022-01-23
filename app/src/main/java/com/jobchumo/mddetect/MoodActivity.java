package com.jobchumo.mddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.auth.FirebaseAuth;

import io.swagger.v3.oas.models.security.SecurityScheme;


public class MoodActivity extends AppCompatActivity {

    String url = "";
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(MoodActivity.this, Login.class));
        }

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