package com.hzz.gankapp.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

/**
 * Created by ewhale on 2016/3/15.
 */
class GlideUtil {

    private lateinit var mContext: Context
    private lateinit var mRequestManager: RequestManager

    companion object {
        lateinit var mInstance: GlideUtil
        fun init(context: Context) {
            mInstance = GlideUtil(context)
        }
    }

    constructor(context: Context) {
        mContext = context
        mRequestManager = Glide.with(context)
    }

    fun loadImage(url: String, iv: ImageView) {
        mRequestManager.load(url)
                .dontAnimate()
                .into(iv)
    }
}