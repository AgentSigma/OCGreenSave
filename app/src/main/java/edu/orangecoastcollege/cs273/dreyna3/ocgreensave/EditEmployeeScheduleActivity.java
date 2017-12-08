package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditEmployeeScheduleActivity extends AppCompatActivity {


    private DBHelper mDb;

    private EditText mNameEditText;
    private EditText mWeekDateEditText;
    private EditText mMondayEditText;
    private EditText mTuesdayEditText;
    private EditText mWednesdayEditText;
    private EditText mThursdayEditText;
    private EditText mFridayEditText;
    private EditText mSaturdayEditText;
    private EditText mSundayEditText;
    private ListView mListView;

    private Employee mEmployee;
    private ScheduleListAdapter mScheduleListAdapter;
    private List<Employee> mEmployeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee_schedule);

        mEmployee = getIntent().getExtras().getParcelable("selectedSchedule");
        mEmployeeList = new ArrayList<>();
        mEmployeeList.add(mEmployee);
        mScheduleListAdapter = new ScheduleListAdapter(this, R.layout.schedule_list_item, mEmployeeList);
        mListView = (ListView) findViewById(R.id.editEmployeeScheduleListView);
        mListView.setAdapter(mScheduleListAdapter);

        mDb = new DBHelper(this);
        mNameEditText = (EditText) findViewById(R.id.editScheduleNameEditText);
        mWeekDateEditText = (EditText) findViewById(R.id.editScheduleWeekDateEditText);
        mMondayEditText = (EditText) findViewById(R.id.editScheduleMondayEditText);
        mTuesdayEditText = (EditText) findViewById(R.id.editScheduleTuesdayEditText);
        mWednesdayEditText = (EditText) findViewById(R.id.editScheduleWednesdayEditText);
        mThursdayEditText = (EditText) findViewById(R.id.editScheduleThursdayEditText);
        mFridayEditText = (EditText) findViewById(R.id.editScheduleFridayEditText);
        mSaturdayEditText = (EditText) findViewById(R.id.editScheduleSaturdayEditText);
        mSundayEditText = (EditText) findViewById(R.id.editScheduleSundayEditText);

        mNameEditText.setText(mEmployee.getName());
        mWeekDateEditText.setText(mEmployee.getWeekDate());
    }

    public void editScheduleClick(View view) {
                mEmployee.setName(mNameEditText.getText().toString());
                mEmployee.setWeekDate(mWeekDateEditText.getText().toString());
                mEmployee.setMondayHours(mMondayEditText.getText().toString());
                mEmployee.setTuesdayHours(mTuesdayEditText.getText().toString());
                mEmployee.setWednesdayHours(mWednesdayEditText.getText().toString());
                mEmployee.setThursdayHours(mThursdayEditText.getText().toString());
                mEmployee.setFridayHours(mFridayEditText.getText().toString());
                mEmployee.setSaturdayHours(mSaturdayEditText.getText().toString());
                mEmployee.setSundayHours(mSundayEditText.getText().toString());

        mDb.updateEmployee(mEmployee);

        Toast.makeText(this, R.string.editted_schedule_text, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Does nothing in this activity
     * @param v n/a
     */
    public void scheduleClick(View v){}
}
