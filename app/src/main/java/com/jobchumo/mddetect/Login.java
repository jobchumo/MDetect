package com.jobchumo.mddetect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Login extends AppCompatActivity {

    protected EditText email, password;
    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emaili);
        password = findViewById(R.id.passw);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Log_in(View view) {
        String email_add = email.getText().toString().trim();
        String passwe = password.getText().toString().trim();

        if (TextUtils.isEmpty(email_add) || TextUtils.isEmpty(passwe)) {
            Toast.makeText(Login.this, "Empty Fields \nPlease Enter All Your Details", Toast.LENGTH_SHORT).show();
        }
        else {
            firebaseLogin(email_add, passwe);
        }
    }

    private void firebaseLogin(String email_add, String passwe) {
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Logging You In...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email_add, passwe)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            finish();
                            startActivity(new Intent(Login.this, TwitterActivity.class));
                        }
                        else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(Login.this, "Login Failed:\n" + e.getMessage() + "\nPlease Try Again", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
    }

    public void Sign_up(View view) {
        startActivity(new Intent(Login.this, Signup.class));
    }
}