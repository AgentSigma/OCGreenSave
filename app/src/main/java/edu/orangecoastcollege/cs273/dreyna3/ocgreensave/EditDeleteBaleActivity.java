package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditDeleteBaleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DBHelper mDb;
    private Bale mBale;
    private Spinner mTypeSpinner;
    private EditText mDate;
    private EditText mWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_bale);

        mDb = new DBHelper(this);
        mBale = getIntent().getExtras().getParcelable("selectedBale");
        mTypeSpinner = (Spinner) findViewById(R.id.editDeleteBaleTypeSpinner);
        mDate = (EditText) findViewById(R.id.editDeleteBaleDate);
        mWeight = (EditText) findViewById(R.id.editDeleteBaleWeight);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.bale_types,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(adapter);

        mDate.setText(mBale.getDate());
        mWeight.setText(String.valueOf(mBale.getWeight()));
        mTypeSpinner.setSelection(mBale.getType().equals("Aluminum/Cans") ? 0
                : mBale.getType().equals("PET #1") ? 1
                : mBale.getType().equals("Cardboard") ? 2
                : 3);
    }

    public void saveBaleChangesClick(View view) {
        mBale.setDate(mDate.getText().toString());
        mBale.setWeight(Double.parseDouble(mWeight.getText().toString()));
        mBale.setType(mTypeSpinner.getSelectedItem().toString());
        mDb.updateBale(mBale);

        Toast.makeText(this, R.string.changes_saved_text, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void deleteBaleClick(View view) {
        mDb.deleteBale(mBale);

        Toast.makeText(this, R.string.deleted_bale_text, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mBale.setType(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
