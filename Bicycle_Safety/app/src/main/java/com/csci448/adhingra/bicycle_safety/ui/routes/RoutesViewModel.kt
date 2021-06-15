package com.csci448.adhingra.bicycle_safety.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoutesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is routes Fragment"
    }
    val text: LiveData<String> = _text
}