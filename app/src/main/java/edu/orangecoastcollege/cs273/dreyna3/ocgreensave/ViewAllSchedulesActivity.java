package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class ViewAllSchedulesActivity extends AppCompatActivity {

    private ListView mSchedulesListView;
    private List<Employee> mScheduleList;
    private DBHelper mDb;
    private ScheduleListAdapter mScheduleListAdapter;

    /**
     * Creates the ViewAllSchedulesActivity
     * @param savedInstanceState
     */
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
     * This will send the user to the EmailScheduleActivity
     * @param view the selected item
     */
    public void scheduleClicked(View view){
        LinearLayout selected = (LinearLayout) view;
        Employee employee = (Employee) selected.getTag();
        Intent toMailScheduleIntent = new Intent(this, MailScheduleActivity.class);
        toMailScheduleIntent.putExtra("selectedSchedule", employee);
        startActivity(toMailScheduleIntent);
    }
}
