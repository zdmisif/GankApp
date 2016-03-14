package com.hzz.gankapp.api

import com.hzz.gankapp.bean.GankResult
import com.hzz.gankapp.bean.Response
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import java.util.*

/**
 * 接口的参数和地址
 */
interface GankService {
    @GET("api/data/{data_type}/{page_size}/{page_number}")
    fun getGankDataByType(@Path("data_type") dataType: String, @Path("page_size") pageSize: Int
                          , @Path("page_number") pageNumber: Int): Observable<Response<List<GankResult>>>

    @GET("api/day/{year}/{month}/{day}")
    fun getGanDataByTime(@Path("year") year: Int, @Path("month") month: Int
                         , @Path("day") day: Int): Observable<Response<HashMap<String, List<GankResult>>>>

}