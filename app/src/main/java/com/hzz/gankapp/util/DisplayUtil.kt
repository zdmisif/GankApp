package com.hzz.gankapp.util

import android.app.Activity
import android.util.DisplayMetrics

/**
 * Created by ewhale on 2016/3/21.
 */
class DisplayUtil {
    companion object {
        fun getScreenWidth(activity: Activity): Int {
            var dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }
}