package com.example.ferraro_android_test

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    lateinit var text : TextView
    lateinit var image : ImageView

    lateinit var title : String
    lateinit var background : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle("Ferraro Shopfully Test")

        text = findViewById(R.id.textView2)
        image = findViewById(R.id.imageView4)

        title= intent.getStringExtra("title").toString()
        background= intent.getStringExtra("background").toString()

        if(!MyData.read_images.contains(background)) {
            MyData.read_titles.add(title)
            MyData.read_images.add(background) }

        text.text=title
        Picasso.get().load(background).into(image);


    }
}