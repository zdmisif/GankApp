package com.hzz.gankapp.bean

/**
 * Created by ewhale on 2016/3/11.
 */
class Response<T> {
    var error: Boolean = true
    var results: T? = null
    var category: List<String>? = null
}