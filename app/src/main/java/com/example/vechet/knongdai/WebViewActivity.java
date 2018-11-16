package com.example.vechet.knongdai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.entity.QuerySearchId;
import com.example.vechet.knongdai.entity.SubCateDetailId;

public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "Webview";
    private WebView webView;
    private TextView tvWebViewActionBarTitle;
    private Toolbar toolbar;
    private ImageView ivWebViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        initView();
        setupWebView();
        initEvent();
        //size 24
    }

    private void setupWebView() {
        SubCateDetailId subCateDetailId = (SubCateDetailId)getIntent().getSerializableExtra("subCateDetailId");
        QuerySearchId querySearchId = (QuerySearchId)getIntent().getSerializableExtra("querySearchId");
        if(subCateDetailId != null) {
            tvWebViewActionBarTitle.setText(subCateDetailId.getSubCateDetailTitle());
            webView.loadUrl(subCateDetailId.getSubCateDetailLink());
        }else if(querySearchId != null) {
            tvWebViewActionBarTitle.setText(querySearchId.getQuerySearchTitle());
            Log.e(TAG, "onQuerySearchCreate: " + querySearchId.getQuerySearchTitle() );
            webView.loadUrl(querySearchId.getQuerySerachLink());
        }

    }

    private void initEvent() {
        ivWebViewBack.setOnClickListener(v->{
            Intent in = new Intent(WebViewActivity.this, SubCateDetailActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        });
    }

    private void initView() {
        tvWebViewActionBarTitle = findViewById(R.id.tvWebViewActionBarTitle);
        toolbar = findViewById(R.id.webViewToolbar);
        ivWebViewBack = findViewById(R.id.ivWebViewBack);
        webView = findViewById(R.id.webView);

        //Enable JavaScript for watching videos
        webView.getSettings().setJavaScriptEnabled(true);

        //You are missing a couple of setup calls that make WebView
        // to render more like a mobile browser:
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        //Optionally, you can also enable pinch-zooming:
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
    }
}
