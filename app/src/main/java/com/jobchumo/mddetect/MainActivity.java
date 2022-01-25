package com.jobchumo.mddetect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.swagger.v3.oas.models.security.SecurityScheme;

public class MainActivity extends AppCompatActivity {

    protected FirebaseUser firebaseUser;
    protected FirebaseAuth firebaseAuth;
    protected TextView userEmail, logout;
    protected ImageView log_outIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userEmail = findViewById(R.id.showuseremail);
        String welcome = "Welcome ";
        userEmail.setText(welcome+firebaseUser.getEmail());

        logout = findViewById(R.id.log_out);
        log_outIcon = findViewById(R.id.log_out_icon);

        logout.setOnClickListener(view -> {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, Login.class));
        });

        log_outIcon.setOnClickListener(view -> {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, Login.class));
        });

    }

    public void twitter_act(View view) {
        finish();
        startActivity(new Intent(MainActivity.this, TwitterActivity.class));
    }
}