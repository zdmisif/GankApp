package com.hzz.gankapp.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import rx.subscriptions.CompositeSubscription

/**
 * Created by ewhale on 2016/3/11.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mCompositeSubscription: CompositeSubscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mCompositeSubscription = CompositeSubscription()
        initControl()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeSubscription?.unsubscribe()
    }

    open fun initControl() {

    }

    abstract fun getLayoutId(): Int

    fun replaceFragment(containerViewId: Int, fragment: Fragment, tag: String) {
        var isAdd = true
        var temp = supportFragmentManager.findFragmentByTag(tag)
        if (temp == null) {
            temp = fragment
            isAdd = false
        }
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerViewId, temp, tag)
        if (!isAdd) {
            transaction.addToBackStack(tag)
        }
        transaction.commitAllowingStateLoss()
    }
}