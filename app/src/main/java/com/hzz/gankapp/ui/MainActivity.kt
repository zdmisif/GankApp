package com.hzz.gankapp.ui

import android.view.Gravity
import android.view.MenuItem
import com.hzz.gankapp.R
import com.hzz.gankapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.module_toolbar.*

class MainActivity : BaseActivity() {
    var TAG_GANK_DAY_INFO = "tag_gank_day_info"
    var TAG_GANK_TYPE_INFO = "tag_gank_type_info"
    lateinit var mGankDayInfoFragment: GankDayInfoFragment
    lateinit var mGankTypeInfoFragment: GankTypeInfoFragment

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initControl() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawer_day -> {
                    replaceFragment(R.id.container, mGankDayInfoFragment, TAG_GANK_DAY_INFO)
                }
                R.id.drawer_type -> {
                    replaceFragment(R.id.container, mGankTypeInfoFragment, TAG_GANK_TYPE_INFO)
                }
            }
            mDrawerLayout.closeDrawers()
            true
        }
        mGankDayInfoFragment = GankDayInfoFragment(mCompositeSubscription)
        mGankTypeInfoFragment = GankTypeInfoFragment(mCompositeSubscription)
        replaceFragment(R.id.container, mGankDayInfoFragment, TAG_GANK_DAY_INFO)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            mDrawerLayout.openDrawer(Gravity.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
    }
}
