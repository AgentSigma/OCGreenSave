package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

public class MailScheduleActivity extends AppCompatActivity {

    private DBHelper db;
    private EditText emailEditText;
    private EditText subjectEditText;
    private String email;
    private String csvWriter;
    private Employee mEmployee;

    /**
     * Creates the MailScheduleActivity
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

        csvWriter = "";
    }


    /**
     * Sends user to the email app, with their schedule populating the body
     * @param view
     */
    public void emailScheduleClick(View view) {
        if (!emailEditText.equals("")) {
            csvWriter = "";
            email = emailEditText.getText().toString();

                csvWriter += mEmployee.getName() +
                        "\nWeek of:   " + mEmployee.getWeekDate() +
                        "\nMonday:    " + mEmployee.getMondayHours() +
                        "\nTuesday:   " + mEmployee.getTuesdayHours() +
                        "\nWednesday: " + mEmployee.getWednesdayHours() +
                        "\nThursday:  " + mEmployee.getThursdayHours() +
                        "\nFriday:    " + mEmployee.getFridayHours() +
                        "\nSaturday:  " + mEmployee.getSaturdayHours() +
                        "\nSunday:    " + mEmployee.getSundayHours();


            //String filename = "EmployeeData.txt";
            //  File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
            //emailEditText.setText(filelocation.toString());
            // Uri path = Uri.fromFile(filelocation);
            // if (path.equals(null)) {

            // } else {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
            emailIntent.setType("vnd.android.cursor.dir/email");
            String to[] = {email};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
// the attachment
            emailIntent.putExtra(Intent.EXTRA_STREAM, path);
// the mail subject
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subjectEditText.getText().toString());
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, csvWriter);
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
    }
}
