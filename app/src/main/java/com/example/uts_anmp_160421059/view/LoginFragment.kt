package com.example.uts_anmp_160421059.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentLoginBinding
import com.example.uts_anmp_160421059.model.User
import com.example.uts_anmp_160421059.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container,false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var successLogin = false
        binding.btnCreateAccount.setOnClickListener{
            val action = LoginFragmentDirections.actionRegistrasiFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnSignin.setOnClickListener{
            var username = binding.txtUsername.text
            var password = binding.txtPassword.text
            viewModel.login(username.toString(),password.toString())
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Username atau password harus diisi!", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.userLD.observe(viewLifecycleOwner, Observer {
                    val user = it
                    if(user!=null){
                        viewModel.userSuccessLoginLD.value = true
                        if(viewModel.userSuccessLoginLD.value==true){
                            successLogin=true
                            val action = LoginFragmentDirections.actionLoginFragmentToGameListFragment()
                            Navigation.findNavController(requireView()).navigate(action)
                        }
                        //menampilkan navbar
                        (activity as MainActivity).binding.bottomNav.visibility = View.VISIBLE
                        //SharedPrefences
                        val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
                        val sharedValue =shared.edit()
                        sharedValue.putString("id", user.id)
                        sharedValue.putString("username",user.username)
                        sharedValue.putString("first_name",user.first_name)
                        sharedValue.putString("last_name",user.last_name)
                        sharedValue.putString("url_profile",user.url)
                        sharedValue.putString("password",user.password)
                        sharedValue.apply()
                    }else{
                        Toast.makeText(activity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                        viewModel.userSuccessLoginLD.value = false
                    }
                })
            }

        }


    }
//    fun observeViewModel():Boolean{
//        var checkUser = false
//
//        return checkUser
//    }

}