package com.example.maheromadan.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AlarmTime {
    @PrimaryKey(autoGenerate = true)
    int uniqueId;
    @ColumnInfo(name = "shahari_time")
    String shahariTime;
    @ColumnInfo(name = "iftar_time")
    String iftarTime;
    @ColumnInfo(name = "alarm_date")
    String alarmDate;

    public AlarmTime(String shahariTime, String iftarTime, String alarmDate) {
        this.shahariTime = shahariTime;
        this.iftarTime = iftarTime;
        this.alarmDate = alarmDate;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getShahariTime() {
        return shahariTime;
    }

    public void setShahariTime(String shahariTime) {
        this.shahariTime = shahariTime;
    }

    public String getIftarTime() {
        return iftarTime;
    }

    public void setIftarTime(String iftarTime) {
        this.iftarTime = iftarTime;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }
}
