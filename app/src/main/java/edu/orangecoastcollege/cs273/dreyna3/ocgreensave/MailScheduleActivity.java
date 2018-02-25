package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

public class MailScheduleActivity extends AppCompatActivity {

    private DBHelper db;
    private EditText emailEditText;
    private EditText subjectEditText;
    private String email;
    private String bodyWriter;
    private Employee mEmployee;

    /**
     * Creates the MailScheduleActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_schedule);
        emailEditText = (EditText) findViewById(R.id.emailScheduleEmailEditText);
        subjectEditText = (EditText) findViewById(R.id.emailScheduleSubjectEditText);

        db = new DBHelper(this);
        mEmployee = getIntent().getExtras().getParcelable("selectedSchedule");

        bodyWriter = "";
    }


    /**
     * Sends user to the email app, with their schedule populating the body
     *
     * @param view
     */
    public void emailScheduleClick(View view) {
        if (!emailEditText.getText().toString().equals("")) {
            bodyWriter = "";
            email = emailEditText.getText().toString();

            bodyWriter += mEmployee.getName() +
                    "\nWeek of: \t\t" + mEmployee.getWeekDate() +
                    "\nSunday: \t\t" + mEmployee.getSundayHours() +
                    "\nMonday: \t\t" + mEmployee.getMondayHours() +
                    "\nTuesday: \t\t" + mEmployee.getTuesdayHours() +
                    "\nWednesday: \t" + mEmployee.getWednesdayHours() +
                    "\nThursday:\t" + mEmployee.getThursdayHours() +
                    "\nFriday: \t\t" + mEmployee.getFridayHours() +
                    "\nSaturday: \t" + mEmployee.getSaturdayHours();

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            // set the type to 'email'
            emailIntent.setType("vnd.android.cursor.dir/email");
            String to[] = {email};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
            // the attachment
            emailIntent.putExtra(Intent.EXTRA_STREAM, path);
            // the mail subject
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectEditText.getText().toString());
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, bodyWriter);
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
        else {
            Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
            emailEditText.setError("");
        }
    }
}
