package com.example.maheromadan.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DayWiseDataTable {
    @PrimaryKey(autoGenerate = true)
    int uniqueId;
    @ColumnInfo(name = "day_wise_data_table")
    String dayWiseDataTable;

    public DayWiseDataTable(String dayWiseDataTable) {
        this.dayWiseDataTable = dayWiseDataTable;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDayWiseDataTable() {
        return dayWiseDataTable;
    }

    public void setDayWiseDataTable(String dayWiseDataTable) {
        this.dayWiseDataTable = dayWiseDataTable;
    }
}
