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
import com.example.uts_anmp_160421059.util.buildDb
import com.example.uts_anmp_160421059.view.LoginFragmentDirections
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class UserViewModel(application:Application) :AndroidViewModel(application),CoroutineScope{

    val userLD =MutableLiveData<User?>()
//    val userRegisterLD = MutableLiveData<Boolean>()
    val userUpdateLD = MutableLiveData<Boolean>()
    val userSuccessLoginLD = MutableLiveData<Boolean>()

    private var job= Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    fun login(username:String,password:String,view:View){
        launch{
            val db = buildDb(getApplication())
            val user = db.userDao().login(username,password)
            if(user == null){
                userSuccessLoginLD.postValue(false)
            }else{
                userSuccessLoginLD.postValue(true)
                userLD.postValue(db.userDao().login(username,password))
//                val action = LoginFragmentDirections.actionLoginFragmentToGameListFragment()
//                Navigation.findNavController(view).navigate(action)

                //buat test aja
//                val action = LoginFragmentDirections.actionRegistrasiFragment()
//                Navigation.findNavController(view).navigate(action)
            }
        }
//        val TAG ="volleytag"
//        var queue: RequestQueue?=null
//        queue = Volley.newRequestQueue(getApplication())
//        var url = "http://172.20.10.2/anmp/login.php"
//        val stringRequest = object: StringRequest(Request.Method.POST,url,
//            {response->
//                val res = JSONObject(response)
//                if(res.getString("Result")=="Success"){
//                    userLD.value =Gson().fromJson(res.getString("Data"),User::class.java)
//                    userSuccessLoginLD.value = true
//                    val action = LoginFragmentDirections.actionLoginFragmentToGameListFragment()
//                    Navigation.findNavController(view).navigate(action)
//                    Toast.makeText(getApplication(), "Success!", Toast.LENGTH_SHORT).show()
//                    Log.d("show_volley_login",res.getString("Data"))
//                }else{
//                    userSuccessLoginLD.value = false
//                    Toast.makeText(getApplication(), "Username atau password salah", Toast.LENGTH_SHORT).show()
//                }
//
//            },{
//                Log.e("show_volley",it.toString())
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String,String>{
//                val params = HashMap<String,String>()
//                params["username"] =username
//                params["password"] = password
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)

    }
    fun register(user: User){
        launch {
        val db = buildDb(getApplication())
        db.userDao().insertAll(user)
        }
//        val TAG ="volleytag"
//        var queue: RequestQueue?=null
//        queue = Volley.newRequestQueue(getApplication())
//        var url = "http://172.20.10.2/anmp/register.php"
//        val stringRequest = object: StringRequest(Request.Method.POST,url,
//            {
////                userRegisterLD.value =true
//                Toast.makeText(getApplication(), "Akun berhasil dibuat", Toast.LENGTH_SHORT).show()
//                Log.d("show_volley_register",it)
////
//            },{
////                userRegisterLD.value = false
//                Log.e("show_volley",it.toString())
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String,String>{
//                val params = HashMap<String,String>()
//                params["username"] =username
//                params["password"] = password
//                params["firstname"] = firstname
//                params["lastname"] = lastname
//                params["email"] = email
//                params["url"] = photoUrl
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    fun profileUser(id: Int){
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(id))
        }
    }
    fun changeProfileUser(first_name:String, last_name:String, pass:String, id:Int){
        launch {
            buildDb(getApplication()).userDao().updateProfileUser(first_name,last_name,pass,id)
        }
//        val TAG ="volleytag"
//        var queue: RequestQueue?=null
//        queue = Volley.newRequestQueue(getApplication())
//        var url = "http://172.20.10.2/anmp/update_user.php"
//        val stringRequest = object: StringRequest(Request.Method.POST,url,
//            {
//                userUpdateLD.value =true
//                Log.d("show_volley_register",it)
//            },{
//                userUpdateLD.value = false
//                Log.e("show_volley",it.toString())
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String,String>{
//                val params = HashMap<String,String>()
//                params["id"] =id.toString()
//                params["first_name"] = first_name
//                params["last_name"] = last_name
//                params["password"] = password_user
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    fun updateProfileUser(user:User) {
        launch {
            buildDb(getApplication()).userDao().updateUser(user)
        }
    }
}