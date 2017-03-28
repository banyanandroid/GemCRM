package banyan.com.gemcrm.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import banyan.com.gemcrm.adapter.Completed_complaints_Adapter;
import banyan.com.gemcrm.adapter.MyTask_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;

/**
 * Created by steve on 13/3/17.
 */

public class Fragment_Target extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public Fragment_Target() {
        // Required empty public constructor
    }

    public static RequestQueue queue;
    String TAG = "Complaints";

    SessionManager session;
    String str_user_name, str_user_id;

    public static final String TAG_ID = "task_id";
    public static final String TAG_TASK_NAME = "task_name";
    public static final String TAG_TASK_ABOUT = "task_about";
    public static final String TAG_TASK_REMARKS = "task_report_description";
    public static final String TAG_TASK_STATUS = "task_status";
    public static final String TAG_ASSIGNEDBY = "task_assigned_by";
    public static final String TAG_CREATE_ON = "task_createon";

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;

    static ArrayList<HashMap<String, String>> my_task_list;

    HashMap<String, String> params = new HashMap<String, String>();

    public MyTask_Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_target, container, false);


        session = new SessionManager(getActivity());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        // Hashmap for ListView
        my_task_list = new ArrayList<HashMap<String, String>>();
        listView = (ListView) rootView.findViewById(R.id.mytask_listView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.mytask_swipe_refresh_layout);

        // Hashmap for ListView
        my_task_list = new ArrayList<HashMap<String, String>>();

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        try {
                                            queue = Volley.newRequestQueue(getActivity());
                                            Function_GetMyTask();

                                        } catch (Exception e) {
                                            // TODO: handle exception
                                        }
                                    }
                                }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String str_task_id = my_task_list.get(position).get(TAG_ID);
                String str_task_name = my_task_list.get(position).get(TAG_TASK_NAME);
                String str_task_about = my_task_list.get(position).get(TAG_TASK_ABOUT);
                String str_task_remarks = my_task_list.get(position).get(TAG_TASK_REMARKS);
                String str_task_status = my_task_list.get(position).get(TAG_TASK_STATUS);
                String str_task_assignedBy = my_task_list.get(position).get(TAG_ASSIGNEDBY);
                String str_createdOn = my_task_list.get(position).get(TAG_CREATE_ON);


                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("str_task_id", str_task_id);
                editor.putString("str_task_name", str_task_name);
                editor.putString("str_task_remarks", str_task_remarks);
                editor.putString("str_task_about", str_task_about);
                editor.putString("str_task_status", str_task_status);
                editor.putString("str_task_assignedBy", str_task_assignedBy);
                editor.putString("str_createdOn", str_createdOn);


                editor.commit();

                Intent i = new Intent(getActivity(), Activity_Target_Edit.class);
                startActivity(i);

            }
        });

        // Inflate the layout for this fragment
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

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        try {
            my_task_list.clear();
            queue = Volley.newRequestQueue(getActivity());
            Function_GetMyTask();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /********************************
     * User GetCompleted_jobs
     *********************************/

    private void Function_GetMyTask() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_my_task, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("task");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String id = obj1.getString(TAG_ID);
                            String task_name = obj1.getString(TAG_TASK_NAME);
                            String about = obj1.getString(TAG_TASK_ABOUT);
                            String remarks = obj1.getString(TAG_TASK_REMARKS);
                            String status = obj1.getString(TAG_TASK_STATUS);
                            String assignedby = obj1.getString(TAG_ASSIGNEDBY);
                            String createon = obj1.getString(TAG_CREATE_ON);
                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(TAG_ID, id);
                            map.put(TAG_TASK_NAME, task_name);
                            map.put(TAG_TASK_ABOUT, about);
                            map.put(TAG_TASK_REMARKS, remarks);
                            map.put(TAG_TASK_STATUS, status);
                            map.put(TAG_ASSIGNEDBY, assignedby);
                            map.put(TAG_CREATE_ON, createon);

                            my_task_list.add(map);

                            System.out.println("HASHMAP ARRAY" + my_task_list);


                            adapter = new MyTask_Adapter(getActivity(),
                                    my_task_list);
                            listView.setAdapter(adapter);

                        }

                    } else if (success == 0) {

                        adapter = new MyTask_Adapter(getActivity(),
                                my_task_list);
                        listView.setAdapter(adapter);

                        Alerter.create(getActivity())
                                .setTitle("GEM CRM")
                                .setText("Data Not Found \n Try Again")
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
