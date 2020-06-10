package com.sakib.googlenews.webview;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientImpl extends WebViewClient {

    private Activity activity = null;

    public WebViewClientImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {

        if (url != null && url.startsWith("http")) {
            webView.loadUrl(url);
            return true;
        } else {
            return false;
        }
    }


}