package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 26/3/17.
 */

public class Activity_Order_Three_Customerdetails_Billing extends AppCompatActivity {

    EditText  edt_customer_name,edt_customer_billing_address_line1, edt_customer_billing_address_line2, edt_customer_billing_city, edt_customer_billing_state, edt_customer_billing_pincode, edt_customer_billing_tincode, edt_customer_billing_cstno, edt_customer_billing_eccno, edt_customer_billing_panno, edt_customer_billing_contact_person, edt_customer_billing_contact_number, edt_customer_billing_email;

    Button btn_customer_billing_next,btn_customer_billing_previous;

    RadioButton ratio_same;

    String str_customer_billing_name,str_customer_billing_address_line1,str_customer_billing_address_line2,str_customer_billing_city,str_customer_billing_state,str_customer_billing_pincode,str_customer_billing_tincode,str_customer_billing_cstno,str_customer_billing_eccno,str_customer_billing_panno,str_customer_billing_contact_person,str_customer_billing_contact_number,str_customer_billing_email;

    SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_third_customerdetails_billing);


        ratio_same=(RadioButton)findViewById(R.id.customer_details_billing_same_address);

        btn_customer_billing_next = (Button) findViewById(R.id.customer_details_billing_next);
        btn_customer_billing_previous = (Button) findViewById(R.id.customer_details_billing_previous);

        edt_customer_billing_address_line1 = (EditText) findViewById(R.id.customer_details_billing_address_line1);
        edt_customer_billing_address_line2 = (EditText) findViewById(R.id.customer_details_billing_address_line2);
        edt_customer_billing_city = (EditText) findViewById(R.id.customer_details_billing_city);
        edt_customer_billing_state = (EditText) findViewById(R.id.customer_details_billing_state);
        edt_customer_billing_pincode = (EditText) findViewById(R.id.customer_details_billing_pincode);
        edt_customer_billing_tincode = (EditText) findViewById(R.id.customer_details_billing_tinno);
        edt_customer_billing_cstno = (EditText) findViewById(R.id.customer_details_billing_cstno);
        edt_customer_billing_eccno = (EditText) findViewById(R.id.customer_details_billing_eccno);
        edt_customer_billing_panno = (EditText) findViewById(R.id.customer_details_billing_panno);
        edt_customer_billing_contact_person = (EditText) findViewById(R.id.customer_details_billing_contact_person);
        edt_customer_billing_contact_number = (EditText) findViewById(R.id.customer_details_billing_contactno);
        edt_customer_billing_email = (EditText) findViewById(R.id.order_customer_billing_email);



        ratio_same.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                str_customer_billing_address_line1=sharedPreferences.getString("str_customer_address_line1","str_customer_address_line1");
                str_customer_billing_address_line2=sharedPreferences.getString("(str_customer_address_line2","(str_customer_address_line2");
                str_customer_billing_city=sharedPreferences.getString("str_customer_city","str_customer_city");
                str_customer_billing_state=sharedPreferences.getString("str_customer_state","str_customer_state");
                str_customer_billing_pincode=sharedPreferences.getString("str_customer_pincode","str_customer_pincode");
                str_customer_billing_tincode=sharedPreferences.getString("str_customer_tincode","str_customer_tincode");
                str_customer_billing_cstno=sharedPreferences.getString("str_customer_cstno","str_customer_cstno");
                str_customer_billing_eccno=sharedPreferences.getString("str_customer_eccno","str_customer_eccno");
                str_customer_billing_panno=sharedPreferences.getString("str_customer_panno","str_customer_panno");
                str_customer_billing_contact_person=sharedPreferences.getString("str_customer_contact_person","str_customer_contact_person");
                str_customer_billing_contact_number=sharedPreferences.getString("str_customer_contact_number","str_customer_contact_number");
                str_customer_billing_email=sharedPreferences.getString("str_customer_email","str_customer_email");


            }
        });
        try {


            edt_customer_billing_address_line1.setText(str_customer_billing_address_line1);
            edt_customer_billing_address_line2.setText(str_customer_billing_address_line2);
            edt_customer_billing_city.setText(str_customer_billing_city);
            edt_customer_billing_state.setText(str_customer_billing_state);
            edt_customer_billing_pincode.setText(str_customer_billing_pincode);
            edt_customer_billing_tincode.setText(str_customer_billing_tincode);
            edt_customer_billing_cstno.setText(str_customer_billing_cstno);
            edt_customer_billing_eccno.setText(str_customer_billing_eccno);
            edt_customer_billing_panno.setText(str_customer_billing_panno);
            edt_customer_billing_contact_person.setText(str_customer_billing_contact_person);
            edt_customer_billing_contact_number.setText(str_customer_billing_contact_number);
            edt_customer_billing_email.setText(str_customer_billing_email);
        } catch (Exception e) {

        }


        btn_customer_billing_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (str_customer_billing_name.equals("")) {
                    edt_customer_name.setError("Please Enter Customer name");
                    TastyToast.makeText(getApplicationContext(), "Customer name is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_customer_billing_address_line1.equals("")) {

                    edt_customer_billing_address_line1.setError("Please Enter Customer address line1");
                    TastyToast.makeText(getApplicationContext(), "Customer address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_address_line2.equals("")){

                    edt_customer_billing_address_line2.setError("Please Enter Customer address line2");
                    TastyToast.makeText(getApplicationContext(), "Customer address line2 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_billing_city.equals("")){

                    edt_customer_billing_city.setError("Please Enter Customer city");
                    TastyToast.makeText(getApplicationContext(), "Customer city", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_state.equals("")){

                    edt_customer_billing_state.setError("Please Enter Customer state");
                    TastyToast.makeText(getApplicationContext(), "Customer state", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_pincode.equals("")){
                    edt_customer_billing_pincode.setError("Please Enter Customer pincode");
                    TastyToast.makeText(getApplicationContext(), "Customer pincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_tincode.equals("")){
                    edt_customer_billing_tincode.setError("Please Enter Customer tincode");
                    TastyToast.makeText(getApplicationContext(), "Customer tincode", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_cstno.equals("")){
                    edt_customer_billing_cstno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_billing_eccno.equals("")){
                    edt_customer_billing_eccno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_billing_panno.equals("")){
                    edt_customer_billing_panno.setError("Please Enter Customer cstno");
                    TastyToast.makeText(getApplicationContext(), "Customer cstno", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_customer_billing_contact_person.equals("")){
                    edt_customer_billing_contact_person.setError("Please Enter Customer contact person");
                    TastyToast.makeText(getApplicationContext(), "Customer contact person", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_contact_number.equals("")){
                    edt_customer_billing_contact_number.setError("Please Enter Customer contact number");
                    TastyToast.makeText(getApplicationContext(), "Customer contact number", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_billing_email.equals("")){
                    edt_customer_billing_email.setError("Please Enter Customer email");
                    TastyToast.makeText(getApplicationContext(), "Customer email", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else {
                    dialog = new SpotsDialog(Activity_Order_Three_Customerdetails_Billing.this);
                    dialog.show();

                }*/

                Intent i = new Intent(getApplicationContext(),Activity_Order_Four_Shipping.class);
                startActivity(i);
                finish();



            }
        });
        btn_customer_billing_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Order_Two_Customerdetails.class);
                startActivity(i);
                finish();
            }
        });

    }
}
