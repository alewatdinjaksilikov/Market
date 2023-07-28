package com.example.market.utils

import android.widget.Toast
import com.example.market.app.App

fun makeToast(text:CharSequence, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(App.instance,text,duration).show()
}