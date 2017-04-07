package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 26/3/17.
 */

public class Activity_Order_Two_Customerdetails extends AppCompatActivity {

    EditText edt_customer_name, edt_customer_address_line1, edt_customer_address_line2, edt_customer_city, edt_customer_state, edt_customer_pincode, edt_customer_tincode, edt_customer_cstno, edt_customer_eccno, edt_customer_panno, edt_customer_contact_person, edt_customer_contact_number, edt_customer_email;

    Button btn_customer_next;

    String str_customer_name,str_customer_address_line1,str_customer_address_line2,str_customer_city,str_customer_state,str_customer_pincode,str_customer_tincode,str_customer_cstno,str_customer_eccno,str_customer_panno,str_customer_contact_person,str_customer_contact_number,str_customer_email;

    SpotsDialog dialog;

    public static RequestQueue queue;

    String TAG = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_two_customerdetails);


        btn_customer_next = (Button) findViewById(R.id.customer_details_next);

        edt_customer_name = (EditText) findViewById(R.id.customer_details_name);
        edt_customer_address_line1 = (EditText) findViewById(R.id.customer_details_address_line1);
        edt_customer_address_line2 = (EditText) findViewById(R.id.customer_details_address_line2);
        edt_customer_city = (EditText) findViewById(R.id.customer_details_city);
        edt_customer_state = (EditText) findViewById(R.id.customer_details_state);
        edt_customer_pincode = (EditText) findViewById(R.id.customer_details_pincode);
        edt_customer_tincode = (EditText) findViewById(R.id.customer_details_tinno);
        edt_customer_cstno = (EditText) findViewById(R.id.customer_details_cstno);
        edt_customer_eccno = (EditText) findViewById(R.id.customer_details_eccno);
        edt_customer_panno = (EditText) findViewById(R.id.customer_details_panno);
        edt_customer_contact_person = (EditText) findViewById(R.id.customer_details_contact_person);
        edt_customer_contact_number = (EditText) findViewById(R.id.customer_details_contactno);
        edt_customer_email = (EditText) findViewById(R.id.customer_details_email);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("str_customer_address_line1","str_customer_address_line1");
        editor.putString("str_customer_address_line2","str_customer_address_line2");
        editor.putString("str_customer_city","str_customer_city");
        editor.putString("str_customer_state","str_customer_state");
        editor.putString("str_customer_pincode","str_customer_pincode");
        editor.putString("str_customer_tincode","str_customer_tincode");
        editor.putString("str_customer_cstno","str_customer_cstno");
        editor.putString("str_customer_eccno","str_customer_eccno");
        editor.putString("str_customer_panno","str_customer_panno");
        editor.putString("str_customer_contact_person","str_customer_contact_person");
        editor.putString("str_customer_contact_number","str_customer_contact_number");
        editor.putString("str_customer_email","str_customer_email");
        editor.commit();


        btn_customer_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (str_customer_name.equals("")) {
                    edt_customer_name.setError("Please Enter Customer name");
                    TastyToast.makeText(getApplicationContext(), "Customer name is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_address_line1.equals("")) {

                    edt_customer_address_line1.setError("Please Enter Customer address line1");
                    TastyToast.makeText(getApplicationContext(), "Customer address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_address_line2.equals("")){

                    edt_customer_address_line2.setError("Please Enter Customer address line2");
                    TastyToast.makeText(getApplicationContext(), "Customer address line2 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_city.equals("")){

                    edt_customer_address_line2.setError("Please Enter Customer city");
                    TastyToast.makeText(getApplicationContext(), "Customer city", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_state.equals("")){

                    edt_customer_state.setError("Please Enter Customer state");
                    TastyToast.makeText(getApplicationContext(), "Customer state", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_pincode.equals("")){
                    edt_customer_pincode.setError("Please Enter Customer pincode");
                    TastyToast.makeText(getApplicationContext(), "Customer pincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_tincode.equals("")){
                    edt_customer_tincode.setError("Please Enter Customer tincode");
                    TastyToast.makeText(getApplicationContext(), "Customer tincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_cstno.equals("")){
                    edt_customer_cstno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_eccno.equals("")){
                    edt_customer_eccno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_panno.equals("")){
                    edt_customer_panno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_contact_person.equals("")){
                    edt_customer_contact_person.setError("Please Enter Customer contact person");
                    TastyToast.makeText(getApplicationContext(), "Customer contact person", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_contact_number.equals("")){
                    edt_customer_contact_number.setError("Please Enter Customer contact number");
                    TastyToast.makeText(getApplicationContext(), "Customer contact number", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_email.equals("")){
                    edt_customer_email.setError("Please Enter Customer email");
                    TastyToast.makeText(getApplicationContext(), "Customer email", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else {
                    dialog = new SpotsDialog(Activity_Order_Two_Customerdetails.this);
                    dialog.show();
                  //  queue = Volley.newRequestQueue(Activity_Order_Two_Customerdetails.this);
                    // Function_Next();

                }*/

                Intent i = new Intent(getApplicationContext(),Activity_Order_Three_Customerdetails_Billing.class);
                startActivity(i);
                finish();
                }
        });
    }
    private void Function_Next(){


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

                        Alerter.create(Activity_Order_Two_Customerdetails.this)
                                .setTitle("GEM CRM")
                                .setText("Customer details entered Successfully (: ")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else {

                        /*adapter = new Appointment_Adapter(Activity_Appoinment_Edit.this,
                                appointment_list);
                        List.setAdapter(adapter);*/

                        Alerter.create(Activity_Order_Two_Customerdetails.this)
                                .setTitle("GEM CRM")
                                .setText("Customer details entered Failed :( \n Try Again")
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


                params.put("edt_customer_name",str_customer_name);
                params.put("edt_customer_address_line1",str_customer_address_line1);
                params.put(" edt_customer_address_line2", str_customer_address_line2);
                params.put("  edt_customer_city", str_customer_city);
                params.put(" edt_customer_state", str_customer_state);
                params.put("edt_customer_pincode", str_customer_pincode);
                params.put("edt_customer_tincode", str_customer_tincode);
                params.put(" edt_customer_cstno", str_customer_cstno);
                params.put("edt_customer_eccno", str_customer_eccno);
                params.put("edt_customer_panno", str_customer_panno);
                params.put(" edt_customer_contact_person", str_customer_contact_person);
                params.put("edt_customer_contact_number", str_customer_contact_number);
                params.put("edt_customer_email", str_customer_email);


                System.out.println("edt_customer_name : " + str_customer_name);
                System.out.println("edt_customer_address_line1 : " + str_customer_address_line1);
                System.out.println(" edt_customer_address_line2 : " + str_customer_address_line2);
                System.out.println("  edt_customer_city : " + str_customer_city);
                System.out.println(" edt_customer_state : " + str_customer_state);
                System.out.println("edt_customer_pincode : " + str_customer_pincode);
                System.out.println("edt_customer_tincode : " + str_customer_tincode);
                System.out.println(" edt_customer_cstno : " + str_customer_cstno);
                System.out.println("edt_customer_eccno : " + str_customer_eccno);
                System.out.println("edt_customer_panno : " + str_customer_panno);
                System.out.println(" edt_customer_contact_person : " + str_customer_contact_person);
                System.out.println("edt_customer_contact_number: " + str_customer_contact_number);
                System.out.println("edt_customer_email : " + str_customer_email);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

}
