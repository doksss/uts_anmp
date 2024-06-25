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
        var index = 0
        var size = 0
        if(index==0){
            binding.btnPrev.isEnabled = false
        }
        var gameList:ArrayList<Game>
        var prgfList:ArrayList<Paragraph>
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            val games_id =GameDetailFragmentArgs.fromBundle(requireArguments()).gamesId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.detailRefresh(games_id)
            //bertujuan untuk mendengarkan dari live data. jika data baru muncul, maka UI akan menanggapi
            viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
//                val gameList:ArrayList<Game>
                gameList = it
                size = gameList[0].paragraphs?.size!!
                binding.txtJudulGames.setText(gameList[0].judul)
                binding.txtAuthorGames.setText("@" + gameList[0].pengarang)
                binding.txtTitleParagraf.setText(gameList[0].paragraphs?.get(index)?.judul_paragraf)
                binding.txtContentParagraf.setText(gameList[0].paragraphs?.get(index)?.isi_paragraf)

                Picasso.get().load(gameList[0].url).into(binding.imgGames)
                //kalau paragraf cuma ada 1 maka btn next di disabled
                if(index == (size-1)){
                    binding.btnNext.isEnabled = false
                }
                binding.btnNext.setOnClickListener{
                    index++
                    if(index==(size-1)){
                        binding.btnNext.isEnabled = false
                    }else{
                        binding.btnNext.isEnabled = true
                    }
                    if(index==0){
                        binding.btnPrev.isEnabled = false
                    }else{
                        binding.btnPrev.isEnabled = true
                    }
                    binding.txtTitleParagraf.setText(gameList[0].paragraphs?.get(index)?.judul_paragraf)
                    binding.txtContentParagraf.setText(gameList[0].paragraphs?.get(index)?.isi_paragraf)
                }
                binding.btnPrev.setOnClickListener {
                    index--
                    if(index==0){
                        binding.btnPrev.isEnabled = false
                    }else{
                        binding.btnPrev.isEnabled = true
                    }
                    if(index==(size-1)){
                        binding.btnNext.isEnabled = false
                    }else{
                        binding.btnNext.isEnabled = true
                    }
                    binding.txtTitleParagraf.setText(gameList[0].paragraphs?.get(index)?.judul_paragraf)
                    binding.txtContentParagraf.setText(gameList[0].paragraphs?.get(index)?.isi_paragraf)
                }
            })
        }


    }

}