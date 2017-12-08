package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StaffActivity extends AppCompatActivity {

    private String currentUser;

    /**
     * Creates the StaffActivity menu
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        currentUser = getIntent().getStringExtra("username");
    }

    /**
     * Starts the LogBaleActivity
     * @param view button that clicked
     */
    public void staffLogBaleClick(View view) {
        Intent toLogBaleIntent = new Intent(this, LogBaleActivity.class);
        toLogBaleIntent.putExtra("username", currentUser);
        startActivity(toLogBaleIntent);
    }

    /**
     * Logs out the staff member to the sign in
     * @param view
     */
    public void staffLogOutClick(View view) {
        finish();
    }

    /**
     * Starts the ViewAllSchedulesActivity
     * @param view
     */
    public void toViewAllSchedulesClick(View view) {
        Intent toViewAllSchedulesIntent = new Intent(this, ViewAllSchedulesActivity.class);
        startActivity(toViewAllSchedulesIntent);
    }
}
