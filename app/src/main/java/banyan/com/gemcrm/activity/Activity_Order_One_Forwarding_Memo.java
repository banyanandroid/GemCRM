package banyan.com.gemcrm.activity;

import android.app.DatePickerDialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;


/**
 * Created by steve on 26/3/17.
 */

public class Activity_Order_One_Forwarding_Memo extends AppCompatActivity {

    EditText edt_order_q_date, edt_order_po_ref, edt_order_po_date, edt_order_sapcode;

    RadioGroup RGroup_price_list, RGroup_To;


    RadioButton ratio_order_master_date, ratio_order_oem, ratio_order_cbe, ratio_order_mumbai, ratio_order_gom;

    Button btn_order_next, btn_order_previous;

    String str_txt_q_ref, str_txt_q_date,
            str_txt_po_ref, str_txt_po_date, str_txt_sapcode, str_order_from = "";

    String str_price_list = "null";
    String str_send_to = "null";

    Spinner spn_order_from, spn_order_q_ref;

    String str_enq_no = "";
    ArrayList<String> Arraylist_quotation_no = null;
    String str_Selected_quotation_no = "";


    SpotsDialog dialog;

    int from_year, from_month, from_date;

    public static final String TAG_QUOTATION_NO = "quotation_no";
    public static final String TAG_QUOTATION_PRICE = "enq_product_price";
    public static final String TAG_QUOTATION_DATE = "created_on";

