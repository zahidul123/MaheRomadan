package com.example.maheromadan.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.maheromadan.model.DistRamadanDetail
import java.util.ArrayList


@Dao
public interface DaoClass {

    @Query("SELECT * FROM daywisedatatable")
    fun getAll(): List<DayWiseDataTable>
    @Insert
    fun insertAll( dayWiseData: DayWiseDataTable)
    @Delete
    fun delete(dayWiseData: DayWiseDataTable)

    @Query("DELETE FROM daywisedatatable")
    fun deleteAllData()



    //this part is for alarm
    @Insert
    fun insertAllAlarms( alarmTime: AlarmTime)

    @Query("SELECT * FROM alarmtime")
    fun getAllAlarm(): List<AlarmTime>


    @Query("select * from alarmtime where alarm_date = :currentDate")
    fun getCurrentDayAlarmData(currentDate:String):AlarmTime

    @Query("DELETE FROM alarmtime")
    fun deleteAllAlarm()


}