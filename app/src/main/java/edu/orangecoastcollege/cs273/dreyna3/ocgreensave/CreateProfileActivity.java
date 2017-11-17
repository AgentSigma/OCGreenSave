package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
    }

    public void createAccountButtonClick(View view) {
        Intent toMenuIntent = new Intent(this, MenuActivity.class);
        startActivity(toMenuIntent);
        finish();
        // Disables user from going back to create account screen with back
        // instead is sent back to the login if user hits back on device
    }
}
