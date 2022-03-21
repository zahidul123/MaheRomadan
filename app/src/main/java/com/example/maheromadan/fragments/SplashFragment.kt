package com.example.maheromadan.fragments

import android.app.backup.SharedPreferencesBackupHelper
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.maheromadan.R
import com.example.maheromadan.utils.SharedPreferencesClass


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        var sharedPreferencesBackupHelper= SharedPreferencesClass(requireActivity())


        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val manager = requireActivity().supportFragmentManager
            manager.popBackStack()
            val transaction = manager.beginTransaction()

            if (sharedPreferencesBackupHelper.getgoToDashBoard()==true){
                var mfragment=DashBoardFragment()

                transaction.replace(R.id.container,mfragment ).commit()
            }else{
                var fragment:Fragment=DistrictSelectFragment()

                transaction.replace(R.id.container,fragment ).commit()
            }
        }, 1500)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}