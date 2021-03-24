package com.project.covid19info.ui.result

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.covid19info.Repo
import com.project.covid19info.ui.home.HomeViewModel

class ResultViewModelFactory (val app: Application, val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ResultViewModel(app,repo) as T
    }

}