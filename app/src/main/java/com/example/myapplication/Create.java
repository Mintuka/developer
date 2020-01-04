package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Create extends AppCompatActivity {

    private final String TAG = "FB_SIGNIN";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText etPassword;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        etEmail = (EditText) findViewById(R.id.ET_user);
        etPassword = (EditText) findViewById(R.id.ET_password);

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


    public boolean checkFormFields() {
        String email, password;
        System.out.println("Entered");
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        Log.i("email",email);
        if (email.isEmpty()) {
            etEmail.setError("Email Required");
            Toast.makeText( Create.this, "email required", Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password Required");
            return false;
        }
        return true;
    }



    private void createUserAccount() {
        System.out.println("dfgdhdghfgfh");
        if (!checkFormFields())
            return;
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             gotoPay();
                            Toast.makeText( Create.this, "User Was Created", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(Create.this, "Account creation failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void gotoPay(){
        Intent intentSignin = new Intent(this,Pay.class);
        startActivity(intentSignin);
    }

    public void gobacktomain(View view) {
        Intent gobacktomain = new Intent(this,Welcome.class);
        startActivity(gobacktomain);
    }

    public void create_and_pay(View view) {
        createUserAccount();
    }
}
