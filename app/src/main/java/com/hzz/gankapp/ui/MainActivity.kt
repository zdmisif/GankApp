package com.hzz.gankapp.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.hzz.gankapp.R
import com.hzz.gankapp.adapter.GankResultAdapter
import com.hzz.gankapp.api.GankApi
import com.hzz.gankapp.base.BaseActivity
import com.hzz.gankapp.bean.GankResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import rx.functions.Action1
import java.util.*

class MainActivity : BaseActivity() {
    var mAdapter: GankResultAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //初始化顶部栏
        initToolbar()

        var api = GankApi(mCompositeSubscription!!);
        mAdapter = GankResultAdapter(this)
        mGankResultRecyclerView.setHasFixedSize(true)
        mGankResultRecyclerView.layoutManager = LinearLayoutManager(this)
        mGankResultRecyclerView.adapter = mAdapter;

        api.getDataDayInfo(2016, 3, 14, object : Action1<HashMap<String, List<GankResult>>> {
            override fun call(rp: HashMap<String, List<GankResult>>?) {
                Log.i("msg", "rp.size=" + rp?.size.toString())
            }

        })

        api.getAndroidInfo(2, object : Action1<List<GankResult>> {
            override fun call(p0: List<GankResult>?) {
                Log.i("msg", p0?.size.toString())
                Log.i("msg", p0?.get(0).toString())
                mAdapter?.replaceAll(p0 as ArrayList<GankResult>)
            }
        })
    }

    fun initToolbar() {
        var toolbar = findViewById(R.id.mToolbar) as Toolbar
        setSupportActionBar(toolbar)
        var actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {

        }
        return super.onOptionsItemSelected(item)
    }
}
