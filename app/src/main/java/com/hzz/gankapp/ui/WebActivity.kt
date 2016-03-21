package com.hzz.gankapp.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.hzz.gankapp.R
import com.hzz.gankapp.base.BaseActivity
import com.hzz.gankapp.util.DisplayUtil
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.module_toolbar.*

class WebActivity : BaseActivity() {

    private lateinit var mWebSetting: WebSettings
    private lateinit var mChromeClient: WebChromeClient
    private lateinit var mClient: WebViewClient
    private lateinit var mTitle: String
    private lateinit var mUrl: String
    private var screenWidth = 1

    private var ENCODING_NAME = "UTF-8"

    companion object {
        var KEY_TITLE = "key_title"
        var KEY_URL = "key_url"
        fun open(context: Context, title: String, url: String) {
            var bundle = Bundle()
            bundle.putString(KEY_TITLE, title)
            bundle.putString(KEY_URL, url)
            var i = Intent(context, WebActivity::class.java);
            i.putExtras(bundle);
            context.startActivity(i);
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_web
    }

    override fun initControl() {
        initWebSetting()
        initData()
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = mTitle
        mWebView.loadUrl(mUrl)
    }


    fun initWebSetting() {
        initWebClient()
        initChromeClient()
        mWebSetting = mWebView.settings;
        mWebSetting.javaScriptEnabled = true
        mWebSetting.blockNetworkImage = false
        mWebSetting.defaultTextEncodingName = ENCODING_NAME
        mWebSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebView.setWebViewClient(mClient)
        mWebView.setWebChromeClient(mChromeClient)

    }

    fun initChromeClient() {
        mChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                supportActionBar?.title = title
                Log.i("msg", "title=$title")
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Log.i("msg", "newProgress=$newProgress")
                if (newProgress >= 0 && newProgress < 100) {
                    mWebProgress.visibility = View.VISIBLE
                    var params = mWebProgress.layoutParams
                    params.width = (newProgress / 100f * screenWidth).toInt()
                    mWebProgress.layoutParams = params
                } else {
                    mWebProgress.visibility = View.GONE
                }
            }
        }
    }

    fun initWebClient() {
        mClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
    }

    fun initData() {
        screenWidth = DisplayUtil.getScreenWidth(this)
        var bundle = intent.extras
        mTitle = bundle.getString(KEY_TITLE)
        mUrl = bundle.getString(KEY_URL)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
