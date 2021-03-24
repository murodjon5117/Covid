package com.project.covid19info.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.covid19info.Repo

class HomeViewModelFactory(val app:Application,val repo:Repo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(app,repo) as T
    }

}