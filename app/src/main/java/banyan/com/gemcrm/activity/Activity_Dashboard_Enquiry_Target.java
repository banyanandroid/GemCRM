package banyan.com.gemcrm.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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
 * Created by Jo on 3/15/2017.
 */

public class Activity_Dashboard_Enquiry_Target extends AppCompatActivity {

    String TAG = "Complaints";
    public static RequestQueue queue;
    SpotsDialog dialog;

    SessionManager session;
    String str_user_name, str_user_id, str_gcm = "";

    public static final String TAG_ENQ_TARGET = "enquiry_target";
    public static final String TAG_TOTAL_ENQUIRY = "totalenq";
    public static final String TAG_YTD_ENQ = "ytdenquiry";
    public static final String TAG_TOTAL_TODAY_ENQ = "totaltodayenq";

    int target_enq = 0;
    int total_enq = 0;
    int ytd_enq = 0;
    int today_enq = 0;

    ArrayList<BarEntry> Arraylist_enq_target;
    ArrayList<BarEntry> Arraylist_total_enq;
    ArrayList<BarEntry> Arraylist_ytd_enq;
    ArrayList<BarEntry> Arraylist_totaltodayenq;

    ArrayList<BarEntry> valueSet1 = null;
    ArrayList<BarEntry> valueSet2 = null;

    BarDataSet barDataSet1;
    BarDataSet barDataSet2;
    BarDataSet barDataSet3;
    BarDataSet barDataSet4;

    BarEntry v1e1, v2e1, v3e1, v4e1;

    ArrayList<BarDataSet> dataSets;
    ArrayList<String> xAxis;

    BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_finance_target);

        session = new SessionManager(Activity_Dashboard_Enquiry_Target.this);

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);
        str_gcm = user.get(SessionManager.KEY_GCM);

        chart = (BarChart) findViewById(R.id.bar_chart);

        try {

            dialog = new SpotsDialog(Activity_Dashboard_Enquiry_Target.this);
            dialog.show();
            queue = Volley.newRequestQueue(Activity_Dashboard_Enquiry_Target.this);
            GetMyTask();

        }catch (Exception e) {

        }

    }

    /*****************************
     * GET My Task
     ***************************/

    public void GetMyTask() {

        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_my_targets, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("enquiry_group");

                        Arraylist_enq_target = new ArrayList<BarEntry>();
                        Arraylist_total_enq = new ArrayList<BarEntry>();
                        Arraylist_ytd_enq = new ArrayList<BarEntry>();
                        Arraylist_totaltodayenq = new ArrayList<BarEntry>();
                        xAxis = new ArrayList<String>();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            target_enq = obj1.getInt(TAG_ENQ_TARGET);
                            total_enq = obj1.getInt(TAG_TOTAL_ENQUIRY);
                            ytd_enq = obj1.getInt(TAG_YTD_ENQ);
                            today_enq = obj1.getInt(TAG_TOTAL_TODAY_ENQ);


                            System.out.println("target_enq : " +target_enq);
                            System.out.println("total_enq : " +total_enq);
                            System.out.println("ytd_enq : " +ytd_enq);
                            System.out.println("today_enq : " +today_enq);

                            v1e1 = new BarEntry(target_enq, i);
                            Arraylist_enq_target.add(v1e1);

                            v2e1 = new BarEntry(total_enq, i);
                            Arraylist_total_enq.add(v2e1);

                            v3e1 = new BarEntry(ytd_enq, i);
                            Arraylist_ytd_enq.add(v3e1);

                            v4e1 = new BarEntry(today_enq, i);
                            Arraylist_totaltodayenq.add(v4e1);

                            xAxis.add("This Month");

                            BarData data = new BarData(getXAxisValues(), getDataSet());
                            chart.setData(data);
                            chart.setDescription("Enquiry Target");
                            chart.animateXY(2000, 2000);
                            chart.invalidate();

                        }

                    } else if (success == 0) {


                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // stopping swipe refresh
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Activity_Dashboard_Enquiry_Target.this, error.getMessage(), Toast.LENGTH_LONG).show();

                dialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_user_id); // replace as str_id

                System.out.println("user_id" + str_user_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    private ArrayList<BarDataSet> getDataSet() {

        barDataSet1 = new BarDataSet(Arraylist_enq_target, "Target Enquiry");
        barDataSet1.setColor(Color.rgb(30, 144, 255));
        barDataSet2 = new BarDataSet(Arraylist_total_enq, "Achived Enquiry");
        barDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet3 = new BarDataSet(Arraylist_ytd_enq, "YTD");
        barDataSet3.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet4 = new BarDataSet(Arraylist_totaltodayenq, "Today's Target");
        barDataSet4.setColor(Color.rgb(204, 0, 204));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);

        return dataSets;

    }

    private ArrayList<String> getXAxisValues() {

        return xAxis;
    }


}
