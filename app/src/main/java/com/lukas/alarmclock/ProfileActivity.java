package com.lukas.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Lukas on 26.02.2018.
 */

public class ProfileActivity extends AppCompatActivity implements PickTimeDialogFragment.NoticeDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Intent intent = getIntent();
        TimeProfile timeProfile = (TimeProfile) intent.getSerializableExtra("profile");
        ArrayList<TimeClock> timeClockArrayList = timeProfile.getTimeClockArrayList();

        ListView profileListView1 = (ListView) findViewById(R.id.profileListView1);

        ClockArrayAdapter adapter = new ClockArrayAdapter(timeClockArrayList, this);

        profileListView1 .setAdapter(adapter);

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
                tdp.show(getSupportFragmentManager(),"dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Stuff to do, dependent on requestCode and resultCode
        if(requestCode == 1) { // 1 is an arbitrary number, can be any int
            // This is the return result of your DialogFragment
            if(resultCode == 1) { // 1 is an arbitrary number, can be any int
                // Now do what you need to do after the dialog dismisses.
            }
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog){
        View v1 = dialog.getView();
        CustomNumberPicker hoursPicker = (CustomNumberPicker) v1.findViewById(R.id.hoursPicker);
        CustomNumberPicker minutesPicker = (CustomNumberPicker) v1.findViewById(R.id.minutesPicker);
        short pickedHours = (short)hoursPicker.getValue();
        short pickedMinutes = (short)minutesPicker.getValue();
    }
}
