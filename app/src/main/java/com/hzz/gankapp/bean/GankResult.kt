package com.hzz.gankapp.bean

/**
 * Created by ewhale on 2016/3/11.
 */
class GankResult {
    var _id = ""
    var _ns = ""
    var createdAt = ""
    var desc = ""
    var source = ""
    var type = ""
    var url = ""
    var used = false
    var who = ""

    override fun toString(): String {
        return "GankResult(_id='$_id', _ns='$_ns', createdAt='$createdAt', desc='$desc', source='$source', type='$type', url='$url', used=$used, who='$who')"
    }

}