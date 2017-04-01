package banyan.com.gemcrm.activity;

/**
 * Created by Jo on 10/03/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.MyTask_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;


public class Fragment_Dashboard extends Fragment {

    public Fragment_Dashboard() {
        // Required empty public constructor
    }

    private static String TAG = MainActivity.class.getSimpleName();

    SessionManager session;
    String str_user_name, str_user_id, str_gcm = "";
    CardView card1, card2, card3, card4, card5;
    LinearLayout thumbnail1, thumbnail2, thumbnail3, thumbnail4, thumbnail5;

    TextView txt_fin_achive, txt_fin_target, txt_enq_achive, txt_enq_target, txt_camp_achive, txt_camp_target,
            txt_prod_achive, txt_prod_target, txt_year_achive, txt_year_target;

    SpotsDialog dialog;
    public static RequestQueue queue;

    public static final String TAG_TARGET_AMOUNT = "target_amount";
    public static final String TAG_ACHIVED_AMT = "acheived_amount";
    public static final String TAG_YTD = "ytd";
    public static final String TAG_TOTALTODAYAMOUNT = "totaltodayamount";

    public static final String TAG_TARGET_DRYER = "user_target_dryer";
    public static final String TAG_ACHIVE_DRYER = "user_acheived_dryer";
    public static final String TAG_TARGET_CHILLER = "user_target_chiller";
    public static final String TAG_ACHIVE_CHILLER = "user_acheived_chiller";
    public static final String TAG_PRO_TARGET_COOLER = "user_target_cooler";
    public static final String TAG_ACHIVE_COOLER = "user_acheived_cooler";
    public static final String TAG_TARGET_VAR = "user_target_var";
    public static final String TAG_ACHIVE_VAR = "user_acheived_var";
    public static final String TAG_TARGET_SMALL_PROD = "user_target_small_products";
    public static final String TAG_ACHIVE_SMALL_PROD = "user_small_products";

    public static final String TAG_ENQ_TARGET = "enquiry_target";
    public static final String TAG_TOTAL_ENQUIRY = "totalenq";
    public static final String TAG_YTD_ENQ = "ytdenquiry";
    public static final String TAG_TOTAL_TODAY_ENQ = "totaltodayenq";

    public static final String TAG_CAMP_TARGET = "campaign_target";
    public static final String TAG_CAMP_ACHIVE = "campaign_acheived";

    public static final String TAG_YEARLY_TARGET = "target_amount";
    public static final String TAG_YEARLY_ACHIVE = "yearly_acheived";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        session = new SessionManager(getActivity());

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);
        str_gcm = user.get(SessionManager.KEY_PERMISSION);

        card1 = (CardView) rootView.findViewById(R.id.dashboard_card_view1);
        card2 = (CardView) rootView.findViewById(R.id.dashboard_card_view2);
        card3 = (CardView) rootView.findViewById(R.id.dashboard_card_view3);
        card4 = (CardView) rootView.findViewById(R.id.dashboard_card_view4);
        card5 = (CardView) rootView.findViewById(R.id.dashboard_card_view5);

        thumbnail1 = (LinearLayout) rootView.findViewById(R.id.dashboard_thumbnail1);
        thumbnail2 = (LinearLayout) rootView.findViewById(R.id.dashboard_thumbnail2);
        thumbnail3 = (LinearLayout) rootView.findViewById(R.id.dashboard_thumbnail3);
        thumbnail4 = (LinearLayout) rootView.findViewById(R.id.dashboard_thumbnail4);
        thumbnail5 = (LinearLayout) rootView.findViewById(R.id.dashboard_thumbnail5);

        txt_fin_achive = (TextView) rootView.findViewById(R.id.dash_txt_fin_achive);
        txt_fin_target = (TextView) rootView.findViewById(R.id.dash_txt_fin_target);

        txt_camp_achive = (TextView) rootView.findViewById(R.id.dash_txt_camp_achive);
        txt_camp_target = (TextView) rootView.findViewById(R.id.dash_txt_camp_target);

        txt_prod_achive = (TextView) rootView.findViewById(R.id.dash_txt_product_achive);
        txt_prod_target = (TextView) rootView.findViewById(R.id.dash_txt_product_target);

        txt_enq_achive = (TextView) rootView.findViewById(R.id.dash_txt_enq_achive);
        txt_enq_target = (TextView) rootView.findViewById(R.id.dash_txt_enq_target);

        txt_year_achive = (TextView) rootView.findViewById(R.id.dash_txt_year_achive);
        txt_year_target = (TextView) rootView.findViewById(R.id.dash_txt_year_target);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Finance_Target.class);
                startActivity(i);

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Product_Target.class);
                startActivity(i);

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Enquiry_Target.class);
                startActivity(i);

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_PieChart_Target.class);
                startActivity(i);

            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Yearly_Target.class);
                startActivity(i);

            }
        });

        thumbnail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Finance_Target.class);
                startActivity(i);

            }
        });

        thumbnail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Product_Target.class);
                startActivity(i);

            }
        });

        thumbnail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Enquiry_Target.class);
                startActivity(i);

            }
        });

        thumbnail4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_PieChart_Target.class);
                startActivity(i);

            }
        });
        thumbnail5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Yearly_Target.class);
                startActivity(i);

            }
        });

        try {

            dialog = new SpotsDialog(getActivity());
            dialog.show();
            queue = Volley.newRequestQueue(getActivity());
            GetMyAllotedEnquiries();

        } catch (Exception e) {

        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*****************************
     * GET My Task
     ***************************/

    public void GetMyAllotedEnquiries() {

        String tag_json_obj = "json_obj_req";
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

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String target = obj1.getString(TAG_TARGET_AMOUNT);
                            String Achived = obj1.getString(TAG_ACHIVED_AMT);
                            String Total = obj1.getString(TAG_TOTALTODAYAMOUNT);
                            String ytd = obj1.getString(TAG_YTD);


                            System.out.println("TAGETTT" + target);
                            System.out.println("Achived" + Achived);
                            System.out.println("Total" + Total);
                            System.out.println("ytd" + ytd);

                            try {
                                txt_fin_achive.setText("" + Achived);
                                txt_fin_target.setText("" + target);
                            } catch (Exception e) {

                            }
                        }

                        JSONArray arr_product = obj.getJSONArray("product_group");

                        for (int i = 0; arr_product.length() > i; i++) {
                            JSONObject obj_prod = arr_product.getJSONObject(i);

                            int target_dryer = obj_prod.getInt(TAG_TARGET_DRYER);
                            int Achived_dryer = obj_prod.getInt(TAG_ACHIVE_DRYER);
                            int target_chiller = obj_prod.getInt(TAG_TARGET_CHILLER);
                            int Achived_chiller = obj_prod.getInt(TAG_ACHIVE_CHILLER);
                            int target_cooler = obj_prod.getInt(TAG_PRO_TARGET_COOLER);
                            int Achived_cooler = obj_prod.getInt(TAG_ACHIVE_COOLER);
                            int target_var = obj_prod.getInt(TAG_TARGET_VAR);
                            int Achived_var = obj_prod.getInt(TAG_ACHIVE_VAR);
                            int target_small_pro = obj_prod.getInt(TAG_TARGET_SMALL_PROD);
                            int Achived_small_pro = obj_prod.getInt(TAG_ACHIVE_SMALL_PROD);

                            try {

                                int pro_target = target_dryer + target_chiller + target_cooler + target_var + target_small_pro;
                                int pro_achive = Achived_dryer + Achived_chiller + Achived_cooler + Achived_var + Achived_small_pro;

                                txt_prod_achive.setText("" + pro_achive);
                                txt_prod_target.setText("" + pro_target);


                            } catch (Exception e) {

                            }

                        }

                        JSONArray arr_enq = obj.getJSONArray("enquiry_group");

                        for (int i = 0; arr_enq.length() > i; i++) {
                            JSONObject obj_enq = arr_enq.getJSONObject(i);

                            String enq_target = obj_enq.getString(TAG_ENQ_TARGET);
                            String total_enq = obj_enq.getString(TAG_TOTAL_ENQUIRY);
                            String ytd_enq = obj_enq.getString(TAG_YTD_ENQ);
                            String total_today_enq = obj_enq.getString(TAG_TOTAL_TODAY_ENQ);

                            try {
                                txt_enq_achive.setText("" + total_enq);
                                txt_enq_target.setText("" + enq_target);
                            } catch (Exception e) {

                            }

                        }

                        JSONArray arr_camp = obj.getJSONArray("campaign_group");

                        for (int i = 0; arr_camp.length() > i; i++) {
                            JSONObject obj_camp = arr_camp.getJSONObject(i);

                            String camp_achive = obj_camp.getString(TAG_CAMP_ACHIVE);
                            String camp_target = obj_camp.getString(TAG_CAMP_TARGET);

                            try {
                                txt_camp_achive.setText("" + camp_achive);
                                txt_camp_target.setText("" + camp_target);
                            } catch (Exception e) {

                            }

                        }

                        queue = Volley.newRequestQueue(getActivity());
                        Function_Yearly_Target();

                    } else if (success == 0) {

                        Alerter.create(getActivity())
                                .setTitle("GEM CRM")
                                .setText("Data Not Found \n Try Again")
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

    /********************************
     * User GetCompleted_jobs
     *********************************/

    private void Function_Yearly_Target() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_my_yearly_target, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);

                    String year_achive = obj.getString(TAG_YEARLY_TARGET);
                    String year_target = obj.getString(TAG_YEARLY_ACHIVE);

                    try {
                        txt_year_achive.setText("" + year_achive);
                        txt_year_target.setText("" + year_target);
                    } catch (Exception e) {

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

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





}
