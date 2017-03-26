package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.Appointment_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by Jo on 3/26/2017.
 */

public class Activity_Quotation extends AppCompatActivity {

    private static final String TAG_TOTAL = "total";
    private static final String TAG_CREATE_ON = "created_on";
    private static final String TAG_DISCOUNT = "discount";
    private static final String TAG_ENQ_COMPANY_NAME = "enq_company_name";
    private static final String TAG_ENQ_COMP_PHN_NO = "enq_company_phn_no";
    private static final String TAG_ENQ_COMP_ADDRESS = "enq_company_address";
    private static final String TAG_PRODUCT_GROUP = "enq_product_group";
    private static final String TAG_PRODUCT_MODEL = "enq_product_model";
    private static final String TAG_PRODUCT_MODEL_NO = "enq_product_model_no";
    private static final String TAG_PRODUCT_TYPE = "enq_product_model_type";
    private static final String TAG_PRODUCT_QTY = "enq_product_qty";

    String TAG = "reg";

    SpotsDialog dialog;
    public static RequestQueue queue;

    static ArrayList<HashMap<String, String>> Quotation_List;

    HashMap<String, String> params = new HashMap<String, String>();

    public Appointment_Adapter adapter;
    ListView list_quotation;

    String str_quotation_no = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);

        list_quotation = (ListView) findViewById(R.id.quotation_lst);

        // Hashmap for ListView
        Quotation_List = new ArrayList<HashMap<String, String>>();

        try {
            Bundle extras = getIntent().getExtras();
            if(extras !=null) {
                str_quotation_no = extras.getString("quotation_number");
            }
        }catch (Exception e) {

        }

        if (str_quotation_no.equals("")){

            TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);

        }else {

            try {

                dialog = new SpotsDialog(Activity_Quotation.this);
                dialog.show();
                queue = Volley.newRequestQueue(Activity_Quotation.this);
                Function_GetQuotation();

            }catch (Exception e) {

            }

        }

    }

    /********************************
     * User Authentication
     *********************************/

    private void Function_GetQuotation() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_authentication, new Response.Listener<String>() {

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


                        JSONArray arr;
                        arr = obj.getJSONArray("products");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String str_product_group = obj1.getString(TAG_PRODUCT_GROUP);
                            String str_product_model = obj1.getString(TAG_PRODUCT_MODEL);
                            String str_product_model_no = obj1.getString(TAG_PRODUCT_MODEL_NO);
                            String str_model_type = obj1.getString(TAG_PRODUCT_TYPE);
                            String str_product_qty = obj1.getString(TAG_PRODUCT_QTY);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(TAG_PRODUCT_GROUP, str_product_group);
                            map.put(TAG_PRODUCT_MODEL, str_product_model);
                            map.put(TAG_PRODUCT_MODEL_NO, str_product_model_no);
                            map.put(TAG_PRODUCT_TYPE, str_model_type);
                            map.put(TAG_PRODUCT_QTY, str_product_qty);

                            Quotation_List.add(map);

                        System.out.println("HASHMAP ARRAY" + Quotation_List);


                            adapter = new Appointment_Adapter(Activity_Quotation.this,
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

                System.out.println("quotation_no" + str_quotation_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

}
