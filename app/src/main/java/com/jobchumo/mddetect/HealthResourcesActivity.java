package com.jobchumo.mddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthResourcesActivity extends AppCompatActivity {

    protected ImageView mhVector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_resources);

        mhVector = findViewById(R.id.mh_vector);
        mhVector.setClipToOutline(true);
        
        linkHelper();
    }

    private void linkHelper() {
        TextView linkTextView = findViewById(R.id.line_five);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void backAction(View view) {
        startActivity(new Intent(HealthResourcesActivity.this, MoodActivity.class));
    }
}