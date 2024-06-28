package com.example.uts_anmp_160421059.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentGameListBinding
import com.example.uts_anmp_160421059.viewmodel.ListGameViewModel

class GameListFragment : Fragment() {

    private lateinit var viewModel: ListGameViewModel
    private lateinit var binding:FragmentGameListBinding
    private val gameListAdapater = GameListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentGameListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userlogin = requireActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
        var id = userlogin.getString("id","")
        if(id.equals("")){
            val action = GameListFragmentDirections.actionBacktoLoginFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        viewModel =ViewModelProvider(this).get(ListGameViewModel::class.java)
        viewModel.getAllGames()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter =gameListAdapater

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.gameLD.observe(viewLifecycleOwner, Observer {
            gameListAdapater.updateList(it)
        })
        Log.d("Test gameListAdapter:", "TESSSTIS")

    }

}