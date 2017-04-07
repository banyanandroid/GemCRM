package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;

/**
 * Created by Schan on 26-Mar-17.
 **/

public class Activity_Order_Five_Product_details extends AppCompatActivity {


    EditText edt_others, edt_ld_clause_desc, edt_commission, edt_commission_value, edt_logistics_preferred;

    Button btn_next, btn_previous;

    Spinner spn_form_applicable, spn_insurance, spn_freight_terms,
            spn_payment_terms, spn_credit_days, spn_pbg_abg, spn_inspection,
            spn_ld_clause, spn_permit, spn_commission_to;

    String str_others, str_ld_clause_desc, str_commission, str_commission_value, str_logistics_preferred,
            str_form_applicable, str_insurance, str_freight_terms, str_payment_terms, str_credit_days,
            str_pbg_abg, str_inspection, str_ld_clause, str_permit, str_commission_to = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fifth_forwarding_memo_product_details);

        //EditText
        edt_others = (EditText) findViewById(R.id.edt_others);
        edt_ld_clause_desc = (EditText) findViewById(R.id.edt_ld_clause_description);
        edt_commission = (EditText) findViewById(R.id.edt_commission_percent);
        edt_commission_value = (EditText) findViewById(R.id.edt_commission_value);
        edt_logistics_preferred = (EditText) findViewById(R.id.edt_logistics_preferred);
        //Button
        btn_previous = (Button) findViewById(R.id.btn_previous);
        btn_next = (Button) findViewById(R.id.btn_next);
        //Spinner
        spn_form_applicable = (Spinner) findViewById(R.id.spn_form_applicable);
        spn_insurance = (Spinner) findViewById(R.id.spn_insurance);
        spn_freight_terms = (Spinner) findViewById(R.id.spn_freight_terms);
        spn_payment_terms = (Spinner) findViewById(R.id.spn_payment_terms);
        spn_credit_days = (Spinner) findViewById(R.id.spn_credit_days);
        spn_pbg_abg = (Spinner) findViewById(R.id.spn_pbg_abg);
        spn_inspection = (Spinner) findViewById(R.id.spn_inspesction);
        spn_ld_clause = (Spinner) findViewById(R.id.spn_ld_clause);
        spn_permit = (Spinner) findViewById(R.id.spn_permit);
        spn_commission_to = (Spinner) findViewById(R.id.spn_commission_to);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting SPINNER values in String
                str_form_applicable = String.valueOf(spn_form_applicable.getSelectedItem());
                str_insurance = String.valueOf(spn_insurance.getSelectedItem());
                str_freight_terms = String.valueOf(spn_freight_terms.getSelectedItem());
                str_payment_terms = String.valueOf(spn_payment_terms.getSelectedItem());
                str_credit_days = String.valueOf(spn_credit_days.getSelectedItem());
                str_pbg_abg = String.valueOf(spn_pbg_abg.getSelectedItem());
                str_inspection = String.valueOf(spn_inspection.getSelectedItem());
                str_ld_clause = String.valueOf(spn_ld_clause.getSelectedItem());
                str_permit = String.valueOf(spn_permit.getSelectedItem());
                str_commission_to = String.valueOf(spn_commission_to.getSelectedItem());

                //Getting EDIT_TEXT values in String
                str_others = edt_others.getText().toString();
                str_ld_clause_desc = edt_ld_clause_desc.getText().toString();
                str_commission = edt_commission.getText().toString();
                str_commission_value = edt_commission_value.getText().toString();
                str_logistics_preferred = edt_logistics_preferred.getText().toString();

               /* if (str_others.equals("")) {
                    edt_others.setError("Please Fill Others");
                    TastyToast.makeText(getApplicationContext(), "Others is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_ld_clause_desc.equals("")) {
                    edt_ld_clause_desc.setError("Please Enter LD Clause");
                    TastyToast.makeText(getApplicationContext(), "LD Clause is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_commission.equals("")) {
                    edt_commission.setError("Please Enter Commission");
                    TastyToast.makeText(getApplicationContext(), "Commission is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_commission_value.equals("")) {
                    edt_commission_value.setError("Please Enter Commission Value");
                    TastyToast.makeText(getApplicationContext(), "Commission Value is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_logistics_preferred.equals("")) {
                    edt_logistics_preferred.setError("Please Enter Logistics Preferred");
                    TastyToast.makeText(getApplicationContext(), "Logistics Preferred is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else {
                   *//* dialog = new SpotsDialog(Activity_Appoinment_Add.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Appoinment_Add.this);
                    Function_Add_Campaign();*//*

                }*/

                Intent i = new Intent(getApplicationContext(),Activity_Order_Six_Product_details.class);
                startActivity(i);
                finish();

            }
        });


    }

}