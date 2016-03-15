package com.hzz.gankapp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rx.subscriptions.CompositeSubscription

/**
 * Summary ：Fragment的基础类
 * Created by zhangdm on 2016/3/14.
 */
abstract class BaseFragment : Fragment {
    var rootView: View? = null
    var isInitialize = false
    open abstract fun getLayoutId(): Int
    lateinit var mCompositeSubscription: CompositeSubscription

    constructor(compositeSubscription: CompositeSubscription) : super() {
        mCompositeSubscription = compositeSubscription
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(getLayoutId(), null)
        }
        var parent = rootView!!.parent
        if (parent != null) {
            parent as ViewGroup
            parent.removeView(rootView)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isInitialize) {
            initControl()
            isInitialize = true
        }
    }

    open fun initControl() {

    }
}