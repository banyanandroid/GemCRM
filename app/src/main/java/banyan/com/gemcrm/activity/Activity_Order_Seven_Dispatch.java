package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 27/3/17.
 */

public class Activity_Order_Seven_Dispatch extends AppCompatActivity {

    TextView txt_dispatch_contact_person_name,txt_dispatch_contact_number,txt_dispatch_address_line1,txt_dispatch_address_line2,txt_dispatch_city,txt_dispatch_state,txt_dispatch_pincode,txt_dispatch_commissioning_instructions,txt_dispatch_prepared,txt_dispatch_checked,txt_dispatch_approved;

    Button btn_dispatch_submit;

    String str_dispatch_contact_person_name,str_dispatch_contact_number,str_dispatch_address_line1,str_dispatch_address_line2,str_dispatch_city,str_dispatch_state,str_dispatch_pincode,str_customer_commissioning_instructions,str_dispatch_prepared,str_dispatch_checked,str_dispatch_approved;

    SpotsDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_seventh_dispatch);


        btn_dispatch_submit = (Button) findViewById(R.id.order_dispatch_submit);

        txt_dispatch_contact_person_name = (TextView) findViewById(R.id.customer_dispatch_contact_person_name);
        txt_dispatch_contact_number = (TextView) findViewById(R.id.customer_dispatch_contact_number);
        txt_dispatch_address_line1=(TextView)findViewById(R.id.customer_dispatch_address_line1);
        txt_dispatch_address_line2=(TextView)findViewById(R.id.customer_dispatch_address_line2);
        txt_dispatch_city=(TextView)findViewById(R.id.customer_dispatch_city);
        txt_dispatch_state=(TextView)findViewById(R.id.customer_dispatch_state);
        txt_dispatch_pincode=(TextView)findViewById(R.id.order_customer_dispatch_pincode);
        txt_dispatch_commissioning_instructions=(TextView)findViewById(R.id.customer_dispatch_commissioning_instruction);
        txt_dispatch_prepared=(TextView)findViewById(R.id.dispatch_prepared);
        txt_dispatch_checked=(TextView)findViewById(R.id.dispatch_checked);
        txt_dispatch_approved=(TextView)findViewById(R.id.dispatch_approved);


        try {

            txt_dispatch_contact_person_name.setText(str_dispatch_contact_person_name);
            txt_dispatch_contact_number.setText(str_dispatch_contact_number);
            txt_dispatch_address_line1.setText(str_dispatch_address_line1);
            txt_dispatch_address_line2.setText(str_dispatch_address_line2);
            txt_dispatch_city.setText(str_dispatch_city);
            txt_dispatch_state.setText(str_dispatch_state);
            txt_dispatch_pincode.setText(str_dispatch_pincode);
            txt_dispatch_commissioning_instructions.setText(str_customer_commissioning_instructions);
            txt_dispatch_prepared.setText(str_dispatch_prepared);
            txt_dispatch_checked.setText(str_dispatch_checked);
            txt_dispatch_approved.setText(str_dispatch_approved);

        } catch (Exception e) {

        }
        btn_dispatch_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (str_dispatch_contact_person_name.equals("")) {
                    txt_dispatch_contact_person_name.setError("Please Enter Contact person name");
                    TastyToast.makeText(getApplicationContext(), "Contact person name is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_dispatch_contact_number.equals("")) {

                    txt_dispatch_contact_number.setError("Please Enter Contact number");
                    TastyToast.makeText(getApplicationContext(), "Contact number is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_dispatch_address_line1.equals("")){

                    txt_dispatch_address_line1.setError("Please Enter dispatch address line1");
                    TastyToast.makeText(getApplicationContext(), "dispatch address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_dispatch_address_line2.equals("")){

                    txt_dispatch_address_line2.setError("Please Enter dispatch address line1");
                    TastyToast.makeText(getApplicationContext(), " dispatch address line1 is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_dispatch_city.equals("")){

                    txt_dispatch_city.setError("Please Enter dispatch city");
                    TastyToast.makeText(getApplicationContext(), "dispatch city is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_dispatch_state.equals("")){
                    txt_dispatch_state.setError("Please Enter Customer  state");
                    TastyToast.makeText(getApplicationContext(), "Customer  state is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_dispatch_pincode.equals("")){
                    txt_dispatch_pincode.setError("Please Enter Customer pincode");
                    TastyToast.makeText(getApplicationContext(), "Customer pincode is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if(str_customer_commissioning_instructions.equals("")){
                    txt_dispatch_commissioning_instructions.setError("Please Enter Commissioning instructions");
                    TastyToast.makeText(getApplicationContext(), "Commissioning instructions is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_dispatch_prepared.equals("")){
                    txt_dispatch_prepared.setError("Please Enter dispatch prepared");
                    TastyToast.makeText(getApplicationContext(), "dispatch prepared is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_dispatch_checked.equals("")){
                    txt_dispatch_checked.setError("Please Enter dispatch checked");
                    TastyToast.makeText(getApplicationContext(), " dispatch checked id empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else if (str_dispatch_approved.equals("")){
                    txt_dispatch_approved.setError("Please Enter  dispatch approved");
                    TastyToast.makeText(getApplicationContext(), "dispatch approved is empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                }else {
                    dialog = new SpotsDialog(Activity_Order_Seven_Dispatch.this);
                    dialog.show();

                }*/

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();



            }
        });


    }

    }
