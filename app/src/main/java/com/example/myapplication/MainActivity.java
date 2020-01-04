package com.example.myapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<DataModel> mLaptopData;
    private Adapter mAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mLaptopData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new Adapter(this, mLaptopData);
        mRecyclerView.setAdapter(mAdapter);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

            }
        };
        // Get the data.
        initializeData();
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

    private void initializeData() {
        // Get the resources from the XML file.
        String[] LaptopNames = getResources()
                .getStringArray(R.array.Laptop_names);
        String[] LaptopsInfo = getResources()
                .getStringArray(R.array.Laptops_info);
        TypedArray LaptopImages = getResources()
                .obtainTypedArray(R.array.Laptops_Images);

        // Clear the existing data (to avoid duplication).
        mLaptopData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (int i = 0; i < LaptopNames.length; i++) {
            mLaptopData.add(new DataModel(LaptopNames[i], LaptopsInfo[i],
                    LaptopImages.getResourceId(i, 0)));
        }
        LaptopImages.recycle();
        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    public void gotopay() {
        Intent gotopay = new Intent(this, Pay.class);
        startActivity(gotopay);
    }

    public void login() {
        Intent gotosignin = new Intent(this, Signin.class);
        startActivity(gotosignin);

    }


    public void purchase(View view) {
                if (user != null) {
                    gotopay();
                }
                else {
                    login();
                     }
                }

    public void gobacktowelcome(View view) {
        Intent gobacktowelcome = new Intent(this,Welcome.class);
        startActivity(gobacktowelcome);
    }
}
