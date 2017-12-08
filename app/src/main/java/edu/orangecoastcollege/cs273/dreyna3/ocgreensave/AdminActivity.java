package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private String currentUser;
    private Button mDeleteBalesButton;
    private GestureDetector mGestureDetector;
    private DBHelper mDb;
    private boolean hasSeenWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        hasSeenWarning = false;
        mDb = new DBHelper(this);
        currentUser = getIntent().getStringExtra("username");
        mDeleteBalesButton = (Button) findViewById(R.id.deleteBaleHistoryButton);

        mGestureDetector = new GestureDetector(mDeleteBalesButton.getContext(), this);

        mGestureDetector.setOnDoubleTapListener(this);
    }

    public void adminLogOutClick(View view) {
        finish();
    }

    public void adminLogBaleClick(View view) {
        Intent toLogBaleIntent = new Intent(this, LogBaleActivity.class);
        toLogBaleIntent.putExtra("username", currentUser);
        startActivity(toLogBaleIntent);
    }

    public void toEditScheduleMenuClick(View view) {
        Intent toEditScheduleIntent = new Intent(this, EditSchedulesActivity.class);
        startActivity(toEditScheduleIntent);
    }

    public void mailBaleHistory(View view) {
        Intent toMailBaleIntent = new Intent(this, MailBaleActivity.class);
        startActivity(toMailBaleIntent);
    }

    public void toViewAllBalesClick(View view) {
        Intent viewAllBalesIntent = new Intent(this, ViewAllBalesActivity.class);
        startActivity(viewAllBalesIntent);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.i("Admin", "Double Tap Confirmed");
        if (!hasSeenWarning){
            Toast.makeText(this, R.string.delete_bales_warning, Toast.LENGTH_LONG).show();
            hasSeenWarning = true;
        }
        else {
            Toast.makeText(this, R.string.deleted_bale_history_notification, Toast.LENGTH_SHORT).show();
            mDb.deleteBaleHistory();
            hasSeenWarning = false;
        }
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
