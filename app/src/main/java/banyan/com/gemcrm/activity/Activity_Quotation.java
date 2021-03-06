package banyan.com.gemcrm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.Appointment_Adapter;
import banyan.com.gemcrm.adapter.Quotation_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by Jo on 3/26/2017.
 */

public class Activity_Quotation extends AppCompatActivity {

    public static final String TAG_TOTAL = "total";
    public static final String TAG_CREATE_ON = "created_on";
    public static final String TAG_DISCOUNT = "discount";
    public static final String TAG_ENQ_COMPANY_NAME = "enq_company_name";
    public static final String TAG_ENQ_COMP_PHN_NO = "enq_company_phn_no";
    public static final String TAG_ENQ_COMP_ADDRESS = "enq_company_address";
    public static final String TAG_PRODUCT_GROUP = "enq_product_group";
    public static final String TAG_PRODUCT_MODEL = "enq_product_model";
    public static final String TAG_PRODUCT_MODEL_NO = "enq_product_model_no";
    public static final String TAG_PRODUCT_TYPE = "enq_product_model_type";
    public static final String TAG_PRODUCT_QTY = "enq_product_qty";
    public static final String TAG_PRODUCT_PRICE = "enq_product_price";

    String TAG = "reg";

    SpotsDialog dialog;
    public static RequestQueue queue;

    static ArrayList<HashMap<String, String>> Quotation_List;

    HashMap<String, String> params = new HashMap<String, String>();

    public Quotation_Adapter adapter;
    ListView list_quotation;

    String str_quotation_no = "";

    String str_current_year = "";
    String str_current_month = "";

    String str_current_date = "";

    Button btn_send_quote;

    private Toolbar mToolbar;

    TextView txt_quote_id, txt_quote_to, txt_quote_address, txt_quote_phone, txt_quote_amt, txt_quote_date, txt_quote_total_amt, txt_discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isInternetOn();

        list_quotation = (ListView) findViewById(R.id.quotation_lst);

        txt_quote_id = (TextView) findViewById(R.id.order_quotation_id);
        txt_quote_to = (TextView) findViewById(R.id.quotation_comp_name);
        txt_quote_address = (TextView) findViewById(R.id.quotation_comp_address);
        txt_quote_phone = (TextView) findViewById(R.id.quotation_comp_phone);
        txt_quote_amt = (TextView) findViewById(R.id.quotation_amt);
        txt_quote_date = (TextView) findViewById(R.id.quotation_new_date);
        txt_quote_total_amt = (TextView) findViewById(R.id.quotation_total_amt);
        txt_discount = (TextView) findViewById(R.id.quotation_txt_tax);

        btn_send_quote = (Button) findViewById(R.id.quotation_send_invoice);


        /********************************
         *  Load Current Day
         * *********************************/

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy");
        String strDate = mdformat.format(calendar.getTime());

        str_current_year = "" + calendar.get(Calendar.YEAR);
        str_current_month = "" + calendar.get(Calendar.MONTH);
        str_current_date = "" + mdformat;


        String str_date_new = str_current_date + "/" + str_current_month + "/" + str_current_date;

