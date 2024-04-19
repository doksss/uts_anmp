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
import com.example.uts_anmp_160421059.viewmodel.UserViewModel


class RegistrasiFragment : Fragment() {
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
        binding.btnCreate.setOnClickListener{
            var username = binding.txtRegUsername.text
            var first_name = binding.txtRegFirstName.text
            var lastname = binding.txtLastName.text
            var email = binding.txtEmail.text
            var password = binding.txtRegPassword.text
            var confirm_pass = binding.txtConfirmPass.text
            var url = binding.txtUrl.text
            if(username.isEmpty()||first_name.isEmpty()||lastname.isEmpty()||email.isEmpty()||password.isEmpty()||
                confirm_pass.isEmpty()){
                Toast.makeText(activity, "Harap semua textbox diisi", Toast.LENGTH_SHORT).show()
            }else{
                if(password.toString() == confirm_pass.toString()){
                    viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                    viewModel.register(username.toString(),first_name.toString(),lastname.toString(),
                        email.toString(),confirm_pass.toString(),url.toString())

                    viewModel.userRegisterLD.observe(viewLifecycleOwner, Observer {
                        if(it==true){
                            Toast.makeText(activity, "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
                        }
                    })
                    val action = RegistrasiFragmentDirections.actionLoginFragment()
                    Navigation.findNavController(it).navigate(action)
                }else{
                    Toast.makeText(activity, "Password yang dimasukkan tidak cocok", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}