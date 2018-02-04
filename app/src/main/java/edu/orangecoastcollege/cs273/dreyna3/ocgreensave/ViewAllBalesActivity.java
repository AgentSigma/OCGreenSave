package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllBalesActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Bale> allBalesList = new ArrayList<>();
    private ListView balesListView;
    private BaleListAdapter mBaleListAdapter;

    /**
     * Creates the ViewAllBalesActivity for admins
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_bales);
        balesListView = (ListView) (findViewById(R.id.viewAllBalesListView));

        db = new DBHelper(this);
        allBalesList = db.getAllBales();
        mBaleListAdapter = new BaleListAdapter(this, R.layout.bale_list_item, allBalesList);
        balesListView.setAdapter(mBaleListAdapter);
    }

    public void editDeleteBaleClick(View v){
        LinearLayout selected = (LinearLayout) v;
        Bale bale = (Bale) v.getTag();

        Intent toEditDeleteBaleIntent = new Intent(this, EditDeleteBaleActivity.class);
        toEditDeleteBaleIntent.putExtra("selectedBale", bale);
        startActivity(toEditDeleteBaleIntent);
        finish();
    }
}
