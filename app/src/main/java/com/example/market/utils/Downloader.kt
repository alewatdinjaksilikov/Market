package com.example.market.utils

interface Downloader {
    fun downloadFile(url:String):Long
}