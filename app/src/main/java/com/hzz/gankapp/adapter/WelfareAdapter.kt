package com.hzz.gankapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hzz.gankapp.R
import com.hzz.gankapp.bean.GankResult
import com.hzz.gankapp.ui.GlideUtil
import kotlinx.android.synthetic.main.item_welfare.*
import java.util.*

/**
 * Summary ：瀑布流显示图片
 * Created by zhangdm on 2016/3/15.
 */
class WelfareAdapter : RecyclerView.Adapter<WelfareAdapter.WelfareHolder> {

    lateinit var mWelfares: ArrayList<GankResult>
    lateinit var mContext: Context

    constructor(context: Context) : super() {
        mContext = context
        mWelfares = ArrayList<GankResult>()
    }

    override fun onBindViewHolder(item: WelfareHolder?, index: Int) {
        var imgUrl = mWelfares.get(index).url
        GlideUtil.mInstance.loadImage(imgUrl, item!!.mWelfareImg)
    }

    override fun getItemCount(): Int {
        return mWelfares.size
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): WelfareHolder? {
        var v = LayoutInflater.from(mContext).inflate(R.layout.item_welfare, p0, false)
        return WelfareHolder(v)
    }

    fun replaceAll(list: ArrayList<GankResult>) {
        mWelfares.clear()
        mWelfares.addAll(list)
        notifyDataSetChanged()
    }

    class WelfareHolder : RecyclerView.ViewHolder {
        var mWelfareImg: ImageView

        constructor(itemView: View) : super(itemView) {
            mWelfareImg = itemView.findViewById(R.id.welfare_img) as ImageView
        }

    }
}