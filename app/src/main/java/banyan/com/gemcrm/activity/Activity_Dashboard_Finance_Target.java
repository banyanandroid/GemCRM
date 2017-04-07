package banyan.com.gemcrm.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class Activity_Dashboard_Finance_Target extends AppCompatActivity {

    String TAG = "Complaints";
    public static RequestQueue queue;
    SpotsDialog dialog;

    SessionManager session;
    String str_user_name, str_user_id, str_gcm = "";

    public static final String TAG_TARGET_AMOUNT = "target_amount";
    public static final String TAG_ACHIVED_AMT = "acheived_amount";
    public static final String TAG_YTD = "ytd";
    public static final String TAG_TOTALTODAYAMOUNT = "totaltodayamount";

    String month = "";
    int target_amount = 0;
    int achived_amount = 0;
    int ytd = 0;
    int totaltodayamount = 0;

    ArrayList<BarEntry> Arraylist_target_amount;
    ArrayList<BarEntry> Arraylist_acheived_amount;
    ArrayList<BarEntry> Arraylist_ytd;
    ArrayList<BarEntry> Arraylist_totaltodayamount;

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

        isInternetOn();

        session = new SessionManager(Activity_Dashboard_Finance_Target.this);

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);
        str_gcm = user.get(SessionManager.KEY_PERMISSION);

        chart = (BarChart) findViewById(R.id.bar_chart);

        try {

            dialog = new SpotsDialog(Activity_Dashboard_Finance_Target.this);
            dialog.show();
            queue = Volley.newRequestQueue(Activity_Dashboard_Finance_Target.this);
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

                        arr = obj.getJSONArray("finance_group");

                        Arraylist_target_amount = new ArrayList<BarEntry>();
                        Arraylist_acheived_amount = new ArrayList<BarEntry>();
                        Arraylist_ytd = new ArrayList<BarEntry>();
                        Arraylist_totaltodayamount = new ArrayList<BarEntry>();
                        xAxis = new ArrayList<String>();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                           // month = obj1.getString("month");
                          // String totaltodayamount = obj1.getString(TAG_TOTALTODAYAMOUNT);
                            target_amount = obj1.getInt(TAG_TARGET_AMOUNT);
                            achived_amount = obj1.getInt(TAG_ACHIVED_AMT);
                            ytd = obj1.getInt(TAG_YTD);


                            System.out.println("target_amount : " +target_amount);
                            System.out.println("achived_amount : " +achived_amount);
                            System.out.println("ytd : " +ytd);
                            System.out.println("totaltodayamt : " +totaltodayamount);

                            v1e1 = new BarEntry(target_amount, i);
                            Arraylist_target_amount.add(v1e1);

                            v2e1 = new BarEntry(achived_amount, i);
                            Arraylist_acheived_amount.add(v2e1);

                            v3e1 = new BarEntry(ytd, i);
                            Arraylist_ytd.add(v3e1);

                            int va = 2000;

                            v4e1 = new BarEntry(va, i);
                            Arraylist_totaltodayamount.add(v4e1);

                            xAxis.add("This Month");

                            BarData data = new BarData(getXAxisValues(), getDataSet());
                            chart.setData(data);
                            chart.setDescription("Financial Target");
                            chart.animateXY(2000, 2000);
                            chart.invalidate();

                        }

                        System.out.println("TEST : " + Arraylist_target_amount);
                        System.out.println("TEST : " + Arraylist_acheived_amount);
                        System.out.println("TEST : " + Arraylist_ytd);

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

                Toast.makeText(Activity_Dashboard_Finance_Target.this, error.getMessage(), Toast.LENGTH_LONG).show();

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

        barDataSet1 = new BarDataSet(Arraylist_target_amount, "Target Amount");
        barDataSet1.setColor(Color.rgb(30, 144, 255));
        barDataSet2 = new BarDataSet(Arraylist_acheived_amount, "Achived Amount");
        barDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet3 = new BarDataSet(Arraylist_ytd, "YTD");
        barDataSet3.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet4 = new BarDataSet(Arraylist_totaltodayamount, "YTD Achived");
        barDataSet4.setColor(Color.rgb(0, 153, 0));

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

            new AlertDialog.Builder(Activity_Dashboard_Finance_Target.this)
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
