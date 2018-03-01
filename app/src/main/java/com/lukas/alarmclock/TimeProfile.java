package com.lukas.alarmclock;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lukas on 12.01.2018.
 */

public class TimeProfile implements Serializable{

    private static final long serialVersionUID = 1L;

    ArrayList<TimeClock> timeClockArrayList = new ArrayList<>();
    String m_profileName;

    public TimeProfile(String profileName) {
        this.m_profileName = profileName;

    }

    public void addTimeClock(TimeClock timeClock) {
        timeClockArrayList.add(timeClock);
    }

    public void deleteTimeClock(TimeClock timeClock) {
        timeClockArrayList.remove(timeClock);
    }

    public String getProfileName() {
        return m_profileName;
    }

    public ArrayList<TimeClock> getTimeClockArrayList() {
        return timeClockArrayList;
    }

    @Override
    public String toString() {
        return this.m_profileName;
    }
}
