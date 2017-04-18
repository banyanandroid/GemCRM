package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import banyan.com.gemcrm.R;

/**
 * Created by User on 4/18/2017.
 */

public class Activity_OFM_Preview extends AppCompatActivity {

    private WebView mWebView;
    ProgressBar progressBar;

    String str_ofm_id = "";

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofm_preview);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        btn_back = (Button) findViewById(R.id.btn_ofm_preview_back);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_ofm_id = sharedPreferences.getString("str_select_ofm", "str_select_ofm");

        try {

            String str_ofm_url = "http://gemservice.in/crm/emp/android_ofm_view.php?ofms_id=" + str_ofm_id;

            StartWebview(str_ofm_url);

        } catch (Exception e) {

        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Enquiry_Completed_Description.class);
                startActivity(i);
                finish();

            }
        });

    }

    /*****************************************
     * WEBVIEW
     **************************************/

    private void StartWebview(String str_url) {

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(str_url);
        mWebView.setWebViewClient(new HelloWebViewClient());
    }

    private class HelloWebViewClient extends WebViewClient {

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(view.GONE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
            progressBar.setVisibility(view.GONE);
            loadError();
        }

        private void loadError() {
            String html = "<html><body><table width=\"100%\" height=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
                    + "<tr>"
                    + "<td><div align=\"center\"><font color=\"#606060\" size=\"10pt\">No Internet Try Again Later!</font></div></td>"
                    + "</tr>" + "</table><html><body>";
            System.out.println("html " + html);

            String base64 = android.util.Base64.encodeToString(html.getBytes(),
                    android.util.Base64.DEFAULT);
            mWebView.loadData(base64, "text/html; charset=utf-8", "base64");
            System.out.println("loaded html");
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //if back key is pressed
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

}
