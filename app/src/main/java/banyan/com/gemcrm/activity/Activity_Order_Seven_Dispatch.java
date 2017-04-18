package banyan.com.gemcrm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;

/**
 * Created by steve on 27/3/17.
 */

public class Activity_Order_Seven_Dispatch extends AppCompatActivity {

    EditText edt_dispatch_contact_person_name, edt_dispatch_contact_number, edt_dispatch_address_line1, edt_dispatch_address_line2, edt_dispatch_city,
            edt_dispatch_state, edt_dispatch_pincode, edt_dispatch_commissioning_instructions;


    Button btn_dispatch_submit, btn_dispatch_previous;

    String str_dispatch_contact_person_name, str_dispatch_contact_number, str_dispatch_address_line1, str_dispatch_address_line2, str_dispatch_city,
            str_dispatch_state, str_dispatch_pincode, str_customer_commissioning_instructions = "";

    //Strings to get values from other forms
    // FORM 1
    String str_price_list, str_send_to, str_txt_q_ref, str_txt_q_date,
            str_txt_po_ref, str_txt_po_date, str_txt_sapcode, str_order_from = "";

    // FORM 2
    String str_customer_name, str_customer_address_line1, str_customer_address_line2, str_customer_city, str_customer_state, str_customer_pincode, str_customer_tincode, str_customer_cstno,
            str_customer_eccno, str_customer_panno, str_customer_contact_person, str_customer_contact_number, str_customer_email = "";

    //FORM 3
    String str_customer_billing_address_line1, str_customer_billing_address_line2, str_customer_billing_city, str_customer_billing_state, str_customer_billing_pincode,
            str_customer_billing_tincode, str_customer_billing_cstno, str_customer_billing_eccno, str_customer_billing_panno, str_customer_billing_contact_person, str_customer_billing_contact_number, str_customer_billing_email = "";

    //FORM 4
    String str_customer_shipping_address_line1, str_customer_shipping_address_line2, str_customer_shipping_city, str_customer_shipping_state, str_customer_shipping_pincode,
            str_customer_shipping_tincode, str_customer_shipping_cstno, str_customer_shipping_eccno, str_customer_shipping_panno, str_customer_shipping_contact_person, str_customer_shipping_contact_number, str_customer_shipping_email = "";

    //FORM 5
    String str_model_one, str_model_two, str_model_three, str_model_four, str_model_five, str_model_six,
            str_sap_code_one, str_sap_code_two, str_sap_code_three, str_sap_code_four, str_sap_code_five, str_sap_code_six,
            str_desc_one, str_desc_two, str_desc_three, str_desc_four, str_desc_five, str_desc_six,
            str_quantity_one, str_quantity_two, str_quantity_three, str_quantity_four, str_quantity_five, str_quantity_six,
            str_list_price_one, str_list_price_two, str_list_price_three, str_list_price_four, str_list_price_five, str_list_price_six,
            str_discount_one, str_discount_two, str_discount_three, str_discount_four, str_discount_five, str_discount_six,
            str_actual_price_one, str_actual_price_two, str_actual_price_three, str_actual_price_four, str_actual_price_five, str_actual_price_six,
            str_req_date_one, str_req_date_two, str_req_date_three, str_req_date_four, str_req_date_five, str_req_date_six,
            str_qno1, str_qno2, str_qno3, str_qno4, str_qno5, str_qno6,

    str_note, str_total_value, str_p_and_f, str_VAT_CET, str_BET, str_p_and_f_value,
            str_VAT_CET_value, str_BET_value, str_grand_total = "";

    //FORM 6

    String str_others, str_ld_clause_desc, str_commission, str_commission_value, str_logistics_preferred,
            str_form_applicable, str_form_five_insurance, str_freight_terms, str_payment_terms, str_credit_days,
            str_pbg_abg, str_inspection, str_ld_clause, str_permit, str_commission_to = "";

    String str_enq_no = "";


    SpotsDialog dialog;

    private Toolbar mToolbar;

