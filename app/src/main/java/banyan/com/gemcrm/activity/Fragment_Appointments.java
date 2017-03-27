package banyan.com.gemcrm.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.fabtransitionactivity.SheetLayout;
import com.tapadoo.alerter.Alerter;

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
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

/**
 * Created by Jo on 15/3/17.
 */

public class Fragment_Appointments extends Fragment implements SheetLayout.OnFabAnimationEndListener, SwipeRefreshLayout.OnRefreshListener {

    SheetLayout mSheetLayout;
    FloatingActionButton mFab;

    public Fragment_Appointments() {
        // Required empty public constructor
    }

    int listposition;

    String str_user_name, str_user_id;
    String str_task_name, str_task_des;

    ProgressDialog pDialog;
    SpotsDialog dialog;
    public static RequestQueue queue;

    // Session Manager Class
    SessionManager session;

    String TAG = "add task";

    public static final String TAG_Appoint_ID = "appointment_id";
    public static final String TAG_Appoint_Date = "appointment_date";
    public static final String TAG_Appoint_Time = "appointment_time";
    public static final String TAG_Appoint_with = "appointment_with";
    public static final String TAG_Appoint_Company_Name = "appointment_company_name";
    public static final String TAG_Appoint_Through = "appointment_through";
    public static final String TAG_Appoint_Location = "appointment_location";
    public static final String TAG_Appoint_Des = "appointment_description";
    public static final String TAG_Appoint_Created = "appointment_created_on";

    static ArrayList<HashMap<String, String>> appointment_list;

    HashMap<String, String> params = new HashMap<String, String>();

    public Appointment_Adapter adapter;

    String str_select_task_id;

    private static final int REQUEST_CODE = 1;

    private ListView List;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_appoinments, null);

        session = new SessionManager(getActivity());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        mFab = (FloatingActionButton) rootview.findViewById(R.id.fab_add_appoinment);
        mSheetLayout = (SheetLayout) rootview.findViewById(R.id.bottom_sheet1);

        // Hashmap for ListView
        appointment_list = new ArrayList<HashMap<String, String>>();

        List = (ListView) rootview.findViewById(R.id.app_listView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.appointment_swipe_refresh_layout);

        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        try {
                                            queue = Volley.newRequestQueue(getActivity());
                                            GetMyAppointment();

                                        } catch (Exception e) {
                                            // TODO: handle exception
                                        }
                                    }
                                }
        );


        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String str_select_id = appointment_list.get(position).get(TAG_Appoint_ID);
                String str_select_app_date = appointment_list.get(position).get(TAG_Appoint_Date);
                String str_select_app_time = appointment_list.get(position).get(TAG_Appoint_Time);
                String str_select_app_with = appointment_list.get(position).get(TAG_Appoint_with);
                String str_select_app_through = appointment_list.get(position).get(TAG_Appoint_Through);
                String str_select_app_location = appointment_list.get(position).get(TAG_Appoint_Location);
                String str_select_app_des = appointment_list.get(position).get(TAG_Appoint_Des);
                String str_select_app_createdon = appointment_list.get(position).get(TAG_Appoint_Created);
                String str_select_app_company_name = appointment_list.get(position).get(TAG_Appoint_Company_Name);

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("str_select_id", str_select_id);
                editor.putString("str_select_app_date", str_select_app_date);
                editor.putString("str_select_app_time", str_select_app_time);
                editor.putString("str_select_app_with", str_select_app_with);
                editor.putString("str_select_app_through", str_select_app_through);
                editor.putString("str_select_app_location", str_select_app_location);
                editor.putString("str_select_app_des", str_select_app_des);
                editor.putString("str_select_app_createdon", str_select_app_createdon);
                editor.putString("str_select_app_createdon", str_select_app_createdon);
                editor.putString("str_select_app_company_name", str_select_app_company_name);

                editor.commit();

                Intent i = new Intent(getActivity(), Activity_Appointment_description.class);
                startActivity(i);


            }

        });

        List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });

        return rootview;

    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        try {
            appointment_list.clear();
            queue = Volley.newRequestQueue(getActivity());
            GetMyAppointment();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @OnClick(R.id.fab_add_appoinment)
    void onFabClick() {
        mSheetLayout.expandFab();
    }


    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getActivity(), Activity_Appoinment_Add.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            mSheetLayout.contractFab();
        }
    }

    /*****************************
     * GET My Appointment
     ***************************/

    public void GetMyAppointment() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_getappointment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("appointment");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String id = obj1.getString(TAG_Appoint_ID);
                            String date = obj1.getString(TAG_Appoint_Date);
                            String time = obj1.getString(TAG_Appoint_Time);
                            String appoint_wit = obj1.getString(TAG_Appoint_with);
                            String appoint_throogh = obj1.getString(TAG_Appoint_Through);
                            String appoint_des = obj1.getString(TAG_Appoint_Des);
                            String appoint_location = obj1.getString(TAG_Appoint_Location);
                            String appoint_created = obj1.getString(TAG_Appoint_Created);
                            String appoint_company_name = obj1.getString(TAG_Appoint_Company_Name);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(TAG_Appoint_ID, id);
                            map.put(TAG_Appoint_Date, date);
                            map.put(TAG_Appoint_Time, time);
                            map.put(TAG_Appoint_with, appoint_wit);
                            map.put(TAG_Appoint_Through, appoint_throogh);
                            map.put(TAG_Appoint_Location, appoint_location);
                            map.put(TAG_Appoint_Des, appoint_des);
                            map.put(TAG_Appoint_Created, appoint_created);
                            map.put(TAG_Appoint_Company_Name, appoint_company_name);

                            appointment_list.add(map);

                            System.out.println("HASHMAP ARRAY" + appointment_list);


                            adapter = new Appointment_Adapter(getActivity(),
                                    appointment_list);
                            List.setAdapter(adapter);

                        }

                    } else if (success == 0) {

                        adapter = new Appointment_Adapter(getActivity(),
                                appointment_list);
                        List.setAdapter(adapter);

                        Alerter.create(getActivity())
                                .setTitle("GEM CRM")
                                .setText("Data Not Found :( \n Try Again")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();
                    }

                    swipeRefreshLayout.setRefreshing(false);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
                // dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);

                Alerter.create(getActivity())
                        .setTitle("GEM CRM")
                        .setText("Internal Error :(\n" + error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
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

}
