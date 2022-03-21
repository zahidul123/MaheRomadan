package com.example.maheromadan.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.maheromadan.R
import com.example.maheromadan.Repositories.RetrofitBuilder
import com.example.maheromadan.interfaces.FragmentTransferInterface
import com.example.maheromadan.model.DistRamadanDetail
import com.example.maheromadan.model.SalatTimingClass
import com.example.maheromadan.roomDatabase.AppDatabase
import com.example.maheromadan.utils.GlobalMethods
import com.example.maheromadan.utils.ResponseStatus
import com.example.maheromadan.utils.SharedPreferencesClass
import com.example.maheromadan.viewModels.MainViewModel
import com.example.maheromadan.viewModels.ViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_dash_board.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class DashBoardFragment : Fragment() {

    var viewModel: MainViewModel?=null
    lateinit var mView: View
    var districtName=ArrayList<String>()

    var pDialog: SweetAlertDialog? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_dash_board, container, false)

        pDialog = SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE)
        pDialog!!.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog!!.titleText = "Loading ..."
        pDialog!!.setCancelable(true)


        getTodayIfterSeheriTime()
        setupViewModel()
        buttonClickListener()
        return mView.rootView
    }

    lateinit var fragmentTransferInterface: FragmentTransferInterface
    private fun buttonClickListener() {
        fragmentTransferInterface=requireActivity() as FragmentTransferInterface


        mView.text_full_schedule.setOnClickListener {
            val manager = requireActivity().supportFragmentManager
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val transaction = manager.beginTransaction()
            transaction.addToBackStack(null)
            val fragment:Fragment=FullTimeLineFragment()
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


    private fun getTodayIfterSeheriTime(){


        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "todo-list.db"
        ).build()
        val userDao = db.getDaoInstance()

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate = sdf.format(Date())
        val calendar = Calendar.getInstance()

        val currentTime = SimpleDateFormat("HH.mm:ss", Locale.getDefault()).format(Date())
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time
        val tomorrowAsString: String = sdf.format(tomorrow)
        var doubleValue=currentTime.substring(0,5).toDouble()


        if (doubleValue>3.45&&doubleValue<19.30){
            mView.txt_next_current_ifter_label.text=getString(R.string.text_today_ifter)
            mView.txt_next_current_shahari_label.text=getString(R.string.text_next_shahari)
        }else{
            mView.txt_next_current_shahari_label.text=getString(R.string.text_today_shahari)
            mView.txt_next_current_ifter_label.text=getString(R.string.text_next_ifter)
        }

        GlobalScope.launch {

            var getAllDatas=userDao.getAll()

            for (i in 0 ..getAllDatas.size-1){

                var nothing= Gson().fromJson(getAllDatas.get(i).dayWiseDataTable, DistRamadanDetail::class.java)


                Handler(Looper.getMainLooper()).post(Runnable {
                    //do stuff like remove view etc
                    if (nothing.date.equals(currentDate)){
                        if (doubleValue>3.45&&doubleValue<19.30){
                            if (i<29){
                                var nothings= Gson().fromJson(getAllDatas.get(i+1).dayWiseDataTable, DistRamadanDetail::class.java)
                                mView.txt_next_current_shahari_time.text= GlobalMethods.convertEnToBn(nothings.sehri.substring(0,5))+" মিঃ"
                            }

                            mView.txt_next_current_ifter_time.text=GlobalMethods.convertEnToBn( nothing.iftar.substring(0,5))+" মিঃ"
                        }else{
                            if (i<29){
                                var nothings= Gson().fromJson(getAllDatas.get(i+1).dayWiseDataTable, DistRamadanDetail::class.java)
                                mView.txt_next_current_ifter_time.text=GlobalMethods.convertEnToBn( nothings.iftar.substring(0,5))+" মিঃ"
                            }
                            mView.txt_next_current_shahari_time.text= GlobalMethods.convertEnToBn(nothing.sehri.substring(0,5))+" মিঃ"

                        }


                        mView.txt_english_date.text="আজ ${nothing.weekDay}\n" +
                                "${nothing.engDate}, ${nothing.banglaDate}"
                        mView.txt_arbi_date.text=nothing.ramadanDate

                    }
               /* else{
                        mView.txt_next_current_shahari_time.text= GlobalMethods.convertEnToBn(nothing.sehri.substring(0,5))+" মিঃ"
                        mView.txt_next_current_ifter_time.text=GlobalMethods.convertEnToBn( nothing.iftar.substring(0,5))+" মিঃ"
                        mView.txt_english_date.text= "${nothing.engDate}, ${nothing.banglaDate}"
                        mView.txt_arbi_date.text=nothing.ramadanDate
                    }*/
                })

            }


        }
    }
    private fun setupViewModel() {

        viewModel = activity?.let {
            ViewModelProvider(
                this,
                ViewModelFactory(RetrofitBuilder.apiServic(""))
            ).get(MainViewModel::class.java)
        }
        var sharedPreferencesBackupHelper= SharedPreferencesClass(requireActivity())

        viewModel?.getDayDetails(sharedPreferencesBackupHelper.getDistrictName())?.observe(requireActivity(), androidx.lifecycle.Observer {
            it?.let { resource ->
                when (resource.status) {
                    ResponseStatus.SUCCESS -> {
                    //    mView.progressBar.visibility=View.GONE

                        resource.data?.let { data ->
                            pDialog!!.dismissWithAnimation()
                            retrieveList(data)

                        }
                    }
                    ResponseStatus.ERROR -> {
                        pDialog!!.dismissWithAnimation()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    ResponseStatus.LOADING -> {
                        pDialog!!.show()
                    }
                }
            }

        })


    }

    private fun retrieveList(datas: SalatTimingClass) {


        val c = Calendar.getInstance()
        val sd = SimpleDateFormat("EEEE")
        val dayofweek = sd.format(c.getTime())


        if (dayofweek.toLowerCase().equals("friday")){
            mView.text_wakto_name2.text="জুমা"
        }

        mView.text_wakto_time1.text=GlobalMethods.convertEnToBn(datas.data.timings.fajr.substring(1,4))+" মিঃ"
        mView.text_wakto_time2.text=GlobalMethods.convertEnToBn(((datas.data.timings.dhuhr.replace(":",".").toDouble()-12).toString().substring(0,4).replace(".",":")))+" মিঃ"
        mView.text_wakto_time3.text=GlobalMethods.convertEnToBn(((datas.data.timings.asr.replace(":",".").toDouble()-12).toString().substring(0,4).replace(".",":")))+" মিঃ"
        mView.text_wakto_time4.text=GlobalMethods.convertEnToBn(((datas.data.timings.maghrib.replace(":",".").toDouble()-12).toString().substring(0,4).replace(".",":")))+" মিঃ"
        mView.text_wakto_time5.text=GlobalMethods.convertEnToBn(((datas.data.timings.isha.replace(":",".").toDouble()-12).toString().substring(0,4).replace(".",":")))+" মিঃ"


        mView.txt_sunrise_time.text=GlobalMethods.convertEnToBn(datas.data.timings.sunrise)+" মিঃ"
        mView.txt_sunset_time.text=GlobalMethods.convertEnToBn(((datas.data.timings.sunset.replace(":",".").toDouble()-12).toString().substring(0,4).replace(".",":")))+" মিঃ"



    }
}