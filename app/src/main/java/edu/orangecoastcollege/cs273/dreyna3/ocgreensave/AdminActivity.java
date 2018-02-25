package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class AdminActivity extends AppCompatActivity{

    private String currentUser;

    /**
     * Initializes the Admin Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        currentUser = getIntent().getStringExtra("username");
    }

    /**
     * Finishes the current activity
     * @param view button that clicked
     */
    public void adminLogOutClick(View view) {
        finish();
    }

    /**
     * Starts the LogBaleActivity
     * @param view button that clicked
     */
    public void adminLogBaleClick(View view) {
        Intent toLogBaleIntent = new Intent(this, LogBaleActivity.class);
        toLogBaleIntent.putExtra("username", currentUser);
        startActivity(toLogBaleIntent);
    }

    /**
     * Starts the EditSchedule Menu Activity
     * @param view button that clicked
     */
    public void toEditScheduleMenuClick(View view) {
        Intent toEditScheduleIntent = new Intent(this, EditSchedulesActivity.class);
        startActivity(toEditScheduleIntent);
    }

    /**
     * Starts the MailBaleActivity
     * @param view button that clicked
     */
    public void mailBaleHistory(View view) {
        Intent toMailBaleIntent = new Intent(this, MailBaleActivity.class);
        startActivity(toMailBaleIntent);
    }

    /**
     * Starts the ViewAllBalesActivity
     * @param view button that clicked
     */
    public void toViewAllBalesClick(View view) {
        Intent viewAllBalesIntent = new Intent(this, ViewAllBalesActivity.class);
        startActivity(viewAllBalesIntent);
    }
}
