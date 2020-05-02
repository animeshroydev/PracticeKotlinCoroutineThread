package com.animesh.roy.practicecoroutine

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.d("MainActivity", Thread.currentThread().name)
        GlobalScope.launch(Dispatchers.IO) {

            Log.d("MainActivity", "launch -> ${Thread.currentThread().name}")
            val imageUrl = URL("https://developer.android.com/images/kotlin/cards/kotlin-bootcamp.png")

            val httpConnection = imageUrl.openConnection() as HttpURLConnection
            httpConnection.doInput = true
            httpConnection.connect()

            val inputStream = httpConnection.inputStream
            val bitmapImage = BitmapFactory.decodeStream(inputStream)

            runOnUiThread {
                Log.d("MainActivity", "runOnUiThread -> ${Thread.currentThread().name}")
                imageView.setImageBitmap(bitmapImage)
            }
        }

    }
}
