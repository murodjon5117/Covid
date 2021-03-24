package com.project.covid19info.ui.result

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.project.covid19info.R
import com.project.covid19info.Repo
import com.project.covid19info.ui.home.HomeViewModel
import com.project.covid19info.ui.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.result_fragment.*

class ResultFragment : Fragment() {


    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val resultViewModelFactory= ResultViewModelFactory(requireActivity().application, repo = Repo())
        viewModel =
            ViewModelProvider(requireActivity(), resultViewModelFactory).get(ResultViewModel::class.java)
        viewModel.fetchTotalBetween("2021-02-25","2021-03-23")

        val root = inflater.inflate(R.layout.result_fragment, container, false)
        viewModel.totalDataBetween.observe(viewLifecycleOwner, {
            total2.text = it?.records?.get(0)?.cases?.dailyconfirmed.toString()
            death2.text = it?.records?.get(0)?.cases?.dailydeceased.toString()
            recover2.text = it?.records?.get(0)?.cases?.dailyrecovered.toString()
        })
        viewModel.totalDataBetweenUzb.observe(viewLifecycleOwner, {
            total3.text = it?.records?.get(0)?.cases?.dailyconfirmed.toString()
            death3.text = it?.records?.get(0)?.cases?.dailydeceased.toString()
            recover3.text = it?.records?.get(0)?.cases?.dailyrecovered.toString()
        })

        return root
    }


}