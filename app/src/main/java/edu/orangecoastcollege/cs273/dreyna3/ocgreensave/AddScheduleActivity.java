package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddScheduleActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        mDb = new DBHelper(this);
        mNameEditText = (EditText) findViewById(R.id.addScheduleNameEditText);
        mWeekDateEditText = (EditText) findViewById(R.id.addScheduleWeekDateEditText);
        mMondayEditText = (EditText) findViewById(R.id.addScheduleMondayEditText);
        mTuesdayEditText = (EditText) findViewById(R.id.addScheduleTuesdayEditText);
        mWednesdayEditText = (EditText) findViewById(R.id.addScheduleWednesdayEditText);
        mThursdayEditText = (EditText) findViewById(R.id.addScheduleThursdayEditText);
        mFridayEditText = (EditText) findViewById(R.id.addScheduleFridayEditText);
        mSaturdayEditText = (EditText) findViewById(R.id.addScheduleSaturdayEditText);
        mSundayEditText = (EditText) findViewById(R.id.addScheduleSundayEditText);

    }

    public void addScheduleClick(View view) {
        Employee employee = new Employee(
                mNameEditText.getText().toString(),
                mWeekDateEditText.getText().toString(),
                mMondayEditText.getText().toString(),
                mTuesdayEditText.getText().toString(),
                mWednesdayEditText.getText().toString(),
                mThursdayEditText.getText().toString(),
                mFridayEditText.getText().toString(),
                mSaturdayEditText.getText().toString(),
                mSundayEditText.getText().toString()
        );

        mDb.addEmployee(employee);
        resetEditTexts();
        Toast.makeText(this, R.string.added_schedule_text, Toast.LENGTH_SHORT).show();
    }

    private void resetEditTexts() {
        mNameEditText.setText("");
        mWeekDateEditText.setText("");
        mMondayEditText.setText("");
        mTuesdayEditText.setText("");
        mWednesdayEditText.setText("");
        mThursdayEditText.setText("");
        mFridayEditText.setText("");
        mSaturdayEditText.setText("");
        mSundayEditText.setText("");
    }
}
