package com.example.uts_anmp_160421059.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhoto(imageView: ImageView, url:String){
    val picasso = Picasso.Builder(imageView.context)
    //jika terjadi sesuatu pada picasso maka ini jalan
    picasso.listener { picasso, uri, exception -> exception.printStackTrace()  }
    picasso.build().load(url).into(imageView)
    imageView.visibility = View.VISIBLE
}