package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 26/3/17.
 */

public class Activity_Order_Two_Customerdetails extends AppCompatActivity {

    EditText edt_customer_name, edt_customer_address_line1, edt_customer_address_line2, edt_customer_city, edt_customer_state,
            edt_customer_pincode, edt_customer_tincode, edt_customer_cstno, edt_customer_eccno, edt_customer_panno,
            edt_customer_contact_person, edt_customer_contact_number, edt_customer_email;

    Button btn_customer_next, btn_customer_previous;

    String str_customer_name, str_customer_address_line1, str_customer_address_line2, str_customer_city, str_customer_state, str_customer_pincode, str_customer_tincode, str_customer_cstno,
            str_customer_eccno, str_customer_panno, str_customer_contact_person, str_customer_contact_number, str_customer_email = "";

    SpotsDialog dialog;

    public static RequestQueue queue;

    String TAG = " ";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_two_customerdetails);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        btn_customer_next = (Button) findViewById(R.id.customer_details_next);
        btn_customer_previous = (Button) findViewById(R.id.customer_details_previous);

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


        btn_customer_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_customer_name = edt_customer_name.getText().toString();
                str_customer_address_line1 = edt_customer_address_line1.getText().toString();
                str_customer_address_line2 = edt_customer_address_line2.getText().toString();
                str_customer_city = edt_customer_city.getText().toString();
                str_customer_state = edt_customer_state.getText().toString();
                str_customer_pincode = edt_customer_pincode.getText().toString();
                str_customer_tincode = edt_customer_tincode.getText().toString();
                str_customer_cstno = edt_customer_cstno.getText().toString();
                str_customer_eccno = edt_customer_eccno.getText().toString();
                str_customer_panno = edt_customer_panno.getText().toString();
                str_customer_contact_person = edt_customer_contact_person.toString();
                str_customer_contact_number = edt_customer_contact_number.getText().toString();
                str_customer_email = edt_customer_email.getText().toString();

                if (str_customer_name.equals("")) {
                    edt_customer_name.setError("Please Enter Customer name");
                    TastyToast.makeText(getApplicationContext(), "Customer name is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_address_line1.equals("")) {

                    edt_customer_address_line1.setError("Please Enter Customer address line1");
                    TastyToast.makeText(getApplicationContext(), "Customer address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_address_line2.equals("")) {

                    edt_customer_address_line2.setError("Please Enter Customer address line2");
                    TastyToast.makeText(getApplicationContext(), "Customer address line2 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_city.equals("")) {

                    edt_customer_address_line2.setError("Please Enter Customer city");
                    TastyToast.makeText(getApplicationContext(), "Customer city", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_state.equals("")) {

                    edt_customer_state.setError("Please Enter Customer state");
                    TastyToast.makeText(getApplicationContext(), "Customer state", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_pincode.equals("")) {
                    edt_customer_pincode.setError("Please Enter Customer pincode");
                    TastyToast.makeText(getApplicationContext(), "Customer pincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_tincode.equals("")) {
                    edt_customer_tincode.setError("Please Enter Customer tincode");
                    TastyToast.makeText(getApplicationContext(), "Customer tincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_cstno.equals("")) {
                    edt_customer_cstno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_eccno.equals("")) {
                    edt_customer_eccno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_panno.equals("")) {
                    edt_customer_panno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_contact_person.equals("")) {
                    edt_customer_contact_person.setError("Please Enter Customer contact person");
                    TastyToast.makeText(getApplicationContext(), "Customer contact person", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_contact_number.equals("")) {
                    edt_customer_contact_number.setError("Please Enter Customer contact number");
                    TastyToast.makeText(getApplicationContext(), "Customer contact number", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_email.equals("")) {
                    edt_customer_email.setError("Please Enter Customer email");
                    TastyToast.makeText(getApplicationContext(), "Customer email", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("str_customer_name", str_customer_name);
                    editor.putString("str_customer_address_line1", str_customer_address_line1);
                    editor.putString("str_customer_address_line2", str_customer_address_line2);
                    editor.putString("str_customer_city", str_customer_city);
                    editor.putString("str_customer_state", str_customer_state);
                    editor.putString("str_customer_pincode", str_customer_pincode);
                    editor.putString("str_customer_tincode", str_customer_tincode);
                    editor.putString("str_customer_cstno", str_customer_cstno);
                    editor.putString("str_customer_eccno", str_customer_eccno);
                    editor.putString("str_customer_panno", str_customer_panno);
                    editor.putString("str_customer_contact_person", str_customer_contact_person);
                    editor.putString("str_customer_contact_number", str_customer_contact_number);
                    editor.putString("str_customer_email", str_customer_email);

                    editor.commit();


                }


                Intent i = new Intent(getApplicationContext(), Activity_Order_Three_Customerdetails_Billing.class);
                startActivity(i);
                finish();
            }
        });

        btn_customer_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Order_One_Forwarding_Memo.class);
                startActivity(i);
                finish();

            }
        });
    }

    /**********************************
     * Main Menu
     *********************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_ofm, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStateme
        if (id == R.id.action_refresh) {


            // GET And Set
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            str_customer_name = sharedPreferences.getString("str_customer_name", "str_customer_name");
            str_customer_address_line1 = sharedPreferences.getString("str_customer_address_line1", "str_customer_address_line1");
            str_customer_address_line2 = sharedPreferences.getString("str_customer_address_line2", "str_customer_address_line2");
            str_customer_city = sharedPreferences.getString("str_customer_city", "str_customer_city");
            str_customer_state = sharedPreferences.getString("str_customer_state", "str_customer_state");
            str_customer_pincode = sharedPreferences.getString("str_customer_pincode", "str_customer_pincode");
            str_customer_tincode = sharedPreferences.getString("str_customer_tincode", "str_customer_tincode");
            str_customer_cstno = sharedPreferences.getString("str_customer_cstno", "str_customer_cstno");
            str_customer_eccno = sharedPreferences.getString("str_customer_eccno", "str_customer_eccno");
            str_customer_panno = sharedPreferences.getString("str_customer_panno", "str_customer_panno");
            str_customer_contact_person = sharedPreferences.getString("str_customer_contact_person", "str_customer_contact_person");
            str_customer_contact_number = sharedPreferences.getString("str_customer_contact_number", "str_customer_contact_number");
            str_customer_email = sharedPreferences.getString("str_customer_email", "str_customer_email");


            try {

                edt_customer_name.setText(str_customer_name);
                edt_customer_address_line1.setText(str_customer_address_line1);
                edt_customer_address_line2.setText(str_customer_address_line2);
                edt_customer_city.setText(str_customer_city);
                edt_customer_state.setText(str_customer_state);
                edt_customer_pincode.setText(str_customer_pincode);
                edt_customer_tincode.setText(str_customer_tincode);
                edt_customer_cstno.setText(str_customer_cstno);
                edt_customer_eccno.setText(str_customer_eccno);
                edt_customer_panno.setText(str_customer_panno);
                edt_customer_contact_person.setText(str_customer_contact_person);
                edt_customer_contact_number.setText(str_customer_contact_number);
                edt_customer_email.setText(str_customer_email);

            } catch (Exception e) {

            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}








