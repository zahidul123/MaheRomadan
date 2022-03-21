package com.example.maheromadan.alarmBackground

import android.R
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.maheromadan.AlarmDisplayActivityActivity
import com.example.maheromadan.utils.SharedPreferencesClass


public class AlarmService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    override fun onCreate() {
        super.onCreate()



        var alert: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            if (alert == null) {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            }
        }

        mediaPlayer = MediaPlayer.create(this, alert);
        mediaPlayer?.setLooping(true);
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator?
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {


        setNotification(intent)

        mediaPlayer!!.start()
        val pattern = longArrayOf(0, 100, 1000)
        vibrator!!.vibrate(pattern, 0)
        //startActivity(Intent(intent,AlarmDisplayActivityActivity::class.java))

        Handler(Looper.getMainLooper()).postDelayed({
            mediaPlayer!!.stop()
            vibrator!!.cancel()
            var sharedPreferencesClass= SharedPreferencesClass(this)
            sharedPreferencesClass.setSnooze(false)

        }, 60000)

        return START_STICKY
    }


    private fun setNotification(intent: Intent) {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else { "" }


        val notificationIntent = Intent(this, AlarmDisplayActivityActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val alarmTitle = String.format("%s Alarm", intent.getStringExtra(TITLE))
        val notificationBuilder = NotificationCompat.Builder(this, channelId )
        val notification = notificationBuilder.setOngoing(true)
            .setContentTitle(alarmTitle)
            .setSmallIcon(R.drawable.ic_notification_clear_all)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(101, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{

       /* val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_DEFAULT)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = channelName
            val descriptionText = "notification fired"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }

        return channelId
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        vibrator!!.cancel()
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}