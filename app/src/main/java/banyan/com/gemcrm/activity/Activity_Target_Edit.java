package banyan.com.gemcrm.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by Schan on 28-Mar-17.
 */

public class Activity_Target_Edit extends AppCompatActivity {

    EditText edt_target_name, edt_target_assigned_by, edt_target_about, edt_target_remarks;

    Spinner spn_target_status;

    Button btn_update;

    TextView target_created_on;

    String str_task_id, str_target_name, str_target_assigned_by, str_target_about, str_target_remarks, str_target_status, str_target_created_on = "";

    String TAG = "reg";

    String str_tsk_status = "";

    SpotsDialog dialog;
    public static RequestQueue queue;


    SessionManager session;
    String str_user_name, str_user_id;

    private Toolbar mToolbar;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_edit);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        session = new SessionManager(Activity_Target_Edit.this);
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        edt_target_name = (EditText) findViewById(R.id.target_edit_edt_target_name);
        edt_target_assigned_by = (EditText) findViewById(R.id.target_edit_edt_target_assigned_by);
        edt_target_about = (EditText) findViewById(R.id.target_edit_edt_target_about);
        edt_target_remarks = (EditText) findViewById(R.id.target_edit_edt_target_remarks);

        target_created_on = (TextView) findViewById(R.id.edit_target_txt_target_created_on);

        spn_target_status = (Spinner) findViewById(R.id.target_edit_spn_target_status);

        btn_update = (Button) findViewById(R.id.target_edit_btn_update);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());


        str_task_id = sharedPreferences.getString("str_task_id", "str_task_id");
        str_target_name = sharedPreferences.getString("str_task_name", "str_task_name");
        str_target_assigned_by = sharedPreferences.getString("str_task_assignedBy", "str_task_assignedBy");
        str_target_about = sharedPreferences.getString("str_task_about", "str_task_about");
        str_target_created_on = sharedPreferences.getString("str_createdOn", "str_createdOn");
        str_target_status = sharedPreferences.getString("str_task_status", "str_task_status");


        try {
            target_created_on.setText(str_target_created_on);

            edt_target_name.setText(str_target_name);
            edt_target_assigned_by.setText(str_target_assigned_by);
            edt_target_about.setText(str_target_about);
            edt_target_remarks.setText(str_target_remarks);


        } catch (Exception e) {

        }


        /*******************************
         *  Spinner Loaders
         * ******************************/

        // Product and Product Model Spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Activity_Target_Edit.this, R.array.appoint_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_target_status.setAdapter(adapter);

        // Spinner Product Interface
        spn_target_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                str_target_status = parent.getItemAtPosition(pos).toString();

                if (str_target_status.equals("null")) {

                    TastyToast.makeText(Activity_Target_Edit.this, "Please Select Status", TastyToast.LENGTH_LONG, TastyToast.INFO);

                } else if (str_target_status.equals("Completed")) {

                    str_tsk_status = "1";

                } else {

                    str_tsk_status = "0";

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }

        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_target_name = edt_target_name.getText().toString();
                str_target_assigned_by = edt_target_assigned_by.getText().toString();
                str_target_about = edt_target_about.getText().toString();
                str_target_remarks = edt_target_remarks.getText().toString();
                str_target_created_on = target_created_on.getText().toString();

                if (str_target_remarks.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Target Remarks is Empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_target_remarks.setError("Please Enter Target Remarks");
                } else {
                    dialog = new SpotsDialog(Activity_Target_Edit.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Target_Edit.this);
                    Function_Update_Target();
                }


            }
        });
    }


    private void Function_Update_Target() {


        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_my_task_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("EXAMPLE", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        Alerter.create(Activity_Target_Edit.this)
                                .setTitle("GEM CRM")
                                .setText("Target Updated Successfully (: ")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else {

                        /*adapter = new Appointment_Adapter(Activity_Appoinment_Edit.this,
                                appointment_list);
                        List.setAdapter(adapter);*/

                        Alerter.create(Activity_Target_Edit.this)
                                .setTitle("GEM CRM")
                                .setText("Target Update Failed :( \n Try Again")
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
                dialog.dismiss();
                TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("task_id", str_task_id);
                params.put("task_desc", str_target_remarks);
                params.put("task_status", str_tsk_status);

                System.out.println("str_task_id : " + str_task_id);
                System.out.println("str_task_remarks : " + str_target_remarks);
                System.out.println("str_task_status : " + str_tsk_status);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


}

