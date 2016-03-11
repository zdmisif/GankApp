package com.hzz.gankapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hzz.gankapp.R
import com.hzz.gankapp.bean.GankResult
import java.util.*

/**
 * Created by ewhale on 2016/3/11.
 */
class GankResultAdapter : RecyclerView.Adapter<GankResultAdapter.GankResultHolder> {

    var mGankResults: ArrayList<GankResult>
    var mContext: Context? = null

    constructor(context: Context) : super() {
        mContext = context
        mGankResults = ArrayList<GankResult>()
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): GankResultHolder? {
        var v = LayoutInflater.from(mContext).inflate(R.layout.item_gank_result, p0, false)
        return GankResultHolder(v)
    }

    override fun onBindViewHolder(item: GankResultHolder?, index: Int) {
        item?.mContent?.text = mGankResults.get(index).desc
    }

    override fun getItemCount(): Int {
        return mGankResults.size
    }

    fun replaceAll(list: ArrayList<GankResult>) {
        mGankResults.clear()
        mGankResults.addAll(list)
        notifyDataSetChanged()
    }

    class GankResultHolder : RecyclerView.ViewHolder {
        var mContent: TextView? = null

        constructor(itemView: View) : super(itemView) {
            mContent = itemView.findViewById(R.id.content) as TextView
        }
    }
}