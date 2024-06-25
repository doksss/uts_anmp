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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val gamesLD =MutableLiveData<ArrayList<Game>>()
    val paragraphsLD=MutableLiveData<ArrayList<Paragraph>>()
    val TAG = "volleyTag" //ini bebas mau dikasi nama apa variablenya
    private var queue: RequestQueue?=null //object volley
    fun detailRefresh(id:String){
        queue = Volley.newRequestQueue(getApplication())//requestQueue butuh context, karena viewmodel gada context maka parent class diganti AndroidViewModel
        val url = "http://172.20.10.2/anmp/listdetailgame.php?id="+id

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {//callback function jika volly success dibaca
                Log.d("show_volley",it)
                val sType = object: TypeToken<List<Game>>(){}.type
                val result = Gson().fromJson<List<Game>>(it,sType)
                gamesLD.value = result as ArrayList<Game>
            },
            {
                Log.e("show_volley",it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}