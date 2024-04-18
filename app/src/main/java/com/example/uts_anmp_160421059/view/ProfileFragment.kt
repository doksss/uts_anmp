package com.example.uts_anmp_160421059.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentGameListBinding
import com.example.uts_anmp_160421059.databinding.FragmentProfileBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

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
        var id = userlogin.getInt("id",0)
        var username = userlogin.getString("username", "")
        var firstname = userlogin.getString("first_name", "")
        var lastname= userlogin.getString("last_name", "")
        var urlprofil = userlogin.getString("url_profile", "")
        var password = userlogin.getString("password","")
        binding.txtNamaDepan.setText(firstname)
        binding.txtLastNameProfile.setText(lastname)
        binding.txtUsernameProfile.setText(username)
        binding.txtOldPass.setText(password)
        Picasso.get().load(urlprofil).into(binding.imgProfile)

        binding.btnLogout.setOnClickListener{
            //menyembunyikan navbar
            (activity as MainActivity).binding.bottomNav.visibility = View.GONE
            val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

    }

}