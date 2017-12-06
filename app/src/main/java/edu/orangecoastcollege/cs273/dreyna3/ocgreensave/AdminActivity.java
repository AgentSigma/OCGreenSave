package edu.orangecoastcollege.cs273.dreyna3.ocgreensave;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    private boolean isAdmin;
    private String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        isAdmin = true;
        currentUser = getIntent().getStringExtra("username");
    }

    public void adminLogOutClick(View view) {
        finish();
    }

    public void adminLogBaleClick(View view) {
        Intent toLogBaleIntent = new Intent(this, LogBaleActivity.class);
        toLogBaleIntent.putExtra("username", currentUser);
        startActivity(toLogBaleIntent);
    }
}
