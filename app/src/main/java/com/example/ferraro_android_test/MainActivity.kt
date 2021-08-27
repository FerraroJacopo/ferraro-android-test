package com.example.ferraro_android_test

import android.app.ProgressDialog
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.GridView
import androidx.core.content.ContextCompat
import androidx.room.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import android.R.menu
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var gridView: GridView
    var titles: ArrayList<String> = arrayListOf()
    var images: ArrayList<String> = arrayListOf()
    var flag = 0

    lateinit var mainAdapter: MainAdapter

    lateinit var read_titles: ArrayList<String>
    lateinit var read_images: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle("Ferraro Shopfully Test")

        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setTitle("Loading data")
        progressDialog.setMessage("please wait :)")
        progressDialog.setCanceledOnTouchOutside(false)

        gridView = findViewById(R.id.gridView)

        val url = "https://run.mocky.io/v3/94da1ce3-3d3f-414c-8857-da813df3bb05"
        var background = "https://it-it-media.shopfully.cloud/images/volantini/<id>@3x.jpg"

        progressDialog.show()
        val request = JsonObjectRequest(
            Request.Method.GET, // method
            url, // url
            null, // json request
            { response -> // response listener

                val mainAdapter = MainAdapter(this@MainActivity, titles, images)
                gridView.adapter = mainAdapter

                val jsonArray = response.getJSONArray("data")

                for (i in 0 until jsonArray.length()) {
                    var flyer = jsonArray.getJSONObject(i)
                    var title = flyer.getString("title")
                    var id = flyer.getString("id")

                    titles.add(title)

                    background = background.replace("<id>", id)
                    images.add(background)
                    background = "https://it-it-media.shopfully.cloud/images/volantini/<id>@3x.jpg"
                }

                progressDialog.dismiss()
                flag = 1

            },
            { error -> // error listener
                //
            })

        val queue = Volley.newRequestQueue(this)
        queue.add(request)

    }

    override fun onResume() {
        gridView.invalidateViews()
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        read_images = MyData.read_images
        read_titles = MyData.read_titles

        if (id == R.id.action_one) {

            if(item.getIcon().getConstantState()==getDrawable(R.drawable.not_seen)?.getConstantState()) {
                item.setIcon(R.drawable.seen)

                mainAdapter = MainAdapter(this@MainActivity, read_titles, read_images)
                flag = 0   }

            else {
                item.setIcon(R.drawable.not_seen)
                mainAdapter = MainAdapter(this@MainActivity, titles, images)
                flag = 1 }


            gridView.adapter = mainAdapter

        }

        return super.onOptionsItemSelected(item)

    }


}