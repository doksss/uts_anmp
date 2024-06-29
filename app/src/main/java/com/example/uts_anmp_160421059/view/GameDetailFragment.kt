package com.example.uts_anmp_160421059.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.FragmentGameDetailBinding
import com.example.uts_anmp_160421059.model.Game
import com.example.uts_anmp_160421059.model.Paragraph
import com.example.uts_anmp_160421059.viewmodel.DetailViewModel
import com.example.uts_anmp_160421059.viewmodel.ListGameViewModel
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit

class GameDetailFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var prgfList:List<Paragraph>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_game_detail, container, false)
        binding =FragmentGameDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var size = 0
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


//        if(index==0){
//            binding.btnPrev.isEnabled = false
//        }

//        var gameList:Game
//        var prgfList:List<Paragraph>? = null


        if(arguments!=null){
            val games_id =GameDetailFragmentArgs.fromBundle(requireArguments()).gamesId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

            viewModel.fetch(games_id.toInt())
            viewModel.getDetails(games_id.toInt())

            observeViewModel()



//            if(index == (size-1)){
//                binding.btnNext.isEnabled = false
//            }
//            binding.btnNext.setOnClickListener{
//                index++
//                if(index==(size-1)){
//                    binding.btnNext.isEnabled = false
//                }else{
//                    binding.btnNext.isEnabled = true
//                }
//                if(index==0){
//                    binding.btnPrev.isEnabled = false
//                }else{
//                    binding.btnPrev.isEnabled = true
//                }
//            }
//            binding.btnPrev.setOnClickListener {
//                index--
//                if(index==0){
//                    binding.btnPrev.isEnabled = false
//                }else{
//                    binding.btnPrev.isEnabled = true
//                }
//                if(index==(size-1)){
//                    binding.btnNext.isEnabled = false
//                }else{
//                    binding.btnNext.isEnabled = true
//                }
//            }
//            viewModel.paragraphsLD.observe(viewLifecycleOwner, Observer {
//                prgfList = it
//                size = it.size
//
//            })
//            binding.btnPrev.setOnClickListener {
//                index--
//                if(index==0){
//                    binding.btnPrev.isEnabled = false
//                }else{
//                    binding.btnPrev.isEnabled = true
//                }
//                if(index==(size-1)){
//                    binding.btnNext.isEnabled = false
//                }else{
//                    binding.btnNext.isEnabled = true
//                }
//                binding.txtTitleParagraf.setText(prgfList?.get(index)?.judul_paragraf)
//            }
        }


    }
    fun observeViewModel(){
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            binding.game = it
        })

        viewModel.paragraphsLD.observe(viewLifecycleOwner, Observer {
            prgfList = it
            var size = it.size
            var index = 0
            if (it.isNotEmpty()) {
                binding.txtTitleParagraf.setText(it[index].judul_paragraf)
                binding.txtContentParagraf.setText(it[index].isi_paragraf)
            }
            fun updateButtons() {
                binding.btnPrev.isEnabled = index > 0
                binding.btnNext.isEnabled = index < size - 1
            }
            updateButtons()
            binding.btnPrev.setOnClickListener {
                if (index > 0){
                    index--
                    binding.txtTitleParagraf.setText(prgfList?.get(index)?.judul_paragraf)
                    binding.txtContentParagraf.setText(prgfList?.get(index)?.isi_paragraf)
                }
                updateButtons()
//                index--
//                if(index==0){
//                    binding.btnPrev.isEnabled = false
//                }else{
//                    binding.btnPrev.isEnabled = true
//                }
//                if(index==(size-1)){
//                    binding.btnNext.isEnabled = false
//                }else{
//                    binding.btnNext.isEnabled = true
//                }
//                binding.txtTitleParagraf.setText(prgfList?.get(index)?.judul_paragraf)
            }
            binding.btnNext.setOnClickListener {
                if(index < size-1){
                    index++
                    binding.txtTitleParagraf.setText(prgfList?.get(index)?.isi_paragraf)
                }
                updateButtons()
            }

            Log.d("Test gameListAdapter:", prgfList.size.toString())
        })

    }

}