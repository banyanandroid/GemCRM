package banyan.com.gemcrm.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class Activity_FollowUp extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SpotsDialog dialog;
    public static RequestQueue queue;

    public static final String TAG_MESSAGE = "message";
    public static final String TAG_PROCESS = "process";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_REPORT = "report";
    public static final String TAG_CREATED_ON = "created_on";

    String TAG = "add task";

    private ListView List;
    private SwipeRefreshLayout swipeRefreshLayout;

    static ArrayList<HashMap<String, String>> campaign_list;

    HashMap<String, String> params = new HashMap<String, String>();

    public Followups_Adapter adapter;

    String str_enq_no = "";

    TextView txt_created_on, txt_created_by, txt_allorted_to, txt_process_Status, txt_process_remark, txt_compleed_on;

    String str_message, str_process, str_description, str_report, str_createon = "";

    TextView txt_msg, txt_process, txt_description, txt_report, txt_create_on;

    Button btn_preview_quotation;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followups);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isInternetOn();

        txt_created_on = (TextView) findViewById(R.id.followup_txt_created_on);
        txt_created_by = (TextView) findViewById(R.id.followup_txt_created_by);
        txt_allorted_to = (TextView) findViewById(R.id.followup_txt_allerted_to);
        txt_process_Status = (TextView) findViewById(R.id.followup_txt_process_Status);
        txt_process_remark = (TextView) findViewById(R.id.followup_txt_process_remark);
        txt_compleed_on = (TextView) findViewById(R.id.followup_txt_completed_on);

        List = (ListView) findViewById(R.id.followup_listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.followup_swipe_refresh_layout);

        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            str_enq_no = sharedPreferences.getString("follow_up_enq_no", "follow_up_enq_no");
        } catch (Exception e) {

        }

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("position" + position);
                System.out.println("position" + position);
                System.out.println("position" + position);

                str_message = campaign_list.get(position).get(TAG_MESSAGE);
                str_process = campaign_list.get(position).get(TAG_PROCESS);
                str_description = campaign_list.get(position).get(TAG_DESCRIPTION);
                str_report = campaign_list.get(position).get(TAG_REPORT);
                str_createon = campaign_list.get(position).get(TAG_CREATED_ON);


                System.out.println("str_message" + str_message);
                System.out.println("str_process" + str_process);
                System.out.println("str_description" + str_description);
                System.out.println("str_report" + str_report);
                System.out.println("str_createon" + str_createon);

                FunctionCAllAlert();


            }
        });


        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        try {
                                            campaign_list.clear();
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
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_followups, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);

                    String created_on = obj.getString("enq_created_on");
                    String created_by = obj.getString("created_user_name");
                    String allorted_to = obj.getString("alotted_user_name");
                    String fol_remark = obj.getString("process_remark");
                    String fol_status = obj.getString("process_status");
                    String completed_on = obj.getString("process_completed_on");

                    try {

                        txt_created_on.setText("" + created_on);
                        txt_created_by.setText("" + created_by);
                        txt_allorted_to.setText("" + allorted_to);
                        txt_process_Status.setText("" + fol_status);
                        txt_process_remark.setText("" + fol_remark);
                        txt_compleed_on.setText("" + completed_on);

                    } catch (Exception e) {

                    }


                    JSONArray arr;

                    arr = obj.getJSONArray("process");

                    for (int i = 0; arr.length() > i; i++) {
                        JSONObject obj1 = arr.getJSONObject(i);

                        String message = obj1.getString(TAG_MESSAGE);
                        String process = obj1.getString(TAG_PROCESS);
                        String description = obj1.getString(TAG_DESCRIPTION);
                        String report = obj1.getString(TAG_REPORT);
                        String creteon = obj1.getString(TAG_CREATED_ON);

                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_MESSAGE, message);
                        map.put(TAG_PROCESS, process);
                        map.put(TAG_DESCRIPTION, description);
                        map.put(TAG_REPORT, report);
                        map.put(TAG_CREATED_ON, creteon);

                        campaign_list.add(map);

                        System.out.println("HASHMAP ARRAY" + campaign_list);


                        adapter = new Followups_Adapter(Activity_FollowUp.this,
                                campaign_list);
                        List.setAdapter(adapter);


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


                params.put("enq_no", str_enq_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * Function ALert
     * *************************/

    private void FunctionCAllAlert() {

        LayoutInflater li = LayoutInflater.from(Activity_FollowUp.this);
        View promptsView = li
                .inflate(R.layout.alertdialog_followup, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Activity_FollowUp.this);
        alertDialogBuilder.setTitle("GEM CRM");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        // alertDialogBuilder.setInverseBackgroundForced(#26A65B);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        txt_msg = (TextView) promptsView
                .findViewById(R.id.followup_alert_txt_message);
        txt_process = (TextView) promptsView
                .findViewById(R.id.followup_alert_txt_process);
        txt_description = (TextView) promptsView
                .findViewById(R.id.followup_alert_txt_desc);
        txt_report = (TextView) promptsView
                .findViewById(R.id.followup_alert_txt_report);
        txt_create_on = (TextView) promptsView
                .findViewById(R.id.followup_alert_txt_date);
        btn_preview_quotation = (Button) promptsView
                .findViewById(R.id.alert_btn_preview_quotation);

        txt_msg.setText("" + str_message);
        txt_process.setText("" + str_process);
        txt_description.setText("" + str_description);
        txt_report.setText("" + str_report);
        txt_create_on.setText("" + str_createon);

        btn_preview_quotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(Activity_FollowUp.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("str_quotation_id_preview", str_process);

                editor.commit();

                Intent i = new Intent(getApplicationContext(), Activity_Quotation_Preview.class);
                startActivity(i);
                finish();
            }
        });

        alertDialogBuilder.setCancelable(false)

                .setNeutralButton("Done",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                System.out.println("allert str_message" + str_message);
                                System.out.println("allert str_process" + str_process);
                                System.out.println("allert str_description" + str_description);
                                System.out.println("allert str_report" + str_report);
                                System.out.println("allert str_createon" + str_createon);




                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

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

            new android.support.v7.app.AlertDialog.Builder(Activity_FollowUp.this)
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
