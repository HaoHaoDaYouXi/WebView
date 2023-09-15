package com.wxt.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.wxt.webapp.controls.MWebView;

public class MainActivity extends AppCompatActivity {
    private MWebView mWebView; // webView 控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置页面全屏，隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 获取控件
        mWebView = (MWebView) findViewById(R.id.mWebView);
        mWebView.setActivity(this);

        String url;
        // 设置url地址
        url = "http://www.baidu.com";
        // 加载url
        mWebView.loadUrl(url);
    }

    /*
     * 接管返回键
     */
    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}