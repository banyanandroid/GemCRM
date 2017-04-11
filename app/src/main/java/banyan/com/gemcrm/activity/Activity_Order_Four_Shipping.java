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
import android.widget.CheckBox;
import android.widget.EditText;

import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 27/3/17.
 */

public class Activity_Order_Four_Shipping extends AppCompatActivity {

    EditText edt_shipping_customer_name, edt_customer_shipping_address_line1, edt_customer_shipping_address_line2, edt_customer_shipping_city, edt_customer_shipping_state, edt_customer_shipping_pincode,
            edt_customer_shipping_tincode, edt_customer_shipping_cstno, edt_customer_shipping_eccno, edt_customer_shipping_panno, edt_customer_shipping_contact_person, edt_customer_shipping_contact_number, edt_customer_shipping_email;

    Button btn_customer_shipping_next, btn_customer_shipping_previous;


    String str_customer_shipping_name, str_customer_shipping_address_line1, str_customer_shipping_address_line2, str_customer_shipping_city, str_customer_shipping_state, str_customer_shipping_pincode,
            str_customer_shipping_tincode, str_customer_shipping_cstno, str_customer_shipping_eccno, str_customer_shipping_panno, str_customer_shipping_contact_person, str_customer_shipping_contact_number, str_customer_shipping_email="";

    SpotsDialog dialog;

