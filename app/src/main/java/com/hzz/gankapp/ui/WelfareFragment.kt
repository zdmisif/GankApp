package com.hzz.gankapp.ui

import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.hzz.gankapp.R
import com.hzz.gankapp.adapter.WelfareAdapter
import com.hzz.gankapp.api.GankApi
import com.hzz.gankapp.base.BaseFragment
import com.hzz.gankapp.bean.GankResult
import kotlinx.android.synthetic.main.fragment_welfare.*
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription
import java.util.*

/**
 * Summary ：用于显示福利数据
 * Created by zhangdm on 2016/3/15.
 */
class WelfareFragment(cs: CompositeSubscription) : BaseFragment(cs) {
    private lateinit var mApi: GankApi
    private lateinit var mAdapter: WelfareAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_welfare
    }

    override fun initControl() {
        mAdapter = WelfareAdapter(this.activity)
        mWelfareRecyclerView.setLayoutManager(StaggeredGridLayoutManager(2
                , StaggeredGridLayoutManager.VERTICAL))
        mWelfareRecyclerView.adapter = mAdapter
        mApi = GankApi(mCompositeSubscription)
        obtainData(1)
    }

    fun obtainData(pageNumber: Int) {
        mApi.getDataTypeInfo(GankApi.WELFARE, pageNumber, object : Action1<List<GankResult>> {
            override fun call(rp: List<GankResult>?) {
                Log.i("msg", rp?.get(0)?.toString())
                mAdapter.replaceAll(rp as ArrayList<GankResult>)
            }
        })
    }
}