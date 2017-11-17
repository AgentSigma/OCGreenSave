package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void toMenuButtonClick(View view) {
        Intent toMenuIntent = new Intent(this, MenuActivity.class);
        startActivity(toMenuIntent);
        // Disables user from going back into admin options view device's 'back' feature
        finish();
    }
}
