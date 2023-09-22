package com.bizlergroup.stockcontrol.utils

import android.content.Context
import android.content.SharedPreferences
import com.bizlergroup.stockcontrol.app.App

class SharedPref {
    companion object{
        val pref: SharedPreferences =
            App.instance.getSharedPreferences("MySharedPref",Context.MODE_PRIVATE)
        val prefEditor: SharedPreferences.Editor = pref.edit()
    }
}