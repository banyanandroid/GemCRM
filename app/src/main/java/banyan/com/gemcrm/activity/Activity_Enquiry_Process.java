package banyan.com.gemcrm.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by User on 3/20/2017.
 */
public class Activity_Enquiry_Process extends AppCompatActivity {

    String str_select_id, str_select_comp_name, str_select_phoneno, str_select_email, str_select_comp_address, str_select_pin, str_select_person_name,
            str_select_person_number, str_select_produc_id, str_select_produc_series, str_select_desc, str_select_enq_throu,
            getStr_select_enq_throu_desc, str_select_createdon, str_select_completeon = "";

    TextView txt_created_on, txt_enq_id, txt_enq_company_name, txt_enq_txt_email, txt_enq_address, txt_enq_pin, txt_enq_phone, txt_enq_conact_person,
            txt_enq_person_phone, txt_enq_product, txt_enq_enq_through, txt_enq_enq_thro_des;

    TextView txt_minus, txt_value, txt_add;

    int i = 0;

    EditText edt_spec, edt_discount, edt_price;

    String str_select_group;

    SpotsDialog dialog;
    public static RequestQueue queue;

    SessionManager session;
    String str_user_name, str_user_id;

    String TAG = "add task";

    public static final String TAG_PROD_TITLE = "product_series_name";
    public static final String TAG_Discount = "user_discount";

    public static final String TAG_Model_NO = "product_model_no";

    public static final String TAG_Model_Type = "product_type";
    public static final String TAG_Model_PRICE = "product_price";

    Spinner spn_model;
    ArrayList<String> Arraylist_model = null;
    String str_Selected_model = "";

    Spinner spn_model_no;
    ArrayList<String> Arraylist_model_no = null;
    String str_Selected_model_no = "";

    Spinner spn_model_type;
    ArrayList<String> Arraylist_model_type = null;
    String str_Selected_model_type, str_Selected_model_price = "";

    ArrayList<String> Arraylist_model_price = null;

    private Toolbar mToolbar;

    //Notification Batch

