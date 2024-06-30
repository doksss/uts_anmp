package com.example.uts_anmp_160421059.view

import android.view.View

interface UserEditClickListener{
    fun onUserEditClick(v:View)
}

interface UserLogoutClickListener{
    fun onUserLogoutClick(v:View)
}

interface UserCreateClickListener{
    fun onUserCreateClick(v: View)
}

interface ParagrafNextPageClickListener{
    fun onParagrafNextPageClick(v: View)
}

interface ParagrafPrevPageClickListener{
    fun onParagrafPrevPageClick(v: View)
}

interface GameDetailClickListener{
    fun onGameDetailClickListener(v: View)
}

