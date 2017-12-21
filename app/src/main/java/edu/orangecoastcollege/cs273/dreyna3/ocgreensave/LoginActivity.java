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
