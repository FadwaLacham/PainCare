package com.JAVA.Beans;

import java.sql.Time;
import java.util.Set;

public class Alarm {
    private int alarmId;
    private String title;
    private Time time;
    private Set<String> repeatDays;

    // Constructeurs

    public Alarm() {
        // Constructeur par défaut
    }

    public Alarm(int alarmId, String title, Time time, Set<String> repeatDays) {
        this.alarmId = alarmId;
        this.title = title;
        this.time = time;
        this.repeatDays = repeatDays;
    }

    // Getters et setters

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Set<String> getRepeatDays() {
        return repeatDays;
    }

    public void setRepeatDays(Set<String> repeatDays) {
        this.repeatDays = repeatDays;
    }
}
