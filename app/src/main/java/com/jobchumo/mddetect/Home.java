package com.jobchumo.mddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Sign_up(View view) {
        startActivity(new Intent(Home.this, Signup.class));
    }

    public void Login(View view) {
        startActivity(new Intent(Home.this, Login.class));
    }
}