package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

public class MailBaleActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Bale> balesList= new ArrayList<>();
    private EditText emailEditText;
    private EditText subjectEditText;
    private String email;
    private String csvWriter;

    /**
     * Creates the MailBaleActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_bale);
emailEditText=(EditText) findViewById(R.id.emailEditText);
        subjectEditText=(EditText) findViewById(R.id.subjectEditText);

        db = new DBHelper(this);
        balesList=db.getAllBales();
       csvWriter="";


    }


    /**
     * Sends user to the email app, sending bale manifest
     * @param view
     */
    public void SendEmail(View view) {
        if(!emailEditText.equals("")) {
            csvWriter = "";
            email = emailEditText.getText().toString();

            for (Bale bale : balesList) {
                csvWriter += bale.getUser() + " @["
                        + bale.getDate() + "]"
                        + "\nType: " + bale.getType() + " - "
                        + bale.getWeight() + " lbs\n\n";
            }

            String filename = "BaleData.txt";
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

