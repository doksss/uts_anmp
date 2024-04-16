package com.example.uts_anmp_160421059.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentRegistrasiBinding


class RegistrasiFragment : Fragment() {
    private lateinit var binding:FragmentRegistrasiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrasiBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_registrasi, container, false)
        return binding.root
    }
}