package banyan.com.gemcrm.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.Campaign_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 14/3/17.
 */

public class Fragment_Campaign extends Fragment implements SheetLayout.OnFabAnimationEndListener{

    SheetLayout mSheetLayout;
    FloatingActionButton mFab;

    String str_user_name, str_user_id;
    String str_task_name, str_task_des;

    ProgressDialog pDialog;
    SpotsDialog dialog;
    public static RequestQueue queue;

    // Session Manager Class
    SessionManager session;

    String TAG = "add task";

    private ListView List;

    public static final String TAG_CAMP_ID = "campaign_id";
    public static final String TAG_CAMP_TITLE = "campaign_title";
    public static final String TAG_CAMP_START_DATE = "campaign_start_date";
    public static final String TAG_CAMP_END_DATE = "campaign_end_date";
    public static final String TAG_CAMP_DES = "campaign_description";
    public static final String TAG_CAMP_LOCATION = "campaign_place";
    public static final String TAG_CAMP_CREATED_ON = "campaign_created_on";

    static ArrayList<HashMap<String, String>> complaint_list;

    HashMap<String, String> params = new HashMap<String, String>();

    public Campaign_Adapter adapter;

    String str_select_task_id;

    private static final int REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_campaign, null);

        session = new SessionManager(getActivity());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        mFab = (FloatingActionButton) rootview.findViewById(R.id.fab_add_campaign);
        mSheetLayout = (SheetLayout) rootview.findViewById(R.id.bottom_sheet2);

        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        List = (ListView) rootview.findViewById(R.id.camp_listView);

        // Hashmap for ListView
        complaint_list = new ArrayList<HashMap<String, String>>();

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


            }

        });

        try {

            dialog = new SpotsDialog(getActivity());
            dialog.show();
            queue = Volley.newRequestQueue(getActivity());
            GetMyCampaign();

        }catch (Exception e) {

        }



        return rootview;

    }

    public void onRefresh() {
        try {
            complaint_list.clear();
            queue = Volley.newRequestQueue(getActivity());
            GetMyCampaign();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @OnClick(R.id.fab_add_campaign)
    void onFabClick() {
        mSheetLayout.expandFab();
    }


    @Override
    public void onFabAnimationEnd() {

        Intent intent = new Intent(getActivity(), Activity_Campaign_Add.class);
        startActivityForResult(intent, REQUEST_CODE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
    }

    /*****************************
     * GET My Task
     ***************************/

    public void GetMyCampaign() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_getcampaign, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("campaign");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String id = obj1.getString(TAG_CAMP_ID);
                            String title = obj1.getString(TAG_CAMP_TITLE);
                            String location = obj1.getString(TAG_CAMP_LOCATION);
                            String description = obj1.getString(TAG_CAMP_DES);
                            String start_date = obj1.getString(TAG_CAMP_START_DATE);
                            String end_date = obj1.getString(TAG_CAMP_END_DATE);
                            String created_on = obj1.getString(TAG_CAMP_CREATED_ON);

                            // creating new HashMap
                            HashMap<String, String> map = new HashMap<String, String>();

                            // adding each child node to HashMap key => value
                            map.put(TAG_CAMP_ID, id);
                            map.put(TAG_CAMP_TITLE, title);
                            map.put(TAG_CAMP_LOCATION, location);
                            map.put(TAG_CAMP_DES, description);
                            map.put(TAG_CAMP_START_DATE, start_date);
                            map.put(TAG_CAMP_END_DATE, end_date);
                            map.put(TAG_CAMP_CREATED_ON, created_on);

                            complaint_list.add(map);

                            System.out.println("HASHMAP ARRAY" + complaint_list);


                            adapter = new Campaign_Adapter(getActivity(),
                                    complaint_list);
                            List.setAdapter(adapter);

                        }

                    } else if (success == 0) {

                        adapter = new Campaign_Adapter(getActivity(),
                                complaint_list);
                        List.setAdapter(adapter);
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
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
