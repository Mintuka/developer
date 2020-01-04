package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Pay extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               user = firebaseAuth.getCurrentUser();
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

    public void Singout(View view) {
        mAuth.signOut();
        Intent gotomain = new Intent(this,MainActivity.class);
        startActivity(gotomain);
    }

    public void confirm(View view) {
        Toast.makeText(Pay.this, "Thanks a lot", Toast.LENGTH_SHORT).show();
    }

    public void gobacktomain(View view) {
        Intent gotomain = new Intent(this,MainActivity.class);
        startActivity(gotomain);
    }
}
