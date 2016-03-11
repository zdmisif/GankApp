package com.hzz.gankapp.api

import com.hzz.gankapp.bean.GankResult
import com.hzz.gankapp.bean.Response
import com.hzz.gankapp.util.retrofit.RetrofitBase
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by ewhale on 2016/3/11.
 */
class GankApi : RetrofitBase {
    companion object {
        var ANDROID = "Android"
        var WELFARE = "福利"
        var IOS = "iOS"
        var VIDEO = "休息视频"
        var expanding = "拓展资源"
        var FRONT = "前端"
        var ALL = "all"
    }

    var gankService: GankService? = null

    constructor(cs: CompositeSubscription) : super() {
        mCompositeSubscription = cs
        gankService = retrofit!!.create(GankService::class.java)
    }


    fun getAndroidInfo(pageNumber: Int, action: Action1<List<GankResult>>) {
        getDataTypeInfo(ANDROID, pageNumber, action)
    }

    fun getDataTypeInfo(dataType: String, pageNumber: Int, action: Action1<List<GankResult>>) {
        deploy(gankService!!.getGankData(dataType, 10, pageNumber), action)
    }



    override fun getServiceUrl(): String {
        return "http://gank.io/"
    }


}