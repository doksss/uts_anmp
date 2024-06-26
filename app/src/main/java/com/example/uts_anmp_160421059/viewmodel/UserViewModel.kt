package com.example.uts_anmp_160421059.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_anmp_160421059.model.User
import com.example.uts_anmp_160421059.view.LoginFragmentDirections
import com.google.gson.Gson
import org.json.JSONObject

class UserViewModel(application:Application) :AndroidViewModel(application){
    val userLD =MutableLiveData<User?>()
//    val userRegisterLD = MutableLiveData<Boolean>()
    val userUpdateLD = MutableLiveData<Boolean>()
    val userSuccessLoginLD = MutableLiveData<Boolean>()
    fun login(username:String,password:String,view:View){
        val TAG ="volleytag"
        var queue: RequestQueue?=null
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://172.20.10.2/anmp/login.php"
        val stringRequest = object: StringRequest(Request.Method.POST,url,
            {response->
                val res = JSONObject(response)
                if(res.getString("Result")=="Success"){
                    userLD.value =Gson().fromJson(res.getString("Data"),User::class.java)
                    userSuccessLoginLD.value = true
                    val action = LoginFragmentDirections.actionLoginFragmentToGameListFragment()
                    Navigation.findNavController(view).navigate(action)
                    Toast.makeText(getApplication(), "Success!", Toast.LENGTH_SHORT).show()
                    Log.d("show_volley_login",res.getString("Data"))
                }else{
                    userSuccessLoginLD.value = false
                    Toast.makeText(getApplication(), "Username atau password salah", Toast.LENGTH_SHORT).show()
                }

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
//                userRegisterLD.value =true
                Toast.makeText(getApplication(), "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
                Log.d("show_volley_register",it)
//
            },{
//                userRegisterLD.value = false
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
    fun changeProfileUser(id:Int,first_name:String,last_name:String,password_user: String){
        val TAG ="volleytag"
        var queue: RequestQueue?=null
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://172.20.10.2/anmp/update_user.php"
        val stringRequest = object: StringRequest(Request.Method.POST,url,
            {
                userUpdateLD.value =true
                Log.d("show_volley_register",it)
            },{
                userUpdateLD.value = false
                Log.e("show_volley",it.toString())
            }
        )
        {
            override fun getParams(): MutableMap<String,String>{
                val params = HashMap<String,String>()
                params["id"] =id.toString()
                params["first_name"] = first_name
                params["last_name"] = last_name
                params["password"] = password_user
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}