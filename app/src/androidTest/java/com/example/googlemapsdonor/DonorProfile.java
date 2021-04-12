package com.example.googlemapsdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.googlemapsdonor.firebasehandler.FBUserAuthHandler;

public class DonorProfile extends AppCompatActivity {
FBUserAuthHandler userAuthHandler = new FBUserAuthHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);


    }
    public void onCreateDonation(View view){
        Intent intent = new Intent(getApplicationContext(),DonorActivity.class);
        startActivity(intent);

    }
    public void onDonationStatus(View view){
        Intent intent = new Intent(getApplicationContext(),DonationsStatus.class);
        startActivity(intent);
    }
    public void onSignOut(View v){
        userAuthHandler.signOutUser();
//        Log.d("Donor profile", );
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}
