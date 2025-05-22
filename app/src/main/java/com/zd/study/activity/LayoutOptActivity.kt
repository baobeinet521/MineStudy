package com.zd.study.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStub
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.zd.study.R

/**
 * 布局优化测试
 */
class LayoutOptActivity: AppCompatActivity() {
    var mShowErrorView: Button? = null
    var mHideErrorView: Button? = null
    var mErrorView: ViewStub? = null
    var mViewStubIsInflate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_opt)
        mShowErrorView = findViewById(R.id.show_error_view_btn)
        mErrorView = findViewById(R.id.error_view)
        mErrorView?.setOnInflateListener { stub, inflated ->
            mViewStubIsInflate = true
        }
        inflateViewStub()
        mHideErrorView = findViewById(R.id.hide_error_view_btn)
        Log.d("viewstub","ceshi")
        mErrorView?.visibility = View.GONE
        mShowErrorView?.setOnClickListener {
            mErrorView?.visibility = View.VISIBLE
        }
        mHideErrorView?.setOnClickListener {
            mErrorView?.visibility = View.GONE
        }
    }

    private fun inflateViewStub(){
        Log.d("viewstub","inflateViewStub  $mViewStubIsInflate")
        if(!mViewStubIsInflate){
            mErrorView?.inflate()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}