    String TAG = "reg";

    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_seventh_dispatch);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        btn_dispatch_submit = (Button) findViewById(R.id.order_dispatch_submit);
        btn_dispatch_previous = (Button) findViewById(R.id.order_dispatch_previous);

        edt_dispatch_contact_person_name = (EditText) findViewById(R.id.customer_dispatch_contact_person_name);
        edt_dispatch_contact_number = (EditText) findViewById(R.id.customer_dispatch_contact_number);
        edt_dispatch_address_line1 = (EditText) findViewById(R.id.customer_dispatch_address_line1);
        edt_dispatch_address_line2 = (EditText) findViewById(R.id.customer_dispatch_address_line2);
        edt_dispatch_city = (EditText) findViewById(R.id.customer_dispatch_city);
        edt_dispatch_state = (EditText) findViewById(R.id.customer_dispatch_state);
        edt_dispatch_pincode = (EditText) findViewById(R.id.customer_dispatch_pincode);
        edt_dispatch_commissioning_instructions = (EditText) findViewById(R.id.customer_dispatch_commissioning_instruction);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_enq_no = sharedPreferences.getString("ofm_enq_no", "ofm_enq_no");

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

                    edt_dispatch_address_line2.setError("Please Enter dispatch address line2");
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

                } else {


                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("str_dispatch_address_line1", str_dispatch_address_line1);
                    editor.putString("str_dispatch_address_line2", str_dispatch_address_line2);
                    editor.putString("str_dispatch_city", str_dispatch_city);
                    editor.putString("str_dispatch_state", str_dispatch_state);
                    editor.putString("str_dispatch_pincode", str_dispatch_pincode);
                    editor.putString("str_customer_commissioning_instructions", str_customer_commissioning_instructions);
                    editor.putString("str_dispatch_contact_person_name", str_dispatch_contact_person_name);
                    editor.putString("str_dispatch_contact_number", str_dispatch_contact_number);

                    editor.commit();

                    try {

                        SharedPreferences sharedPreferences_two = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                        //from FORM 1
                        str_price_list = sharedPreferences.getString("str_price_list", "str_price_list");
                        str_send_to = sharedPreferences.getString("str_send_to", "str_send_to");
                        str_txt_q_ref = sharedPreferences.getString("str_txt_q_ref", "str_txt_q_ref");
                        str_txt_q_date = sharedPreferences.getString("str_txt_q_date", "str_txt_q_date");
                        str_txt_po_ref = sharedPreferences.getString("str_txt_po_ref", "str_txt_po_ref");
                        str_txt_po_date = sharedPreferences.getString("str_txt_po_date", "str_txt_po_date");
                        str_txt_sapcode = sharedPreferences.getString("str_txt_sapcode", "str_txt_sapcode");
                        str_order_from = sharedPreferences.getString("str_order_from", "str_order_from");

                        /*$cfirst_names = $_POST['cfirst_name'];
                        $c_address_line_1s = $_POST['c_address_line_1'];
                        $c_citys = $_POST['c_city'];
                        $c_pin_codes = $_POST['c_pin_code'];
                        $c_cst_nos = $_POST['c_cst_no'];
                        $c_pan_nos = $_POST['c_pan_no'];
                        $c_contact_nos = $_POST['c_contact_no'];
                        $c_last_names = $_POST['c_last_name'];
                        $c_address_line_2s = $_POST['c_address_line_2'];
                        $c_states = $_POST['c_state'];
                        $c_tin_nos = $_POST['c_tin_no'];
                        $c_ecc_nos = $_POST['c_ecc_no'];
                        $c_name_contact_persions = $_POST['c_name_contact_persion'];
                        $c_emails = $_POST['c_email'];*/

                        //from FORM 2
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

                        //from FORM 3
                        str_customer_billing_address_line1 = sharedPreferences.getString("str_customer_address_line1", "str_customer_address_line1");
                        str_customer_billing_address_line2 = sharedPreferences.getString("str_customer_address_line2", "str_customer_address_line2");
                        str_customer_billing_city = sharedPreferences.getString("str_customer_city", "str_customer_city");
                        str_customer_billing_state = sharedPreferences.getString("str_customer_state", "str_customer_state");
                        str_customer_billing_pincode = sharedPreferences.getString("str_customer_pincode", "str_customer_pincode");
                        str_customer_billing_tincode = sharedPreferences.getString("str_customer_tincode", "str_customer_tincode");
                        str_customer_billing_cstno = sharedPreferences.getString("str_customer_cstno", "str_customer_cstno");
                        str_customer_billing_eccno = sharedPreferences.getString("str_customer_eccno", "str_customer_eccno");
                        str_customer_billing_panno = sharedPreferences.getString("str_customer_panno", "str_customer_panno");
                        str_customer_billing_contact_person = sharedPreferences.getString("str_customer_contact_person", "str_customer_contact_person");
                        str_customer_billing_contact_number = sharedPreferences.getString("str_customer_contact_number", "str_customer_contact_number");
                        str_customer_billing_email = sharedPreferences.getString("str_customer_email", "str_customer_email");

                        //from FORM 4
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

                        //from FORM 5
                        //MODEL
                        str_model_one = sharedPreferences.getString("str_model_one", "str_model_one");
                        str_model_two = sharedPreferences.getString("str_model_two", "str_model_two");
                        str_model_three = sharedPreferences.getString("str_model_three", "str_model_three");
                        str_model_four = sharedPreferences.getString("str_model_four", "str_model_four");
                        str_model_five = sharedPreferences.getString("str_model_five", "str_model_five");
                        str_model_six = sharedPreferences.getString("str_model_six", "str_model_six");
                        //SAP CODE
                        str_sap_code_one = sharedPreferences.getString("str_sap_code_one", "str_sap_code_one");
                        str_sap_code_two = sharedPreferences.getString("str_sap_code_two", "str_sap_code_two");
                        str_sap_code_three = sharedPreferences.getString("str_sap_code_three", "str_sap_code_three");
                        str_sap_code_four = sharedPreferences.getString("str_sap_code_four", "str_sap_code_four");
                        str_sap_code_five = sharedPreferences.getString("str_sap_code_five", "str_sap_code_five");
                        str_sap_code_six = sharedPreferences.getString("str_sap_code_six", "str_sap_code_six");
                        //DESCRIPTION
                        str_desc_one = sharedPreferences.getString("str_desc_one", "str_desc_one");
                        str_desc_two = sharedPreferences.getString("str_desc_two", "str_desc_two");
                        str_desc_three = sharedPreferences.getString("str_desc_three", "str_desc_three");
                        str_desc_four = sharedPreferences.getString("str_desc_four", "str_desc_four");
                        str_desc_five = sharedPreferences.getString("str_desc_five", "str_desc_five");
                        str_desc_six = sharedPreferences.getString("str_desc_six", "str_desc_six");
                        //QUANTITY
                        str_quantity_one = sharedPreferences.getString("str_quantity_one", "str_quantity_one");
                        str_quantity_two = sharedPreferences.getString("str_quantity_two", "str_quantity_two");
                        str_quantity_three = sharedPreferences.getString("str_quantity_three", "str_quantity_three");
                        str_quantity_four = sharedPreferences.getString("str_quantity_four", "str_quantity_four");
                        str_quantity_five = sharedPreferences.getString("str_quantity_five", "str_quantity_five");
                        str_quantity_six = sharedPreferences.getString("str_quantity_six", "str_quantity_six");
                        //LIST PRICE
                        str_list_price_one = sharedPreferences.getString("str_list_price_one", "str_list_price_one");
                        str_list_price_two = sharedPreferences.getString("str_list_price_two", "str_list_price_two");
                        str_list_price_three = sharedPreferences.getString("str_list_price_three", "str_list_price_three");
                        str_list_price_four = sharedPreferences.getString("str_list_price_four", "str_list_price_four");
                        str_list_price_five = sharedPreferences.getString("str_list_price_five", "str_list_price_five");
                        str_list_price_six = sharedPreferences.getString("str_list_price_six", "str_list_price_six");
                        //DISCOUNT
                        str_discount_one = sharedPreferences.getString("str_discount_one", "str_discount_one");
                        str_discount_two = sharedPreferences.getString("str_discount_two", "str_discount_two");
                        str_discount_three = sharedPreferences.getString("str_discount_three", "str_discount_three");
                        str_discount_four = sharedPreferences.getString("str_discount_four", "str_discount_four");
                        str_discount_five = sharedPreferences.getString("str_discount_five", "str_discount_five");
                        str_discount_six = sharedPreferences.getString("str_discount_six", "str_discount_six");
                        //ACTUAL PRICE
                        str_actual_price_one = sharedPreferences.getString("str_actual_price_one", "str_actual_price_one");
                        str_actual_price_two = sharedPreferences.getString("str_actual_price_two", "str_actual_price_two");
                        str_actual_price_three = sharedPreferences.getString("str_actual_price_three", "str_actual_price_three");
                        str_actual_price_four = sharedPreferences.getString("str_actual_price_four", "str_actual_price_four");
                        str_actual_price_five = sharedPreferences.getString("str_actual_price_five", "str_actual_price_five");
                        str_actual_price_six = sharedPreferences.getString("str_actual_price_six", "str_actual_price_six");

                        //REQUIRED DATE
                        str_req_date_one = sharedPreferences.getString("str_req_date_one", "str_req_date_one");
                        str_req_date_two = sharedPreferences.getString("str_req_date_two", "str_req_date_two");
                        str_req_date_three = sharedPreferences.getString("str_req_date_three", "str_req_date_three");
                        str_req_date_four = sharedPreferences.getString("str_req_date_four", "str_req_date_four");
                        str_req_date_five = sharedPreferences.getString("str_req_date_five", "str_req_date_five");
                        str_req_date_six = sharedPreferences.getString("str_req_date_six", "str_req_date_six");

                        //Quotation ID
                        str_qno1 = sharedPreferences.getString("str_qid1", "str_qid1");
                        str_qno2 = sharedPreferences.getString("str_qid2", "str_qid2");
                        str_qno3 = sharedPreferences.getString("str_qid3", "str_qid3");
                        str_qno4 = sharedPreferences.getString("str_qid4", "str_qid4");
                        str_qno5 = sharedPreferences.getString("str_qid5", "str_qid5");
                        str_qno6 = sharedPreferences.getString("str_qid6", "str_qid6");

                        //OTHERS
                        str_note = sharedPreferences.getString("str_note", "str_note");
                        str_total_value = sharedPreferences.getString("str_total_value", "str_total_value");
                        str_p_and_f = sharedPreferences.getString("str_p_and_f", "str_p_and_f");
                        str_p_and_f_value = sharedPreferences.getString("str_p_and_f_value", "str_p_and_f_value");
                        str_VAT_CET = sharedPreferences.getString("str_VAT_CET", "str_VAT_CET");
                        str_VAT_CET_value = sharedPreferences.getString("str_VAT_CET_value", "str_VAT_CET_value");
                        str_BET = sharedPreferences.getString("str_BET", "str_BET");
                        str_BET_value = sharedPreferences.getString("str_BET_value", "str_BET_value");
                        str_grand_total = sharedPreferences.getString("str_grand_total", "str_grand_total");

                        //FORM 6
                        str_others = sharedPreferences.getString("str_others", "str_others");
                        str_ld_clause_desc = sharedPreferences.getString("str_ld_clause_desc", "str_ld_clause_desc");
                        str_commission = sharedPreferences.getString("str_commission", "str_commission");
                        str_commission_value = sharedPreferences.getString("str_commission_value", "str_commission_value");
                        str_logistics_preferred = sharedPreferences.getString("str_logistics_preferred", "str_logistics_preferred");
                        //Spinner
                        str_form_applicable = sharedPreferences.getString("str_form_applicable", "str_form_applicable");
                        str_form_five_insurance = sharedPreferences.getString("str_form_five_insurance", "str_form_five_insurance");
                        str_freight_terms = sharedPreferences.getString("str_freight_terms", "str_freight_terms");
                        str_payment_terms = sharedPreferences.getString("str_payment_terms", "str_payment_terms");
                        str_credit_days = sharedPreferences.getString("str_credit_days", "str_credit_days");
                        str_pbg_abg = sharedPreferences.getString("str_pbg_abg", "str_pbg_abg");
                        str_inspection = sharedPreferences.getString("str_inspection", "str_inspection");
                        str_ld_clause = sharedPreferences.getString("str_ld_clause", "str_ld_clause");
                        str_permit = sharedPreferences.getString("str_permit", "str_permit");
                        str_commission_to = sharedPreferences.getString("str_commission_to", "str_commission_to");


                    } catch (Exception e) {

                    }

//                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(i);
//                    finish();

                    try {
                        dialog = new SpotsDialog(Activity_Order_Seven_Dispatch.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(getApplicationContext());
                        Function_Submit_OFM();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }


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

    private void Function_Submit_OFM() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_ofm_submit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_LOGIN", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("OFM STATUS : " + success);

                    if (success == 1) {

                        Alerter.create(Activity_Order_Seven_Dispatch.this)
                                .setTitle("GEM CRM")
                                .setText("OFM Submitted Sucessfully :)")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                        try {
                            FunctionAlert();
                        }catch (Exception e) {

                        }


                    } else {

                        Alerter.create(Activity_Order_Seven_Dispatch.this)
                                .setTitle("GEM CRM")
                                .setText("Oops..! OFM Submission Failed :(")
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
                Alerter.create(Activity_Order_Seven_Dispatch.this)
                        .setTitle("GEM CRM")
                        .setText("Internal Error !")
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                //FORM 1
                params.put("price_lst", str_price_list);
                params.put("qref", str_txt_q_ref);
                params.put("qdate", str_txt_q_date);
                params.put("sap_cd", str_txt_sapcode);
                params.put("poref", str_txt_po_ref);
                params.put("podate", str_txt_po_date);
                params.put("from_prc", str_order_from);
                params.put("to_gem", str_send_to);
                params.put("eq_no", str_enq_no);

                //Form 2

                params.put("cfirst_name", str_customer_name);
                params.put("c_address_line_1", str_customer_address_line1);
                params.put("c_address_line_2", str_customer_address_line2);
                params.put("c_city", str_customer_city);
                params.put("c_state", str_customer_state);
                params.put("c_pin_code", str_customer_pincode);
                params.put("c_tin_no", str_customer_tincode);
                params.put("c_cst_no", str_customer_cstno);
                params.put("c_ecc_no", str_customer_eccno);
                params.put("c_pan_no", str_customer_panno);
                params.put("c_name_contact_persion", str_customer_contact_person);
                params.put("c_contact_no", str_customer_contact_number);
                params.put("c_email", str_customer_email);
                params.put("c_last_name", "");

                //Form 3
                params.put("b_address_line_1", str_customer_billing_address_line1);
                params.put("b_address_line_2", str_customer_billing_address_line2);
                params.put("b_city", str_customer_billing_city);
                params.put("b_state", str_customer_billing_state);
                params.put("b_pin_code", str_customer_billing_pincode);
                params.put("b_tin_no", str_customer_billing_tincode);
                params.put("b_cst_no", str_customer_billing_cstno);
                params.put("b_ecc_no", str_customer_billing_eccno);
                params.put("b_pan_no", str_customer_billing_panno);
                params.put("b_name_contact_persion", str_customer_billing_contact_person);
                params.put("b_contact_no", str_customer_billing_contact_number);
                params.put("b_email", str_customer_billing_email);
                // params.put("b_last_name", "");

                //Form 4
                params.put("s_address_line_1", str_customer_shipping_address_line1);
                params.put("s_address_line", str_customer_shipping_address_line2);
                params.put("s_city", str_customer_shipping_city);
                params.put("s_state", str_customer_shipping_state);
                params.put("s_pin_code", str_customer_shipping_pincode);
                params.put("s_tin_no", str_customer_shipping_tincode);
                params.put("s_cst_no", str_customer_shipping_cstno);
                params.put("s_ecc_no", str_customer_shipping_eccno);
                params.put("s_pan_no", str_customer_shipping_panno);
                params.put("s_name_contact_persion", str_customer_shipping_contact_person);
                params.put("s_contact_no", str_customer_shipping_contact_number);
                params.put("s_email", str_customer_shipping_email);
                //Form 6 : set 1
                params.put("req_date", str_req_date_one);
                params.put("q_id", str_qno1);
                params.put("price", str_actual_price_one);
                //Form 6 : set 2
                params.put("req_date1", str_req_date_two);
                params.put("q_id1", str_qno2);
                params.put("price1", str_actual_price_two);
                //Form 6 : set 3
                params.put("req_date2", str_req_date_three);
                params.put("q_id2", str_qno3);
                params.put("price2", str_actual_price_three);
                //Form 6 : set 4
                params.put("req_date3", str_req_date_four);
                params.put("q_id3", str_qno4);
                params.put("price3", str_actual_price_four);
                //Form 6 : set 5
                params.put("req_date4", str_req_date_five);
                params.put("q_id4", str_qno5);
                params.put("price4", str_actual_price_five);
                //Form 6 : set 6
                params.put("req_date5", str_req_date_six);
                params.put("q_id5", str_qno6);
                params.put("price5", str_actual_price_six);
                //Form 6 : other
                params.put("pf_percent", str_p_and_f);
                params.put("pf_amount", str_p_and_f_value);
                params.put("vat_percent", str_VAT_CET);
                params.put("vat_amount", str_VAT_CET_value);
                params.put("bed_percent", str_BET);
                params.put("bed_amount", str_BET_value);
                params.put("grand_total", str_grand_total);

                //FORM 6
                params.put("other_payment", str_others);
                params.put("commission_permit_per", str_commission);
                params.put("commission_permit_value", str_commission_value);
                params.put("logistic_prefer", str_logistics_preferred);
                params.put("form_applicable", str_form_applicable);
                params.put("insurance", str_form_five_insurance);
                params.put("freight_terms", str_freight_terms);
                params.put("payment_terms", str_payment_terms);
                params.put("days", str_credit_days);
                params.put("pbg_abg", str_pbg_abg);
                params.put("inspection", str_inspection);
                params.put("ld_clause", str_ld_clause);
                params.put("Permite", str_permit);
                params.put("Permit", str_commission_to);
                params.put("applicable_detail", str_ld_clause_desc);
                params.put("ldclause_applicable", "");

                //Form 7
                params.put("despatch_contact_person", str_dispatch_contact_person_name);
                params.put("despatch_contact", str_dispatch_contact_number);
                params.put("despatch_addr", str_dispatch_address_line1);
                params.put("despatch_addr2", str_dispatch_address_line2);
                params.put("despatch_city", str_dispatch_city);
                params.put("despatch_state", str_dispatch_state);
                params.put("despatch_pin", str_dispatch_pincode);
                params.put("despatch_commisssioning", str_customer_commissioning_instructions);
                //verify
                params.put("despatch_spacial", "");
                params.put("despatch_note", "");


                return checkParams(params);
            }


            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);

        // Adding request to request queue
        queue.add(request);
    }


    private void FunctionAlert() {

        new android.app.AlertDialog.Builder(Activity_Order_Seven_Dispatch.this)
                .setTitle("GEM CRM")
                .setMessage("OFM Sent Successfully :)")
                .setIcon(R.mipmap.ic_launcher)

                .setPositiveButton("Done",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();

                                dialog.dismiss();


                            }
                        }).show();
    }

}
