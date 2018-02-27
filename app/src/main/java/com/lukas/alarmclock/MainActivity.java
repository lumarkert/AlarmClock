package com.lukas.alarmclock;

import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TimeProfile> list = new ArrayList<TimeProfile>();
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        TimeProfile item1 = new TimeProfile("item1");
        TimeProfile item2 = new TimeProfile("item2");
        TimeClock tc = new TimeClock((short)10, (short)2, true);
        TimeClock tc2 = new TimeClock((short)4, (short)1, true);
        TimeClock tc3 = new TimeClock((short)1, (short)1, false);
        TimeClock tc4 = new TimeClock((short)2, true);
        item1.addTimeClock(tc);
        item1.addTimeClock(tc2);
        item1.addTimeClock(tc3);
        item1.addTimeClock(tc4);

        list.add(item1);
        list.add(item2);

        ListView listView1 = (ListView) findViewById(R.id.listView1);

        ProfileArrayAdapter adapter = new ProfileArrayAdapter(list, this);

        listView1.setAdapter(adapter);
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
                //add new Profile if Button pressed
                TimeProfile profile3 = new TimeProfile("neues shit");
                list.add(profile3);
                ListView listView1 = (ListView) findViewById(R.id.listView1);
                ProfileArrayAdapter adapter = new ProfileArrayAdapter(list, this);
                listView1.setAdapter(adapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

//TODO: New Menu for Profile Activity(add AND rename)

//PickTimeDialogFragment tdp = new PickTimeDialogFragment();
//tdp.show(getSupportFragmentManager(),"dialog");
