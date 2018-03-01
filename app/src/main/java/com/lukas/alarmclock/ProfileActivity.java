package com.lukas.alarmclock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lukas on 26.02.2018.
 */

public class ProfileActivity extends AppCompatActivity implements PickTimeDialogFragment.NoticeDialogListener,EditTimeClockDialogFragment.EditTimeClockDialogListener {
    TimeProfile timeProfile;
    int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Intent intent = getIntent();
        timeProfile = (TimeProfile) intent.getSerializableExtra("profile");
        position = (int) intent.getSerializableExtra("position");
        updateListView();
    }

    public void updateListView() {
        ArrayList<TimeClock> timeClockArrayList = timeProfile.getTimeClockArrayList();
        ListView profileListView1 = (ListView) findViewById(R.id.profileListView1);
        ClockArrayAdapter adapter = new ClockArrayAdapter(timeClockArrayList, this);
        profileListView1.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Pressed Add Button:
            case R.id.add_profile:
                PickTimeDialogFragment tdp = new PickTimeDialogFragment();
                tdp.show(getSupportFragmentManager(), "dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDialogPositiveClick(short hours, short minutes, boolean before) {
        TimeClock tc = new TimeClock(hours, minutes, before);
        timeProfile.addTimeClock(tc);
        Log.d("OnDialogPositiveClick", "Size: " + timeProfile.getTimeClockArrayList().size());
        updateListView();
    }
    @Override
    public void onTimeClockPositiveClick(TimeClock tc, int position){
        timeProfile.getTimeClockArrayList().set(position, tc);
        updateListView();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("profile", timeProfile);
        intent.putExtra("position", position);
        setResult(Activity.RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}
