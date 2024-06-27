package com.example.uts_anmp_160421059.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentRegistrasiBinding
import com.example.uts_anmp_160421059.model.User
import com.example.uts_anmp_160421059.viewmodel.UserViewModel


class RegistrasiFragment : Fragment(), UserEditClickListener{
    private lateinit var binding:FragmentRegistrasiBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrasiBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_registrasi, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //diinisialisasi diawal supaya ga nullPointerException!
        binding.user =User("","","","","","")

        binding.addlistener = this
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

    }

    override fun onUserEditClick(v: View) {
        viewModel.register(binding.user!!)
        Toast.makeText(context, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}