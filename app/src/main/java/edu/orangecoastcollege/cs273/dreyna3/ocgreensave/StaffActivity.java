package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StaffActivity extends AppCompatActivity {

    private String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        currentUser = getIntent().getStringExtra("username");
    }

    public void staffLogBaleClick(View view) {
        Intent toLogBaleIntent = new Intent(this, LogBaleActivity.class);
        toLogBaleIntent.putExtra("username", currentUser);
        startActivity(toLogBaleIntent);
    }

    public void staffLogOutClick(View view) {
        finish();
    }

    public void toViewAllSchedulesClick(View view) {
        Intent toViewAllSchedulesIntent = new Intent(this, ViewAllSchedulesActivity.class);
        startActivity(toViewAllSchedulesIntent);
    }
}
