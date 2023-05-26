package com.example.homeless_helper_vs29.utils

import android.app.Activity
import android.content.Intent

object ImageControler {

    fun selectPhotoFromGalley(activity: Activity, code: Int)
    {
        val intent = Intent(Intent.ACTION_PICK)
       // val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        activity.startActivityForResult(intent, code)


    }
}