package com.lukas.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by Lukas on 10.01.2018.
 */

public class ClockActivity extends AppCompatActivity {
    TimeProfile timeProfile;
    short hours;
    short minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_activity);
        Intent intent = getIntent();
        timeProfile = (TimeProfile) intent.getSerializableExtra("profile");


        Button WeckerEinstellen = (Button) findViewById(R.id.button);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        WeckerEinstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter = 1;

                hours = timePicker.getCurrentHour().shortValue();
                minutes = timePicker.getCurrentMinute().shortValue();
                for (TimeClock tc : timeProfile.getTimeClockArrayList()) {

                    int combinedMinutes = minutes + tc.getMinutes();
                    int combinedHours = hours + tc.getHours();
                    if (combinedMinutes > 59) {
                        combinedHours = combinedHours - 1;
                        combinedMinutes = combinedMinutes - 60;
                    }
                    Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, "" + timeProfile.getProfileName() + counter);
                    intent.putExtra(AlarmClock.EXTRA_HOUR, combinedHours);
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, combinedMinutes);
                    startActivityForResult(intent,10);
                }


            }
        });

    }
}
