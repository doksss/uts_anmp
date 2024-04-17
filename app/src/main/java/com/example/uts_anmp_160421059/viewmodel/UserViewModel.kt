package com.example.uts_anmp_160421059.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_anmp_160421059.model.User
import com.google.gson.Gson

class UserViewModel(application:Application) :AndroidViewModel(application){
    val userLD =MutableLiveData<User>()
    val userRegisterLD = MutableLiveData<Boolean>()
    fun login(username:String,password:String){
        val TAG ="volleytag"
        var queue: RequestQueue?=null
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://172.20.10.2/anmp/login.php"
        val stringRequest = object: StringRequest(Request.Method.POST,url,
            {
                Log.d("show_volley_login",it)
                val user1 =Gson().fromJson(it,User::class.java)
                userLD.value =user1?:null
            },{
                Log.e("show_volley",it.toString())
            }
        )
        {
            override fun getParams(): MutableMap<String,String>{
                val params = HashMap<String,String>()
                params["username"] =username
                params["password"] = password
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
    fun register(username:String,firstname:String,lastname:String,email:String,password:String,photoUrl:String){
        val TAG ="volleytag"
        var queue: RequestQueue?=null
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://172.20.10.2/anmp/register.php"
        val stringRequest = object: StringRequest(Request.Method.POST,url,
            {
                Log.d("show_volley_register",it)
                userRegisterLD.value =true
            },{
                userRegisterLD.value = false
                Log.e("show_volley",it.toString())
            }
        )
        {
            override fun getParams(): MutableMap<String,String>{
                val params = HashMap<String,String>()
                params["username"] =username
                params["password"] = password
                params["firstname"] = firstname
                params["lastname"] = lastname
                params["email"] = email
                params["url"] = photoUrl
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}