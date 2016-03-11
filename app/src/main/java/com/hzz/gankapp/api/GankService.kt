package com.hzz.gankapp.api

import com.hzz.gankapp.bean.GankResult
import com.hzz.gankapp.bean.Response
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by ewhale on 2016/3/11.
 */
public interface GankService {
    @GET("api/data/{data_type}/{page_size}/{page_number}")
    fun getGankData(@Path("data_type") dataType: String, @Path("page_size") pageSize: Int
                    , @Path("page_number") pageNumber: Int): Observable<Response<List<GankResult>>>
}