package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class DeleteSchedulesActivity extends AppCompatActivity {

    private ListView mSchedulesListView;
    private List<Employee> mScheduleList;
    private DBHelper mDb;
    private ScheduleListAdapter mScheduleListAdapter;

    /**
     * Creates the DeleteSchedulesActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_schedules);

        mSchedulesListView = (ListView) findViewById(R.id.deleteSchedulesListView);
        mDb = new DBHelper(this);
        mScheduleList = mDb.getAllEmployees();
        mScheduleListAdapter = new ScheduleListAdapter(this, R.layout.schedule_list_item, mScheduleList);

        mSchedulesListView.setAdapter(mScheduleListAdapter);
    }

    /**
     * This will delete the schedules once clicked
     * Only happens in the DeleteSchedulesActivity
     * @param view the selected item
     */
    public void scheduleClicked(View view){
        LinearLayout selected = (LinearLayout) view;
        selected.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Employee employee = (Employee) view.getTag();

                mDb.deleteEmployee(employee);
                mScheduleList.remove(employee);
                mScheduleListAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }
}
