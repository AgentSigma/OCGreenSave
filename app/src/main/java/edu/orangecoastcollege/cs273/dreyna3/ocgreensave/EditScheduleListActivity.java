package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class EditScheduleListActivity extends AppCompatActivity {

    private ListView mSchedulesListView;
    private List<Employee> mScheduleList;
    private DBHelper mDb;
    private ScheduleListAdapter mScheduleListAdapter;

    /**
     * Creates the EditScheduleListActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule_list);

        mSchedulesListView = (ListView) findViewById(R.id.editSchedulesListView);
        mDb = new DBHelper(this);
        mScheduleList = mDb.getAllEmployees();
        mScheduleListAdapter = new ScheduleListAdapter(this, R.layout.schedule_list_item, mScheduleList);

        mSchedulesListView.setAdapter(mScheduleListAdapter);
    }

    /**
     * This will start a new activity to edit the selected employee schedule
     * @param view the selected item
     */
    public void scheduleClicked(View view){
        LinearLayout selected = (LinearLayout) view;
        Employee employee = (Employee) selected.getTag();
        Intent toMailScheduleIntent = new Intent(this, EditEmployeeScheduleActivity.class);
        toMailScheduleIntent.putExtra("selectedSchedule", employee);
        startActivity(toMailScheduleIntent);
        finish();
    }
}
