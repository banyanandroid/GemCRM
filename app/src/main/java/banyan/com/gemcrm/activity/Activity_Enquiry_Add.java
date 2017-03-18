package banyan.com.gemcrm.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.Camp_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.BaseActivity_Enquiry;
import banyan.com.gemcrm.global.SessionManager;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;


public class Activity_Enquiry_Add extends BaseActivity_Enquiry implements AdapterView.OnItemSelectedListener {

    String str_user_name, str_user_id;

    AutoCompleteTextView auto_campaign_name;
    EditText edt_user, edt_email, edt_address, edt_phone, edt_contact_person, edt_contact_person_phone, edt_pincode,edt_description;

    String str_user, str_email, str_address, str_phoneno, str_contact_person, str_contact_person_phone, str_pincode , str_description= "";

    Button btn_submit;

    LinearLayout linear_camp, linear_ref;

    TextView t1;

    SpotsDialog dialog;
    public static RequestQueue queue;

    SessionManager session;

    String TAG = "add task";

    public static final String TAG_PROD_ID = "product_id";
    public static final String TAG_PROD_TITLE = "product_group_name";

    public static final String TAG_ENT_ID = "enquiry_through_id";
    public static final String TAG_ENT_TITLE = "enquiry_through_name";

    public static final String TAG_CAMP_ID = "campaign_id";
    public static final String TAG_CAMP_TITLE = "campaign_title";

    Spinner spn_product, spn_enq_through;
    ArrayList<String> Arraylist_products = null;
    ArrayList<String> Arraylist_product_id = null;

    ArrayList<String> Arraylist_ent_id = null;
    ArrayList<String> Arraylist_ent_title = null;

    ArrayList<String> Arraylist_camp_id = null;
    ArrayList<String> Arraylist_camp_title = null;

    private ArrayAdapter<String> adapter_campaign;

