package com.csci448.adhingra.bicycle_safety.ui.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.csci448.adhingra.bicycle_safety.databinding.FragmentRoutesBinding


class RoutesFragment : Fragment() {

    private lateinit var routesViewModel: RoutesViewModel
    private var _binding: FragmentRoutesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Connect ViewModel and Binding to switch between fragments

        routesViewModel =
            ViewModelProvider(this).get(RoutesViewModel::class.java)

        _binding = FragmentRoutesBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}