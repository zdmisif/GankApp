package com.hzz.gankapp.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hzz.gankapp.ui.TypePagerFragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by ewhale on 2016/3/15.
 */
class TypeFragmentPagerAdapter : FragmentPagerAdapter {
    private lateinit var mContext: Context
    private lateinit var mCompositeSubscription: CompositeSubscription
    private lateinit var mTitles: Array<String>

    constructor(content: Context, fm: FragmentManager
                , cs: CompositeSubscription, titles: Array<String>) : super(fm) {
        mTitles = titles
        mCompositeSubscription = cs
    }

    override fun getItem(p0: Int): Fragment? {
        return TypePagerFragment(mTitles[p0], mCompositeSubscription)
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}