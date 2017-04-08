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

import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 27/3/17.
 */

public class Activity_Order_Seven_Dispatch extends AppCompatActivity {

    EditText edt_dispatch_contact_person_name, edt_dispatch_contact_number, edt_dispatch_address_line1, edt_dispatch_address_line2, edt_dispatch_city, edt_dispatch_state, edt_dispatch_pincode, edt_dispatch_commissioning_instructions, edt_dispatch_prepared, edt_dispatch_checked, edt_dispatch_approved;


    Button btn_dispatch_submit,btn_dispatch_previous;

    String str_dispatch_contact_person_name, str_dispatch_contact_number, str_dispatch_address_line1, str_dispatch_address_line2, str_dispatch_city, str_dispatch_state, str_dispatch_pincode, str_customer_commissioning_instructions, str_dispatch_prepared, str_dispatch_checked, str_dispatch_approved = "";

    SpotsDialog dialog;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_seventh_dispatch);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        btn_dispatch_submit = (Button) findViewById(R.id.order_dispatch_submit);
        btn_dispatch_previous=(Button)findViewById(R.id.order_dispatch_previous);

        edt_dispatch_contact_person_name = (EditText) findViewById(R.id.customer_dispatch_contact_person_name);
        edt_dispatch_contact_number = (EditText) findViewById(R.id.customer_dispatch_contact_number);
        edt_dispatch_address_line1 = (EditText) findViewById(R.id.customer_dispatch_address_line1);
        edt_dispatch_address_line2 = (EditText) findViewById(R.id.customer_dispatch_address_line2);
        edt_dispatch_city = (EditText) findViewById(R.id.customer_dispatch_city);
        edt_dispatch_state = (EditText) findViewById(R.id.customer_dispatch_state);
        edt_dispatch_pincode = (EditText) findViewById(R.id.order_customer_dispatch_pincode);
        edt_dispatch_commissioning_instructions = (EditText) findViewById(R.id.customer_dispatch_commissioning_instruction);
        edt_dispatch_prepared = (EditText) findViewById(R.id.dispatch_prepared);
        edt_dispatch_checked = (EditText) findViewById(R.id.dispatch_checked);
        edt_dispatch_approved = (EditText) findViewById(R.id.dispatch_approved);




        btn_dispatch_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_dispatch_contact_person_name = edt_dispatch_contact_person_name.getText().toString();
                str_dispatch_contact_number = edt_dispatch_contact_number.getText().toString();
                str_dispatch_address_line1 = edt_dispatch_address_line1.getText().toString();
                str_dispatch_address_line2 = edt_dispatch_address_line2.getText().toString();
                str_dispatch_city = edt_dispatch_city.getText().toString();
                str_dispatch_state = edt_dispatch_state.getText().toString();
                str_dispatch_pincode = edt_dispatch_pincode.getText().toString();
                str_customer_commissioning_instructions = edt_dispatch_commissioning_instructions.getText().toString();
                str_dispatch_prepared = edt_dispatch_prepared.getText().toString();
                str_dispatch_checked = edt_dispatch_checked.toString();
                str_dispatch_approved = edt_dispatch_approved.getText().toString();

                if (str_dispatch_contact_person_name.equals("")) {
                    edt_dispatch_contact_person_name.setError("Please Enter Contact person name");
                    TastyToast.makeText(getApplicationContext(), "Contact person name is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_contact_number.equals("")) {

                    edt_dispatch_contact_number.setError("Please Enter Contact number");
                    TastyToast.makeText(getApplicationContext(), "Contact number is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_address_line1.equals("")) {

                    edt_dispatch_address_line1.setError("Please Enter dispatch address line1");
                    TastyToast.makeText(getApplicationContext(), "dispatch address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_address_line2.equals("")) {

                    edt_dispatch_address_line2.setError("Please Enter dispatch address line1");
                    TastyToast.makeText(getApplicationContext(), " dispatch address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_city.equals("")) {

                    edt_dispatch_city.setError("Please Enter dispatch city");
                    TastyToast.makeText(getApplicationContext(), "dispatch city is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_state.equals("")) {
                    edt_dispatch_state.setError("Please Enter Customer  state");
                    TastyToast.makeText(getApplicationContext(), "Customer  state is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_pincode.equals("")) {
                    edt_dispatch_pincode.setError("Please Enter Customer pincode");
                    TastyToast.makeText(getApplicationContext(), "Customer pincode is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_commissioning_instructions.equals("")) {
                    edt_dispatch_commissioning_instructions.setError("Please Enter Commissioning instructions");
                    TastyToast.makeText(getApplicationContext(), "Commissioning instructions is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_prepared.equals("")) {
                    edt_dispatch_prepared.setError("Please Enter dispatch prepared");
                    TastyToast.makeText(getApplicationContext(), "dispatch prepared is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_checked.equals("")) {
                    edt_dispatch_checked.setError("Please Enter dispatch checked");
                    TastyToast.makeText(getApplicationContext(), " dispatch checked id empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_approved.equals("")) {
                    edt_dispatch_approved.setError("Please Enter  dispatch approved");
                    TastyToast.makeText(getApplicationContext(), "dispatch approved is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("str_dispatch_contact_person_name", str_dispatch_contact_person_name);
                    editor.putString("str_dispatch_contact_number", str_dispatch_contact_number);
                    editor.putString("str_dispatch_address_line1", str_dispatch_address_line1);
                    editor.putString("str_dispatch_address_line2", str_dispatch_address_line2);
                    editor.putString("str_dispatch_city", str_dispatch_city);
                    editor.putString("str_dispatch_state", str_dispatch_state);
                    editor.putString("str_dispatch_pincode", str_dispatch_pincode);
                    editor.putString("str_customer_commissioning_instructions", str_customer_commissioning_instructions);
                    editor.putString("str_dispatch_prepared", str_dispatch_prepared);
                    editor.putString("str_dispatch_checked", str_dispatch_checked);
                    editor.putString("str_dispatch_approved", str_dispatch_approved);

                    editor.commit();


                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();


                }


            }
        });

        btn_dispatch_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Order_Six_Product_details.class);
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


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