    String str_selected_product, str_selected_product_id = "";
    String str_selected_enquiry_through, str_selected_enquiry_through_id = "";
    String str_selected_campaign = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enterFromBottomAnimation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_enquiry);
        ButterKnife.bind(this);
        setUpToolbarWithTitle(getString(R.string.COMPOSE), true);

        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        edt_user = (EditText) findViewById(R.id.add_enquiry_edt_name);
        edt_email = (EditText) findViewById(R.id.add_enquiry_edt_email);
        edt_address = (EditText) findViewById(R.id.add_enquiry_edt_address);
        edt_phone = (EditText) findViewById(R.id.add_enquiry_edt_phonono);
        edt_contact_person = (EditText) findViewById(R.id.add_enquiry_edt_contact_person);
        edt_contact_person_phone = (EditText) findViewById(R.id.add_enquiry_edt_contact_person_number);
        edt_pincode = (EditText) findViewById(R.id.add_enquiry_edt_pincode);
        edt_description = (EditText) findViewById(R.id.add_enquiry_edt_description);

        spn_product = (Spinner) findViewById(R.id.add_enquiry_spn_product_group);
        spn_enq_through = (Spinner) findViewById(R.id.add_enquiry_spn_product_enquiry_through);

        linear_camp = (LinearLayout) findViewById(R.id.new_enq_linear_camp);
        linear_ref = (LinearLayout) findViewById(R.id.new_enq_linear_ref);

        btn_submit = (Button) findViewById(R.id.add_enq_btn_submit);

        Arraylist_products = new ArrayList<String>();
        Arraylist_product_id = new ArrayList<String>();

        Arraylist_ent_id = new ArrayList<String>();
        Arraylist_ent_title = new ArrayList<String>();

        Arraylist_camp_id = new ArrayList<String>();
        Arraylist_camp_title = new ArrayList<String>();

        spn_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_selected_product = Arraylist_products.get(arg2);
                str_selected_product_id = Arraylist_product_id.get(arg2);

                System.out.println("ID : " + str_selected_product+ " :   " +str_selected_product_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_enq_through.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_selected_enquiry_through = Arraylist_ent_title.get(arg2);
                str_selected_enquiry_through_id = Arraylist_ent_id.get(arg2);

                System.out.println("ID : " + str_selected_enquiry_through);
                System.out.println("NAME : " + str_selected_enquiry_through_id);

                if (str_selected_enquiry_through.equals("CAMPAIGN")){
                    linear_camp.setVisibility(View.VISIBLE);
                    linear_ref.setVisibility(View.GONE);

                    try {
                        queue = Volley.newRequestQueue(getApplicationContext());
                        Get_Campaign();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                }else if (str_selected_enquiry_through.equals("REFERENCE")){
                    linear_camp.setVisibility(View.GONE);
                    linear_ref.setVisibility(View.VISIBLE);

                }else{
                    linear_camp.setVisibility(View.GONE);
                    linear_ref.setVisibility(View.GONE);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_user = edt_user.getText().toString();
                str_email = edt_email.getText().toString();
                str_address = edt_address.getText().toString();
                str_pincode = edt_pincode.getText().toString();
                str_phoneno = edt_phone.getText().toString();
                str_contact_person = edt_contact_person.getText().toString();
                str_contact_person_phone = edt_contact_person_phone.getText().toString();

                if (str_user.equals("")) {
                    edt_user.setError("Please Enter Company Name");
                    TastyToast.makeText(getApplicationContext(), "Please Enter Company Name", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_email.equals("")) {
                    edt_email.setError("Please Enter Company Email");
                    TastyToast.makeText(getApplicationContext(), "Please Enter Company Email", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_address.equals("")) {
                    edt_address.setError("Please Enter Company Email");
                    TastyToast.makeText(getApplicationContext(), "Please Enter Company Address", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_phoneno.equals("")) {
                    edt_phone.setError("Please Enter Company Email");
                    TastyToast.makeText(getApplicationContext(), "Please Enter Phone Number", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_contact_person.equals("")) {
                    edt_contact_person.setError("Please Enter the Field");
                    TastyToast.makeText(getApplicationContext(), "Please Enter Contact Person", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }

            }
        });


        try {
            queue = Volley.newRequestQueue(getApplicationContext());
            GetProductGroup();
        } catch (Exception e) {
            // TODO: handle exception
        }


    }

    @Override
    protected void onPause() {
        exitToBottomAnimation();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    /***************************
     * GET Product Group
     ***************************/

    public void GetProductGroup() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.GET,
                AppConfig.url_product_group, new Response.Listener<String>() {

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

                            String id = obj1.getString(TAG_PROD_ID);
                            String product = obj1.getString(TAG_PROD_TITLE);

                            Arraylist_products.add(product);
                            Arraylist_product_id.add(id);

                            try {
                                spn_product
                                        .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_products));

                            } catch (Exception e) {

                            }
                        }

                        try {
                            queue = Volley.newRequestQueue(getApplicationContext());
                            Get_Enquiry_through();
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    } else if (success == 0) {


                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Enquiry_Add.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /*****************************
     * GET Enquiry Through
     ***************************/

    public void Get_Enquiry_through() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.GET,
                AppConfig.url_enquiry_through, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("enquiry");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String ent_id = obj1.getString(TAG_ENT_ID);
                            String rnt_type = obj1.getString(TAG_ENT_TITLE);

                            Arraylist_ent_id.add(ent_id);
                            Arraylist_ent_title.add(rnt_type);

                            try {

                                spn_enq_through
                                        .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_ent_title));

                            } catch (Exception e) {

                            }
                        }

                    } else if (success == 0) {


                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Enquiry_Add.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /*****************************
     * GET CAMPIGN DETAILS
     ***************************/

    public void Get_Campaign() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.GET,
                AppConfig.url_campaign, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("campaign");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String camp_id = obj1.getString(TAG_CAMP_ID);
                            String camp_title = obj1.getString(TAG_CAMP_TITLE);

                            Arraylist_camp_id.add(camp_id);
                            Arraylist_camp_title.add(camp_title);
                        }
                        try {

                            System.out.println("CAMP B$4444444: " + Arraylist_camp_title);

                            adapter_campaign = new ArrayAdapter<String>(Activity_Enquiry_Add.this,
                                    android.R.layout.simple_list_item_1, Arraylist_camp_title);
                            auto_campaign_name.setAdapter(adapter_campaign);
                            auto_campaign_name.setThreshold(1);

                            System.out.println("CAMP : " + Arraylist_camp_title);

                            System.out.println("CAMP DONEEEEEE: " + Arraylist_camp_title);

                            auto_campaign_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                        long arg3) {
                                    t1 = (TextView) arg1;
                                    str_selected_campaign = t1.getText().toString();

                                    System.out.println("SELECTED : " + str_selected_campaign);

                                }
                            });

                        } catch (Exception e) {

                        }

                    } else if (success == 0) {


                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Enquiry_Add.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


}
