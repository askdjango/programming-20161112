package kr.festi.programming20161112;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NaverWebtoonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_webtoon_detail);

        Webtoon webtoon = getIntent().getParcelableExtra("webtoon");

        // String webtoonUrl = getIntent().getStringExtra("webtoonUrl");

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.loadUrl(webtoon.pageUrl);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        /*
        // DEPRECATED
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
        */
    }
}
