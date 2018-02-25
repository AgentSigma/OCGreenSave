package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.path;

public class MailBaleActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Bale> balesList = new ArrayList<>();
    private EditText emailEditText;
    private EditText subjectEditText;
    private String email;
    private String csvWriter;
    private RadioButton mAluminumRadioButton;
    private RadioButton mPET1RadioButton;
    private RadioButton mCardboardRadioButton;
    private RadioButton mHDPE2RadioButton;
    private int selectedType;
    private List<Bale> mFilteredBales = new ArrayList<>();

    /**
     * Creates the MailBaleActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_bale);
        emailEditText = findViewById(R.id.emailEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        mAluminumRadioButton = findViewById(R.id.emailBaleRadioAluminumButton);
        mPET1RadioButton = findViewById(R.id.emailBaleRadioPET1Button);
        mCardboardRadioButton = findViewById(R.id.emailBaleRadioCardboardButton);
        mHDPE2RadioButton = findViewById(R.id.emailBaleRadioHDPE2Button);

        db = new DBHelper(this);
        balesList = db.getAllBales();
        csvWriter = "";
    }


    /**
     * Sends user to the email app, sending bale manifest
     *
     * @param view
     */
    public void SendEmail(View view) {
        if (!emailEditText.getText().toString().equals("")) {
            csvWriter = "";
            email = emailEditText.getText().toString();
            if (mAluminumRadioButton.isChecked()) selectedType = 1;
            else if (mPET1RadioButton.isChecked()) selectedType = 2;
            else if (mCardboardRadioButton.isChecked()) selectedType = 3;
            else if (mHDPE2RadioButton.isChecked()) selectedType = 4;

            for (Bale bale : balesList) {
                switch (selectedType) {
                    case 1:
                        if (bale.getType().equals("Aluminum/Cans"))
                            mFilteredBales.add(bale);
                        break;
                    case 2:
                        if (bale.getType().equals("PET #1"))
                            mFilteredBales.add(bale);
                        break;
                    case 3:
                        if (bale.getType().equals("Cardboard"))
                            mFilteredBales.add(bale);
                        break;
                    case 4:
                        if (bale.getType().equals("HDPE #2"))
                            mFilteredBales.add(bale);
                        break;
                    default:
                        break;
                }
            }

            for (Bale bale : mFilteredBales) {
                csvWriter += "[ " + bale.getDate() + " | "
                        + bale.getType() + " | "
                        + bale.getWeight() + " | "
                        + bale.getUser() + " ]\n";
            }

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
        else{
            Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show();
            emailEditText.setError("");
        }
    }


}

