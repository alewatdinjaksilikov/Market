package com.bizlergroup.stockcontrol.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object AddButtonClick{
    private val _buttonClickLiveData = MutableLiveData<Boolean>()
    val buttonClickLiveData: LiveData<Boolean> = _buttonClickLiveData

    fun emitButtonClick(boolean: Boolean) {
        _buttonClickLiveData.value = boolean
    }
}

object AddButtonCategoryClick{
    private val _buttonAddCategoryLiveData = MutableLiveData<Boolean>()
    val buttonAddCategoryLiveData: LiveData<Boolean> = _buttonAddCategoryLiveData

    fun buttonAddCategoryClick(boolean: Boolean) {
        _buttonAddCategoryLiveData.value = boolean
    }
}

object EditProductClick{
    private val _buttonEditProductLiveData = MutableLiveData<Boolean>()
    val buttonEditProductLiveData: LiveData<Boolean> = _buttonEditProductLiveData

    fun buttonAddCategoryClick(boolean: Boolean) {
        _buttonEditProductLiveData.value = boolean
    }
}

object EditCategoryClick{
    private val _buttonEditCategoryLiveData = MutableLiveData<Boolean>()
    val buttonEditCategoryLiveData: LiveData<Boolean> = _buttonEditCategoryLiveData

    fun buttonAddCategoryClick(boolean: Boolean) {
        _buttonEditCategoryLiveData.value = boolean
    }
}

object AddAmountClick{
    private val _buttonAddAmountLiveData = MutableLiveData<Boolean>()
    val buttonAddAmountLiveData: LiveData<Boolean> = _buttonAddAmountLiveData

    fun buttonAddAmountClick(boolean: Boolean) {
        _buttonAddAmountLiveData.value = boolean
    }
}