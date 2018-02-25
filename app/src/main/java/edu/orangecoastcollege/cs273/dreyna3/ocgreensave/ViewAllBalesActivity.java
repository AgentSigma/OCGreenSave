package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ViewAllBalesActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Bale> allBalesList = new ArrayList<>();
    private List<Bale> filteredBalesList;
    private ListView balesListView;
    private BaleListAdapter mBaleListAdapter;
    private RadioButton mACRadioButton;
    private RadioButton mPET1RadioButton;
    private RadioButton mCardboardRadioButton;
    private RadioButton mHDPE2RadioButton;
    private Button mDeleteButton;

    /**
     * Creates the ViewAllBalesActivity for admins
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bales);
        balesListView = (ListView) (findViewById(R.id.viewAllBalesListView));
        mACRadioButton = findViewById(R.id.viewBalesACRadioButton);
        mPET1RadioButton = findViewById(R.id.viewBalesPET1RadioButton);
        mCardboardRadioButton = findViewById(R.id.viewBalesCardboardRadioButton);
        mHDPE2RadioButton = findViewById(R.id.viewBalesHDPE2RadioButton);
        mDeleteButton = findViewById(R.id.viewBalesDeleteButton);

        mDeleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deleteBaleByType();
                return true;
            }
        });

        db = new DBHelper(this);
        allBalesList = db.getAllBales();
        sortListByDate();
        filteredBalesList = new ArrayList<>(allBalesList);
        mBaleListAdapter = new BaleListAdapter(this, R.layout.bale_list_item, filteredBalesList);
        balesListView.setAdapter(mBaleListAdapter);
    }

    /**
     * Sends user to the activity for deleting/editing bales
     *
     * @param v item that is selected from the list
     */
    public void editDeleteBaleClick(View v) {
        LinearLayout selected = (LinearLayout) v;
        Bale bale = (Bale) v.getTag();

        Intent toEditDeleteBaleIntent = new Intent(this, EditDeleteBaleActivity.class);
        toEditDeleteBaleIntent.putExtra("selectedBale", bale);
        startActivity(toEditDeleteBaleIntent);
        finish();
    }

    /**
     * Sorts the bales by type selected from the radio buttons
     *
     * @param view
     */
    public void viewBalesFilterByType(View view) {
        mBaleListAdapter.clear();
        filteredBalesList.clear();

        if (mACRadioButton.isChecked()) {
            for (Bale b : allBalesList)
                if (b.getType().equals(mACRadioButton.getText().toString())) {
                    mBaleListAdapter.add(b);
                }
        } else if (mPET1RadioButton.isChecked()) {
            for (Bale b : allBalesList)
                if (b.getType().equals(mPET1RadioButton.getText().toString())) {
                    mBaleListAdapter.add(b);
                }
        } else if (mCardboardRadioButton.isChecked()) {
            for (Bale b : allBalesList)
                if (b.getType().equals(mCardboardRadioButton.getText().toString())) {
                    mBaleListAdapter.add(b);
                }
        } else if (mHDPE2RadioButton.isChecked()) {
            for (Bale b : allBalesList)
                if (b.getType().equals(mHDPE2RadioButton.getText().toString())) {
                    mBaleListAdapter.add(b);
                }
        }
        mBaleListAdapter.notifyDataSetChanged();
    }

    /**
     * Sorts all bales from the database by date
     */
    public void sortListByDate() {
        Collections.sort(allBalesList, new Comparator<Bale>() {
            @Override
            public int compare(Bale bale, Bale t1) {
                DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
                Date d1;
                Date d2;

                try {
                    d1 = format.parse(bale.getDate());
                    d2 = format.parse(t1.getDate());

                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    /**
     * Deletes all bales by type selected
     */
    public void deleteBaleByType() {
        if (!(mACRadioButton.isChecked() || mHDPE2RadioButton.isChecked() || mPET1RadioButton.isChecked()
                || mCardboardRadioButton.isChecked())) {
            Toast.makeText(this, R.string.bale_type_selection_missing, Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if user has given perm to write files
        int perms = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (perms != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        // if perm is granted
        else if (filteredBalesList.size() > 0) {
            try {
                // Creation of the file directory and file name
                String baseDir = android.os.Environment.getExternalStorageDirectory() + File.separator
                        + "OCGreenSave_Staff" + File.separator;
                String fileName = baseDir
                        + filteredBalesList.get(0)
                        .getDate().replace("/", "")
                        + "-"
                        + filteredBalesList.get(filteredBalesList.size() - 1)
                        .getDate().replace("/", "")
                        + "_"
                        + filteredBalesList.get(0).getType().replace("/", "+")
                        + ".txt";

                // Create the folder of baseDir OCGreenSave_Staff
                File folder = new File(baseDir);
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdirs();
                }
                if (success) {
                    // Create the text file
                    FileWriter writer = new FileWriter(fileName);

                    // Write to the text file line by line
                    for (Bale b : filteredBalesList) {
                        writer.write("[ " + b.getDate() + " | " + String.valueOf(b.getWeight()) + " | "
                                + b.getUser() + " ]\r\n"); // \r\n both required for new line to insert
                    }

                    writer.close();

                    // Delete off the database and finish activity
                    db.deleteBalesByType(filteredBalesList);
                    finish();
                    Toast.makeText(this, R.string.bale_type_cleared, Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
