package com.lukas.alarmclock;

import java.io.Serializable;

/**
 * Created by Lukas on 12.01.2018.
 */

public class TimeClock implements Serializable{
    short hours;
    short minutes;
    boolean before;

    public TimeClock(short hours, short minutes, boolean before) {
        this.hours = hours;
        this.minutes = minutes;
        this.before = before;
    }

    public TimeClock( short minutes, boolean before) {
        this.hours = 0;
        this.minutes = minutes;
        this.before = before;
    }

    public short getHours() {
        return hours;
    }

    public void setHours(short hours) {
        this.hours = hours;
    }

    public short getMinutes() {
        return minutes;
    }

    public void setMinutes(short minutes) {
        this.minutes = minutes;
    }

    public boolean isBefore() {
        return before;
    }

    public void setBefore(boolean before) {
        this.before = before;
    }

    @Override
    public String toString() {
        if(before && hours==0){
            return minutes + " Minuten vorher";
        } else if(before && hours != 0){
            return hours + " Stunden und " + minutes + " Minuten vorher";
        } else if(!before && hours==0){
            return minutes + " Minuten danach";
        } else if(!before && hours != 0){
            return hours + " Stunden und " + minutes + " Minuten danach";
        }
        return "";
    }
}
