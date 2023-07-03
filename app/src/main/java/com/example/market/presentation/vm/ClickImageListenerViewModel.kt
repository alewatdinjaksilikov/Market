package com.example.market.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClickImageListenerViewModel @Inject constructor():ViewModel() {

    private val _imageClicked = MutableLiveData<String>()
    val imageClicked: LiveData<String> get() = _imageClicked

    fun onButtonClick(imageUrl: String) {
        Log.d("AAA","func onbuttonClick")
        viewModelScope.launch {
            _imageClicked.value = imageUrl
            Log.d("AAA","emit")
        }
    }
}