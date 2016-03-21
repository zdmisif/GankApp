package com.hzz.gankapp.ui

import android.util.Log
import com.hzz.gankapp.R
import com.hzz.gankapp.api.GankApi
import com.hzz.gankapp.base.BaseFragment
import com.hzz.gankapp.bean.GankResult
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription
import java.util.*

/**
 * Summary ：显示一日的数据
 * Created by zhangdm on 2016/3/14.
 */
class GankDayInfoFragment(cs: CompositeSubscription) : BaseFragment(cs) {

    private lateinit var api: GankApi
    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_day_info
    }

    override fun initControl() {
        api = GankApi(mCompositeSubscription);
        var time = Calendar.getInstance()
        var year = time.get(Calendar.YEAR)
        var month = time.get(Calendar.MONTH) + 1
        var day = time.get(Calendar.DAY_OF_MONTH)

        Log.i("msg", "year=$year,month=$month,day=$day")
        api.getDataDayInfo(year, month, day, object : Action1<HashMap<String, List<GankResult>>> {
            override fun call(rp: HashMap<String, List<GankResult>>?) {
                Log.i("msg", "rp.size=" + rp?.size.toString())
            }
        })

    }

    override fun onStart() {
        super.onStart()
    }
}