package com.example.maheromadan.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.maheromadan.R
import com.example.maheromadan.Repositories.RetrofitBuilder
import com.example.maheromadan.interfaces.FragmentTransferInterface
import com.example.maheromadan.model.RamadanDetails
import com.example.maheromadan.roomDatabase.AlarmTime
import com.example.maheromadan.roomDatabase.AppDatabase
import com.example.maheromadan.roomDatabase.DayWiseDataTable
import com.example.maheromadan.utils.ResponseStatus.*
import com.example.maheromadan.utils.SharedPreferencesClass
import com.example.maheromadan.viewModels.MainViewModel
import com.example.maheromadan.viewModels.ViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_dash_board.view.*
import kotlinx.android.synthetic.main.fragment_district_select.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DistrictSelectFragment : Fragment() {


      var viewModel: MainViewModel?=null
    lateinit var mView: View
    var districtName=ArrayList<String>()
    var districtNameEng=ArrayList<String>()
    lateinit var fragmentTransferInterface: FragmentTransferInterface
    var pDialog: SweetAlertDialog? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView= inflater.inflate(R.layout.fragment_district_select, container, false)
        setDistrictNameAndCode()
        setupViewModel()
         pDialog = SweetAlertDialog(requireContext(), SweetAlertDialog.PROGRESS_TYPE)
         pDialog!!.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog!!.titleText = "Loading ..."
        pDialog!!.setCancelable(true)
        return mView.rootView
    }

    private fun setDistrictNameAndCode() {

        districtName.add(getString(R.string.text_district_names))
        districtName.add("ঢাকা")
        districtName.add("গাজীপুর")
        districtName.add("কিশোরগঞ্জ")
                districtName.add("মানিকগঞ্জ")
                districtName.add("মুন্সীগঞ্জ")
                districtName.add("নারায়ণগঞ্জ")
                districtName.add("নরসিংদী")
                districtName.add("টাঙ্গাইল")
                districtName.add("ফরিদপুর")
                districtName.add("গোপালগঞ্জ")
                districtName.add("মাদারীপুর")
                districtName.add("রাজবাড়ী")
                districtName.add("শরীয়তপুর")
                districtName.add("চট্টগ্রাম")
                districtName.add("ব্রাহ্মণবাড়িয়া")
                districtName.add("কুমিল্লা")
                districtName.add("চাঁদপুর")
                districtName.add("লক্ষ্মীপুর")
                districtName.add("নোয়াখালী")
                districtName.add("ফেনী")
                districtName.add("খাগড়াছড়ি")
                districtName.add("রাঙামাটি")
                districtName.add("বান্দরবান")
                districtName.add("কক্সবাজার")
                districtName.add("খুলনা")
                districtName.add("চুয়াডাঙ্গা")
                districtName.add("যশোর")
                districtName.add("ঝিনাইদাহ")
                districtName.add("বাগেরহাট")
                districtName.add("কুষ্টিয়া")
                districtName.add("মাগুরা")
                districtName.add("মেহেরপুর")
                districtName.add("নড়াইল")
                districtName.add("সাতক্ষীরা")
                districtName.add("রাজশাহী")
                districtName.add("বগুড়া")
                districtName.add("জয়পুরহাট")
                districtName.add("নওগাঁ")
                districtName.add("নাটোরে")
                districtName.add("পাবনা")
                districtName.add("চাঁপাইনবাবগঞ্জ")
                districtName.add("সিরাজগঞ্জ")
                districtName.add("রংপুর")
                districtName.add("দিনাজপুর")
                districtName.add("কুড়িগ্রাম")
                districtName.add("নীলফামারী")
                districtName.add("গাইবান্ধা")
                districtName.add("ঠাকুরগাঁও")
                districtName.add("পঞ্চগড়")
                districtName.add("লালমনিরহাট")
                districtName.add("বরিশাল")
                districtName.add("বরগুনা")
                districtName.add("ভোলা")
                districtName.add("ঝালকাঠী")
                districtName.add("পটুয়াখালী")
                districtName.add("পিরোজপুর")
                districtName.add("সিলেট")
                districtName.add("সুনামগঞ্জ")
                districtName.add("হবিগঞ্জ")
                districtName.add("মৌলভীবাজার")
                districtName.add("ময়মনসিংহ")
                districtName.add("নেত্রকোনা")
                districtName.add("জামালপুর")
                districtName.add("শেরপুর")



        val arrayAdapter=ArrayAdapter<String>(requireActivity(),R.layout.spinner_item,districtName)
        mView.district_spinner.adapter=arrayAdapter

        mView.district_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position>0){
                    fetchDistricData(position)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }



        districtNameEng.add(getString(R.string.text_district_names))
        districtNameEng.add("Dhaka")
        districtNameEng.add("Gazipur")
        districtNameEng.add("Kishoreganj")
        districtNameEng.add("Manikganj")
        districtNameEng.add("Munshiganj")
        districtNameEng.add("Narayanganj")
        districtNameEng.add("Narsingdi")
        districtNameEng.add("Tangail")
        districtNameEng.add("Faridpur")
        districtNameEng.add("Gopalganj")
        districtNameEng.add("Madaripur")
        districtNameEng.add("Rajbari")
        districtNameEng.add("Shariatpur")
        districtNameEng.add("Chattogram")
        districtNameEng.add("Brahmanbaria")
        districtNameEng.add("Cumilla")
        districtNameEng.add("Chandpur")
        districtNameEng.add("Lakshmipur")
        districtNameEng.add("Noakhali")
        districtNameEng.add("Feni")
        districtNameEng.add("Khagrachhari")
        districtNameEng.add("Rangamati")
        districtNameEng.add("Bandarban")
        districtNameEng.add("Cox's Bazaar")
        districtNameEng.add("Khulna")
        districtNameEng.add("Chuadanga")
        districtNameEng.add("Jessore")
        districtNameEng.add("Jhenaidah")
        districtNameEng.add("Bagerhat")
        districtNameEng.add("Kushtia")
        districtNameEng.add("Magura")
        districtNameEng.add("Meherpur")
        districtNameEng.add("Narail")
        districtNameEng.add("Satkhira")
        districtNameEng.add("Rajshahi")
        districtNameEng.add("Bogura")
        districtNameEng.add("Joypurhat")
        districtNameEng.add("Naogaon")
        districtNameEng.add("Natore")
        districtNameEng.add("Pabna")
        districtNameEng.add("Chapainawabganj")
        districtNameEng.add("Sirajgonj")
        districtNameEng.add("Rangpur")
        districtNameEng.add("Dinajpur")
        districtNameEng.add("Kurigram")
        districtNameEng.add("Nilphamari")
        districtNameEng.add("Gaibandha")
        districtNameEng.add("Thakurgaon")
        districtNameEng.add("Panchagarh")
        districtNameEng.add("Lalmonirhat")
        districtNameEng.add("Barisal")
        districtNameEng.add("Barguna")
        districtNameEng.add("Bhola")
        districtNameEng.add("Jhalokati")
        districtNameEng.add("Patuakhali")
        districtNameEng.add("Pirojpur")
        districtNameEng.add("Sylhet")
        districtNameEng.add("Shunamganj")
        districtNameEng.add("Habiganj")
        districtNameEng.add("Moulovibazar")
        districtNameEng.add("Mymensingh")
        districtNameEng.add("Netrokona")
        districtNameEng.add("Jamalpur")
        districtNameEng.add("Sherpur")

    }

    private fun fetchDistricData(position: Int) {
        viewModel?.getRamadanDetails(districtCode[position-1].toString())?.observe(this, androidx.lifecycle.Observer {
            it?.let { resource ->
                when (resource.status) {
                  SUCCESS -> {

                      pDialog!!.dismissWithAnimation()
                        resource.data?.let { data ->
                            retrieveList(data,position)
                        }
                    }
                    ERROR -> {
                        pDialog!!.dismissWithAnimation()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        pDialog!!.show()
                    }
                }
            }

        })
    }

    private fun retrieveList(data: RamadanDetails, position: Int) {
        var distRamadanDetail=data.data.get(0).distRamadanDetails
        Log.e("response data",data.toString());

        var sharedPreferencesBackupHelper=SharedPreferencesClass(requireActivity())
        sharedPreferencesBackupHelper.putDistrictCode(districtCode[position-1].toString())
        sharedPreferencesBackupHelper.putDistrictName(districtNameEng[position].toString())
        sharedPreferencesBackupHelper.putDistrictNameBang(districtName[position].toString())
        sharedPreferencesBackupHelper.putgoToDashBoard()

        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "todo-list.db"
        ).build()
        val userDao = db.getDaoInstance()
        GlobalScope.launch {
            userDao.deleteAllData()

            for (i in 0 ..distRamadanDetail.size-1){
                userDao.insertAll(dayWiseData = DayWiseDataTable(Gson().toJson(distRamadanDetail.get(i))))
            }

            userDao.deleteAllAlarm()
            for (i in 0 ..distRamadanDetail.size-1){
                userDao.insertAllAlarms(alarmTime = AlarmTime(distRamadanDetail.get(i).sehriFormatted,
                    distRamadanDetail.get(i).iftarFormatted,distRamadanDetail.get(i).engDate)
                )
            }

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                Handler(Looper.getMainLooper()).post(Runnable { //do stuff like remove view etc
                    val manager = requireActivity().supportFragmentManager
                    manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    val transaction = manager.beginTransaction()
                    val fragment:Fragment=DashBoardFragment()
                    transaction.replace(R.id.container,fragment ).commit()
                })
            }, 500)


        }

        fragmentTransferInterface=requireActivity() as FragmentTransferInterface



    }

    private fun setupViewModel() {

        viewModel = activity?.let {
            ViewModelProvider(
            this,
            ViewModelFactory(RetrofitBuilder.apiServic("dist"))
        ).get(MainViewModel::class.java)
    }}

    var districtCode = arrayOf<Int>(
        22001,
        22002,
        22003,
        22004,
        22005,
        22006,
        22007,
        22008,
        22009,
        22010,
        22011,
        22012,
        22013,
        22014,
        22015,
        22016,
        22017,
        22018,
        22019,
        22020,
        22021,
        22022,
        22023,
        22024,
        22025,
        22026,
        22027,
        22028,
        22029,
        22030,
        22031,
        22032,
        22033,
        22034,
        22035,
        22036,
        22037,
        22038,
        22039,
        22040,
        22041,
        22042,
        22043,
        22044,
        22045,
        22046,
        22047,
        22048,
        22049,
        22050,
        22051,
        22052,
        22053,
        22054,
        22055,
        22056,
        22057,
        22058,
        22059,
        22060,
        22061,
        22062,
        22063,
        22064
    )



}