package com.jobchumo.mddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


public class MoodActivity extends AppCompatActivity {

    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

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