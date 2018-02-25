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

// DONE 1:    Bale Date Fixes
// DONE 1-A:  Logic Fix  - Logging bale with custom date now logs edit text's date
// DONE 1-B:  Format     - Bale's date (autofilled/custom) should be MM/DD/YY Format

// TODO 2:    Crash Fixes
// TODO 2-A:  Crash Fix  - Email bale list may crash app (may require some testing to find exact cause)
// DONE 2-B:  Crash Fix  - Logging empty bale crashes app (easy fix)

// DONE 3:    Bales by Type Features
// DONE 3-A:  Format Fix - View bales in format [ Date | Type | Weight | Logger ]
// DONE 3-B:  Sorting    - View bales by type (via radio buttons) sorted by date
// DONE 3-C:  Email      - Email bales by type (via radio buttons + button to confirm)
// DONE 3-D:  Saving     - Save a copy of the selected bale type history when clearing (onto tablet)
// DONE 3-E:  Deleting   - Delete bale history by type (via radiobuttons) EDIT: Combined with View All Bales

// DONE 4:   Schedules Fixes
// DONE 4-A: QoL Change - Editting a schedule doesn't autopopulate with previous data

// DONE 5:   Schedules Format
// DONE 5-A: Format     - Adjust format for schedules so they are 1 line each

/**
 * Patch Notes
 *
 *      [# Fix/Change | + Added Feature | - Removed Feature]
 *
 *      Bale Date Fixes
 *          # Bale dates now properly formatted to mm/dd/yyyy
 *          # Custom bale dates are now properly saved when adding or editing bales
 *
 *      Crash Fixes
 *          # Fixed crash that occurred when logging an empty bale
 *          # Fixed a fairly uncommon crash when mailing bales
 *
 *      Bales by Type Features
 *          # Bales are now listed in format [ Date | Type | Weight | Employee ]
 *          + Viewing all bales by type is now possible
 *          + Emailing bales by type is now possible
 *          + Bales may now be cleared by type, and saves a .txt file of them
 *          - Removed the ability to clear all bales
 *
 *      Schedule Fixes
 *          # Editing a schedule now auto-populates fields with previous schedule's data
 *
 *      Schedule Formatting
 *          # Adjusted format for schedules so they are 1 line each,
 *           in format [ Name | Week Date | Sun | Mon | Tue | Wed | Thu | Fri | Sat ]
 *
 *      And a handful of slight UI changes for convenience and overall look-and-feel enhancements!
 *
 *      Known Issues:
 *      > Spanish translations may be worded oddly, and may cause UI elements to break neat formatting
 *      > Emailing a bale on certain OS's of Android may cause a crash
 *      > Using the back button to exit the app may result in a crash
 *
 */


public class LoginActivity extends AppCompatActivity {

    // ADMIN KEY FOR OC RECYCLE CENTER MANAGERS
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
    private TextView mStaffTextView;

    /**
     * Creates the LoginACtivity
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
     *
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
                && mKeyEditText.getText().toString().equals("")) {
            // Login as staff
            Intent toStaffIntent = new Intent(this, StaffActivity.class);
            toStaffIntent.putExtra("username", mUsernameEditText.getText().toString());
            mUsernameEditText.setText("");
            startActivity(toStaffIntent);
        }
        // if both are entered
        else {
            if (Integer.parseInt(mKeyEditText.getText().toString()) == ADMIN_KEY) {
                Intent toAdminIntent = new Intent(this, AdminActivity.class);
                toAdminIntent.putExtra("username", mUsernameEditText.getText().toString());
                mUsernameEditText.setText("");
                mKeyEditText.setText("");
                startActivity(toAdminIntent);
            } else {
                Toast.makeText(this, R.string.incorrect_admin_key, Toast.LENGTH_SHORT).show();
                mKeyEditText.setText("");
                mKeyEditText.setError("Invalid Key");
                mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_anim);
                mRelativeLayout.startAnimation(mShakeAnim);
            }
        }
    }
}
