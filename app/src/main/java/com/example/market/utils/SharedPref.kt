package com.example.market.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.market.app.App

class SharedPref {
    companion object{
        val pref: SharedPreferences =
            App.instance.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE)
    }
}