package com.csci448.adhingra.bicycle_safety.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "The Chad William Young Foundation \n \n \n One of the goals of the foundation is to pursue technological developments in injury prevention in all types of cycling. In partnership with Colorado School of Mines, a Capstone Computer Science Field Session Project was launched. The goal was to enhance the algorithm that identified risky portions on cycling routes by automating it. Currently, this app serves as a template as a tangible way the algorithm could be used."
    }
    val text: LiveData<String> = _text
}