package com.hzz.gankapp.ui

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hzz.gankapp.R
import com.hzz.gankapp.adapter.GankResultAdapter
import com.hzz.gankapp.api.GankApi
import com.hzz.gankapp.base.BaseFragment
import com.hzz.gankapp.bean.GankResult
import kotlinx.android.synthetic.main.fragment_type_pager.*
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription
import java.util.*

/**
 * Created by ewhale on 2016/3/15.
 */
class TypePagerFragment : BaseFragment {
    private lateinit var mApi: GankApi
    private lateinit var mAdapter: GankResultAdapter
    private lateinit var mType: String

    constructor(type: String, cs: CompositeSubscription) : super(cs) {
        mType = type
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_type_pager
    }

    override fun initControl() {
        mAdapter = GankResultAdapter(this.activity)
        mGankResultRecyclerView.setHasFixedSize(true)
        mGankResultRecyclerView.layoutManager = LinearLayoutManager(this.activity)
        mGankResultRecyclerView.adapter = mAdapter;
        mApi = GankApi(mCompositeSubscription)
        obtainData(1)
    }

    fun obtainData(pageNumber: Int) {
        mApi.getDataTypeInfo(mType, pageNumber, object : Action1<List<GankResult>> {
            override fun call(p0: List<GankResult>?) {
                Log.i("msg", p0?.size.toString())
                Log.i("msg", p0?.get(0).toString())
                mAdapter.replaceAll(p0 as ArrayList<GankResult>)
            }
        })
    }
}