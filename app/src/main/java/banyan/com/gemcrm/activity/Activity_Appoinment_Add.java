package banyan.com.gemcrm.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.BaseActivity_Appoinment;
import banyan.com.gemcrm.global.BaseActivity_Enquiry;
import banyan.com.gemcrm.global.SessionManager;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;


public class Activity_Appoinment_Add extends BaseActivity_Appoinment implements AdapterView.OnItemSelectedListener {

    EditText edt_app_with, edt_app_location, edt_app_note, edt_app_through, edt_app_date, edt_app_time;

    Button btn_submit, btn_reset;

    String str_app_with, str_app_location, str_app_note, str_app_through, str_app_date, str_app_time;

    int from_year, from_month, from_date;

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
        setContentView(R.layout.activity_add_new_appoinment);
        setUpToolbarWithTitle(getString(R.string.COMPOSE), true);

        edt_app_with = (EditText) findViewById(R.id.add_appoint_edt_name);
        edt_app_location = (EditText) findViewById(R.id.add_appoint_edt_location);
        edt_app_through = (EditText) findViewById(R.id.add_appoint_edt_appoint_through);
        edt_app_note = (EditText) findViewById(R.id.add_appoint_edt_camp_description);
        edt_app_date = (EditText) findViewById(R.id.add_appoint_edt_start_date);
        edt_app_time = (EditText) findViewById(R.id.add_appoint_edt_time);

        edt_app_with.setFilters(new InputFilter[]{filter});
        edt_app_location.setFilters(new InputFilter[]{filter});
        edt_app_through.setFilters(new InputFilter[]{filter});
        edt_app_note.setFilters(new InputFilter[]{filter});

        btn_submit = (Button) findViewById(R.id.add_appoint_btn_submit);
        btn_reset = (Button) findViewById(R.id.add_appoint_btn_reset);

        session = new SessionManager(Activity_Appoinment_Add.this);

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
                        Activity_Appoinment_Add.this,
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
                mTimePicker = new TimePickerDialog(Activity_Appoinment_Add.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edt_app_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_app_with = edt_app_with.getText().toString();
                str_app_location = edt_app_location.getText().toString();
                str_app_note = edt_app_note.getText().toString();
                str_app_through = edt_app_through.getText().toString();
                str_app_date = edt_app_date.getText().toString();
                str_app_time = edt_app_time.getText().toString();

                if (str_app_with.equals("")) {
                    edt_app_with.setError("Please Enter Appointment With");
                    TastyToast.makeText(getApplicationContext(), "Appointment With is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_app_location.equals("")) {
                    edt_app_location.setError("Please Enter Appointment Location");
                    TastyToast.makeText(getApplicationContext(), "Appointment Location is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_app_note.equals("")) {
                    edt_app_note.setError("Please Enter Appointment Note");
                    TastyToast.makeText(getApplicationContext(), "Appointment Note is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_app_through.equals("")) {
                    edt_app_through.setError("Please Appointment Through");
                    TastyToast.makeText(getApplicationContext(), "Appointment Through is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_app_date.equals("")) {
                    edt_app_date.setError("Please Enter Date");
                    TastyToast.makeText(getApplicationContext(), "Appointment Date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else {
                   /* dialog = new SpotsDialog(Activity_Appoinment_Add.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Appoinment_Add.this);
                    Function_Add_Campaign();*/
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
                AppConfig.url_addappointment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_LOGIN", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        Alerter.create(Activity_Appoinment_Add.this)
                                .setTitle("GEM CRM")
                                .setText("Appointment Created Sucessfully :)")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();
                        function_reset();

                    } else {
                        Alerter.create(Activity_Appoinment_Add.this)
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
                Alerter.create(Activity_Appoinment_Add.this)
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
                params.put("appoint_date", str_app_date);
                params.put("appoint_time", str_app_time);
                params.put("appoint_with", str_app_with);
                params.put("appoint_through", str_app_through);
                params.put("appoint_location", str_app_location);
                params.put("appoint_description", str_app_note);

                System.out.println("user : " + str_user_id);
                System.out.println("appoint_date : " + str_app_date);
                System.out.println("appoint_time : " + str_app_time);
                System.out.println("appoint_with : " + str_app_with);
                System.out.println("appoint_through : " + str_app_through);
                System.out.println("appoint_location : " + str_app_location);
                System.out.println("appoint_description : " + str_app_note);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    private void function_reset() {

        edt_app_with.setText("");
        edt_app_note.setText("");
        edt_app_through.setText("");
        edt_app_location.setText("");
        edt_app_date.setText("");
        edt_app_time.setText("");

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

}
