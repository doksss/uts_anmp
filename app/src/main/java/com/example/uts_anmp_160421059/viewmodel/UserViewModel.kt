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
    fun login(username:String,password:String){
        launch{
            val db = buildDb(getApplication())
            val user = db.userDao().login(username,password)
            if(user == null){
                userSuccessLoginLD.postValue(false)
            }else{
                userSuccessLoginLD.postValue(true)
                userLD.postValue(user)
            }
        }
    }
    fun register(user: User){
        launch {
        val db = buildDb(getApplication())
        db.userDao().insertAll(user)
        }
    }

    //LOAD PROFILE USER
    fun profileUser(id: Int){
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(id))
        }
    }

    //UDAH GA KEPAKE KARENA UPDATE UDAH PAKE OBJECT USER
    fun changeProfileUser(first_name:String, last_name:String, pass:String, id:Int){
        launch {
            buildDb(getApplication()).userDao().updateProfileUser(first_name,last_name,pass,id)
        }
    }

    fun updateProfileUser(user:User) {
        launch {
            buildDb(getApplication()).userDao().updateUser(user)
        }
    }
}