    RelativeLayout notificationCount1, parent_batch;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_process);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        txt_created_on = (TextView) findViewById(R.id.enq_process_txt_app_created_on);
        txt_enq_id = (TextView) findViewById(R.id.enq_process_txt_id);
        txt_enq_company_name = (TextView) findViewById(R.id.enq_process_txt_company_name);
        txt_enq_txt_email = (TextView) findViewById(R.id.enq_process_txt_email);
        txt_enq_address = (TextView) findViewById(R.id.enq_process_txt_address);
        txt_enq_pin = (TextView) findViewById(R.id.enq_process_txt_pin);
        txt_enq_phone = (TextView) findViewById(R.id.enq_process_txt_comp_phone);
        txt_enq_conact_person = (TextView) findViewById(R.id.enq_process_txt_conact_person);
        txt_enq_person_phone = (TextView) findViewById(R.id.enq_process_txt_contact_person_phone);
        txt_enq_product = (TextView) findViewById(R.id.enq_process_txt_product);
        txt_enq_enq_through = (TextView) findViewById(R.id.enq_process_txt_enq_thro);
        txt_enq_enq_thro_des = (TextView) findViewById(R.id.enq_process_txt_enq_thro_desc);

        txt_minus = (TextView) findViewById(R.id.enq_process_txt_minus);
        txt_value = (TextView) findViewById(R.id.enq_process_txt_count);
        txt_add = (TextView) findViewById(R.id.enq_process_txt_add);

        edt_discount = (EditText) findViewById(R.id.add_appoint_edt_name);
        edt_spec = (EditText) findViewById(R.id.add_appoint_edt_discount);
        edt_price = (EditText) findViewById(R.id.enq_process_edt_price);

        spn_model = (Spinner) findViewById(R.id.enq_process_spn_product_model);
        spn_model_no = (Spinner) findViewById(R.id.enq_process_spn_model_no);
        spn_model_type = (Spinner) findViewById(R.id.enq_process_spn_product_type);


        Arraylist_model = new ArrayList<String>();
        Arraylist_model_no = new ArrayList<String>();
        Arraylist_model_type = new ArrayList<String>();
        Arraylist_model_price = new ArrayList<String>();

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_select_produc_id = sharedPreferences.getString("enq_product_id", "enq_product_id");
        str_select_createdon = sharedPreferences.getString("enq_created_on", "enq_created_on");
        str_select_id = sharedPreferences.getString("enq_no", "enq_no");
        str_select_comp_name = sharedPreferences.getString("enq_company_name", "enq_company_name");
        str_select_email = sharedPreferences.getString("enq_company_email", "enq_company_email");
        str_select_comp_address = sharedPreferences.getString("enq_company_address", "enq_company_address");
        str_select_pin = sharedPreferences.getString("enq_company_pincode", "enq_company_pincode");
        str_select_phoneno = sharedPreferences.getString("enq_company_phn_no", "enq_company_phn_no");
        str_select_person_name = sharedPreferences.getString("enq_contact_person_name", "enq_contact_person_name");
        str_select_person_number = sharedPreferences.getString("enq_contact_person_phone_no", "enq_contact_person_phone_no");
        str_select_produc_series = sharedPreferences.getString("enq_product_series", "enq_product_series");
        str_select_enq_throu = sharedPreferences.getString("enquiry_through", "enquiry_through");
        getStr_select_enq_throu_desc = sharedPreferences.getString("enquiry_through_description", "enquiry_through_description");
        str_select_desc = sharedPreferences.getString("enq_description", "enq_description");

        System.out.println("IN _enq_created_on " + str_select_createdon);
        System.out.println("IN _ enq_created_on" + str_select_createdon);
        System.out.println("IN _enq_created_on " + str_select_createdon);
        System.out.println("IN _ enq_created_on" + str_select_createdon);

        try {

            txt_created_on.setText(str_select_createdon);
            txt_enq_id.setText(str_select_id);
            txt_enq_company_name.setText(str_select_comp_name);
            txt_enq_txt_email.setText(str_select_email);
            txt_enq_address.setText(str_select_comp_address);
            txt_enq_pin.setText(str_select_pin);
            txt_enq_phone.setText(str_select_phoneno);
            txt_enq_conact_person.setText(str_select_person_name);
            txt_enq_person_phone.setText(str_select_person_number);
            txt_enq_product.setText(str_select_produc_series);
            txt_enq_enq_through.setText(str_select_enq_throu);
            txt_enq_enq_thro_des.setText(getStr_select_enq_throu_desc);
            edt_spec.setText("" + str_select_desc);


        } catch (Exception e) {

        }

        spn_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model = Arraylist_model.get(arg2);

                System.out.println("NAME : " + str_Selected_model);

               /* dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();*/
                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_no = Arraylist_model_no.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no);

                /*dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();*/
                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type = Arraylist_model_type.get(arg2);
                str_Selected_model_price = Arraylist_model_price.get(arg2);

                System.out.println("NAME : " + str_Selected_model_type);

                Toast.makeText(getApplicationContext(), "TYPE : " + str_Selected_model_type + "Price : " + str_Selected_model_price, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        txt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price);

                    int acutal_value = i * price;

                    edt_price.setText(""+acutal_value);

                }

            }
        });

        txt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price);

                    int acutal_value = i * price;

                    edt_price.setText(""+acutal_value);

                }

            }
        });


        try {

            dialog = new SpotsDialog(Activity_Enquiry_Process.this);
            dialog.show();
            queue = Volley.newRequestQueue(getApplicationContext());
            GetModel_Type();

        } catch (Exception e) {
            // TODO: handle exception
        }


    }


    /**********************************
     * Main Menu
     *********************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item1 = menu.findItem(R.id.action_alert);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        parent_batch = (RelativeLayout) MenuItemCompat.getActionView(item1);
        tv = (TextView) notificationCount1.findViewById(R.id.badge_notification_2);
        tv.setText("0");
        //str_cart = Integer.toString(count);
        //tv.setText("" + cart_size);

        parent_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Batch Clicked", Toast.LENGTH_LONG).show();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Batch Clicked", Toast.LENGTH_LONG).show();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alert) {

            Toast.makeText(getApplicationContext(), "Batch Clicked", Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /***************************
     * GET Model
     ***************************/

    public void GetModel_Type() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_model, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("model");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String product = obj1.getString(TAG_PROD_TITLE);

                            Arraylist_model.add(product);

                            try {
                                spn_model
                                        .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_model));

                            } catch (Exception e) {

                            }
                        }

                        queue = Volley.newRequestQueue(getApplicationContext());
                        Get_Discount();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


                dialog.dismiss();

                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("group_id", str_select_produc_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * GET Product Group
     ***************************/

    public void Get_Discount() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_discount, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("discount");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String discount = obj1.getString(TAG_Discount);

                            try {
                                edt_discount.setText("" + discount);

                            } catch (Exception e) {

                            }
                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Discount Alerted")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

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
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_user_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * GET Product Model No
     ***************************/

    public void Get_Product_modelNo() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_modelno, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String model_no = obj1.getString(TAG_Model_NO);

                            Arraylist_model_no.add(model_no);

                            try {
                                spn_model_no
                                        .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_model_no));

                            } catch (Exception e) {

                            }

                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("series", str_Selected_model);

                System.out.println("series" + str_Selected_model);
                System.out.println("series" + str_Selected_model);
                System.out.println("series" + str_Selected_model);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * GET Model Type
     ***************************/

    public void Get_Product_modelType() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_modeltype, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    Arraylist_model_type.clear();
                    Arraylist_model_price.clear();

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            JSONArray arr_type = obj1.getJSONArray("product_type_main");
                            JSONArray arr_price = obj1.getJSONArray("product_price_main");

                            for (int j = 0; arr_type.length() > j; j++) {
                                JSONObject obj_type = arr_type.getJSONObject(j);

                                String model_type = obj_type.getString(TAG_Model_Type);

                                Arraylist_model_type.add(model_type);

                                try {
                                    spn_model_type
                                            .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_type));

                                } catch (Exception e) {

                                }

                            }

                            for (int k = 0; arr_price.length() > k; k++) {
                                JSONObject obj_price = arr_price.getJSONObject(k);

                                String model_price = obj_price.getString(TAG_Model_PRICE);

                                Arraylist_model_price.add(model_price);

                            }


                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("model_no", str_Selected_model_no);

                System.out.println("model_no" + str_Selected_model_no);
                System.out.println("model_no" + str_Selected_model_no);
                System.out.println("model_no" + str_Selected_model_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


}
