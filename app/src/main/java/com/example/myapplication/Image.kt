package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso

class Image : AppCompatActivity() {

    companion object {
        const val link_ph: String = "link_photo_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val full_Img: ImageView = findViewById(R.id.imageView2)
        var link = intent.getStringExtra(link_ph)

        Picasso.with(this).load(link).into(full_Img);


    }
}