    public static RequestQueue queue;
    String TAG = " ";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_one_forwarding_memo);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isInternetOn();

        btn_order_next = (Button) findViewById(R.id.order_forwarding_memo_next);
        btn_order_previous = (Button) findViewById(R.id.order_forwarding_memo_previous);

        spn_order_from = (Spinner) findViewById(R.id.form1_from_spn);

        spn_order_q_ref = (Spinner) findViewById(R.id.order_forwarding_memo_q_ref);
        edt_order_q_date = (EditText) findViewById(R.id.order_forwarding_memo_q_date);
        edt_order_po_ref = (EditText) findViewById(R.id.order_forwarding_memo_po_ref);
        edt_order_po_date = (EditText) findViewById(R.id.order_forwarding_memo_po_date);
        edt_order_sapcode = (EditText) findViewById(R.id.order_forwarding_memo_sap);

        RGroup_price_list = (RadioGroup) findViewById(R.id.rg_ofm_price_group);
        ratio_order_master_date = (RadioButton) findViewById(R.id.order_forwarding_memo_master_date_ratio);
        ratio_order_oem = (RadioButton) findViewById(R.id.order_forwarding_memo_oem_ratio);

        RGroup_To = (RadioGroup) findViewById(R.id.rg_ofm_to);
        ratio_order_cbe = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_cbe);
        ratio_order_mumbai = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_mumbai);
        ratio_order_gom = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_gom);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_enq_no = sharedPreferences.getString("ofm_enq_no", "ofm_enq_no");

        Arraylist_quotation_no = new ArrayList<String>();

        edt_order_po_date.setKeyListener(null);

        edt_order_po_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_One_Forwarding_Memo.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                int month = monthOfYear + 1;
                                String formattedMonth = "" + month;
                                String formattedDayOfMonth = "" + dayOfMonth;

                                if (month < 10) {

                                    formattedMonth = "0" + month;
                                }
                                if (dayOfMonth < 10) {

                                    formattedDayOfMonth = "0" + dayOfMonth;
                                }
                                edt_order_po_date.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });


        RGroup_price_list.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected
                if (checkedId == R.id.order_forwarding_memo_master_date_ratio) {

                    str_price_list = "As per price master date";

                } else if (checkedId == R.id.order_forwarding_memo_oem_ratio) {

                    str_price_list = "As per OEM price list";

                } else {

                    str_price_list = "null";
                }

            }


        });

        RGroup_To.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if (checkedId == R.id.order_forwarding_memo_gem_cbe) {

                    str_send_to = "GEM EQUIPMENT(P) LTD,COIMBATORE";

                } else if (checkedId == R.id.order_forwarding_memo_gem_mumbai) {

                    str_send_to = "GEM EQUIPMENTS(P) LTD,MUMBAI";


                } else if (checkedId == R.id.order_forwarding_memo_gem_gom) {

                    str_send_to = "GOM";

                } else {

                    str_send_to = "null";
                }
            }

        });


        btn_order_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_order_from = String.valueOf(spn_order_from.getSelectedItem());

                str_txt_q_date = edt_order_q_date.getText().toString();
                str_txt_po_ref = edt_order_po_ref.getText().toString();
                str_txt_po_date = edt_order_po_date.getText().toString();
                str_txt_sapcode = edt_order_sapcode.getText().toString();

                if (str_price_list.equals("null")) {

                    /*ratio_order_master_date.setError("Please Select any One");*/
                    TastyToast.makeText(getApplicationContext(), "Price Type is Not Selected", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_send_to.equals("null")) {

                   /* ratio_order_cbe.setError("Please Select any One");*/
                    TastyToast.makeText(getApplicationContext(), "To is Not Selected", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_po_ref.equals("")) {

                    edt_order_po_ref.setError("Please Enter PO_Ref");
                    TastyToast.makeText(getApplicationContext(), "PO_Ref is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_po_date.equals("")) {

                    edt_order_po_date.setError("Please Enter PO-date");
                    TastyToast.makeText(getApplicationContext(), "PO-date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_sapcode.equals(" ")) {

                    edt_order_sapcode.setError("Please enter Sapcode");
                    TastyToast.makeText(getApplicationContext(), "Sapcode is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //Radio button value strings
                    editor.putString("str_price_list", str_price_list);
                    editor.putString("str_send_to", str_send_to);


                    //edit text value strings
                    editor.putString("str_txt_q_ref", str_Selected_quotation_no);
                    editor.putString("str_txt_q_date", str_txt_q_date);
                    editor.putString("str_txt_po_ref", str_txt_po_ref);
                    editor.putString("str_txt_po_date", str_txt_po_date);
                    editor.putString("str_txt_sapcode", str_txt_sapcode);
                    editor.putString("str_order_from", str_order_from);


                    editor.commit();


                    Intent i = new Intent(getApplicationContext(), Activity_Order_Two_Customerdetails.class);
                    startActivity(i);
                    finish();


                }


            }

        });

        try {

            dialog = new SpotsDialog(Activity_Order_One_Forwarding_Memo.this);
            dialog.show();
            queue = Volley.newRequestQueue(getApplicationContext());
            GetEnq_No();

        } catch (Exception e) {
            // TODO: handle exception
        }

        /*********************************************
         *  Spinner Get Enq_Quotation Number
         * ********************************************/

        spn_order_q_ref.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                String str_quo = Arraylist_quotation_no.get(arg2);

                String[] separated = str_quo.split(":");
                str_Selected_quotation_no = separated[0];
                String str_quote_date = separated[1];
                String str_quote_date111111 = separated[2];
                String str_quote_date222222 = separated[3];

                edt_order_q_date.setText("" + str_quote_date222222);

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(Activity_Order_One_Forwarding_Memo.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("ofm_quotation_no", str_Selected_quotation_no);

                editor.commit();

                System.out.println("QUOTATION NUMBER : " + str_Selected_quotation_no);
                System.out.println("QUOTATION NUMBER : " + str_Selected_quotation_no);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        btn_order_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Enquiry_Completed_Description.class);
                startActivity(i);
                finish();


            }
        });


    }

    /**********************************
     * Main Menu
     *********************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_ofm, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStateme
        if (id == R.id.action_refresh) {


            // GET And Set

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


            str_txt_q_date = sharedPreferences.getString("str_txt_q_date", "str_txt_q_date");
            str_txt_po_ref = sharedPreferences.getString("str_txt_po_ref", "str_txt_po_ref");
            str_txt_po_date = sharedPreferences.getString("str_txt_po_date", "str_txt_po_date");
            str_txt_sapcode = sharedPreferences.getString("str_txt_sapcode", "str_txt_sapcode");
            str_order_from = sharedPreferences.getString("str_order_from", "str_order_from");

            try {

                edt_order_q_date.setText(str_txt_q_date);
                edt_order_po_ref.setText(str_txt_po_ref);
                edt_order_po_date.setText(str_txt_po_date);
                edt_order_sapcode.setText(str_txt_sapcode);


            } catch (Exception e) {

            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /***************************
     * GET Quotation Number
     ***************************/

    public void GetEnq_No() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_quotation_number, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("quotation");

                        Arraylist_quotation_no.clear();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String quote_no = obj1.getString(TAG_QUOTATION_NO);
                            String quote_price = obj1.getString(TAG_QUOTATION_PRICE);
                            String quote_date = obj1.getString(TAG_QUOTATION_DATE);

                            String product = quote_no + ": " + "Price : " + quote_price + ", " + "Date :" + quote_date + ":";

                            Arraylist_quotation_no.add(product);

                            try {

                                System.out.println("Quotation no :: " + Arraylist_quotation_no);

                                spn_order_q_ref
                                        .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_quotation_no));

                            } catch (Exception e) {

                            }

                        }

                        dialog.dismiss();

                    } else if (success == 0) {

                        Alerter.create(Activity_Order_One_Forwarding_Memo.this)
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
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Order_One_Forwarding_Memo.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("enq_no", str_enq_no);

                System.out.println("ENQQQQQQ :::::: " + str_enq_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***********************************
     *  Internet Connection
     * ************************************/

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            new AlertDialog.Builder(Activity_Order_One_Forwarding_Memo.this)
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


}





