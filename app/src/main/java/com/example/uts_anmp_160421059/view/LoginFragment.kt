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

class LoginFragment : Fragment(), UserCreateClickListener, UserEditClickListener {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var users:User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createlistener = this
        binding.loginlistener = this
        viewModel =ViewModelProvider(this).get(UserViewModel::class.java)

        binding.user =User("","","","","","",0)


//        binding.btnSignin.setOnClickListener{
//            var username = binding.txtUsername.text
//            var password = binding.txtPassword.text
//
//            if(username.isEmpty() || password.isEmpty()){
//                Toast.makeText(activity, "Username atau password harus diisi!", Toast.LENGTH_SHORT).show()
//            }else{
////                viewModel.login(username.toString(),password.toString(),view)
////                viewModel.userLD.observe(viewLifecycleOwner, Observer {
////                    if (it != null) {
////                        user = it
////                        Toast.makeText(context,"Berhasil",Toast.LENGTH_SHORT).show()
////                    }
////                })
//                viewModel.login(username.toString(),password.toString(),view)
//                viewModel.userSuccessLoginLD.observe(viewLifecycleOwner, Observer { checkSuccess ->
//                    val userLogin = checkSuccess
//                    if(userLogin==true){
//                        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
//                            if (user != null) {
//                                users = user
//                            }
//                            //menampilkan navbar
//                            (activity as MainActivity).binding.bottomNav.visibility = View.VISIBLE
//                            //SharedPrefences
//                            val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
//                            val sharedValue =shared.edit()
//                            if (users != null) {
//                                sharedValue.putString("id", users.id.toString())
//                            }
//
//                            sharedValue.apply()
//                            val action = LoginFragmentDirections.actionLoginFragmentToGameListFragment()
//                            Navigation.findNavController(it).navigate(action)
//                        })
//                    }
//                })
//            }
//
//        }


    }

    override fun onUserCreateClick(v: View) {
        val action = LoginFragmentDirections.actionRegistrasiFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onUserEditClick(v: View) {
        if(binding.user?.username == "" || binding.user?.password == ""){
            Toast.makeText(context, "Username atau Password harus diisi!", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.login(binding.user!!.username, binding.user!!.password)
            viewModel.userSuccessLoginLD.observe(viewLifecycleOwner, Observer { checkSuccess ->
                if(checkSuccess == true){
                    viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
                            //menampilkan navbar
                            (activity as MainActivity).binding.bottomNav.visibility = View.VISIBLE

                            //SharedPrefences
                            val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
                            val sharedValue =shared.edit()
                            sharedValue.putString("id", user!!.id.toString())
                            sharedValue.apply()

                            val action = LoginFragmentDirections.actionLoginFragmentToGameListFragment()
                            Navigation.findNavController(v).navigate(action)
                        })
                }else{
                    Toast.makeText(context, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

}