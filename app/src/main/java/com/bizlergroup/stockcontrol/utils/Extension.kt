package com.bizlergroup.stockcontrol.utils

import android.widget.Toast
import com.bizlergroup.stockcontrol.app.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun makeToast(text:CharSequence, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(App.instance,text,duration).show()
}

fun retrofitBuilder(baseUrl:String,client: OkHttpClient):Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}