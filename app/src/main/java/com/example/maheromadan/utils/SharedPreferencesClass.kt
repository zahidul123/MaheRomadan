package com.example.maheromadan.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.example.maheromadan.R

public class SharedPreferencesClass(context: Context) {
    lateinit var sharedPref:SharedPreferences

   init{
        sharedPref=context.getSharedPreferences("local_store", Context.MODE_PRIVATE)
    }

    fun putDistrictCode(districtCode:String){
        sharedPref.edit().putString("district_code",districtCode).apply()
    }
    fun getDistrictCode():String{
        return sharedPref.getString("district_code","").toString()
    }

    fun putDistrictName(districtCode:String){
        sharedPref.edit().putString("district_name",districtCode).apply()
    }
    fun getDistrictName():String{
        return sharedPref.getString("district_name","").toString()
    }

    fun putSnoozeTime(districtCode:String){
        sharedPref.edit().putString("snooze_time",districtCode).apply()
    }

    fun getSnoozeTime():String{
        return sharedPref.getString("snooze_time","").toString()
    }

    fun putDistrictNameBang(districtCode:String){
        sharedPref.edit().putString("district_name_bang",districtCode).apply()
    }
    fun getDistrictNameBang():String{
        return sharedPref.getString("district_name_bang","").toString()
    }



    fun putgoToDashBoard(){
        sharedPref.edit().putBoolean("is_back_class",true).apply()
    }
    fun getgoToDashBoard():Boolean{
        return sharedPref.getBoolean("is_back_class",false)
    }


    fun setSnooze(boolean: Boolean){

        sharedPref.edit().putBoolean("is_snooze",boolean).apply()

    }

    fun isSnooze():Boolean{

       return sharedPref.getBoolean("is_snooze",false)

    }


}