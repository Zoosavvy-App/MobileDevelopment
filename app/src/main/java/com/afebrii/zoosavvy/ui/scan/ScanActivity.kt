package com.afebrii.zoosavvy.ui.scan

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Gallery
import android.widget.ImageView
import com.afebrii.zoosavvy.R

class ScanActivity : AppCompatActivity() {

    lateinit var galleryButton:Button
    lateinit var cameraButton: Button
    lateinit var imageView: ImageView
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)


        galleryButton = findViewById(R.id.galleryButton)


    }
}