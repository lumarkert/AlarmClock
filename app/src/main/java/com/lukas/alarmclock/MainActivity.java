package com.lukas.alarmclock;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewProfileDialogFragment.NewProfileDialogListener {
    ArrayList<TimeProfile> list = new ArrayList<TimeProfile>();
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TimeProfile item1 = new TimeProfile("item1");
        TimeProfile item2 = new TimeProfile("item2");
        TimeClock tc = new TimeClock((short) 10, (short) 2, true);
        TimeClock tc2 = new TimeClock((short) 4, (short) 1, true);
        TimeClock tc3 = new TimeClock((short) 1, (short) 1, false);
        TimeClock tc4 = new TimeClock((short) 2, true);
        item1.addTimeClock(tc);
        item1.addTimeClock(tc2);
        item1.addTimeClock(tc3);
        item1.addTimeClock(tc4);

        list.add(item1);
        list.add(item2);
        updateListView();
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
                NewProfileDialogFragment dialogFragment = new NewProfileDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void startProfileActivity(Intent i) {
        startActivityForResult(i, 1);
    }

    public void startClockActivity(Intent i) {
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();
                if (b != null) {
                    TimeProfile tp = (TimeProfile) b.getSerializable("profile");
                    int posi = (int) b.getSerializable("position");
                    list.set(posi, tp);
                    Log.d("Result", "Size: " + tp.getTimeClockArrayList().size() + " Posi: " + posi);
                }
            } else if (resultCode == 0) {
                System.out.println("RESULT CANCELLED");
            }
        }
    }

    @Override
    public void onDialogPositiveClick(String profileName) {
        TimeProfile tp = new TimeProfile(profileName);
        list.add(tp);
        updateListView();
    }

    public void updateListView() {
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        ProfileArrayAdapter adapter = new ProfileArrayAdapter(list, this);
        listView1.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("profile", list);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        list = (ArrayList<TimeProfile>) savedInstanceState.getSerializable("profile");
    }


}


//TODO: New Menu for Profile Activity (rename)
////TODO: Save File
//TODO: FAB Button

//PickTimeDialogFragment tdp = new PickTimeDialogFragment();
//tdp.show(getSupportFragmentManager(),"dialog");
