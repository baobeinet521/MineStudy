package com.zd.study.activity

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zd.study.R

/**
 * 布局优化测试
 */
class LayoutOptActivity: AppCompatActivity() {
    var mShowErrorView: Button? = null
    var mHideErrorView: Button? = null
    var mErrorView: ViewStub? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_opt)
        mShowErrorView = findViewById(R.id.show_error_view_btn)
        mHideErrorView = findViewById(R.id.hide_error_view_btn)
        mErrorView = findViewById(R.id.error_view)
        mErrorView?.inflate()
        mShowErrorView?.setOnClickListener {
            mErrorView?.visibility = View.VISIBLE
        }
        mHideErrorView?.setOnClickListener {
            mErrorView?.visibility = View.GONE
        }
    }
}