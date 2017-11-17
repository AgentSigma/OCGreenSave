package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void toCountButtonClick(View view) {
        Intent toCounterIntent = new Intent(this, CountActivity.class);
        startActivity(toCounterIntent);
        finish();
    }

    public void toAdminButtonClick(View view) {
        Intent toAdminIntent = new Intent(this, AdminActivity.class);
        startActivity(toAdminIntent);
        finish();
    }

    public void logOutButtonClick(View view) {
        Intent toLoginInent = new Intent(this, LoginActivity.class);
        // This finishes all activities and launches the login
        // Disables the user from going "back" to previous screens w/o logging in again
        toLoginInent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toLoginInent);
    }
}
