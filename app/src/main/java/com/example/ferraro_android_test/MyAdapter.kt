package com.example.ferraro_android_test

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MainAdapter(
    private val context: Context,
    private val numbersInWords: ArrayList<String>,
    private val numberImage: ArrayList<String>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var read: TextView
    private lateinit var frameLayout : FrameLayout
    override fun getCount(): Int {
        return numbersInWords.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.row_item, null)
        }
        imageView = convertView!!.findViewById(R.id.imageView)
        textView = convertView.findViewById(R.id.textView)
        read = convertView.findViewById(R.id.textVieww)
        read.setVisibility(View.GONE)
        //frameLayout = convertView.findViewById(R.id.layout_practitioner)
        //frameLayout.setForeground(ColorDrawable(ContextCompat.getColor(context, R.color.trans)));
        //imageView.setImageResource(numberImage[position])

        Picasso.get().load(numberImage[position]).into(imageView)
        textView.text = numbersInWords[position]

        if(MyData.read_images.contains(numberImage[position])) {
            imageView.alpha = 0.35f
            textView.alpha = 0.35f
            read.setVisibility(View.VISIBLE)

        }
        else {
            imageView.alpha = 1f
            textView.alpha = 1f
            read.setVisibility(View.GONE)
        }

        imageView.setOnClickListener{view->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("title",numbersInWords[position])
            intent.putExtra("background",numberImage[position])
            context.startActivity(intent)
        }

        convertView

        return convertView
    }
}