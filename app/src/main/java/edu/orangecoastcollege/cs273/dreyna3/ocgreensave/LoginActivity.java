package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final int ADMIN_KEY = 1234;
    private EditText mUsernameEditText;
    private EditText mKeyEditText;
    private Animation mShakeAnim;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper db = new DBHelper(this);
        //db.clearDatabases();

        mUsernameEditText = (EditText) findViewById(R.id.signatureEditText);
        mKeyEditText = (EditText) findViewById(R.id.keyEditText);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.signInLayout);

    }

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
