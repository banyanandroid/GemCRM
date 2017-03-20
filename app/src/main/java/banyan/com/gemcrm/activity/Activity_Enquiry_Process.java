package banyan.com.gemcrm.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import banyan.com.gemcrm.R;

/**
 * Created by User on 3/20/2017.
 */

public class Activity_Enquiry_Process extends AppCompatActivity {

    String str_select_id, str_select_comp_name, str_select_phoneno, str_select_email, str_select_comp_address, str_select_pin, str_select_person_name,
            str_select_person_number, str_select_produc_series, str_select_desc, str_select_enq_throu,
            getStr_select_enq_throu_desc, str_select_createdon, str_select_completeon = "";

    TextView txt_created_on, txt_enq_id, txt_enq_company_name, txt_enq_txt_email, txt_enq_address, txt_enq_pin, txt_enq_phone, txt_enq_conact_person,
            txt_enq_person_phone, txt_enq_product, txt_enq_enq_through, txt_enq_enq_thro_des;

    EditText edt_spec, edt_discount;


    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_process);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        txt_created_on = (TextView) findViewById(R.id.enq_process_txt_app_created_on);
        txt_enq_id = (TextView) findViewById(R.id.enq_process_txt_id);
        txt_enq_company_name = (TextView) findViewById(R.id.enq_process_txt_company_name);
        txt_enq_txt_email = (TextView) findViewById(R.id.enq_process_txt_email);
        txt_enq_address = (TextView) findViewById(R.id.enq_process_txt_address);
        txt_enq_pin = (TextView) findViewById(R.id.enq_process_txt_pin);
        txt_enq_phone = (TextView) findViewById(R.id.enq_process_txt_comp_phone);
        txt_enq_conact_person = (TextView) findViewById(R.id.enq_process_txt_conact_person);
        txt_enq_person_phone = (TextView) findViewById(R.id.enq_process_txt_contact_person_phone);
        txt_enq_product = (TextView) findViewById(R.id.enq_process_txt_product);
        txt_enq_enq_through = (TextView) findViewById(R.id.enq_process_txt_enq_thro);
        txt_enq_enq_thro_des = (TextView) findViewById(R.id.enq_process_txt_enq_thro_desc);

        edt_discount = (EditText) findViewById(R.id.add_appoint_edt_name);
        edt_spec = (EditText) findViewById(R.id.add_appoint_edt_discount);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_select_createdon = sharedPreferences.getString("enq_created_on", "enq_created_on");
        str_select_id = sharedPreferences.getString("enq_no", "enq_no");
        str_select_comp_name = sharedPreferences.getString("enq_company_name", "enq_company_name");
        str_select_email = sharedPreferences.getString("enq_company_email", "enq_company_email");
        str_select_comp_address = sharedPreferences.getString("enq_company_address", "enq_company_address");
        str_select_pin = sharedPreferences.getString("enq_company_pincode", "enq_company_pincode");
        str_select_phoneno = sharedPreferences.getString("enq_company_phn_no", "enq_company_phn_no");
        str_select_person_name = sharedPreferences.getString("enq_contact_person_name", "enq_contact_person_name");
        str_select_person_number = sharedPreferences.getString("enq_contact_person_phone_no", "enq_contact_person_phone_no");
        str_select_produc_series = sharedPreferences.getString("enq_product_series", "enq_product_series");
        str_select_enq_throu = sharedPreferences.getString("enquiry_through", "enquiry_through");
        getStr_select_enq_throu_desc = sharedPreferences.getString("enquiry_through_description", "enquiry_through_description");
        str_select_desc = sharedPreferences.getString("enq_description", "enq_description");

        try {

            txt_created_on.setText(str_select_completeon);
            txt_enq_id.setText(str_select_id);
            txt_enq_company_name.setText(str_select_comp_name);
            txt_enq_txt_email.setText(str_select_email);
            txt_enq_address.setText(str_select_comp_address);
            txt_enq_pin.setText(str_select_pin);
            txt_enq_phone.setText(str_select_phoneno);
            txt_enq_conact_person.setText(str_select_person_name);
            txt_enq_person_phone.setText(str_select_person_number);
            txt_enq_product.setText(str_select_produc_series);
            txt_enq_enq_through.setText(str_select_enq_throu);
            txt_enq_enq_thro_des.setText(getStr_select_enq_throu_desc);
            edt_spec.setText("" + str_select_desc);



        } catch (Exception e) {

        }



    }

}
