package com.zdy.application.learnandroid.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class ImageUtils {
    companion object{
        fun getAssetsSplashPicture(context: Context):Bitmap{
            val assetManager = context.resources.assets
            val inputStream = assetManager.open("splash_logo.jpeg")
            val imageBitMap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            return imageBitMap
        }
    }
}