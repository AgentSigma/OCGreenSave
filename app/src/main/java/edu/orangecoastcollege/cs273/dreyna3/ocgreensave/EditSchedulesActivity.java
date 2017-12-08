package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditSchedulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedules);
    }

    public void backToAdminMenuClick(View view) {
        finish();
    }

    public void toViewAllSchedulesClick(View view) {
        Intent toAllSchedulesIntent = new Intent(this, ViewAllSchedulesActivity.class);
        startActivity(toAllSchedulesIntent);
    }

    public void toEditSchedulesClick(View view) {
        Intent toEditSchedulesIntent = new Intent(this, EditScheduleListActivity.class);
        startActivity(toEditSchedulesIntent);
    }

    public void toAddScheduleClick(View view) {
        Intent toAddScheduleIntent = new Intent(this, AddScheduleActivity.class);
        startActivity(toAddScheduleIntent);
    }

    public void toDeleteSchedulesClick(View view) {
        Intent toDeleteScheduleIntent = new Intent(this, DeleteSchedulesActivity.class);
        startActivity(toDeleteScheduleIntent);
    }
}
