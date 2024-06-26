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
import com.example.uts_anmp_160421059.model.User
import com.example.uts_anmp_160421059.viewmodel.UserViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var user:User

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
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val userlogin = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        var id = userlogin.getString("id","0")
        viewModel.profileUser(id!!.toInt())

        if(id.equals("")){
            val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

//        binding.txtNamaDepan.setText(firstname)
//        binding.txtLastNameProfile.setText(lastname)
//        binding.txtUsernameProfile.setText(username)
//        binding.txtOldPass.setText(password)
//        if(urlprofil!=""){
//            Picasso.get().load(urlprofil).into(binding.imgProfile)
//        }

        binding.btnLogout.setOnClickListener{
            val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
            val sharedValue =shared.edit()
            sharedValue.remove("id")
            sharedValue.apply()
            //menyembunyikan navbar
            (activity as MainActivity).binding.bottomNav.visibility = View.GONE
            val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        binding.btnUpdate.setOnClickListener{
            if(binding.txtNamaDepan.text.isEmpty()||binding.txtLastNameProfile.text.isEmpty()||binding.txtOldPass.text.isEmpty()){
                Toast.makeText(activity, "Harap semua textbox diisi", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.changeProfileUser(binding.txtNamaDepan.text.toString(), binding.txtLastNameProfile.text.toString(),
                    binding.txtOldPass.text.toString(), id.toInt())
                Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()
            }


        }
        observeViewModelUser()
    }

    fun observeViewModelUser(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.txtNamaDepan.setText(it.first_name)
            }
            if (it != null) {
                binding.txtLastNameProfile.setText(it.last_name)
            }
            if (it != null) {
                binding.txtUsernameProfile.setText(it.username)
            }
            if (it != null) {
                binding.txtOldPass.setText(it.password)
            }
            if (it != null) {
                if(it.url!=""){
                    if (it != null) {
                        Picasso.get().load(it.url).into(binding.imgProfile)
                    }
                }
            }
        })
    }

}