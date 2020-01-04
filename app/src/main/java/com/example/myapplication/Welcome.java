package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    public FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

            }
        };

        if (user != null) {
            gotopay();
        }
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

    public void gotopay() {
        Intent gotopay = new Intent(this, Pay.class);
        startActivity(gotopay);
    }
    public void gotomain(View view) {
        Intent gotomain = new Intent(this, MainActivity.class);
        startActivity(gotomain);
    }

    public void gotocreate(View view) {
        Intent gotocreate = new Intent(this, Create.class);
        startActivity(gotocreate);
    }

    public void login(View view) {
        Intent gotosignin = new Intent(this, Signin.class);
        startActivity(gotosignin);
    }
}


