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


class ProfileFragment : Fragment(), UserEditClickListener, UserLogoutClickListener {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var user:User //UDAH GA KEPAKE DAH PAKE DATA BINDING

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val userlogin = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        var id = userlogin.getString("id","0")
        viewModel.profileUser(id!!.toInt())

        binding.editlistener = this
        binding.logoutlistener = this

        if(id.equals("")){
            val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }

        observeViewModelUser()
    }

    fun observeViewModelUser(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {

            binding.user = it

//            if (it != null) {
//                binding.txtNamaDepan.setText(it.first_name)
//            }
//            if (it != null) {
//                binding.txtLastNameProfile.setText(it.last_name)
//            }
//            if (it != null) {
//                binding.txtUsernameProfile.setText(it.username)
//            }
//            if (it != null) {
//                binding.txtOldPass.setText(it.password)
//            }
//            if (it != null) {
//                if(it.url!=""){
//                    if (it != null) {
//                        Picasso.get().load(it.url).into(binding.imgProfile)
//                    }
//                }
//            }
        })
    }

    override fun onUserEditClick(v: View) {
        viewModel.updateProfileUser(binding.user!!)
        Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()
    }

    override fun onUserLogoutClick(v: View) {
        val shared = requireContext().getSharedPreferences("login",Context.MODE_PRIVATE)
        val sharedValue =shared.edit()
        sharedValue.remove("id")
        sharedValue.apply()
        //menyembunyikan navbar
        (activity as MainActivity).binding.bottomNav.visibility = View.GONE
        val action = ProfileFragmentDirections.actionProfileFragmentLoginFragment()
        Navigation.findNavController(v).navigate(action)
    }

}