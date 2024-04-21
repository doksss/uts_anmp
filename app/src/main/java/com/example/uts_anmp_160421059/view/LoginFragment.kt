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

//        var successLogin = false
        binding.btnCreateAccount.setOnClickListener{
            val action = LoginFragmentDirections.actionRegistrasiFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel =ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnSignin.setOnClickListener{
            var username = binding.txtUsername.text
            var password = binding.txtPassword.text

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Username atau password harus diisi!", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.login(username.toString(),password.toString(),view)
                viewModel.userSuccessLoginLD.observe(viewLifecycleOwner, Observer { checkSuccess ->
                    val userLogin = checkSuccess
                    if(userLogin==true){
                        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
                            val users = user
                            //menampilkan navbar
                            (activity as MainActivity).binding.bottomNav.visibility = View.VISIBLE
                            //SharedPrefences
                            val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
                            val sharedValue =shared.edit()
                            if (users != null) {
                                sharedValue.putString("id", users.id)
                            }
                            if (users != null) {
                                sharedValue.putString("username",users.username)
                            }
                            if (users != null) {
                                sharedValue.putString("first_name",users.first_name)
                            }
                            if (users != null) {
                                sharedValue.putString("last_name",users.last_name)
                            }
                            if (users != null) {
                                sharedValue.putString("url_profile",users.url)
                            }
                            if (users != null) {
                                sharedValue.putString("password",users.password)
                            }
                            sharedValue.apply()
                        })
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