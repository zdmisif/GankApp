package com.hzz.gankapp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Summary ：Fragment的基础类
 * Created by zhangdm on 2016/3/14.
 */
abstract class BaseFragment : Fragment() {
    var rootView: View? = null
    open abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutId(), null)
        }
        var parent: ViewGroup? = rootView?.parent as ViewGroup
        parent?.removeView(rootView)
        return rootView
    }
}