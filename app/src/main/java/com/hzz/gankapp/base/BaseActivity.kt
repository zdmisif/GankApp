package com.hzz.gankapp.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rx.subscriptions.CompositeSubscription

/**
 * Created by ewhale on 2016/3/11.
 */
open class BaseActivity : AppCompatActivity() {

    var mCompositeSubscription: CompositeSubscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCompositeSubscription = CompositeSubscription()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeSubscription?.unsubscribe()
    }

    //开启activity
    fun start(context: Context) {
        var i = Intent(context, javaClass);
        context.startActivity(i);
    }

    fun start(context: Context, bundle: Bundle) {
        var i = Intent(context, javaClass);
        i.putExtras(bundle);
        context.startActivity(i);
    }
}