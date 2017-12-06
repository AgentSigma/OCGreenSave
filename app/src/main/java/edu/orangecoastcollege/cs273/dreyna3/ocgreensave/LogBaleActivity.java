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

        mDateEditText.setText(currentTime.toString());

        Intent fromMenuIntent = getIntent();
        currentUser = fromMenuIntent.getStringExtra("username");
        mBale.setUser(currentUser);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mBale.setType(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        mBale.setType("PET #1");
        mTypeSpinner.setSelection(0);
    }

    public void logSaveClick(View view) {
        mBale.setDate(currentTime.toString());
        mBale.setWeight(Double.parseDouble(mWeightEditText.getText().toString()));
        mBale.setType(mTypeSpinner.getSelectedItem().toString());
        db.addBale(mBale);
        Toast.makeText(this, "Bale added: " + mBale.toString(), Toast.LENGTH_LONG).show();
        finish();
    }
}
