package com.example.uts_anmp_160421059.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_anmp_160421059.databinding.GameListItemBinding
import com.example.uts_anmp_160421059.model.Game
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class GameListAdapter(val gameList:ArrayList<Game>):RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {
    class GameViewHolder(var binding: GameListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding=GameListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.txtJudulGame.text =gameList[position].judul
        holder.binding.txtKreator.text = gameList[position].authors
        holder.binding.txtDescription.text = gameList[position].deskripsi
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()//jika picasso error
        }
        picasso.build().load(gameList[position].url).into(holder.binding.imageView,
            object: Callback {
                override fun onSuccess() {
                    Log.d("Picasso Success","success")
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso error", e.toString())
                }

            })


    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    fun updateList(newGameList:ArrayList<Game>){
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

}