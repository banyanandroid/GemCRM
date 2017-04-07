package banyan.com.gemcrm.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
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

public class Activity_Dashboard_PieChart_Target extends AppCompatActivity {

    String TAG = "Complaints";
    public static RequestQueue queue;
    SpotsDialog dialog;

    SessionManager session;
    String str_user_name, str_user_id, str_gcm = "";

    public static final String TAG_CAMP_TARGET = "campaign_target";
    public static final String TAG_CAMP_ACHIVE = "campaign_acheived";

    int target = 0;
    int achive = 0;

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_piechart_target);

        isInternetOn();


        pieChart = (PieChart) findViewById(R.id.pie_chart);

        session = new SessionManager(Activity_Dashboard_PieChart_Target.this);

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);
        str_gcm = user.get(SessionManager.KEY_PERMISSION);

        try {

            dialog = new SpotsDialog(Activity_Dashboard_PieChart_Target.this);
            dialog.show();
            queue = Volley.newRequestQueue(Activity_Dashboard_PieChart_Target.this);
            GetMyTask();

        }catch (Exception e) {

        }


        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener


//        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

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

                        arr = obj.getJSONArray("campaign_group");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            target = obj1.getInt(TAG_CAMP_ACHIVE);
                            achive = obj1.getInt(TAG_CAMP_TARGET);

                            System.out.println("target" + target);
                            System.out.println("achive" + achive);
                            try{
                                function_piechart();
                            }catch (Exception e) {

                            }


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

                Toast.makeText(Activity_Dashboard_PieChart_Target.this, error.getMessage(), Toast.LENGTH_LONG).show();

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

    private void function_piechart(){

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(target, 0));
        entries.add(new Entry(achive, 1));

        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Campaign Target");
        labels.add("Campaign Achived");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS); //
        pieChart.setDescription("Campaign Targer");
        pieChart.setData(data);


        pieChart.animateY(2000);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

    }


    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Campaign Target for \n This Month");

        return s;
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

            new AlertDialog.Builder(Activity_Dashboard_PieChart_Target.this)
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
