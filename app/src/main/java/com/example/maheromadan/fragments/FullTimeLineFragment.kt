package com.example.maheromadan.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.maheromadan.R
import com.example.maheromadan.adapterr.AllRamadanTimeAdapter
import com.example.maheromadan.model.DistRamadanDetail
import com.example.maheromadan.roomDatabase.AppDatabase
import com.example.maheromadan.utils.SharedPreferencesClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_full_time_line.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FullTimeLineFragment : Fragment() {

    lateinit var mView: View
    var distRamadanDetail=ArrayList<DistRamadanDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_full_time_line, container, false)
        setRecAdpter()

        getRoomData()
        var sharedPreferencesBackupHelper= SharedPreferencesClass(requireActivity())
        mView.districtName.text=sharedPreferencesBackupHelper.getDistrictNameBang()+" "+getString(R.string.text_district_time)
        buttonClickListener()
        return mView.rootView
    }

    private fun buttonClickListener() {

        mView.text_full_schedule.setOnClickListener {
             val manager = requireActivity().supportFragmentManager
             manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
             val transaction = manager.beginTransaction()
            // transaction.addToBackStack(null)
             val fragment:Fragment=DashBoardFragment()
             transaction.replace(R.id.container,fragment ).commit()
        }
        mView.text_dowa.setOnClickListener {

            val manager = requireActivity().supportFragmentManager
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val transaction = manager.beginTransaction()
            transaction.addToBackStack(null)
            val fragment:Fragment=DuwaFragment()
            transaction.replace(R.id.container,fragment ).commit()
        }
        mView.text_district_change.setOnClickListener {

            val manager = requireActivity().supportFragmentManager
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val transaction = manager.beginTransaction()
            transaction.addToBackStack(null)
            val fragment:Fragment=DistrictSelectFragment()
            transaction.replace(R.id.container,fragment ).commit()
        }
    }

    private fun getRoomData() {

        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "todo-list.db"
        ).build()
        val userDao = db.getDaoInstance()

        val sdf = SimpleDateFormat("dd/MM/yy")
        val currentDate = sdf.format(Date())

        distRamadanDetail.clear()

        GlobalScope.launch {

            var getAllDatas=userDao.getAll()

            for (i in 0 ..getAllDatas.size-1){

                var nothing=Gson().fromJson(getAllDatas.get(0).dayWiseDataTable,DistRamadanDetail::class.java)
                distRamadanDetail.add(nothing)
            }


            Handler(Looper.getMainLooper()).post(Runnable { //do stuff like remove view etc
                val allRamadanTimeAdapter=AllRamadanTimeAdapter(distRamadanDetail)
                mView.myrecyclerview.layoutManager= LinearLayoutManager(context)
                mView.myrecyclerview.adapter=allRamadanTimeAdapter
                allRamadanTimeAdapter.notifyDataSetChanged()
            })

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecAdpter() {




    }
}