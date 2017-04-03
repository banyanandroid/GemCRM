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

public class Activity_Dashboard_Product_Target extends AppCompatActivity {

    String TAG = "Complaints";
    public static RequestQueue queue;
    SpotsDialog dialog;

    SessionManager session;
    String str_user_name, str_user_id, str_gcm = "";

    public static final String TAG_TARGET_DRYER = "user_target_dryer";
    public static final String TAG_ACHIVE_DRYER = "user_acheived_dryer";
    public static final String TAG_TARGET_CHILLER = "user_target_chiller";
    public static final String TAG_ACHIVE_CHILLER = "user_acheived_chiller";
    public static final String TAG_PRO_TARGET_COOLER = "user_target_cooler";
    public static final String TAG_ACHIVE_COOLER = "user_acheived_cooler";
    public static final String TAG_TARGET_VAR = "user_target_var";
    public static final String TAG_ACHIVE_VAR = "user_acheived_var";
    public static final String TAG_TARGET_SMALL_PROD = "user_small_products";
    public static final String TAG_ACHIVE_SMALL_PROD = "user_acheived_small";

    String month = "";
    int target_dryer = 0;
    int achived_dryer = 0;
    int target_chiller = 0;
    int achived_chiller = 0;
    int target_cooler = 0;
    int achived_cooler = 0;
    int target_var = 0;
    int achived_var = 0;
    int target_small_pro = 0;
    int achived_small_pro = 0;

    ArrayList<BarEntry> Arraylist_target;
    ArrayList<BarEntry> Arraylist_acheived;

    ArrayList<BarEntry> valueSet1 = null;
    ArrayList<BarEntry> valueSet2 = null;

    BarDataSet barDataSet1;
    BarDataSet barDataSet2;

    BarEntry v1e1, v2e1;

    ArrayList<BarDataSet> dataSets;
    ArrayList<String> xAxis;

    BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_product_target);

        session = new SessionManager(Activity_Dashboard_Product_Target.this);

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);
        str_gcm = user.get(SessionManager.KEY_PERMISSION);

        chart = (BarChart) findViewById(R.id.product_bar_chart);

        Arraylist_target = new ArrayList<BarEntry>();
        Arraylist_acheived= new ArrayList<BarEntry>();
        xAxis = new ArrayList<String>();

        try {

            dialog = new SpotsDialog(Activity_Dashboard_Product_Target.this);
            dialog.show();
            queue = Volley.newRequestQueue(Activity_Dashboard_Product_Target.this);
            GetMyTask();

        } catch (Exception e) {

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

                        JSONArray arr_product = obj.getJSONArray("product_group");

                        for (int i = 0; arr_product.length() > i; i++) {
                            JSONObject obj_prod = arr_product.getJSONObject(i);

                            target_dryer = obj_prod.getInt(TAG_TARGET_DRYER);
                            achived_dryer = obj_prod.getInt(TAG_ACHIVE_DRYER);
                            target_chiller = obj_prod.getInt(TAG_TARGET_CHILLER);
                            achived_chiller = obj_prod.getInt(TAG_ACHIVE_CHILLER);
                            target_cooler = obj_prod.getInt(TAG_PRO_TARGET_COOLER);
                            achived_cooler = obj_prod.getInt(TAG_ACHIVE_COOLER);
                            target_var = obj_prod.getInt(TAG_TARGET_VAR);
                            achived_var = obj_prod.getInt(TAG_ACHIVE_VAR);
                            target_small_pro = obj_prod.getInt(TAG_TARGET_SMALL_PROD);
                            achived_small_pro = obj_prod.getInt(TAG_ACHIVE_SMALL_PROD);

                            v1e1 = new BarEntry(target_dryer, i);
                            Arraylist_target.add(v1e1);

                            xAxis.add("Dryer");

                            v1e1 = new BarEntry(target_chiller, i+1);
                            Arraylist_target.add(v1e1);

                            xAxis.add("Chiller");

                            v1e1 = new BarEntry(target_cooler, i+2);
                            Arraylist_target.add(v1e1);

                            xAxis.add("Cooling Tower");

                            v1e1 = new BarEntry(target_var, i+3);
                            Arraylist_target.add(v1e1);

                            xAxis.add("Var");

                            v1e1 = new BarEntry(target_small_pro, i+4);
                            Arraylist_target.add(v1e1);

                            xAxis.add("Small Products");

                            v2e1 = new BarEntry(achived_dryer, i);
                            Arraylist_acheived.add(v2e1);

                            v2e1 = new BarEntry(achived_chiller, i+1);
                            Arraylist_acheived.add(v2e1);

                            v2e1 = new BarEntry(achived_cooler, i+2);
                            Arraylist_acheived.add(v2e1);

                            v2e1 = new BarEntry(achived_var, i+3);
                            Arraylist_acheived.add(v2e1);

                            v2e1 = new BarEntry(achived_small_pro, i+4);
                            Arraylist_acheived.add(v2e1);


                            BarData data = new BarData(getXAxisValues(), getDataSet());
                            chart.setData(data);
                            chart.setDescription("Product Target");
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

                Toast.makeText(Activity_Dashboard_Product_Target.this, error.getMessage(), Toast.LENGTH_LONG).show();

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

        barDataSet1 = new BarDataSet(Arraylist_target, "Target");
        barDataSet1.setColor(Color.rgb(30, 144, 255));
        barDataSet2 = new BarDataSet(Arraylist_acheived, "Achived");
        barDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        return dataSets;

    }

    private ArrayList<String> getXAxisValues() {

        return xAxis;
    }


}
