package banyan.com.gemcrm.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

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
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by Schan on 18-Mar-17.
 */

public class Activity_Appoinment_Edit extends AppCompatActivity {

    EditText edt_app_with, edt_app_company_name, edt_app_location, edt_app_note, edt_app_through, edt_app_date, edt_app_time;

    TextView app_created_on, app_up_created_on;

    SpotsDialog dialog;
    public static RequestQueue queue;

    Button btn_update, btn_cancel;

    Spinner spn_appoint_status;

    String str_appoint_id,str_with, str_company_name, str_date, str_time, str_through, str_note, str_created_on, str_location = "";
    String str_up_with, str_up_company_name, str_up_date, str_up_time, str_up_through, str_up_note, str_up_created_on, str_up_location, str_up_status = "";

    int from_year, from_month, from_date;

    SessionManager session;
    String str_user_name, str_user_id;

    private Toolbar mToolbar;

    String TAG = "reg";

    String str_po_status = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_edit);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        edt_app_with = (EditText) findViewById(R.id.edit_appoint_edt_name);
        edt_app_company_name = (EditText) findViewById(R.id.edit_appoint_edt_company_name);
        edt_app_location = (EditText) findViewById(R.id.edit_appoint_edt_location);
        edt_app_through = (EditText) findViewById(R.id.edit_appoint_edt_appoint_through);
        edt_app_note = (EditText) findViewById(R.id.edit_appoint_edt_camp_description);
        edt_app_date = (EditText) findViewById(R.id.edit_appoint_edt_start_date);
        edt_app_time = (EditText) findViewById(R.id.edit_appoint_edt_time);
        app_created_on = (TextView) findViewById(R.id.edit_appoint_txt_app_created_on);
        btn_update = (Button) findViewById(R.id.edit_appoint_btn_update);
        btn_cancel = (Button) findViewById(R.id.edit_appoint_btn_cancel);

        spn_appoint_status = (Spinner) findViewById(R.id.edit_appoint_spn_appoinment_status);

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

            app_created_on.setText(str_created_on);
            edt_app_company_name.setText(str_company_name);
            edt_app_with.setText(str_with);
            edt_app_time.setText(str_time);
            edt_app_location.setText(str_location);
            edt_app_through.setText(str_through);
            edt_app_note.setText(str_note);
            edt_app_date.setText(str_date);

        } catch (Exception e) {

        }


        session = new SessionManager(Activity_Appoinment_Edit.this);
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);


        edt_app_date.setKeyListener(null);
        edt_app_time.setKeyListener(null);

        edt_app_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Appoinment_Edit.this,
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
                                edt_app_date.setText(year + "-"
                                        + formattedMonth + "-"
                                        + formattedDayOfMonth);

                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });

        edt_app_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Activity_Appoinment_Edit.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edt_app_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });


        /*******************************
         *  Spinner Loaders
         * ******************************/

        // Product and Product Model Spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Activity_Appoinment_Edit.this, R.array.appoint_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_appoint_status.setAdapter(adapter);

        // Spinner Product Interface
        spn_appoint_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                str_up_status = parent.getItemAtPosition(pos).toString();

                if (str_up_status.equals("null")) {

                    TastyToast.makeText(Activity_Appoinment_Edit.this, "Please Select Status", TastyToast.LENGTH_LONG, TastyToast.INFO);

                } else if (str_up_status.equals("Completed")) {

                    str_po_status = "0";

                } else {

                    str_po_status = "1";

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

                str_up_with = edt_app_with.getText().toString();
                str_up_company_name = edt_app_company_name.getText().toString();
                str_up_date = edt_app_date.getText().toString();
                str_up_note = edt_app_note.getText().toString();
                str_up_time = edt_app_time.getText().toString();
                str_up_through = edt_app_through.getText().toString();
                str_up_location = edt_app_location.getText().toString();
                str_up_created_on = app_created_on.getText().toString();


                if (str_up_with.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Appointment With is Empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_with.setError("Please Enter Appointment With");
                } else if (str_up_company_name.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Company Name is Empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_company_name.setError("Please Enter Company Name");
                } else if (str_up_date.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Date is not Selected", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_date.setError("Please Select Date");
                } else if (str_up_note.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Note is Empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_note.setError("Please Enter Note");
                } else if (str_up_time.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Time is not Selected", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_time.setError("Please Select Time");
                } else if (str_up_through.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Appoinment Type is not Selected", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_through.setError("Please Select Appoinment Type");
                } else if (str_up_location.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Locationis Empty", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_app_location.setError("Please Enter The Location");
                } else {
                    dialog = new SpotsDialog(Activity_Appoinment_Edit.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Appoinment_Edit.this);
                    Function_Update();
                }


            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Appointment_description.class);
                startActivity(i);
                finish();
            }
        });


    }

    /*******************************
     *
     * Appoinment Update
     *
     * *******************************/

    private void Function_Update() {


        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_editappointment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("EXAMPLE", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        Alerter.create(Activity_Appoinment_Edit.this)
                                .setTitle("GEM CRM")
                                .setText("Appoinment Updated Successfully (: ")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else {

                        /*adapter = new Appointment_Adapter(Activity_Appoinment_Edit.this,
                                appointment_list);
                        List.setAdapter(adapter);*/

                        Alerter.create(Activity_Appoinment_Edit.this)
                                .setTitle("GEM CRM")
                                .setText("Appoinment Update Failed :( \n Try Again")
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

                params.put("appoint_id", str_appoint_id);
                params.put("appoint_company_name", str_up_company_name);
                params.put("appoint_date", str_up_date);
                params.put("appoint_time", str_up_time);
                params.put("appoint_with", str_up_with);
                params.put("appoint_through", str_up_through);
                params.put("appoint_location", str_up_location);
                params.put("appoint_description", str_up_note);
                params.put("appoint_status", str_po_status);

                System.out.println("appoint_id : " + str_appoint_id);
                System.out.println("appoint_date : " + str_up_date);
                System.out.println("appoint_time : " + str_up_time);
                System.out.println("appoint_with : " + str_up_with);
                System.out.println("appoint_through : " + str_up_through);
                System.out.println("appoint_location : " + str_up_location);
                System.out.println("appoint_description : " + str_up_note);
                System.out.println("appoint_status : " + str_po_status);
                System.out.println("appoint_company_name : " + str_up_company_name);

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

