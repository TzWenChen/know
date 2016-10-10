package com.knowmemo.usermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by TingEn on 2016/10/11.
 */
public class SearchByhandActivity extends Activity {
    WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbyhand);

        mWebView = (WebView)findViewById(R.id.webView);

        mWebView.loadUrl("https://tw.dictionary.yahoo.com/");

        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }

        });

    }
}
