package com.sakib.googlenews.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.sakib.googlenews.R;
import com.sakib.googlenews.webview.WebViewClientImpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView webView = null;
    private Toolbar toolbar;
    private  TextView mTitle;
    private EditText weburl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent i = getIntent();
        String url = i.getStringExtra("url");

        initViews();

       mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(url);

        weburl.setText(webView.getUrl());

    }

    void initViews()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.webView = (WebView) findViewById(R.id.webview);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_text);
        weburl = (EditText) toolbar.findViewById(R.id.weburl);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}