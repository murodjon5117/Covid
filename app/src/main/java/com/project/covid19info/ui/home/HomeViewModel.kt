package com.project.covid19info.ui.home

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.project.covid19info.Repo
import com.project.covid19info.RetrofitBuilder
import com.project.covid19info.RetrofitBuilderUzb
import com.project.covid19info.model.CovidResponse
import com.project.covid19info.modelTotal.Total
import com.project.covid19info.modelUzb.ResUzb
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(val app: Application, val repo: Repo) : ViewModel() {

    private val _totalData = MutableLiveData<Total>()
    val totalData: LiveData<Total> = _totalData


    private val _totalUzb = MutableLiveData<ResUzb>()
    val totalUzb: LiveData<ResUzb> = _totalUzb

    private val _status = MutableLiveData<String>().apply { null }
    val status: LiveData<String> = _status


    fun fetchTotal() =
        viewModelScope.launch {
            while(isActive) {
                safeFetchTotal()
                //do your network request here
                Log.i("alijon","Ishladim")
                delay(10000)
            }
        }


    private suspend fun safeFetchTotal() {
        try {
            if (hasInternetConnection(application = app)) {
                val response = repo.loadTotal()
                _totalData.postValue(response.body())
                val responseUzb = RetrofitBuilderUzb.invoke().loadUzb("Uzbekistan")
                _totalUzb.postValue(responseUzb.body())

            } else {
                _status.postValue("No Internet")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _status.postValue("Network failure $t")
                else -> _status.postValue("Conversation Error $t")
            }
        }
    }


    private fun hasInternetConnection(application: Application): Boolean {
        val connectivityManager = application.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(ContentValues.TAG, "SDK_M")
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            Log.d(ContentValues.TAG, "SDK_N")
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ContactsContract.CommonDataKinds.Email.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}