        System.out.println("DATTTEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + strDate);
        System.out.println("DATTTEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + strDate);
        System.out.println("DATTTEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + str_date_new);
        System.out.println("DATTTEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + str_date_new);

        txt_quote_date.setText("" + strDate);

        // Hashmap for ListView
        Quotation_List = new ArrayList<HashMap<String, String>>();

        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            str_quotation_no = sharedPreferences.getString("quotation_no", "quotation_no");
        } catch (Exception e) {

        }

        System.out.println("quotation_no Inside" + str_quotation_no);
        System.out.println("quotation_no Inside" + str_quotation_no);
        System.out.println("quotation_no Inside" + str_quotation_no);
        System.out.println("quotation_no Inside" + str_quotation_no);
        System.out.println("quotation_no Inside" + str_quotation_no);
        System.out.println("quotation_no Inside" + str_quotation_no);

        if (str_quotation_no.equals("")) {

            TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);

        } else {

            try {

                dialog = new SpotsDialog(Activity_Quotation.this);
                dialog.show();
                queue = Volley.newRequestQueue(Activity_Quotation.this);
                Function_GetQuotation();

            } catch (Exception e) {

            }

        }

        btn_send_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    dialog = new SpotsDialog(Activity_Quotation.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Quotation.this);
                    POST_Quotation();

                } catch (Exception e) {

                }

            }
        });

    }

    /********************************
     * User Authentication
     *********************************/

    private void Function_GetQuotation() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_Quotation, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_LOGIN", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);

                    String str_total = obj.getString(TAG_TOTAL);
                    String str_create_on = obj.getString(TAG_CREATE_ON);
                    String str_discoutn = obj.getString(TAG_DISCOUNT);
                    String str_comp_name = obj.getString(TAG_ENQ_COMPANY_NAME);
                    String str_comp_phone = obj.getString(TAG_ENQ_COMP_PHN_NO);
                    String str_comp_address = obj.getString(TAG_ENQ_COMP_ADDRESS);

                    try {
                        txt_quote_id.setText("" + str_current_year + "/" + str_current_month + "/" + str_quotation_no);
                        txt_quote_to.setText("" + str_comp_name);
                        txt_quote_address.setText("" + str_comp_address);
                        txt_quote_phone.setText("" + str_comp_phone);
                        txt_quote_amt.setText("" + str_total);
                        txt_quote_date.setText("" + str_create_on);
                        txt_quote_total_amt.setText("" + str_total);
                        txt_discount.setText("Discount Percent is : " + str_discoutn + "Valid up to Next 30 Days");

                    } catch (Exception e) {

                    }


                    JSONArray arr;
                    arr = obj.getJSONArray("products");

                    for (int i = 0; arr.length() > i; i++) {
                        JSONObject obj1 = arr.getJSONObject(i);

                        String str_product_group = obj1.getString(TAG_PRODUCT_GROUP);
                        String str_product_model = obj1.getString(TAG_PRODUCT_MODEL);
                        String str_product_model_no = obj1.getString(TAG_PRODUCT_MODEL_NO);
                        String str_model_type = obj1.getString(TAG_PRODUCT_TYPE);
                        String str_product_qty = obj1.getString(TAG_PRODUCT_QTY);
                        String str_product_price = obj1.getString(TAG_PRODUCT_PRICE);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_PRODUCT_GROUP, str_product_group);
                        map.put(TAG_PRODUCT_MODEL, str_product_model);
                        map.put(TAG_PRODUCT_MODEL_NO, str_product_model_no);
                        map.put(TAG_PRODUCT_TYPE, str_model_type);
                        map.put(TAG_PRODUCT_QTY, str_product_qty);
                        map.put(TAG_PRODUCT_PRICE, str_product_price);

                        Quotation_List.add(map);

                        System.out.println("HASHMAP ARRAY" + Quotation_List);


                        adapter = new Quotation_Adapter(Activity_Quotation.this,
                                Quotation_List);
                        list_quotation.setAdapter(adapter);

                    }


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("quotation_no", str_quotation_no);

                System.out.println("SEND QUOTE" + str_quotation_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * POST Quotation
     * *************************/
    public void POST_Quotation() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_send_quotation, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Quotation.this)
                                .setTitle("GEM CRM")
                                .setText("Quotation Sent Sucessfully !")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                        try {
                            FunctionAlert();
                        }catch (Exception e) {

                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Quotation.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                    dialog.dismiss();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                dialog.dismiss();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Alerter.create(Activity_Quotation.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", str_quotation_no);

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

    /***********************************
     *  Internet Connection
     * ************************************/

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            new AlertDialog.Builder(Activity_Quotation.this)
                    .setTitle("GEM CRM")
                    .setMessage("Oops no internet !")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                }
                            }).show();
            return false;
        }
        return false;
    }

    private void FunctionAlert() {

        new android.app.AlertDialog.Builder(Activity_Quotation.this)
                .setTitle("GEM CRM")
                .setMessage("Quotation Sent Successfully :)")
                .setIcon(R.mipmap.ic_launcher)

                .setPositiveButton("Done",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();

                                dialog.dismiss();


                            }
                        }).show();
    }

}
