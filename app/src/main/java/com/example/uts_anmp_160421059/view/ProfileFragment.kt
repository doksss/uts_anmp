package com.example.uts_anmp_160421059.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentGameListBinding
import com.example.uts_anmp_160421059.databinding.FragmentProfileBinding
import com.example.uts_anmp_160421059.viewmodel.UserViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userlogin = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        var id = userlogin.getString("id","")
        var username = userlogin.getString("username", "")
        var firstname = userlogin.getString("first_name", "")
        var lastname= userlogin.getString("last_name", "")
        var urlprofil = userlogin.getString("url_profile", "")
        var password = userlogin.getString("password","")
        if(id.equals("")){
            val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.txtNamaDepan.setText(firstname)
        binding.txtLastNameProfile.setText(lastname)
        binding.txtUsernameProfile.setText(username)
        binding.txtOldPass.setText(password)
        if(urlprofil!=""){
            Picasso.get().load(urlprofil).into(binding.imgProfile)
        }


        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnLogout.setOnClickListener{
            val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
            val sharedValue =shared.edit()
            sharedValue.remove("id")
            sharedValue.remove("username")
            sharedValue.remove("first_name")
            sharedValue.remove("last_name")
            sharedValue.remove("password")
            sharedValue.remove("url_profile")
            sharedValue.apply()
            //menyembunyikan navbar
            (activity as MainActivity).binding.bottomNav.visibility = View.GONE
            val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.btnUpdate.setOnClickListener{
            var first_name = binding.txtNamaDepan.text
            var last_name = binding.txtLastNameProfile.text
            var password_user = binding.txtOldPass.text
            if(first_name.isEmpty()||last_name.isEmpty()||password_user.isEmpty()){
                Toast.makeText(activity, "Harap semua textbox diisi", Toast.LENGTH_SHORT).show()
            }else{
                if (id != null) {
                    viewModel.changeProfileUser(id.toInt(),first_name.toString(),last_name.toString(), password_user.toString())
                }
                viewModel.userUpdateLD.observe(viewLifecycleOwner, Observer {
                    if(it==true){
                        val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
                        val sharedValue =shared.edit()
                        sharedValue.remove("first_name")
                        sharedValue.remove("last_name")
                        sharedValue.remove("password")
                        sharedValue.putString("first_name",first_name.toString())
                        sharedValue.putString("last_name",last_name.toString())
                        sharedValue.putString("password",password_user.toString())
                        sharedValue.apply()
                        Toast.makeText(activity, "Akun berhasil diupdate", Toast.LENGTH_SHORT).show()
                    }
                })

            }


        }

    }

}