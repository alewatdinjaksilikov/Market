package com.bizlergroup.stockcontrol.utils

interface Downloader {
    fun downloadFile(url:String):Long
}