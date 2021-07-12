package com.ninetysixgroup.dimsumplace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = intent
        val content = intent.getStringExtra("content")
        val imageContent = intent.getIntExtra("image", 0)
        val imageBody = intent.getIntExtra("imageBody", 0)
    //        val actionbar = supportActionBar
    //        actionbar!!.title = "Let Start"


        image_header.setImageResource(imageContent)
        image_body.setImageResource(imageBody)

//        detailText.setText(content)
//        imageView2.setImageResource(imageContent)
//        imageView.setImageResource(imageContent)
//        popupAds()
    }
}