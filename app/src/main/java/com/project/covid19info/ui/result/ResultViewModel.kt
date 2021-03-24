package com.project.covid19info.ui.result

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
import com.project.covid19info.Repo
import com.project.covid19info.model.CovidResponse
import com.project.covid19info.modelTotalUzb.TotalUzbBetwen
import kotlinx.coroutines.launch
import java.io.IOException

class ResultViewModel(val app: Application, private val repo: Repo) : ViewModel() {

    private val _totalDataBetween = MutableLiveData<CovidResponse>()
    val totalDataBetween: LiveData<CovidResponse> = _totalDataBetween

    private val _totalDataBetweenUzb = MutableLiveData<TotalUzbBetwen>()
    val totalDataBetweenUzb: LiveData<TotalUzbBetwen> = _totalDataBetweenUzb

    private val _status = MutableLiveData<String>().apply { null }
    val status: LiveData<String> = _status

    fun fetchTotalBetween(date1:String,date2: String) =
        viewModelScope.launch {
            safeFetchTotal1(date1,date2)
        }
    private suspend fun safeFetchTotal1(date1: String, date2: String) {
        try {
            if (hasInternetConnection(application =app )) {
                val response = repo.loadBetweenDate(date1, date2)
                val response1 = repo.loadBetweenDateUzb(date1, date2,"UZ")

                Log.d("responsebek", response.toString())
                _totalDataBetween.postValue(response.body())
                _totalDataBetweenUzb.postValue(response1.body())

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