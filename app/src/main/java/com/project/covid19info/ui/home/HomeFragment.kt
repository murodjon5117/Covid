package com.project.covid19info.ui.home

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.project.covid19info.R
import com.project.covid19info.Repo
import com.project.covid19info.model.RecordsItem
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModelFactory=HomeViewModelFactory(requireActivity().application, repo = Repo())
        homeViewModel =
            ViewModelProvider(requireActivity(), homeViewModelFactory).get(HomeViewModel::class.java)
        homeViewModel.fetchTotal()

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.totalData.observe(viewLifecycleOwner, Observer {
            total.text = it?.data?.get(0)?.confirmed.toString()
            death.text = it?.data?.get(0)?.deaths.toString()
            recover.text = it?.data?.get(0)?.recovered.toString()
        })
        homeViewModel.totalUzb.observe(viewLifecycleOwner,{
            total1.text= it?.data?.confirmed.toString()
            death1.text= it?.data?.deaths.toString()
            recover1.text= it?.data?.recovered.toString()
        })
        homeViewModel.status.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*homeViewModel.totalDataBetween.observe(viewLifecycleOwner, {
            val list: MutableList<RecordsItem> = it.records as MutableList<RecordsItem>
            val totaldeathCountBetweenDate = list[0].cases?.totaldeceased!!.toInt() - list[list.size-1].cases?.totaldeceased!!.toInt()
            death.text = totaldeathCountBetweenDate.toString()

        })*/
        date1.setOnClickListener {
            openDateTimePickerDialog()
        }
        date2.setOnClickListener {
            openDateTimePickerDialog1()
        }
        result.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_resultFragment)

            //homeViewModel.fetchTotalBetween(date1 = date1.toString(),date2 = date2.toString())
        }
        refresh.setOnClickListener {
            homeViewModel.fetchTotal()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun openDateTimePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                date1.text = "$dayOfMonth-${monthOfYear+1}-$year"

            },
            year,
            month,
            day
        )

        dpd.show()
    }
    @SuppressLint("SetTextI18n")
    private fun openDateTimePickerDialog1() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                date2.text = "$dayOfMonth-${monthOfYear+1}-$year"

            },
            year,
            month,
            day
        )

        dpd.show()
    }


}