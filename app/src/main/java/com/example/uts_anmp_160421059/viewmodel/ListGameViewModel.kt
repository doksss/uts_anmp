package com.example.uts_anmp_160421059.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_anmp_160421059.model.Game
import com.example.uts_anmp_160421059.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListGameViewModel(application:Application) :AndroidViewModel(application), CoroutineScope{

    val gameLD = MutableLiveData<List<Game>>()
    private var job= Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getAllGames(){
        launch {
            val db = buildDb(getApplication())
            gameLD.postValue(db.gameDao().selectAll())
        }
    }

    fun addGame(game: Game){
        launch {
            val db = buildDb(getApplication())
            db.gameDao().insertAll(game)
        }
    }


}