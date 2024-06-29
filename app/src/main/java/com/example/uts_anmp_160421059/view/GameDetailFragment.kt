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

class GameDetailFragment : Fragment(), ParagrafNextPageClickListener, ParagrafPrevPageClickListener {

    private lateinit var binding: FragmentGameDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var prgfList:List<Paragraph>
    var index = 0
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

        binding.game = Game("","","","https://randomuser.me/api/portraits/men/74.jpg")
        var size = 0
        binding.pagenextlistener = this
        binding.pageprevlistener = this
//        binding.paragraf = Paragraph("","","")
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


        if(arguments!=null){
            val games_id =GameDetailFragmentArgs.fromBundle(requireArguments()).gamesId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

            viewModel.fetch(games_id.toInt())
            viewModel.getDetails(games_id.toInt())
            observeViewModel()
        }

    }
    fun observeViewModel(){
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            binding.game = it
        })

        viewModel.paragraphsLD.observe(viewLifecycleOwner, Observer {
            prgfList = it
            var size = it.size

            if (it.isNotEmpty()) {

                binding.paragraf = it[index]

            }
            binding.btnPrev.isEnabled = index > 0
            binding.btnNext.isEnabled = index < size - 1

            Log.d("Test gameListAdapter:", prgfList.size.toString() + it[index])
        })

    }


    override fun onParagrafNextPageClick(v: View) {
        var size = prgfList.size
        if(index < size-1){
            index++
            binding.paragraf = prgfList[index]
//            binding.paragraf!!.judul_paragraf = prgfList[index].judul_paragraf
//            binding.paragraf!!.isi_paragraf = prgfList[index].isi_paragraf
        }
        binding.btnPrev.isEnabled = index > 0
        binding.btnNext.isEnabled = index < size - 1
    }

    override fun onParagrafPrevPageClick(v: View) {
        var size = prgfList.size
        if (index > 0){
            index--
            binding.paragraf = prgfList[index]
//            binding.txtTitleParagraf.setText(prgfList?.get(index)?.judul_paragraf)
//            binding.txtContentParagraf.setText(prgfList?.get(index)?.isi_paragraf)
        }
        binding.btnPrev.isEnabled = index > 0
        binding.btnNext.isEnabled = index < size - 1
    }

}