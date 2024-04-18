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

        binding.btnCreateAccount.setOnClickListener{
            val action = LoginFragmentDirections.actionRegistrasiFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.btnSignin.setOnClickListener{
            var username = binding.txtUsername.text
            var password = binding.txtPassword.text
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Username atau password harus diisi!", Toast.LENGTH_SHORT).show()
            }else{
                viewModel =ViewModelProvider(this).get(UserViewModel::class.java)
                viewModel.login(username.toString(),password.toString())
                viewModel.userLD.observe(viewLifecycleOwner, Observer {
                    var user = it
                    if(user==null){
                        Toast.makeText(activity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                    }else{
                        //menampilkan navbar
                        (activity as MainActivity).binding.bottomNav.visibility = View.VISIBLE
                        //SharedPrefences
                        val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
                        val sharedValue =shared.edit()
                        if (user.id != null) {
                            sharedValue.putInt("id",id)
                        }
                        if (user.username != null) {
                            sharedValue.putString("username",user.username)
                        }
                        if (user.first_name != null) {
                            sharedValue.putString("first_name",user.first_name)
                        }
                        if (user.last_name != null) {
                            sharedValue.putString("last_name",user.last_name)
                        }
                        if (user.url != null) {
                            sharedValue.putString("url_profile",user.url)
                        }
                        if(user.password!=null){
                            sharedValue.putString("password",user.password)
                        }
                        sharedValue.apply()


                        val action = LoginFragmentDirections.actionGameListFragment()
                        Navigation.findNavController(requireView()).navigate(action)
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