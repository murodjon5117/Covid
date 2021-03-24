package com.project.covid19info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.project.covid19info.model.CovidResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private var call : Call<CovidResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO) {
           val response = RetrofitBuilder.invoke().loadBetweenDate("2021-02-22","2021-02-23")
            withContext(Dispatchers.Main){
                if (response.code()==200){

                Log.d("TAG", "onCreate:${response.body()} ")}

                else
                {
                    txt.text = response.code().toString()
                }
            }
        }
    }

}