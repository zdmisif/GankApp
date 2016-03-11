package com.hzz.gankapp.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hzz.gankapp.R
import com.hzz.gankapp.adapter.GankResultAdapter
import com.hzz.gankapp.api.GankApi
import com.hzz.gankapp.base.BaseActivity
import com.hzz.gankapp.bean.GankResult
import kotlinx.android.synthetic.main.activity_main.*
import rx.functions.Action1
import java.util.*

class MainActivity : BaseActivity() {
    var mAdapter: GankResultAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var api = GankApi(mCompositeSubscription!!);
        mAdapter = GankResultAdapter(this)
        mGankResultRecyclerView.setHasFixedSize(true)
        mGankResultRecyclerView.layoutManager = LinearLayoutManager(this)
        mGankResultRecyclerView.adapter = mAdapter;

        api.getAndroidInfo(2, object : Action1<List<GankResult>> {
            override fun call(p0: List<GankResult>?) {
                Log.i("msg", p0?.size.toString())
                Log.i("msg", p0?.get(0).toString())
                mAdapter?.replaceAll(p0 as ArrayList<GankResult>)
            }
        })
    }
}
