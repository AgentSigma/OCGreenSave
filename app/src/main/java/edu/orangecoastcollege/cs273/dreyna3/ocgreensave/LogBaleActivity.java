package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class LogBaleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText mDateEditText;
    private EditText mWeightEditText;
    private Spinner mTypeSpinner;
    private Bale mBale;
    private DBHelper db;
    private String currentUser;
    private Date currentTime;

    /**
     * Creates the LogBaleActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_bale);

        db = new DBHelper(this);
        mBale = new Bale();
        currentTime = Calendar.getInstance().getTime();

        mTypeSpinner = (Spinner) findViewById(R.id.baleTypeSpinner);
        mDateEditText = (EditText) findViewById(R.id.baleDateEditText);
        mWeightEditText = (EditText) findViewById(R.id.baleWeightEditText);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.bale_types,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(adapter);

        mDateEditText.setText(currentTime.toString().replace("PST", ""));

        Intent fromMenuIntent = getIntent();
        currentUser = fromMenuIntent.getStringExtra("username");
        mBale.setUser(currentUser);
    }

    /**
     * If an item from the spinner is selected, bale type is set
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mBale.setType(adapterView.getItemAtPosition(i).toString());
    }

    /**
     * if nothing is selected, default is set
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mBale.setType("Aluminum/Cans");
        mTypeSpinner.setSelection(0);
    }

    /**
     * Adds the log to the database given the edit text's texts
     * @param view
     */
    public void logSaveClick(View view) {
        mBale.setDate(currentTime.toString());
        mBale.setWeight(Double.parseDouble(mWeightEditText.getText().toString()));
        mBale.setType(mTypeSpinner.getSelectedItem().toString());
        db.addBale(mBale);
        Toast.makeText(this, R.string.bale_added_text, Toast.LENGTH_SHORT).show();
        finish();
    }
}
