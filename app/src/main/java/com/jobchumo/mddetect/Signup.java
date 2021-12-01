package com.jobchumo.mddetect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Signup extends AppCompatActivity {

    protected EditText email, pass, confpass;
    protected ProgressBar progressBar;
    protected FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email_address);
        pass = findViewById(R.id.password);
        confpass = findViewById(R.id.confirmpass);
        progressBar = new ProgressBar(this);
        firebaseAuth =FirebaseAuth.getInstance();

    }

    public void signup(View view) {
        String emaili = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String confirmpassword = confpass.getText().toString().trim();

        if (TextUtils.isEmpty(emaili) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(Signup.this, "Empty Fields! \nPlease Enter All Your Details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals(confirmpassword)) {
            firebaseSave(emaili, password);
        }
        else {
            Toast.makeText(Signup.this, "Passwords do not match \nPlease try again", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void firebaseSave(String emaili, String password) {
        final ProgressDialog progressDialog =new ProgressDialog(Signup.this);
        progressDialog.setMessage("Hold On, We are Registering You...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(emaili, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup.this, "Registration Successful \nYou Can Now Login", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            finish();
                            startActivity(new Intent(Signup.this, Login.class));
                        }
                        else {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            Toast.makeText(Signup.this, "Registration Failed: \n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });
    }
}