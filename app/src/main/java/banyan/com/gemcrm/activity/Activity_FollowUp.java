package banyan.com.gemcrm.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import banyan.com.gemcrm.adapter.Alloted_Complaints_Adapter;
import banyan.com.gemcrm.adapter.Camp_Adapter;
import banyan.com.gemcrm.adapter.Followups_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;

/**
 * Created by Jo on 3/23/2017.
 */

public class Activity_FollowUp extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener  {

    SpotsDialog dialog;
    public static RequestQueue queue;

    public static final String TAG_PROD_ID = "product_id";
    public static final String TAG_PROD_TITLE = "product_group_name";

    String TAG = "add task";

    private ListView List;
    private SwipeRefreshLayout swipeRefreshLayout;

    static ArrayList<HashMap<String, String>> campaign_list;

    HashMap<String, String> params = new HashMap<String, String>();

    public Followups_Adapter adapter;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followups);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        List = (ListView) findViewById(R.id.followup_listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.followup_swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        try {
                                            queue = Volley.newRequestQueue(Activity_FollowUp.this);
                                            GetProductGroup();

                                        } catch (Exception e) {
                                            // TODO: handle exception
                                        }
                                    }
                                }
        );

        // Hashmap for ListView
        campaign_list = new ArrayList<HashMap<String, String>>();
    }

    @Override
    public void onRefresh() {
        try {
            campaign_list.clear();
            queue = Volley.newRequestQueue(Activity_FollowUp.this);
            GetProductGroup();

        } catch (Exception e) {
            // TODO: handle exception
        }
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

                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(TAG_PROD_ID, id);
                            map.put(TAG_PROD_TITLE, product);

                            campaign_list.add(map);

                            System.out.println("HASHMAP ARRAY" + campaign_list);


                            adapter = new Followups_Adapter(Activity_FollowUp.this,
                                    campaign_list);
                            List.setAdapter(adapter);


                        }


                    } else if (success == 0) {

                        Alerter.create(Activity_FollowUp.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                    swipeRefreshLayout.setRefreshing(false);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                swipeRefreshLayout.setRefreshing(false);
                Alerter.create(Activity_FollowUp.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
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
