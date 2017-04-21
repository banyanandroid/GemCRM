package banyan.com.gemcrm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;
import thebat.lib.validutil.ValidUtils;

/**
 * Created by User on 4/18/2017.
 */

public class Activity_Quotation_Preview extends AppCompatActivity {

    private WebView mWebView;
    ProgressBar progressBar;

    String str_quotaion_id = "";

    Button btn_back, btn_send;

    EditText edt_email1, edt_email2;

    String str_email1, str_email2 = "";

    String TAG = "add task";
    SpotsDialog spot_dialog;
    public static RequestQueue queue;

    ValidUtils validUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_preview);

        validUtils = new ValidUtils();

        mWebView = (WebView) findViewById(R.id.quotation_main_webview);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_quotation);

        btn_back = (Button) findViewById(R.id.btn_quote_preview_back);
        btn_send = (Button) findViewById(R.id.btn_quote_send);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_quotaion_id = sharedPreferences.getString("str_quotation_id_preview", "str_quotation_id_preview");

        try {

            String str_ofm_url = "http://gemservice.in/crm/emp/android_quotation.php?id=" + str_quotaion_id;

            StartWebview(str_ofm_url);

        } catch (Exception e) {

        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_FollowUp.class);
                startActivity(i);
                finish();

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FunctionCAllAlert();
                } catch (Exception e) {

                }

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

    /***************************
     * Function ALert
     * *************************/

    private void FunctionCAllAlert() {

        LayoutInflater li = LayoutInflater.from(Activity_Quotation_Preview.this);
        View promptsView = li
                .inflate(R.layout.alertdialog_quotation_send, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Activity_Quotation_Preview.this);
        alertDialogBuilder.setTitle("GEM CRM");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        // alertDialogBuilder.setInverseBackgroundForced(#26A65B);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        edt_email1 = (EditText) promptsView
                .findViewById(R.id.quotation_pre_email1);
        edt_email2 = (EditText) promptsView
                .findViewById(R.id.quotation_pre_email2);

        alertDialogBuilder.setCancelable(false)

                .setNeutralButton("Done",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                str_email1 = edt_email1.getText().toString();
                                str_email2 = edt_email2.getText().toString();

                                if (str_email1.equals("") && str_email2.equals("")) {
                                    TastyToast.makeText(getApplicationContext(), "Please Enter Email ID", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                                } else if (!validUtils.validateEmail(edt_email1)) {
                                    validUtils.showToast(Activity_Quotation_Preview.this, "Invalid Email on 1st Mail ID");
                                } else if (!str_email2.equals("")) {
                                    if (!validUtils.validateEmail(edt_email2)) {
                                        validUtils.showToast(Activity_Quotation_Preview.this, "Invalid Email on 2nd Mail ID");
                                    }

                                } else {
                                    spot_dialog = new SpotsDialog(Activity_Quotation_Preview.this);
                                    spot_dialog.show();
                                    queue = Volley.newRequestQueue(Activity_Quotation_Preview.this);
                                    Function_SendCatalogue();
                                }

                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    /********************************
     * User Send Catalogue
     *********************************/

    private void Function_SendCatalogue() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_preview_send_quotation, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Quotation_Preview.this)
                                .setTitle("GEM CRM")
                                .setText("Quotation Sent Successfully :)")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else if (success == 0) {

                        Alerter.create(Activity_Quotation_Preview.this)
                                .setTitle("GEM CRM")
                                .setText("Quotation Sent Failed :(")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                    spot_dialog.dismiss();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                spot_dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                spot_dialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", str_quotaion_id);
                params.put("cemail1", str_email1);
                params.put("cemail2", str_email2);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };
        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        // Adding request to request queue
        queue.add(request);
    }

}
