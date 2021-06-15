package com.csci448.adhingra.bicycle_safety.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.csci448.adhingra.bicycle_safety.R
import com.csci448.adhingra.bicycle_safety.databinding.FragmentHomeBinding
import com.google.android.gms.maps.SupportMapFragment

class HomeFragment : SupportMapFragment() {
    companion object{
        private const val LOG_TAG = "370.HomeFragment"
    }
    // Not much done here as home fragment involves MapsActivity and Google Maps API
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(LOG_TAG, "onCreateView() called")
        val mapView = super.onCreateView(inflater,
            container,
            savedInstanceState)
        return mapView
    }
}