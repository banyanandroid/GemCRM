package banyan.com.gemcrm.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.BaseActivity_Appoinment;
import banyan.com.gemcrm.global.SessionManager;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 15/3/17.
 */

public class Activity_Campaign_Add extends BaseActivity_Appoinment implements AdapterView.OnItemSelectedListener {

    EditText edt_camp_name, edt_camp_location, edt_camp_desc,edt_start_date, edt_end_date;

    Button btn_submit, btn_reset;

    String str_camp_name, str_camp_location, str_camp_des, str_camp_start_date, str_camp_end_date;

    int from_year, from_month, from_date;
    int to_year, to_month, to_date;

    // Session Manager Class
    SessionManager session;
    String str_user_name, str_user_id;

    private String blockCharacterSet = "~#^|$%&*!'";

    String TAG = "reg";
    SpotsDialog dialog;
    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enterFromBottomAnimation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_campaign);

        isInternetOn();

        edt_camp_name = (EditText) findViewById(R.id.add_campaign_edt_name);
        edt_camp_location = (EditText) findViewById(R.id.add_campaign_edt_location);
        edt_camp_desc = (EditText) findViewById(R.id.add_campaign_edt_camp_description);
        edt_start_date = (EditText) findViewById(R.id.add_campaign_edt_start_date);
        edt_end_date = (EditText) findViewById(R.id.add_campaign_edt_end_date);

        edt_camp_name.setFilters(new InputFilter[] { filter });
        edt_camp_location.setFilters(new InputFilter[] { filter });
        edt_camp_desc.setFilters(new InputFilter[] { filter });

        btn_submit = (Button) findViewById(R.id.add_camp_btn_submit);
        btn_reset = (Button) findViewById(R.id.add_camp_btn_reset);

        session = new SessionManager(Activity_Campaign_Add.this);

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        edt_start_date.setKeyListener(null);
        edt_end_date.setKeyListener(null);

        edt_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Campaign_Add.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                int month = monthOfYear + 1;
                                String formattedMonth = "" + month;
                                String formattedDayOfMonth = "" + dayOfMonth;

                                if (month < 10) {

                                    formattedMonth = "0" + month;
                                }
                                if (dayOfMonth < 10) {

                                    formattedDayOfMonth = "0" + dayOfMonth;
                                }
                                edt_start_date.setText(year + "-"
                                        + formattedMonth + "-"
                                        + formattedDayOfMonth);

                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });

        edt_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                to_year = c.get(Calendar.YEAR);
                to_month = c.get(Calendar.MONTH);
                to_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Campaign_Add.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                int month = monthOfYear + 1;
                                String formattedMonth = "" + month;
                                String formattedDayOfMonth = "" + dayOfMonth;

                                if (month < 10) {

                                    formattedMonth = "0" + month;
                                }
                                if (dayOfMonth < 10) {

                                    formattedDayOfMonth = "0" + dayOfMonth;
                                }
                                edt_end_date.setText(year + "-"
                                        + formattedMonth + "-"
                                        + formattedDayOfMonth);

                            }
                        }, to_year, to_month, to_date);
                dpd.show();

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_camp_name = edt_camp_name.getText().toString();
                str_camp_location = edt_camp_location.getText().toString();
                str_camp_des = edt_camp_desc.getText().toString();
                str_camp_start_date = edt_start_date.getText().toString();
                str_camp_end_date = edt_end_date.getText().toString();

                if (str_camp_name.equals("")){
                    edt_camp_name.setError("Please Enter Campaign Name");
                    TastyToast.makeText(getApplicationContext(), "Campaign Name is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_camp_location.equals("")){
                    edt_camp_location.setError("Please Enter Campaign Location");
                    TastyToast.makeText(getApplicationContext(), "Campaign Location is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_camp_des.equals("")){
                    edt_camp_location.setError("Please Enter Campaign Description");
                    TastyToast.makeText(getApplicationContext(), "Campaign Description is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_camp_start_date.equals("")){
                    edt_start_date.setError("Please Enter Start Date");
                    TastyToast.makeText(getApplicationContext(), "Campaign Start Date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_camp_end_date.equals("")){
                    edt_end_date.setError("Please Enter End Date");
                    TastyToast.makeText(getApplicationContext(), "Campaign End Date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else {
                    dialog = new SpotsDialog(Activity_Campaign_Add.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Campaign_Add.this);
                    Function_Add_Campaign();
                }

            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function_reset();
            }
        });


    }


    @Override
    protected void onPause() {
        exitToBottomAnimation();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    /********************************
     * Add Campaign
     *********************************/

    private void Function_Add_Campaign() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_addcampaign, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_LOGIN", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        Alerter.create(Activity_Campaign_Add.this)
                                .setTitle("GEM CRM")
                                .setText("Campaign Created Sucessfully :)")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();
                        function_reset();

                    } else {

                        Alerter.create(Activity_Campaign_Add.this)
                                .setTitle("GEM CRM")
                                .setText("Something Went Wrong :(")
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
                Alerter.create(Activity_Campaign_Add.this)
                        .setTitle("GEM CRM")
                        .setText("Internal Error !")
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_user_id);
                params.put("camp_title", str_camp_name);
                params.put("camp_start", str_camp_start_date);
                params.put("camp_end", str_camp_end_date);
                params.put("camp_desc", str_camp_des);
                params.put("camp_place", str_camp_location);

                System.out.println("user : " + str_user_id);
                System.out.println("camp_title : " + str_camp_name);
                System.out.println("camp_start : " + str_camp_start_date);
                System.out.println("camp_end : " + str_camp_end_date);
                System.out.println("camp_desc : " + str_camp_des);
                System.out.println("camp_place : " + str_camp_location);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    private void function_reset(){

        edt_camp_name.setText("");
        edt_camp_location.setText("");
        edt_camp_desc.setText("");
        edt_start_date.setText("");
        edt_end_date.setText("");

    }

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };



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

            new AlertDialog.Builder(Activity_Campaign_Add.this)
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