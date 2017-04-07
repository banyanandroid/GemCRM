package banyan.com.gemcrm.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;

/**
 * Created by Jo on 3/18/2017.
 */

public class Activity_Appointment_description extends AppCompatActivity {

    TextView txt_app_with, txt_app_company_name, txt_app_date, txt_app_time, txt_app_through, txt_app_note, txt_created_on, txt_location;

    String str_appoint_id, str_with, str_company_name, str_date, str_time, str_through, str_note, str_created_on, str_location = "";

    Button btn_edit_appoinment, btn_delete_appoinment;

    FrameLayout frame;

    SpotsDialog spotdialog;
    public static RequestQueue queue;

    String TAG = "delete appointment";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_description);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isInternetOn();

        frame = (FrameLayout) findViewById(R.id.container_body);

        btn_edit_appoinment = (Button) findViewById(R.id.app_des_btn_edit);
        btn_delete_appoinment = (Button) findViewById(R.id.app_des_btn_delete);
        txt_created_on = (TextView) findViewById(R.id.appoint_des_txt_app_created_on);
        txt_app_with = (TextView) findViewById(R.id.appoint_des_txt_app_with);
        txt_location = (TextView) findViewById(R.id.appoint_des_txt_app_location);
        txt_app_through = (TextView) findViewById(R.id.appoint_des_txt_through);
        txt_app_note = (TextView) findViewById(R.id.appoint_des_txt_app_note);
        txt_app_date = (TextView) findViewById(R.id.appoint_des_txt_app_date);
        txt_app_time = (TextView) findViewById(R.id.appoint_des_txt_time);
        txt_app_company_name = (TextView) findViewById(R.id.appoint_des_txt_company_name);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());


        str_appoint_id = sharedPreferences.getString("str_select_id", "str_select_id");
        str_with = sharedPreferences.getString("str_select_app_with", "str_select_app_with");
        str_company_name = sharedPreferences.getString("str_select_app_company_name", "str_select_app_company_name");
        str_date = sharedPreferences.getString("str_select_app_date", "str_select_app_date");
        str_time = sharedPreferences.getString("str_select_app_time", "str_select_app_time");
        str_through = sharedPreferences.getString("str_select_app_through", "str_select_app_through");
        str_note = sharedPreferences.getString("str_select_app_des", "str_select_app_des");
        str_created_on = sharedPreferences.getString("str_select_app_createdon", "str_select_app_createdon");
        str_location = sharedPreferences.getString("str_select_app_location", "str_select_app_location");


        try {

            txt_created_on.setText(str_created_on);
            txt_app_with.setText(str_with);
            txt_app_time.setText(str_time);
            txt_location.setText(str_location);
            txt_app_through.setText(str_through);
            txt_app_note.setText(str_note);
            txt_app_date.setText(str_date);
            txt_app_company_name.setText(str_company_name);

        } catch (Exception e) {

        }

        btn_edit_appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Appoinment_Edit.class);
                startActivity(i);
                finish();
            }
        });

        btn_delete_appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Delete_Task_alert();


               /* Fragment fragment = null;
                fragment = new Fragment_Appointments();
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
*/

             //   getSupportFragmentManager().beginTransaction().add(android.R.id.content, new Fragment_Appointments()).commit();


            }
        });


    }

    /************************************
     * Delete My Task Alert Dialog
     ***********************************/

    private void Delete_Task_alert() {

        new android.support.v7.app.AlertDialog.Builder(Activity_Appointment_description.this)
                .setTitle("GEM CRM")
                .setMessage("Want to Delete This Appointment?")
                .setIcon(R.mipmap.ic_launcher)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub

                                System.out.println("str_select_appoint ID " + str_appoint_id);

                                spotdialog = new SpotsDialog(Activity_Appointment_description.this);
                                spotdialog.show();
                                queue = Volley.newRequestQueue(Activity_Appointment_description.this);
                                Function_DeleteAppointment();

                            }
                        }).show();
    }


    /************************************
     * Delete My Task Function
     ***********************************/

    private void Function_DeleteAppointment() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_deleteappointment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_REGISTER", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        Alerter.create(Activity_Appointment_description.this)
                                .setTitle("GEM CRM")
                                .setText("Appointment Deleted Successfully")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                        try {


                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    } else {

                        Alerter.create(Activity_Appointment_description.this)
                                .setTitle("GEM CRM")
                                .setText("Appointment Can't Delete")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                    spotdialog.dismiss();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                spotdialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                spotdialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("appoint_id", str_appoint_id);

                System.out.println("appoint_id" + str_appoint_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
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

            new AlertDialog.Builder(Activity_Appointment_description.this)
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
