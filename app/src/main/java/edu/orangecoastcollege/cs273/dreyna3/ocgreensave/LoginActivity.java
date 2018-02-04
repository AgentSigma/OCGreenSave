package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

// TODO LIST FOR APP 2.0
// TODO 1-A: Logic Fix  - Logging bale with custom date logs current date instead of edit text date
// TODO 1-B: Format     - Bale date should be MM/DD/YY Format, as requested

// TODO 2:   Crash Fixes
// TODO 2-A: Crash Fix  - Email bale list crashes app (may require some testing to find exact cause)
// TODO 2-B: Crash Fix  - Logging empty bale crashes app (easy fix)

// TODO 3:   Bales by Type
// TODO 3-A: Format Fix - View bales in format [ Date | Type | Weight | Logger ]
// TODO 3-B: Sorting    - View bales by type (via dropdown?) sorted by date
// TODO 3-C: Email      - Email bales by type (via dropdown + button to confirm selection)
// TODO 3-D: CSV        - Email/Save a CSV of the selected bale type history when clearing, (on tablet)
// TODO 3-E: Deleting   - Delete bale history by type (via checkboxes)

// TODO 4:   Staff Schedules
// TODO 4-A: QoL Change - Editting a schedule doesn't autopopulate with previous data

// TODO LIST FOR 2.1
// TODO 5:   Schedule system requires a bit of an overhaul.




public class LoginActivity extends AppCompatActivity {

    // ADMIN KEY FOR OC RECYCLE CENTER MANGERS
    private static final int ADMIN_KEY = 1234;
    private EditText mUsernameEditText;
    private EditText mKeyEditText;
    private Animation mShakeAnim;
    private RelativeLayout mRelativeLayout;
    private Animation mFromLeftAnim;
    private Animation mFromRightAnim;
    private Animation mFromBottomAnim;
    private Animation mFadeOutAnim;
    private ImageView mRecycleLogo;
    private ImageView mPirateImage;
    private DBHelper mDb;
    private TextView mStaffTextView;

    /**
     * Creates the LoginACtivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDb = new DBHelper(this);
        if (mDb.getBatchNumber() == 0)
            mDb.addBatchOneTimeCall();

        mUsernameEditText = (EditText) findViewById(R.id.signatureEditText);
        mKeyEditText = (EditText) findViewById(R.id.keyEditText);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.signInLayout);

        mPirateImage = (ImageView) findViewById(R.id.pirateImageView);
        mRecycleLogo = (ImageView) findViewById(R.id.recycleLogoImageView);
        mStaffTextView = (TextView) findViewById(R.id.staffEditionTextView);

        mFromLeftAnim = AnimationUtils.loadAnimation(this, R.anim.in_from_left);
        mFromRightAnim = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        mFadeOutAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);
        mFromBottomAnim = AnimationUtils.loadAnimation(this, R.anim.in_from_bottom);

        mUsernameEditText.setEnabled(false);
        mKeyEditText.setEnabled(false);
        mPirateImage.startAnimation(mFromRightAnim);
        mRecycleLogo.startAnimation(mFromLeftAnim);
        mStaffTextView.startAnimation(mFromBottomAnim);


        mPirateImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPirateImage.startAnimation(mFadeOutAnim);
                mRecycleLogo.startAnimation(mFadeOutAnim);
                mStaffTextView.startAnimation(mFadeOutAnim);
            }
        }, 1400);
        mRecycleLogo.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPirateImage.setVisibility(View.GONE);
                mRecycleLogo.setVisibility(View.GONE);
                mStaffTextView.setVisibility(View.GONE);
                mKeyEditText.setEnabled(true);
                mUsernameEditText.setEnabled(true);
            }
        }, 2400);

//        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
//        mRelativeLayout.startAnimation(mShakeAnim);
        DBHelper db = new DBHelper(this);
        //db.clearDatabases();



    }

    /**
     * Signs in the user given the texts from the edit texts
     * Checks to see if valid, and verifies against database
     * Also checks for admin key
     * @param view
     */
    public void signInClick(View view) {

        // if both fields empty
        if ((mUsernameEditText.getText().toString().equals("")
                && mKeyEditText.getText().toString().equals(""))
                || (mUsernameEditText.getText().toString().equals("")
                && !mKeyEditText.getText().toString().equals(""))) {
            Toast.makeText(this, R.string.empty_login, Toast.LENGTH_SHORT).show();
            mUsernameEditText.setError("Required");
            mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
            mRelativeLayout.startAnimation(mShakeAnim);
        }
        // if only a signature is entered
        else if (!mUsernameEditText.getText().toString().equals("")
                && mKeyEditText.getText().toString().equals("")){
            // Login as staff
            Intent toStaffIntent = new Intent(this, StaffActivity.class);
            toStaffIntent.putExtra("username", mUsernameEditText.getText().toString());
            mUsernameEditText.setText("");
            startActivity(toStaffIntent);
        }
        // if both are entered
        else{
            if (Integer.parseInt(mKeyEditText.getText().toString()) == ADMIN_KEY){
                Intent toAdminIntent = new Intent(this, AdminActivity.class);
                toAdminIntent.putExtra("username", mUsernameEditText.getText().toString());
                mUsernameEditText.setText("");
                mKeyEditText.setText("");
                startActivity(toAdminIntent);
            }
            else{
                Toast.makeText(this, R.string.incorrect_admin_key, Toast.LENGTH_SHORT).show();
                mKeyEditText.setText("");
                mKeyEditText.setError("Invalid Key");
                mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
                mRelativeLayout.startAnimation(mShakeAnim);
            }
        }
    }
}
