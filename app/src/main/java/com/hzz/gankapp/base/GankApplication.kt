package com.hzz.gankapp.base

import android.app.Application
import com.hzz.gankapp.ui.GlideUtil

/**
 * Created by ewhale on 2016/3/15.
 */
class GankApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlideUtil.init(this)
    }
}