package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void toCreateAccount(View view) {
        Intent toCreateIntent = new Intent(this, CreateProfileActivity.class);
        startActivity(toCreateIntent);
    }

    public void toMenuButtonClick(View view) {
        Intent toMenuIntent = new Intent(this, MenuActivity.class);

        // Check against admin key if one is entered, otherwise just login with a name

        startActivity(toMenuIntent);
    }
}
