package com.wxt.webapp.controls;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MWebView extends WebView {
    private Activity mActivity;

    public MWebView(Context context) {
        super(context);
        init();
    }

    public MWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 设置要选择图片的activity
     *
     * @param activity
     */
    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSettings = getSettings(); // 设置
        // 开启JavaScript支持
        webSettings.setJavaScriptEnabled(true);
        // 让Webivew支持<meta>标签的viewport属性
        webSettings.setUseWideViewPort(true);
        // 是否启动概述模式浏览界面，当页面宽度超过WebView显示宽度时，缩小页面适应WebView。默认false
        webSettings.setLoadWithOverviewMode(true);
        /**
         * 基于WebView导航的类型使用缓存：正常页面加载会加载缓存并按需判断内容是否需要重新验证。
         * 如果是页面返回，页面内容不会重新加载，直接从缓存中恢复。setCacheMode允许客户端根据指定的模式来
         * 使用缓存。
         * LOAD_DEFAULT 默认加载方式
         * LOAD_CACHE_ELSE_NETWORK 按网络情况使用缓存
         * LOAD_NO_CACHE 不使用缓存
         * LOAD_CACHE_ONLY 只使用缓存
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 是否存储页面DOM结构，默认false。
        webSettings.setDomStorageEnabled(true);
        // 是否允许访问WebView内部文件，默认true
        webSettings.setAllowFileAccess(true);
        // 是否允许获取WebView的内容URL ，可以让WebView访问ContentPrivider存储的内容。 默认true
        webSettings.setAllowContentAccess(true);
        // 设置页面的编码格式，默认UTF-8
        webSettings.setDefaultTextEncodingName("utf-8");
        // 是否允许运行在一个URL环境中的JavaScript访问来自其他URL环境的内容
        webSettings.setAllowFileAccessFromFileURLs(false);
        // 是否允许运行在一个file schema URL环境下的JavaScript访问来自其他任何来源的内容，包括其他file schema URLs
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        /**
         *  Webview在安卓5.0之前默认允许其加载混合网络协议内容
         *  在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            //测试打开
            WebView.setWebContentsDebuggingEnabled(true);
        }


        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //判断url是否以http或https开头，是则不消费事件
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

        });
    }
}