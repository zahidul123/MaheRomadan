package com.example.maheromadan

import android.app.AlarmManager
import android.app.PendingIntent

import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.maheromadan.alarmBackground.AlarmBroadCastReceiver
import com.example.maheromadan.fragments.DistrictSelectFragment
import com.example.maheromadan.fragments.SplashFragment
import com.example.maheromadan.interfaces.FragmentTransferInterface
import com.example.maheromadan.interfaces.ObservableObject
import java.util.Observable;
import java.util.Observer;
import kotlin.properties.ObservableProperty

class MainActivity : AppCompatActivity(),FragmentTransferInterface,Observer {

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(MainActivity@this, AlarmBroadCastReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(MainActivity@this, 0, intent, 0)
        }

      /*  val timeInterval = 5 * 1_000L
        val alarmTime = System.currentTimeMillis()*/

        val timeInterval: Long= 60 * 1000
        val alarmTime = System.currentTimeMillis() + 5000

        //alarmMgr!!.setExact(AlarmManager.RTC_WAKEUP,alarmTime,alarmIntent)
        alarmMgr?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            timeInterval,
            alarmIntent
        )

        ObservableObject.getInstance().addObserver(this)

        initSplashScreen()

    }

    private fun initSplashScreen() {
            val manager = supportFragmentManager
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val transaction = manager.beginTransaction()
            val fragment:Fragment=SplashFragment()
            transaction.replace(R.id.container,fragment ).commit()


    }

    override fun onFragmentTransactionHandler(fragment: Fragment, tag: String) {
        val manager = supportFragmentManager
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val transaction = manager.beginTransaction()
        val fragment:Fragment=DistrictSelectFragment()
        transaction.replace(R.id.container,fragment ).commit()
    }

    override fun update(p0: Observable?, p1: Any?) {
        val intentService= Intent(MainActivity@this,AlarmDisplayActivityActivity::class.java)
        startActivity(intentService)
    }


}