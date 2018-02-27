package com.lukas.alarmclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Lukas on 10.01.2018.
 */

public class ClockActivity extends AppCompatActivity {
    ArrayList<TimeProfile> profiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_activity);


        Button WeckerEinstellen = (Button) findViewById(R.id.button);


        ArrayList<TimeProfile> profiles = new ArrayList<>();
        TimeProfile profile1 = new TimeProfile("Standard");
        TimeProfile profile2 = new TimeProfile("Standard2");
        profiles.add(profile1);
        profiles.add(profile2);
    }
}
