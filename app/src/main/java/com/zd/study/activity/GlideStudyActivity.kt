package com.zd.study.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zd.study.R

class GlideStudyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_layout)
        val imageView = findViewById<View>(R.id.my_image_view) as ImageView
//        val image = "https://img-blog.csdnimg.cn/20191215043500229.png"
        val image = "https://db.workercn.cn/media/2025/01/01/CD428CD5FF7B4835B1DC8C49AFCD1A9F/thumb.jpg"

        val option:RequestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_RGB_565).priority(Priority.IMMEDIATE)
//        Glide.with(this).setDefaultRequestOptions(option)

        for (i in 0 until 3){
            Glide.with(this).setDefaultRequestOptions(option).load(image).into(imageView)
        }
        val endwith = image.endsWith("29.png")
        Log.d("Zdtest", "endwith    $endwith")


//        val imageUrl = "https://db.workercn.cn/media/2025/01/01/CD428CD5FF7B4835B1DC8C49AFCD1A9F/thumb.jpg"
//
//        Glide.with(this).setDefaultRequestOptions(option).load(imageUrl).into(imageView)

    }
}