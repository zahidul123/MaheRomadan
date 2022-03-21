package com.example.maheromadan

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import androidx.appcompat.app.AppCompatActivity
import com.example.maheromadan.alarmBackground.AlarmService
import com.example.maheromadan.utils.SharedPreferencesClass
import kotlinx.android.synthetic.main.activity_alarm_display_activity.*
import java.text.SimpleDateFormat
import java.util.*


class AlarmDisplayActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_display_activity)

          val intentService = Intent(this, AlarmService::class.java)
       //intentService.putExtra(TITLE, p1.getStringExtra(TITLE))
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(intentService)
     } else {
         startService(intentService)
     }
        buttonclickEvents()
        animateClock()
    }

    private fun buttonclickEvents() {
        var sharedPreferencesClass=SharedPreferencesClass(this)
        btn_stop.setOnClickListener {
            stopIntentService()
            sharedPreferencesClass.setSnooze(false)
        }

        btn_snooze.setOnClickListener {

            val calendar: Calendar = Calendar.getInstance()
            calendar.setTimeInMillis(System.currentTimeMillis())
            calendar.add(Calendar.MINUTE, 10)
            var simpleFormatter=SimpleDateFormat("hh:mm:ss")
            var currentTime=simpleFormatter.format(calendar)
            sharedPreferencesClass.setSnooze(true)
            sharedPreferencesClass.putSnoozeTime(currentTime)
            stopIntentService()
        }


    }

    fun stopIntentService(){
        val intentService = Intent(applicationContext, AlarmService::class.java)
        applicationContext.stopService(intentService)
        finish()
    }
    private fun animateClock() {

        val rotateAnimation = ObjectAnimator.ofFloat(clock, "rotation", 0f, 20f, 0f, -20f, 0f)
        rotateAnimation.repeatCount = ValueAnimator.INFINITE
        rotateAnimation.duration = 800
        rotateAnimation.start()
    }
}