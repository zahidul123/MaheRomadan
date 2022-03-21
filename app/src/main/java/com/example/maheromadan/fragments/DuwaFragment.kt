package com.example.maheromadan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.maheromadan.R
import com.example.maheromadan.interfaces.FragmentTransferInterface
import kotlinx.android.synthetic.main.fragment_dash_board.view.*


class DuwaFragment : Fragment() {

    lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_duwa, container, false)

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
}