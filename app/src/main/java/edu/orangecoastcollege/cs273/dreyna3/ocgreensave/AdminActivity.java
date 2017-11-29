package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void logOutClick(View view) {
        finish();
    }
}
