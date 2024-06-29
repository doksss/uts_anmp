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
import com.example.uts_anmp_160421059.model.Paragraph
import com.example.uts_anmp_160421059.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val gamesLD =MutableLiveData<Game>()
    val paragraphsLD=MutableLiveData<List<Paragraph>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun getDetails(id: Int){
        launch{
            val db = buildDb(getApplication())
            gamesLD.postValue(db.gameDao().selectGame(id))
        }
    }

    fun fetch(id: Int){
        launch {
            val db = buildDb(getApplication())
            paragraphsLD.postValue(db.paragraphDao().selectParagrafDetail(id))
        }
    }

    fun addParagraf(paragraf: Paragraph){
        launch {
            val db = buildDb(getApplication())
            db.paragraphDao().insertAll(paragraf)
        }
    }
}