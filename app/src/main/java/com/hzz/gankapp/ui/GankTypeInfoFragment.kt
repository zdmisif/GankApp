package com.hzz.gankapp.ui

import android.support.design.widget.TabLayout
import com.hzz.gankapp.R
import com.hzz.gankapp.adapter.TypeFragmentPagerAdapter
import com.hzz.gankapp.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_gank_type_info.*
import rx.subscriptions.CompositeSubscription

/**
 * Summary ：按类型显示数据
 * Created by zhangdm on 2016/3/14.
 */
class GankTypeInfoFragment(cs: CompositeSubscription) : BaseFragment(cs) {

    private lateinit var mAdapter: TypeFragmentPagerAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_type_info
    }

    override fun initControl() {
        var type = resources.getStringArray(R.array.data_type_array)
        mAdapter = TypeFragmentPagerAdapter(activity, activity.supportFragmentManager
                , mCompositeSubscription, type)
        mViewPager.adapter = mAdapter
        mTypeLayout.setupWithViewPager(mViewPager)
        mTypeLayout.tabMode = TabLayout.MODE_SCROLLABLE

    }
}