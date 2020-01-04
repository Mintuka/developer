package com.example.myapplication;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "FB_SIGNIN";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText etPassword;
    private EditText etEmail;
    private TextView enter_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        findViewById(R.id.btnsignin).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.ETuser).setOnClickListener(this);

        enter_email = findViewById(R.id.enter_email);
        etEmail = (EditText) findViewById(R.id.ETuser);
        etPassword = (EditText) findViewById(R.id.ETpassword);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

    }

    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener((mAuthListener));
    }

    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsignin:
                signUserIn();
                break;
            case R.id.register:
                gotocreate(v);
                break;
            case R.id.ETuser:
                Toast.makeText(Signin.this, "Signed In", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public boolean checkFormFields() {
        String email, password;

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Email Required");
            return false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password Required");
            return false;
        }
        return true;
    }



    private void signUserIn() {
        if (!checkFormFields())
            return;
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                     gotoPay();
                    Toast.makeText(Signin.this, "Signed In", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signin.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

public void gotoPay(){
    Intent intentSignin = new Intent(this,Pay.class);
    startActivity(intentSignin);
}

public void gotocreate(View view) {
        Intent intentCreate = new Intent(this, Create.class);
        startActivity(intentCreate);
    }

    public void gobacktomain(View view) {
        Intent gobacktomain = new Intent(this,Welcome.class);
        startActivity(gobacktomain);
    }
}
