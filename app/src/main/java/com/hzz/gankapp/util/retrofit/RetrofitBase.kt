package com.hzz.gankapp.util.retrofit

import android.util.Log
import com.hzz.gankapp.bean.Response
import okhttp3.OkHttpClient
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.net.SocketTimeoutException

/**
 * Created by ewhale on 2016/3/11.
 */
open class RetrofitBase {
    var TAG = "RetrofitBase";
    var retrofit: Retrofit? = null
    var mCompositeSubscription: CompositeSubscription? = null

    constructor() {
        var client = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(getServiceUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    fun <T> deploy(o: Observable<Response<T>>, action: Action1<T>) {
        var sub = o
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { flatMapForResponse(it) }
                .subscribe(subscriber(action))
        mCompositeSubscription?.add(sub)
    }

    fun <T> subscriber(action: Action1<T>): Subscriber<T> {
        return object : Subscriber<T>() {
            override fun onNext(p0: T) {
                if (!mCompositeSubscription!!.isUnsubscribed) {
                    action.call(p0)
                }
            }

            override fun onError(p0: Throwable?) {
                if (p0 is SocketTimeoutException) {
                    Log.i(TAG, "连接超时")
                } else {
                    p0?.printStackTrace()
                }
            }

            override fun onCompleted() {
            }
        }
    }

    fun <T> flatMapForResponse(response: Response<T>): Observable<T> {
        return Observable.create(Observable.OnSubscribe<T> {
            if (response.error) {
                it.onError(Throwable("Api Error"))
            } else {
                it.onNext(response.results)
            }
            it.onCompleted()
        })
    }

    open fun getServiceUrl(): String {
        return ""
    }
}