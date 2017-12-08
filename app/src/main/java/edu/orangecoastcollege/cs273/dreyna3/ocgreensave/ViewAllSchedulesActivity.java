package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ViewAllSchedulesActivity extends AppCompatActivity {

    private ListView mSchedulesListView;
    private List<Employee> mScheduleList;
    private DBHelper mDb;
    private ScheduleListAdapter mScheduleListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_schedules);

        mSchedulesListView = (ListView) findViewById(R.id.viewAllSchedulesListView);
        mDb = new DBHelper(this);
        mScheduleList = mDb.getAllEmployees();
        mScheduleListAdapter = new ScheduleListAdapter(this, R.layout.schedule_list_item, mScheduleList);

        mSchedulesListView.setAdapter(mScheduleListAdapter);
    }

    /**
     * Does nothing for this Activity
     * Must be implemented because schedule_list_item has this onClick for its layout
     * Handled in DeleteScheduleActivity
     */
    public void deleteThisScheduleClick(){}
}
