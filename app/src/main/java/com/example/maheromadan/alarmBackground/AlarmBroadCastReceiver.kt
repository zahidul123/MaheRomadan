package com.example.maheromadan.alarmBackground

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.maheromadan.AlarmDisplayActivityActivity
import com.example.maheromadan.interfaces.ObservableObject
import com.example.maheromadan.roomDatabase.AppDatabase
import com.example.maheromadan.utils.SharedPreferencesClass
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class AlarmBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent) {
       // Log.e("inside broadcast class","yes")
      //  if (Intent.ACTION_BOOT_COMPLETED == p1!!.action) {
         //   Log.e("inside broadcast if condition","yes")
           /* val toastText = String.format("Alarm Reboot")
            Toast.makeText(p0, toastText, Toast.LENGTH_SHORT).show()
          */

        startRescheduleAlarmsService(p0,p1)

    }


    @SuppressLint("SimpleDateFormat")
    private fun startRescheduleAlarmsService(context: Context?, p1: Intent) {

        val db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "todo-list.db"
        ).build()
        val userDao = db.getDaoInstance()



        GlobalScope.launch {

            var alarmList=userDao.getAllAlarm()


            Handler(Looper.getMainLooper()).post {
                val today = Date()
                val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                val dateToStr: String = format.format(today)
                var sharedPreferences=SharedPreferencesClass(context)
                var isSnoozeEnable=sharedPreferences.isSnooze()

                if (isSnoozeEnable){

                    val calendar: Calendar = Calendar.getInstance()
                    calendar.setTimeInMillis(System.currentTimeMillis())
                    var simpleFormatter=SimpleDateFormat("hh:mm:ss")
                    var currentTime=simpleFormatter.format(calendar)

                    if (currentTime.equals(sharedPreferences.getSnoozeTime())){
                        callServiceCalling(context,p1)
                    }

                }
                else{
                    for (i in 0..alarmList.size-1){
                        var shaharitime=alarmList[i].shahariTime
                        if (dateToStr.equals(alarmList[i].iftarTime)||dateToStr.equals(shaharitime)){
                            callServiceCalling(context,p1)
                            break
                        }
                    }
                }

            }

        }




    }


    @SuppressLint("InvalidWakeLockTag")
    private fun callServiceCalling(context: Context, p1: Intent) {

        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val isScreenOn = pm.isScreenOn
        if (isScreenOn == false) {

            val wl = pm.newWakeLock(
                PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.ON_AFTER_RELEASE,
                "MyLock"
            )
            wl.acquire(10000)
            val wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock")
            wl_cpu.acquire(10000)

        }



        ObservableObject.getInstance().updateValue(p1);

    }
}