    CheckBox shipping_same_as_previous;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fourth_shipping);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        shipping_same_as_previous = (CheckBox) findViewById(R.id.customer_details_shipping_same_previous);


        btn_customer_shipping_next = (Button) findViewById(R.id.customer_shipping_next);
        btn_customer_shipping_previous = (Button) findViewById(R.id.customer_shipping_previous);

        edt_customer_shipping_address_line1 = (EditText) findViewById(R.id.customer_shipping_address_line1);
        edt_customer_shipping_address_line2 = (EditText) findViewById(R.id.customer_shipping_address_line2);
        edt_customer_shipping_city = (EditText) findViewById(R.id.customer_shipping_city);
        edt_customer_shipping_state = (EditText) findViewById(R.id.customer_shipping_state);
        edt_customer_shipping_pincode = (EditText) findViewById(R.id.customer_shipping_pincode);
        edt_customer_shipping_tincode = (EditText) findViewById(R.id.customer_shipping_tinno);
        edt_customer_shipping_cstno = (EditText) findViewById(R.id.customer_shipping_cstno);
        edt_customer_shipping_eccno = (EditText) findViewById(R.id.customer_shipping_eccno);
        edt_customer_shipping_panno = (EditText) findViewById(R.id.customer_shipping_panno);
        edt_customer_shipping_contact_person = (EditText) findViewById(R.id.customer_shipping_contact_person);
        edt_customer_shipping_contact_number = (EditText) findViewById(R.id.customer_shipping_contactno);
        edt_customer_shipping_email = (EditText) findViewById(R.id.customer_shipping_email);


        shipping_same_as_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shipping_same_as_previous.isChecked()){

                    System.out.println("CHECKED");

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


                    str_customer_shipping_address_line1 = sharedPreferences.getString("str_customer_billing_address_line1", "str_customer_billing_address_line1");
                    str_customer_shipping_address_line2 = sharedPreferences.getString("str_customer_billing_address_line2", "str_customer_billing_address_line2");
                    str_customer_shipping_city = sharedPreferences.getString("str_customer_billing_city", "str_customer_billing_city");
                    str_customer_shipping_state = sharedPreferences.getString("str_customer_billing_state", "str_customer_billing_state");
                    str_customer_shipping_pincode = sharedPreferences.getString("str_customer_billing_pincode", "str_customer_billing_pincode");
                    str_customer_shipping_tincode = sharedPreferences.getString("str_customer_billing_tincode", "str_customer_billing_tincode");
                    str_customer_shipping_cstno = sharedPreferences.getString("str_customer_billing_cstno", "str_customer_billing_cstno");
                    str_customer_shipping_eccno = sharedPreferences.getString("str_customer_billing_eccno", "str_customer_billing_eccno");
                    str_customer_shipping_panno = sharedPreferences.getString("str_customer_billing_panno", "str_customer_billing_panno");
                    str_customer_shipping_contact_person = sharedPreferences.getString("str_customer_billing_contact_person", "str_customer_billing_contact_person");
                    str_customer_shipping_contact_number = sharedPreferences.getString("str_customer_billing_contact_number", "str_customer_billing_contact_number");
                    str_customer_shipping_email = sharedPreferences.getString("str_customer_billing_email", "str_customer_billing_email");


                    try {


                        edt_customer_shipping_address_line1.setText(str_customer_shipping_address_line1);
                        edt_customer_shipping_address_line2.setText(str_customer_shipping_address_line2);
                        edt_customer_shipping_city.setText(str_customer_shipping_city);
                        edt_customer_shipping_state.setText(str_customer_shipping_state);
                        edt_customer_shipping_pincode.setText(str_customer_shipping_pincode);
                        edt_customer_shipping_tincode.setText(str_customer_shipping_tincode);
                        edt_customer_shipping_cstno.setText(str_customer_shipping_cstno);
                        edt_customer_shipping_eccno.setText(str_customer_shipping_eccno);
                        edt_customer_shipping_panno.setText(str_customer_shipping_panno);
                        edt_customer_shipping_contact_person.setText(str_customer_shipping_contact_person);
                        edt_customer_shipping_contact_number.setText(str_customer_shipping_contact_number);
                        edt_customer_shipping_email.setText(str_customer_shipping_email);

                    } catch (Exception e) {

                    }

                }else {

                    try {


                        edt_customer_shipping_address_line1.setText("");
                        edt_customer_shipping_address_line2.setText("");
                        edt_customer_shipping_city.setText("");
                        edt_customer_shipping_state.setText("");
                        edt_customer_shipping_pincode.setText("");
                        edt_customer_shipping_tincode.setText("");
                        edt_customer_shipping_cstno.setText("");
                        edt_customer_shipping_eccno.setText("");
                        edt_customer_shipping_panno.setText("");
                        edt_customer_shipping_contact_person.setText("");
                        edt_customer_shipping_contact_number.setText("");
                        edt_customer_shipping_email.setText("");


                    } catch (Exception e) {

                    }

                }

            }
        });


        btn_customer_shipping_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_customer_shipping_address_line1 = edt_customer_shipping_address_line1.getText().toString();
                str_customer_shipping_address_line2 = edt_customer_shipping_address_line2.getText().toString();
                str_customer_shipping_city = edt_customer_shipping_city.getText().toString();
                str_customer_shipping_state = edt_customer_shipping_state.getText().toString();
                str_customer_shipping_pincode = edt_customer_shipping_pincode.getText().toString();
                str_customer_shipping_tincode = edt_customer_shipping_tincode.getText().toString();
                str_customer_shipping_cstno = edt_customer_shipping_cstno.getText().toString();
                str_customer_shipping_eccno = edt_customer_shipping_eccno.getText().toString();
                str_customer_shipping_panno = edt_customer_shipping_panno.getText().toString();
                str_customer_shipping_contact_person = edt_customer_shipping_contact_person.getText().toString();
                str_customer_shipping_contact_number = edt_customer_shipping_contact_number.getText().toString();
                str_customer_shipping_email = edt_customer_shipping_email.getText().toString();

               if (str_customer_shipping_address_line1.equals("")) {

                    edt_customer_shipping_address_line1.setError("Please Enter Customer address line1");
                    TastyToast.makeText(getApplicationContext(), "Customer address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_address_line2.equals("")) {

                    edt_customer_shipping_address_line2.setError("Please Enter Customer address line2");
                    TastyToast.makeText(getApplicationContext(), "Customer address line2 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_city.equals("")) {

                    edt_customer_shipping_city.setError("Please Enter Customer city");
                    TastyToast.makeText(getApplicationContext(), "Customer city", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_state.equals("")) {

                    edt_customer_shipping_state.setError("Please Enter Customer state");
                    TastyToast.makeText(getApplicationContext(), "Customer state", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_pincode.equals("")) {
                    edt_customer_shipping_pincode.setError("Please Enter Customer pincode");
                    TastyToast.makeText(getApplicationContext(), "Customer pincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_tincode.equals("")) {
                    edt_customer_shipping_tincode.setError("Please Enter Customer tincode");
                    TastyToast.makeText(getApplicationContext(), "Customer tincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_cstno.equals("")) {
                    edt_customer_shipping_cstno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_eccno.equals("")) {
                    edt_customer_shipping_eccno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_panno.equals("")) {
                    edt_customer_shipping_panno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_contact_person.equals("")) {
                    edt_customer_shipping_contact_person.setError("Please Enter Customer contact person");
                    TastyToast.makeText(getApplicationContext(), "Customer contact person", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_contact_number.equals("")) {
                    edt_customer_shipping_contact_number.setError("Please Enter Customer contact number");
                    TastyToast.makeText(getApplicationContext(), "Customer contact number", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_shipping_email.equals("")) {
                    edt_customer_shipping_email.setError("Please Enter Customer email");
                    TastyToast.makeText(getApplicationContext(), "Customer email", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("str_customer_shipping_address_line1", str_customer_shipping_address_line1);
                    editor.putString("str_customer_shipping_address_line2", str_customer_shipping_address_line2);
                    editor.putString("str_customer_shipping_city", str_customer_shipping_city);
                    editor.putString("str_customer_shipping_state", str_customer_shipping_state);
                    editor.putString("str_customer_shipping_pincode", str_customer_shipping_pincode);
                    editor.putString("str_customer_shipping_tincode", str_customer_shipping_tincode);
                    editor.putString("str_customer_shipping_cstno", str_customer_shipping_cstno);
                    editor.putString("str_customer_shipping_eccno", str_customer_shipping_eccno);
                    editor.putString("str_customer_shipping_panno", str_customer_shipping_panno);
                    editor.putString("str_customer_shipping_contact_person", str_customer_shipping_contact_person);
                    editor.putString("str_customer_shipping_contact_number", str_customer_shipping_contact_number);
                    editor.putString("str_customer_shipping_email", str_customer_shipping_email);

                    editor.commit();

                    Intent i = new Intent(getApplicationContext(), Activity_Order_Five_Product_details.class);
                    startActivity(i);
                    finish();


                }


            }
        });
        btn_customer_shipping_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Order_Three_Customerdetails_Billing.class);
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

            str_customer_shipping_address_line1 = sharedPreferences.getString("str_customer_shipping_address_line1", "str_customer_shipping_address_line1");
            str_customer_shipping_address_line2 = sharedPreferences.getString("str_customer_shipping_address_line2", "str_customer_shipping_address_line2");
            str_customer_shipping_city = sharedPreferences.getString("str_customer_shipping_city", "str_customer_shipping_city");
            str_customer_shipping_state = sharedPreferences.getString("str_customer_shipping_state", "str_customer_shipping_state");
            str_customer_shipping_pincode = sharedPreferences.getString("str_customer_shipping_pincode", "str_customer_shipping_pincode");
            str_customer_shipping_tincode = sharedPreferences.getString("str_customer_shipping_tincode", "str_customer_shipping_tincode");
            str_customer_shipping_cstno = sharedPreferences.getString("str_customer_shipping_cstno", "str_customer_shipping_cstno");
            str_customer_shipping_eccno = sharedPreferences.getString("str_customer_shipping_eccno", "str_customer_shipping_eccno");
            str_customer_shipping_panno = sharedPreferences.getString("str_customer_shipping_panno", "str_customer_shipping_panno");
            str_customer_shipping_contact_person = sharedPreferences.getString("str_customer_shipping_contact_person", "str_customer_shipping_contact_person");
            str_customer_shipping_contact_number = sharedPreferences.getString("str_customer_shipping_contact_number", "str_customer_shipping_contact_number");
            str_customer_shipping_email = sharedPreferences.getString("str_customer_shipping_email", "str_customer_shipping_email");

            try {

                edt_customer_shipping_address_line1.setText(str_customer_shipping_address_line1);
                edt_customer_shipping_address_line2.setText(str_customer_shipping_address_line2);
                edt_customer_shipping_city.setText(str_customer_shipping_city);
                edt_customer_shipping_state.setText(str_customer_shipping_state);
                edt_customer_shipping_pincode.setText(str_customer_shipping_pincode);
                edt_customer_shipping_tincode.setText(str_customer_shipping_tincode);
                edt_customer_shipping_cstno.setText(str_customer_shipping_cstno);
                edt_customer_shipping_eccno.setText(str_customer_shipping_eccno);
                edt_customer_shipping_panno.setText(str_customer_shipping_panno);
                edt_customer_shipping_contact_person.setText(str_customer_shipping_contact_person);
                edt_customer_shipping_contact_number.setText(str_customer_shipping_contact_number);
                edt_customer_shipping_email.setText(str_customer_shipping_email);

            } catch (Exception e) {

            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
