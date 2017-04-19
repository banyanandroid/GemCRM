package banyan.com.gemcrm.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;

/**
 * Created by Schan on 26-Mar-17.
 */

public class Activity_Order_Five_Product_details extends AppCompatActivity {

    EditText edt_model_one, edt_model_two, edt_model_three, edt_model_four, edt_model_five, edt_model_six,
            edt_sap_code_one, edt_sap_code_two, edt_sap_code_three, edt_sap_code_four, edt_sap_code_five, edt_sap_code_six,
            edt_desc_one, edt_desc_two, edt_desc_three, edt_desc_four, edt_desc_five, edt_desc_six,
            edt_quantity_one, edt_quantity_two, edt_quantity_three, edt_quantity_four, edt_quantity_five, edt_quantity_six,
            edt_list_price_one, edt_list_price_two, edt_list_price_three, edt_list_price_four, edt_list_price_five, edt_list_price_six,
            edt_discount_one, edt_discount_two, edt_discount_three, edt_discount_four, edt_discount_five, edt_discount_six,
            edt_actual_price_one, edt_actual_price_two, edt_actual_price_three, edt_actual_price_four, edt_actual_price_five, edt_actual_price_six,
            edt_req_date_one, edt_req_date_two, edt_req_date_three, edt_req_date_four, edt_req_date_five, edt_req_date_six,

    edt_note, edt_total_value, edt_p_and_f, edt_VAT_CET, edt_BET, edt_p_and_f_value,
            edt_VAT_CET_value, edt_BET_value, edt_grand_total;

    Button btn_recalculate;

    CardView card1, card2, card3, card4, card5, card6;

    Button btn_previous, btn_next;

    String str_model_one, str_model_two, str_model_three, str_model_four, str_model_five, str_model_six,
            str_sap_code_one, str_sap_code_two, str_sap_code_three, str_sap_code_four, str_sap_code_five, str_sap_code_six,
            str_desc_one, str_desc_two, str_desc_three, str_desc_four, str_desc_five, str_desc_six,
            str_quantity_one, str_quantity_two, str_quantity_three, str_quantity_four, str_quantity_five, str_quantity_six,
            str_list_price_one, str_list_price_two, str_list_price_three, str_list_price_four, str_list_price_five, str_list_price_six,
            str_discount_one, str_discount_two, str_discount_three, str_discount_four, str_discount_five, str_discount_six,
            str_actual_price_one, str_actual_price_two, str_actual_price_three, str_actual_price_four, str_actual_price_five, str_actual_price_six,
            str_req_date_one, str_req_date_two, str_req_date_three, str_req_date_four, str_req_date_five, str_req_date_six,

    str_note, str_total_value, str_p_and_f, str_VAT_CET, str_BET, str_p_and_f_value,
            str_VAT_CET_value, str_BET_value, str_grand_total = "";

    String str_qid1, str_qid2, str_qid3, str_qid4, str_qid5, str_qid6 = "";


    SpotsDialog dialog;

    String str_list_price1, str_list_price2, str_list_price3, str_list_price4, str_list_price5, str_list_price6 = "0";

    int from_year, from_month, from_date;

    public static final String TAG_QUOTATION_NO = "quotation_no";
    public static final String TAG_QUOTATION_PRICE = "enq_product_price";
    public static final String TAG_QUOTATION_DATE = "created_on";

    public static RequestQueue queue;
    String TAG = " ";
    String str_ofm_quotation_no = "";

    String str_count, str_total, str_create_on;

    int int_count;

    long long_pf_value, long_bed_value, long_vat_value, long_total, long_grant_total = 0;

    private Toolbar mToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fifth_forwarding_memo_product_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        edt_model_one = (EditText) findViewById(R.id.prod_dtl_edt_model_one);
        edt_model_two = (EditText) findViewById(R.id.prod_dtl_edt_model_two);
        edt_model_three = (EditText) findViewById(R.id.prod_dtl_edt_model_three);
        edt_model_four = (EditText) findViewById(R.id.prod_dtl_edt_model_four);
        edt_model_five = (EditText) findViewById(R.id.prod_dtl_edt_model_five);
        edt_model_six = (EditText) findViewById(R.id.prod_dtl_edt_model_six);

        edt_sap_code_one = (EditText) findViewById(R.id.prod_dtl_edt_sap_code_one);
        edt_sap_code_two = (EditText) findViewById(R.id.prod_dtl_edt_sap_code_two);
        edt_sap_code_three = (EditText) findViewById(R.id.prod_dtl_edt_sap_code_three);
        edt_sap_code_four = (EditText) findViewById(R.id.prod_dtl_edt_sap_code_four);
        edt_sap_code_five = (EditText) findViewById(R.id.prod_dtl_edt_sap_code_five);
        edt_sap_code_six = (EditText) findViewById(R.id.prod_dtl_edt_sap_code_six);

        edt_desc_one = (EditText) findViewById(R.id.prod_dtl_edt_desc_one);
        edt_desc_two = (EditText) findViewById(R.id.prod_dtl_edt_desc_two);
        edt_desc_three = (EditText) findViewById(R.id.prod_dtl_edt_desc_three);
        edt_desc_four = (EditText) findViewById(R.id.prod_dtl_edt_desc_four);
        edt_desc_five = (EditText) findViewById(R.id.prod_dtl_edt_desc_five);
        edt_desc_six = (EditText) findViewById(R.id.prod_dtl_edt_desc_six);

        edt_quantity_one = (EditText) findViewById(R.id.prod_dtl_edt_quantity_one);
        edt_quantity_two = (EditText) findViewById(R.id.prod_dtl_edt_quantity_two);
        edt_quantity_three = (EditText) findViewById(R.id.prod_dtl_edt_quantity_three);
        edt_quantity_four = (EditText) findViewById(R.id.prod_dtl_edt_quantity_four);
        edt_quantity_five = (EditText) findViewById(R.id.prod_dtl_edt_quantity_five);
        edt_quantity_six = (EditText) findViewById(R.id.prod_dtl_edt_quantity_six);

        edt_list_price_one = (EditText) findViewById(R.id.prod_dtl_edt_list_price_one);
        edt_list_price_two = (EditText) findViewById(R.id.prod_dtl_edt_list_price_two);
        edt_list_price_three = (EditText) findViewById(R.id.prod_dtl_edt_list_price_three);
        edt_list_price_four = (EditText) findViewById(R.id.prod_dtl_edt_list_price_four);
        edt_list_price_five = (EditText) findViewById(R.id.prod_dtl_edt_list_price_five);
        edt_list_price_six = (EditText) findViewById(R.id.prod_dtl_edt_list_price_six);

        edt_discount_one = (EditText) findViewById(R.id.prod_dtl_edt_discount_one);
        edt_discount_two = (EditText) findViewById(R.id.prod_dtl_edt_discount_two);
        edt_discount_three = (EditText) findViewById(R.id.prod_dtl_edt_discount_three);
        edt_discount_four = (EditText) findViewById(R.id.prod_dtl_edt_discount_four);
        edt_discount_five = (EditText) findViewById(R.id.prod_dtl_edt_discount_five);
        edt_discount_six = (EditText) findViewById(R.id.prod_dtl_edt_discount_six);

        edt_actual_price_one = (EditText) findViewById(R.id.prod_dtl_edt_actual_price_one);
        edt_actual_price_two = (EditText) findViewById(R.id.prod_dtl_edt_actual_price_two);
        edt_actual_price_three = (EditText) findViewById(R.id.prod_dtl_edt_actual_price_three);
        edt_actual_price_four = (EditText) findViewById(R.id.prod_dtl_edt_actual_price_four);
        edt_actual_price_five = (EditText) findViewById(R.id.prod_dtl_edt_actual_price_five);
        edt_actual_price_six = (EditText) findViewById(R.id.prod_dtl_edt_actual_price_six);

        edt_req_date_one = (EditText) findViewById(R.id.prod_dtl_edt_req_date_one);
        edt_req_date_two = (EditText) findViewById(R.id.prod_dtl_edt_req_date_two);
        edt_req_date_three = (EditText) findViewById(R.id.prod_dtl_edt_req_date_three);
        edt_req_date_four = (EditText) findViewById(R.id.prod_dtl_edt_req_date_four);
        edt_req_date_five = (EditText) findViewById(R.id.prod_dtl_edt_req_date_five);
        edt_req_date_six = (EditText) findViewById(R.id.prod_dtl_edt_req_date_six);

        edt_note = (EditText) findViewById(R.id.prod_dtl_edt_note);
        edt_total_value = (EditText) findViewById(R.id.prod_dtl_edt_total_value);
        edt_p_and_f = (EditText) findViewById(R.id.prod_dtl_edt_pf_percent);
        edt_p_and_f_value = (EditText) findViewById(R.id.prod_dtl_edt_pf_value);
        edt_VAT_CET = (EditText) findViewById(R.id.prod_dtl_edt_vat_cst_percent);
        edt_VAT_CET_value = (EditText) findViewById(R.id.prod_dtl_edt_vat_cst_value);
        edt_BET = (EditText) findViewById(R.id.prod_dtl_edt_bed_percent);
        edt_BET_value = (EditText) findViewById(R.id.prod_dtl_edt_bed_value);
        edt_grand_total = (EditText) findViewById(R.id.prod_dtl_edt_grand_total);

        card1 = (CardView) findViewById(R.id.product_details_card_view_one);
        card2 = (CardView) findViewById(R.id.product_details_card_view_two);
        card3 = (CardView) findViewById(R.id.product_details_card_view_three);
        card4 = (CardView) findViewById(R.id.product_details_card_view_four);
        card5 = (CardView) findViewById(R.id.product_details_card_view_five);
        card6 = (CardView) findViewById(R.id.product_details_card_view_six);

        btn_recalculate = (Button) findViewById(R.id.btn_recalculate_price);
        btn_previous = (Button) findViewById(R.id.prod_dtl_btn_previous);
        btn_next = (Button) findViewById(R.id.prod_dtl_btn_next);

        edt_total_value.setFocusable(false);
        edt_p_and_f_value.setFocusable(false);
        edt_VAT_CET_value.setFocusable(false);
        edt_BET_value.setFocusable(false);
        edt_grand_total.setFocusable(false);


        edt_req_date_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_Five_Product_details.this,
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
                                edt_req_date_one.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });


        edt_req_date_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_Five_Product_details.this,
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
                                edt_req_date_two.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });

        edt_req_date_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_Five_Product_details.this,
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
                                edt_req_date_three.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });


        edt_req_date_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_Five_Product_details.this,
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
                                edt_req_date_four.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });

        edt_req_date_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_Five_Product_details.this,
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
                                edt_req_date_five.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });


        edt_req_date_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Order_Five_Product_details.this,
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
                                edt_req_date_six.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);


                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });


        edt_p_and_f.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                System.out.println(" ::::::::::::: onTextChanged ::::::::::: ");

                if (i == 1) {

                    String str_pandf = edt_p_and_f.getText().toString();

                    String str_actual_pandf = "26";

                    double actual_pandf = Double.parseDouble(str_actual_pandf);

                    Double pandf = Double.parseDouble(str_pandf);

                    if (pandf >= actual_pandf) {
                        TastyToast.makeText(getApplicationContext(), "Your P and F Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_p_and_f.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                System.out.println(" ::::::::::::: afterTextChanged ::::::::::: ");

                String str_pandf = edt_p_and_f.getText().toString();
                String total_value = edt_total_value.getText().toString();

                if (str_pandf.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Please Enter Some Value", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    edt_p_and_f_value.setText("");
                } else if (str_pandf.equals("0")) {
                    TastyToast.makeText(getApplicationContext(), "P & F Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    edt_p_and_f_value.setText("");
                } else {

                    try {

                        Double pnf_percent = Double.parseDouble(str_pandf);
                        Double pnf_total_value = Double.parseDouble(total_value);

                        Double pnf_value = pnf_percent / 100 * pnf_total_value;

                        System.out.println("P&F : " + pnf_value);
                        System.out.println("P&F : " + pnf_value);
                        System.out.println("P&F : " + pnf_value);

                        long_pf_value = Math.round(pnf_value);

                        edt_p_and_f_value.setText("" + long_pf_value);

                        long_total = Math.round(pnf_total_value);

                        long_grant_total = long_pf_value + long_bed_value + long_vat_value + long_total;

                        edt_grand_total.setText("" + long_grant_total);

                    } catch (Exception e) {

                    }
                }

            }
        });

        edt_BET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                System.out.println(" ::::::::::::: onTextChanged ::::::::::: ");

                if (i == 1) {

                    String str_bed = edt_BET.getText().toString();

                    String str_actual_bed = "26";

                    double actual_bed = Double.parseDouble(str_actual_bed);

                    Double bed = Double.parseDouble(str_bed);

                    if (bed >= actual_bed) {
                        TastyToast.makeText(getApplicationContext(), "Your B.E.D Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_BET.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                System.out.println(" ::::::::::::: afterTextChanged ::::::::::: ");

                String str_bed = edt_BET.getText().toString();
                String total_value = edt_total_value.getText().toString();

                if (str_bed.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Please Enter Some Value", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    edt_BET_value.setText("");
                } else if (str_bed.equals("0")) {
                    TastyToast.makeText(getApplicationContext(), "B.E.D Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    edt_BET_value.setText("");
                } else {

                    try {

                        Double bed_percent = Double.parseDouble(str_bed);
                        Double bed_total_value = Double.parseDouble(total_value);

                        Double bed_value = bed_percent / 100 * bed_total_value;

                        System.out.println("P&F : " + bed_value);
                        System.out.println("P&F : " + bed_value);
                        System.out.println("P&F : " + bed_value);

                        long_bed_value = Math.round(bed_value);

                        edt_BET_value.setText("" + long_bed_value);

                        long_total = Math.round(bed_total_value);

                        long_grant_total = long_pf_value + long_bed_value + long_vat_value + long_total;

                        edt_grand_total.setText("" + long_grant_total);

                    } catch (Exception e) {

                    }
                }

            }
        });

        edt_VAT_CET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                System.out.println(" ::::::::::::: onTextChanged ::::::::::: ");

                if (i == 1) {

                    String str_vat = edt_VAT_CET.getText().toString();

                    String str_actual_vat = "26";

                    double actual_vat = Double.parseDouble(str_actual_vat);

                    Double vat = Double.parseDouble(str_vat);

                    if (vat >= actual_vat) {
                        TastyToast.makeText(getApplicationContext(), "Your VAT/CST % Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_VAT_CET.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                System.out.println(" ::::::::::::: afterTextChanged ::::::::::: ");

                String str_vat = edt_VAT_CET.getText().toString();
                String total_value = edt_total_value.getText().toString();

                if (str_vat.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Please Enter Some Value", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    edt_VAT_CET_value.setText("");
                } else if (str_vat.equals("0")) {
                    TastyToast.makeText(getApplicationContext(), "VAT/CST % Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    edt_VAT_CET_value.setText("");
                } else {

                    try {

                        Double vat_percent = Double.parseDouble(str_vat);
                        Double vat_total_value = Double.parseDouble(total_value);

                        Double vat_value = vat_percent / 100 * vat_total_value;

                        System.out.println("vat : " + vat_value);
                        System.out.println("vat : " + vat_value);
                        System.out.println("vat : " + vat_value);

                        long_vat_value = Math.round(vat_value);

                        edt_VAT_CET_value.setText("" + long_vat_value);

                        long_total = Math.round(vat_total_value);

                        long_grant_total = long_pf_value + long_bed_value + long_vat_value + long_total;

                        edt_grand_total.setText("" + long_grant_total);

                    } catch (Exception e) {

                    }

                }

            }
        });


        btn_recalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_list_price1 = edt_actual_price_one.getText().toString();
                str_list_price2 = edt_actual_price_two.getText().toString();
                str_list_price3 = edt_actual_price_three.getText().toString();
                str_list_price4 = edt_actual_price_four.getText().toString();
                str_list_price5 = edt_actual_price_five.getText().toString();
                str_list_price6 = edt_actual_price_six.getText().toString();

                if (int_count == 1) {
                    if (str_list_price1.equals("") || str_list_price1.equals("0")) {
                        str_list_price1 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 1 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        int price1 = Integer.parseInt(str_list_price1);
                        edt_total_value.setText("" + price1);
                    }
                } else if (int_count == 2) {

                    if (str_list_price1.equals("") || str_list_price1.equals("0")) {
                        str_list_price1 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 1 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price2.equals("") || str_list_price2.equals("0")) {
                        str_list_price2 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 2 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        int price1 = Integer.parseInt(str_list_price1);
                        int price2 = Integer.parseInt(str_list_price2);

                        int total_price = price1 + price2;

                        edt_total_value.setText("" + total_price);
                    }

                } else if (int_count == 3) {

                    if (str_list_price1.equals("") || str_list_price1.equals("0")) {
                        str_list_price1 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 1 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price2.equals("") || str_list_price2.equals("0")) {
                        str_list_price2 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 2 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price3.equals("") || str_list_price3.equals("0")) {
                        str_list_price3 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 3 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        int price1 = Integer.parseInt(str_list_price1);
                        int price2 = Integer.parseInt(str_list_price2);
                        int price3 = Integer.parseInt(str_list_price3);


                        int total_price = price1 + price2 + price3;

                        edt_total_value.setText("" + total_price);
                    }


                } else if (int_count == 4) {

                    if (str_list_price1.equals("") || str_list_price1.equals("0")) {
                        str_list_price1 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 1 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price2.equals("") || str_list_price2.equals("0")) {
                        str_list_price2 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 2 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price3.equals("") || str_list_price3.equals("0")) {
                        str_list_price3 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 3 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price4.equals("") || str_list_price4.equals("0")) {
                        str_list_price4 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 4 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        int price1 = Integer.parseInt(str_list_price1);
                        int price2 = Integer.parseInt(str_list_price2);
                        int price3 = Integer.parseInt(str_list_price3);
                        int price4 = Integer.parseInt(str_list_price4);

                        int total_price = price1 + price2 + price3 + price4;

                        edt_total_value.setText("" + total_price);
                    }

                } else if (int_count == 5) {

                    if (str_list_price1.equals("") || str_list_price1.equals("0")) {
                        str_list_price1 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 1 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price2.equals("") || str_list_price2.equals("0")) {
                        str_list_price2 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 2 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price3.equals("") || str_list_price3.equals("0")) {
                        str_list_price3 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 3 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price4.equals("") || str_list_price4.equals("0")) {
                        str_list_price4 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 4 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price5.equals("") || str_list_price5.equals("0")) {
                        str_list_price5 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 5 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        int price1 = Integer.parseInt(str_list_price1);
                        int price2 = Integer.parseInt(str_list_price2);
                        int price3 = Integer.parseInt(str_list_price3);
                        int price4 = Integer.parseInt(str_list_price4);
                        int price5 = Integer.parseInt(str_list_price5);

                        int total_price = price1 + price2 + price3 + price4 + price5;

                        edt_total_value.setText("" + total_price);
                    }

                } else if (int_count == 6) {

                    if (str_list_price1.equals("") || str_list_price1.equals("0")) {
                        str_list_price1 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 1 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price2.equals("") || str_list_price2.equals("0")) {
                        str_list_price2 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 2 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price3.equals("") || str_list_price3.equals("0")) {
                        str_list_price3 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 3 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price4.equals("") || str_list_price4.equals("0")) {
                        str_list_price4 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 4 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price5.equals("") || str_list_price5.equals("0")) {
                        str_list_price5 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 5 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_list_price6.equals("") || str_list_price6.equals("0")) {
                        str_list_price6 = "0";
                        TastyToast.makeText(getApplicationContext(), "Product 6 Price cannot be Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        int price1 = Integer.parseInt(str_list_price1);
                        int price2 = Integer.parseInt(str_list_price2);
                        int price3 = Integer.parseInt(str_list_price3);
                        int price4 = Integer.parseInt(str_list_price4);
                        int price5 = Integer.parseInt(str_list_price5);
                        int price6 = Integer.parseInt(str_list_price6);

                        int total_price = price1 + price2 + price3 + price4 + price5 + price6;

                        edt_total_value.setText("" + total_price);
                    }

                }

            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_model_one = edt_model_one.getText().toString();
                str_model_two = edt_model_two.getText().toString();
                str_model_three = edt_model_three.getText().toString();
                str_model_four = edt_model_four.getText().toString();
                str_model_five = edt_model_five.getText().toString();
                str_model_six = edt_model_six.getText().toString();

                str_sap_code_one = edt_sap_code_one.getText().toString();
                str_sap_code_two = edt_sap_code_two.getText().toString();
                str_sap_code_three = edt_sap_code_three.getText().toString();
                str_sap_code_four = edt_sap_code_four.getText().toString();
                str_sap_code_five = edt_sap_code_five.getText().toString();
                str_sap_code_six = edt_sap_code_six.getText().toString();

                str_desc_one = edt_desc_one.getText().toString();
                str_desc_two = edt_desc_two.getText().toString();
                str_desc_three = edt_desc_three.getText().toString();
                str_desc_four = edt_desc_four.getText().toString();
                str_desc_five = edt_desc_five.getText().toString();
                str_desc_six = edt_desc_six.getText().toString();

                str_quantity_one = edt_quantity_one.getText().toString();
                str_quantity_two = edt_quantity_two.getText().toString();
                str_quantity_three = edt_quantity_three.getText().toString();
                str_quantity_four = edt_quantity_four.getText().toString();
                str_quantity_five = edt_quantity_five.getText().toString();
                str_quantity_six = edt_quantity_six.getText().toString();

                str_list_price_one = edt_list_price_one.getText().toString();
                str_list_price_two = edt_list_price_two.getText().toString();
                str_list_price_three = edt_list_price_three.getText().toString();
                str_list_price_four = edt_list_price_four.getText().toString();
                str_list_price_five = edt_list_price_five.getText().toString();
                str_list_price_six = edt_list_price_six.getText().toString();

                str_discount_one = edt_discount_one.getText().toString();
                str_discount_two = edt_discount_two.getText().toString();
                str_discount_three = edt_discount_three.getText().toString();
                str_discount_four = edt_discount_four.getText().toString();
                str_discount_five = edt_discount_five.getText().toString();
                str_discount_six = edt_discount_six.getText().toString();

                str_actual_price_one = edt_actual_price_one.getText().toString();
                str_actual_price_two = edt_actual_price_two.getText().toString();
                str_actual_price_three = edt_actual_price_three.getText().toString();
                str_actual_price_four = edt_actual_price_four.getText().toString();
                str_actual_price_five = edt_actual_price_five.getText().toString();
                str_actual_price_six = edt_actual_price_six.getText().toString();

                str_req_date_one = edt_req_date_one.getText().toString();
                str_req_date_two = edt_req_date_two.getText().toString();
                str_req_date_three = edt_req_date_three.getText().toString();
                str_req_date_four = edt_req_date_four.getText().toString();
                str_req_date_five = edt_req_date_five.getText().toString();
                str_req_date_six = edt_req_date_six.getText().toString();

                str_note = edt_note.getText().toString();
                str_total_value = edt_total_value.getText().toString();
                str_p_and_f = edt_p_and_f.getText().toString();
                str_p_and_f_value = edt_p_and_f_value.getText().toString();
                str_VAT_CET = edt_VAT_CET.getText().toString();
                str_VAT_CET_value = edt_VAT_CET_value.getText().toString();
                str_BET = edt_BET.getText().toString();
                str_BET_value = edt_BET_value.getText().toString();
                str_grand_total = edt_grand_total.getText().toString();


                /*if (str_model_one.equals("")) {
                    edt_model_one.setError("Please Enter Model name for Product One");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_model_two.equals("")) {
                    edt_model_two.setError("Please Enter Model name for Product Two");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_model_three.equals("")) {
                    edt_model_three.setError("Please Enter Model name for Product Three");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_model_four.equals("")) {
                    edt_model_four.setError("Please Enter Model name for Product Four");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_model_five.equals("")) {
                    edt_model_five.setError("Please Enter Model name for Product Five");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_model_six.equals("")) {
                    edt_model_six.setError("Please Enter Model name for Product Six");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                // SAP CODE
                else if (str_sap_code_one.equals("")) {
                    edt_sap_code_one.setError("Please Enter SAP Code for Product One");
                    TastyToast.makeText(getApplicationContext(), "SAP Code for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_sap_code_two.equals("")) {
                    edt_sap_code_two.setError("Please Enter SAP Code for Product Two");
                    TastyToast.makeText(getApplicationContext(), "SAP Code for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_sap_code_three.equals("")) {
                    edt_sap_code_three.setError("Please Enter SAP Code for Product Three");
                    TastyToast.makeText(getApplicationContext(), "SAP Code for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_sap_code_four.equals("")) {
                    edt_sap_code_four.setError("Please Enter SAP Code for Product Four");
                    TastyToast.makeText(getApplicationContext(), "SAP Code for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_sap_code_five.equals("")) {
                    edt_sap_code_five.setError("Please Enter SAP Code for Product Five");
                    TastyToast.makeText(getApplicationContext(), "SAP Code for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_sap_code_six.equals("")) {
                    edt_sap_code_six.setError("Please Enter SAP Code for Product Six");
                    TastyToast.makeText(getApplicationContext(), "SAP Code for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                // DESCRIPTION
                else if (str_desc_one.equals("")) {
                    edt_desc_one.setError("Please Enter Description for Product One");
                    TastyToast.makeText(getApplicationContext(), "Description for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_desc_two.equals("")) {
                    edt_desc_two.setError("Please Enter Description for Product Two");
                    TastyToast.makeText(getApplicationContext(), "Description for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_desc_three.equals("")) {
                    edt_desc_three.setError("Please Enter Description for Product Three");
                    TastyToast.makeText(getApplicationContext(), "Description for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_desc_four.equals("")) {
                    edt_desc_four.setError("Please Enter Description for Product Four");
                    TastyToast.makeText(getApplicationContext(), "Description for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_desc_five.equals("")) {
                    edt_desc_five.setError("Please Enter Description for Product Five");
                    TastyToast.makeText(getApplicationContext(), "Description for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_desc_six.equals("")) {
                    edt_desc_six.setError("Please Enter Description for Product Six");
                    TastyToast.makeText(getApplicationContext(), "Description for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //QUANTITY
                else if (str_quantity_one.equals("")) {
                    edt_quantity_one.setError("Please Enter Qunatity for Product One");
                    TastyToast.makeText(getApplicationContext(), "Qunatity for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_quantity_two.equals("")) {
                    edt_quantity_two.setError("Please Enter Qunatity for Product Two");
                    TastyToast.makeText(getApplicationContext(), "Qunatity for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_quantity_three.equals("")) {
                    edt_quantity_three.setError("Please Enter Qunatity for Product Three");
                    TastyToast.makeText(getApplicationContext(), "Qunatity for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_quantity_four.equals("")) {
                    edt_quantity_four.setError("Please Enter Qunatity for Product Four");
                    TastyToast.makeText(getApplicationContext(), "Qunatity for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_quantity_five.equals("")) {
                    edt_quantity_five.setError("Please Enter Qunatity for Product Five");
                    TastyToast.makeText(getApplicationContext(), "Qunatity for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_quantity_six.equals("")) {
                    edt_quantity_six.setError("Please Enter Qunatity for Product Six");
                    TastyToast.makeText(getApplicationContext(), "Qunatity for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //LIST_PRICE
                else if (str_list_price_one.equals("")) {
                    edt_list_price_one.setError("Please Enter List Price for Product One");
                    TastyToast.makeText(getApplicationContext(), "List Price for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_list_price_two.equals("")) {
                    edt_list_price_two.setError("Please Enter List Price for Product Two");
                    TastyToast.makeText(getApplicationContext(), "List Price for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_list_price_three.equals("")) {
                    edt_list_price_three.setError("Please Enter List Price for Product Three");
                    TastyToast.makeText(getApplicationContext(), "List Price for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_list_price_four.equals("")) {
                    edt_list_price_four.setError("Please Enter List Price for Product Four");
                    TastyToast.makeText(getApplicationContext(), "List Price for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_list_price_five.equals("")) {
                    edt_list_price_five.setError("Please Enter List Price for Product Five");
                    TastyToast.makeText(getApplicationContext(), "List Price for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_list_price_six.equals("")) {
                    edt_list_price_six.setError("Please Enter List Price for Product Six");
                    TastyToast.makeText(getApplicationContext(), "List Price for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //DISCOUNT
                else if (str_discount_one.equals("")) {
                    edt_discount_one.setError("Please Enter Discount for Product One");
                    TastyToast.makeText(getApplicationContext(), "Discount for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_discount_two.equals("")) {
                    edt_discount_two.setError("Please Enter Discount for Product Two");
                    TastyToast.makeText(getApplicationContext(), "Discount for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_discount_three.equals("")) {
                    edt_discount_three.setError("Please Enter Discount for Product Three");
                    TastyToast.makeText(getApplicationContext(), "Discount for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_discount_four.equals("")) {
                    edt_discount_four.setError("Please Enter Discount for Product Four");
                    TastyToast.makeText(getApplicationContext(), "Discount for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_discount_five.equals("")) {
                    edt_discount_five.setError("Please Enter Discount for Product Five");
                    TastyToast.makeText(getApplicationContext(), "Discount for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_discount_six.equals("")) {
                    edt_discount_six.setError("Please Enter Discount for Product Six");
                    TastyToast.makeText(getApplicationContext(), "Discount for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //ACTUAL_PRICE
                else if (str_actual_price_one.equals("")) {
                    edt_actual_price_one.setError("Please Enter Actual Price for Product One");
                    TastyToast.makeText(getApplicationContext(), "Actual Price for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_actual_price_two.equals("")) {
                    edt_actual_price_two.setError("Please Enter Actual Price for Product Two");
                    TastyToast.makeText(getApplicationContext(), "Actual Price for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_actual_price_three.equals("")) {
                    edt_actual_price_three.setError("Please Enter Actual Price for Product Three");
                    TastyToast.makeText(getApplicationContext(), "Actual Price for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_actual_price_four.equals("")) {
                    edt_actual_price_four.setError("Please Enter Actual Price for Product Four");
                    TastyToast.makeText(getApplicationContext(), "Actual Price for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_actual_price_five.equals("")) {
                    edt_actual_price_five.setError("Please Enter Actual Price for Product Five");
                    TastyToast.makeText(getApplicationContext(), "Actual Price for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_actual_price_six.equals("")) {
                    edt_actual_price_six.setError("Please Enter Actual Price for Product Six");
                    TastyToast.makeText(getApplicationContext(), "Actual Price for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //REQUIRED_DATE
                else if (str_req_date_one.equals("")) {
                    edt_req_date_one.setError("Please Enter Recquired Date for Product One");
                    TastyToast.makeText(getApplicationContext(), "Recquired Date for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_req_date_two.equals("")) {
                    edt_req_date_two.setError("Please Enter Recquired Date for Product Two");
                    TastyToast.makeText(getApplicationContext(), "Recquired Date for Product Two is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_req_date_three.equals("")) {
                    edt_req_date_three.setError("Please Enter Recquired Date for Product Three");
                    TastyToast.makeText(getApplicationContext(), "Recquired Date for Product Three is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_req_date_four.equals("")) {
                    edt_req_date_four.setError("Please Enter Recquired Date for Product Four");
                    TastyToast.makeText(getApplicationContext(), "Recquired Date for Product Four is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_req_date_five.equals("")) {
                    edt_req_date_five.setError("Please Enter Recquired Date for Product Five");
                    TastyToast.makeText(getApplicationContext(), "Recquired Date for Product Five is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_req_date_six.equals("")) {
                    edt_req_date_six.setError("Please Enter Recquired Date for Product Six");
                    TastyToast.makeText(getApplicationContext(), "Recquired Date for Product Six is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //OTHERS
                else if (str_note.equals("")) {
                    edt_note.setError("Please Enter Note");
                    TastyToast.makeText(getApplicationContext(), "Note One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_total_value.equals("")) {
                    edt_total_value.setError("Please Enter Total Value");
                    TastyToast.makeText(getApplicationContext(), "Total Value is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_p_and_f.equals("")) {
                    edt_p_and_f.setError("Please Enter P and F");
                    TastyToast.makeText(getApplicationContext(), "P and F is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_VAT_CET.equals("")) {
                    edt_VAT_CET.setError("Please Enter VAT and CET");
                    TastyToast.makeText(getApplicationContext(), "VAT and CET is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_BET.equals("")) {
                    edt_BET.setError("Please Enter BET");
                    TastyToast.makeText(getApplicationContext(), "BET is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_grand_total.equals("")) {
                    edt_grand_total.setError("Please Enter Grand Total");
                    TastyToast.makeText(getApplicationContext(), "Grand Total is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //Else For FUNCTION
                else {

                }*/


                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Model
                editor.putString("str_model_one", str_model_one);
                editor.putString("str_model_two", str_model_two);
                editor.putString("str_model_three", str_model_three);
                editor.putString("str_model_four", str_model_four);
                editor.putString("str_model_five", str_model_five);
                editor.putString("str_model_six", str_model_six);
                //SAP Code
                editor.putString("str_sap_code_one", str_sap_code_one);
                editor.putString("str_sap_code_two", str_sap_code_two);
                editor.putString("str_sap_code_three", str_sap_code_three);
                editor.putString("str_sap_code_four", str_sap_code_four);
                editor.putString("str_sap_code_five", str_sap_code_five);
                editor.putString("str_sap_code_six", str_sap_code_six);
                //Description
                editor.putString("str_desc_one", str_desc_one);
                editor.putString("str_desc_two", str_desc_two);
                editor.putString("str_desc_three", str_desc_three);
                editor.putString("str_desc_four", str_desc_four);
                editor.putString("str_desc_five", str_desc_five);
                editor.putString("str_desc_six", str_desc_six);
                //Quantity
                editor.putString("str_quantity_one", str_quantity_one);
                editor.putString("str_quantity_two", str_quantity_two);
                editor.putString("str_quantity_three", str_quantity_three);
                editor.putString("str_quantity_four", str_quantity_four);
                editor.putString("str_quantity_five", str_quantity_five);
                editor.putString("str_quantity_six", str_quantity_six);
                //List Price
                editor.putString("str_list_price_one", str_list_price_one);
                editor.putString("str_list_price_two", str_list_price_two);
                editor.putString("str_list_price_three", str_list_price_three);
                editor.putString("str_list_price_four", str_list_price_four);
                editor.putString("str_list_price_five", str_list_price_five);
                editor.putString("str_list_price_six", str_list_price_six);
                //Discount
                editor.putString("str_discount_one", str_discount_one);
                editor.putString("str_discount_two", str_discount_two);
                editor.putString("str_discount_three", str_discount_three);
                editor.putString("str_discount_four", str_discount_four);
                editor.putString("str_discount_five", str_discount_five);
                editor.putString("str_discount_six", str_discount_six);
                //Actual Price
                editor.putString("str_actual_price_one", str_actual_price_one);
                editor.putString("str_actual_price_two", str_actual_price_two);
                editor.putString("str_actual_price_three", str_actual_price_three);
                editor.putString("str_actual_price_four", str_actual_price_four);
                editor.putString("str_actual_price_five", str_actual_price_five);
                editor.putString("str_actual_price_six", str_actual_price_six);
                //Required Date
                editor.putString("str_req_date_one", str_req_date_one);
                editor.putString("str_req_date_two", str_req_date_two);
                editor.putString("str_req_date_three", str_req_date_three);
                editor.putString("str_req_date_four", str_req_date_four);
                editor.putString("str_req_date_five", str_req_date_five);
                editor.putString("str_req_date_six", str_req_date_six);
                //QID
                editor.putString("str_qid1", str_qid1);
                editor.putString("str_qid2", str_qid2);
                editor.putString("str_qid3", str_qid3);
                editor.putString("str_qid4", str_qid4);
                editor.putString("str_qid5", str_qid5);
                editor.putString("str_qid6", str_qid6);
                //Others
                editor.putString("str_note", str_note);
                editor.putString("str_total_value", str_total_value);
                editor.putString("str_p_and_f", str_p_and_f);
                editor.putString("str_p_and_f_value", str_p_and_f_value);
                editor.putString("str_VAT_CET", str_VAT_CET);
                editor.putString("str_VAT_CET_value", str_VAT_CET_value);
                editor.putString("str_BET", str_BET);
                editor.putString("str_BET_value", str_BET_value);
                editor.putString("str_grand_total", str_grand_total);

                editor.commit();

                Intent i = new Intent(getApplicationContext(), Activity_Order_Six_Product_details.class);
                startActivity(i);
                finish();


            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Order_Four_Shipping.class);
                startActivity(i);
                finish();

            }
        });

        try {

            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());

            str_ofm_quotation_no = sharedPreferences.getString("ofm_quotation_no", "ofm_quotation_no");

            dialog = new SpotsDialog(Activity_Order_Five_Product_details.this);
            dialog.show();
            queue = Volley.newRequestQueue(getApplicationContext());
            Get_Quotation_Products();

        } catch (Exception e) {
            // TODO: handle exception
        }

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
            // GET
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());
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

            //SET
            try {
                //MODEL
                edt_model_one.setText(str_model_one);
                edt_model_two.setText(str_model_two);
                edt_model_three.setText(str_model_three);
                edt_model_four.setText(str_model_four);
                edt_model_five.setText(str_model_five);
                edt_model_six.setText(str_model_six);
                //SAP CODE
                edt_sap_code_one.setText(str_sap_code_one);
                edt_sap_code_two.setText(str_sap_code_two);
                edt_sap_code_three.setText(str_sap_code_three);
                edt_sap_code_four.setText(str_sap_code_four);
                edt_sap_code_five.setText(str_sap_code_five);
                edt_sap_code_six.setText(str_sap_code_six);
                //DESCRIPTION
                edt_desc_one.setText(str_desc_one);
                edt_desc_two.setText(str_desc_two);
                edt_desc_three.setText(str_desc_three);
                edt_desc_four.setText(str_desc_four);
                edt_desc_five.setText(str_desc_five);
                edt_desc_six.setText(str_desc_six);
                //QUANTITY
                edt_quantity_one.setText(str_quantity_one);
                edt_quantity_two.setText(str_quantity_two);
                edt_quantity_three.setText(str_quantity_three);
                edt_quantity_four.setText(str_quantity_four);
                edt_quantity_five.setText(str_quantity_five);
                edt_quantity_six.setText(str_quantity_six);
                //LIST PRICE
                edt_list_price_one.setText(str_list_price_one);
                edt_list_price_two.setText(str_list_price_two);
                edt_list_price_three.setText(str_list_price_three);
                edt_list_price_four.setText(str_list_price_four);
                edt_list_price_five.setText(str_list_price_five);
                edt_list_price_six.setText(str_list_price_six);
                //DISCOUNT
                edt_discount_one.setText(str_discount_one);
                edt_discount_two.setText(str_discount_two);
                edt_discount_three.setText(str_discount_three);
                edt_discount_four.setText(str_discount_four);
                edt_discount_five.setText(str_discount_five);
                edt_discount_six.setText(str_discount_six);
                //ACTUAL PRICE
                edt_actual_price_one.setText(str_actual_price_one);
                edt_actual_price_two.setText(str_actual_price_two);
                edt_actual_price_three.setText(str_actual_price_three);
                edt_actual_price_four.setText(str_actual_price_four);
                edt_actual_price_five.setText(str_actual_price_five);
                edt_actual_price_six.setText(str_actual_price_six);
                //REQUIRED DATE
                edt_req_date_one.setText(str_req_date_one);
                edt_req_date_two.setText(str_req_date_two);
                edt_req_date_three.setText(str_req_date_three);
                edt_req_date_four.setText(str_req_date_four);
                edt_req_date_five.setText(str_req_date_five);
                edt_req_date_six.setText(str_req_date_six);
                //OTHERS
                edt_note.setText(str_note);
                edt_total_value.setText(str_total_value);
                edt_p_and_f.setText(str_p_and_f);
                edt_p_and_f_value.setText(str_p_and_f_value);
                edt_VAT_CET.setText(str_VAT_CET);
                edt_VAT_CET_value.setText(str_VAT_CET_value);
                edt_BET.setText(str_BET);
                edt_BET_value.setText(str_BET_value);
                edt_grand_total.setText(str_grand_total);

            } catch (Exception e) {

            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /***************************
     * GET Quotation Products
     ***************************/

    public void Get_Quotation_Products() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_ofm_quotation_products, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");


                    if (success == 1) {

                        int_count = obj.getInt("count");
                        str_total = obj.getString("total");

                        if (int_count != 0) {

                            if (int_count == 1) {

                                card1.setVisibility(View.VISIBLE);
                                card2.setVisibility(View.GONE);
                                card3.setVisibility(View.GONE);
                                card4.setVisibility(View.GONE);
                                card5.setVisibility(View.GONE);
                                card6.setVisibility(View.GONE);

                                JSONArray arr;

                                arr = obj.getJSONArray("products");

                                for (int i = 0; arr.length() > i; i++) {

                                    JSONObject obj1 = arr.getJSONObject(i);

                                    str_qid1 = obj1.getString("qid0");
                                    str_model_one = obj1.getString("enq_product_model_no0");
                                    str_quantity_one = obj1.getString("enq_product_qty0");
                                    str_list_price_one = obj1.getString("enq_product_price0");
                                    str_discount_one = obj1.getString("discount0");
                                    str_desc_one = obj1.getString("enq_product_description0");
                                    str_sap_code_one = obj1.getString("sapcode0");
                                    str_actual_price_one = obj1.getString("actual_price0");

                                    //MODEL
                                    edt_model_one.setText(str_model_one);
                                    edt_sap_code_one.setText(str_sap_code_one);
                                    edt_desc_one.setText(str_desc_one);
                                    edt_quantity_one.setText(str_quantity_one);
                                    edt_list_price_one.setText(str_list_price_one);
                                    edt_discount_one.setText(str_discount_one);
                                    edt_actual_price_one.setText(str_actual_price_one);

                                    edt_total_value.setText(str_list_price_one);

                                }

                            } else if (int_count == 2) {

                                card1.setVisibility(View.VISIBLE);
                                card2.setVisibility(View.VISIBLE);
                                card3.setVisibility(View.GONE);
                                card4.setVisibility(View.GONE);
                                card5.setVisibility(View.GONE);
                                card6.setVisibility(View.GONE);


                                JSONArray arr;

                                arr = obj.getJSONArray("products");

                                for (int i = 0; arr.length() > i; i++) {

                                    JSONObject obj1 = arr.getJSONObject(i);

                                    if (i == 0) {

                                        str_qid1 = obj1.getString("qid0");
                                        str_model_one = obj1.getString("enq_product_model_no0");
                                        str_quantity_one = obj1.getString("enq_product_qty0");
                                        str_list_price_one = obj1.getString("enq_product_price0");
                                        str_discount_one = obj1.getString("discount0");
                                        str_desc_one = obj1.getString("enq_product_description0");
                                        str_sap_code_one = obj1.getString("sapcode0");
                                        str_actual_price_one = obj1.getString("actual_price0");

                                        //MODEL
                                        edt_model_one.setText(str_model_one);
                                        edt_sap_code_one.setText(str_sap_code_one);
                                        edt_desc_one.setText(str_desc_one);
                                        edt_quantity_one.setText(str_quantity_one);
                                        edt_list_price_one.setText(str_list_price_one);
                                        edt_discount_one.setText(str_discount_one);
                                        edt_actual_price_one.setText(str_actual_price_one);

                                    } else if (i == 1) {

                                        str_qid2 = obj1.getString("qid1");
                                        str_model_two = obj1.getString("enq_product_model_no1");
                                        str_quantity_two = obj1.getString("enq_product_qty1");
                                        str_list_price_two = obj1.getString("enq_product_price1");
                                        str_discount_two = obj1.getString("discount1");
                                        str_desc_two = obj1.getString("enq_product_description1");
                                        str_sap_code_two = obj1.getString("sapcode1");
                                        str_actual_price_two = obj1.getString("actual_price1");

                                        edt_model_two.setText(str_model_two);
                                        edt_sap_code_two.setText(str_sap_code_two);
                                        edt_desc_two.setText(str_desc_two);
                                        edt_quantity_two.setText(str_quantity_two);
                                        edt_list_price_two.setText(str_list_price_two);
                                        edt_discount_two.setText(str_discount_two);
                                        edt_actual_price_two.setText(str_actual_price_two);

                                    }

                                }

                                try {

                                    if (str_list_price_one.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 1 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_two.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 2 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else {

                                        int price1 = Integer.parseInt(str_list_price_one);
                                        int price2 = Integer.parseInt(str_list_price_two);

                                        if (price1 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 1 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price2 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 2 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else {
                                            int result = price1 + price2;
                                            edt_total_value.setText("" + result);
                                        }
                                    }

                                } catch (Exception e) {

                                }

                            } else if (int_count == 3) {

                                card1.setVisibility(View.VISIBLE);
                                card2.setVisibility(View.VISIBLE);
                                card3.setVisibility(View.VISIBLE);
                                card4.setVisibility(View.GONE);
                                card5.setVisibility(View.GONE);
                                card6.setVisibility(View.GONE);

                                JSONArray arr;

                                arr = obj.getJSONArray("products");

                                for (int i = 0; arr.length() > i; i++) {

                                    JSONObject obj1 = arr.getJSONObject(i);

                                    if (i == 0) {

                                        str_qid1 = obj1.getString("qid0");
                                        str_model_one = obj1.getString("enq_product_model_no0");
                                        str_quantity_one = obj1.getString("enq_product_qty0");
                                        str_list_price_one = obj1.getString("enq_product_price0");
                                        str_discount_one = obj1.getString("discount0");
                                        str_desc_one = obj1.getString("enq_product_description0");
                                        str_sap_code_one = obj1.getString("sapcode0");
                                        str_actual_price_one = obj1.getString("actual_price0");

                                        //MODEL
                                        edt_model_one.setText(str_model_one);
                                        edt_sap_code_one.setText(str_sap_code_one);
                                        edt_desc_one.setText(str_desc_one);
                                        edt_quantity_one.setText(str_quantity_one);
                                        edt_list_price_one.setText(str_list_price_one);
                                        edt_discount_one.setText(str_discount_one);
                                        edt_actual_price_one.setText(str_actual_price_one);

                                    } else if (i == 1) {

                                        str_qid2 = obj1.getString("qid1");
                                        str_model_two = obj1.getString("enq_product_model_no1");
                                        str_quantity_two = obj1.getString("enq_product_qty1");
                                        str_list_price_two = obj1.getString("enq_product_price1");
                                        str_discount_two = obj1.getString("discount1");
                                        str_desc_two = obj1.getString("enq_product_description1");
                                        str_sap_code_two = obj1.getString("sapcode1");
                                        str_actual_price_two = obj1.getString("actual_price1");

                                        edt_model_two.setText(str_model_two);
                                        edt_sap_code_two.setText(str_sap_code_two);
                                        edt_desc_two.setText(str_desc_two);
                                        edt_quantity_two.setText(str_quantity_two);
                                        edt_list_price_two.setText(str_list_price_two);
                                        edt_discount_two.setText(str_discount_two);
                                        edt_actual_price_two.setText(str_actual_price_two);

                                    } else if (i == 2) {

                                        str_qid3 = obj1.getString("qid2");
                                        str_model_three = obj1.getString("enq_product_model_no2");
                                        str_quantity_three = obj1.getString("enq_product_qty2");
                                        str_list_price_three = obj1.getString("enq_product_price2");
                                        str_discount_three = obj1.getString("discount2");
                                        str_desc_three = obj1.getString("enq_product_description2");
                                        str_sap_code_three = obj1.getString("sapcode2");
                                        str_actual_price_three = obj1.getString("actual_price2");

                                        edt_model_three.setText(str_model_three);
                                        edt_sap_code_three.setText(str_sap_code_three);
                                        edt_desc_three.setText(str_desc_three);
                                        edt_quantity_three.setText(str_quantity_three);
                                        edt_list_price_three.setText(str_list_price_three);
                                        edt_discount_three.setText(str_discount_three);
                                        edt_actual_price_three.setText(str_actual_price_three);

                                    }

                                }

                                try {
                                    if (str_list_price_one.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 1 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_two.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 2 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_three.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 3 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else {

                                        int price1 = Integer.parseInt(str_list_price_one);
                                        int price2 = Integer.parseInt(str_list_price_two);
                                        int price3 = Integer.parseInt(str_list_price_three);

                                        if (price1 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 1 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price2 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 2 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price3 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 3 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else {
                                            int result = price1 + price2 + price3;
                                            edt_total_value.setText("" + result);
                                        }
                                    }

                                } catch (Exception e) {

                                }

                            } else if (int_count == 4) {

                                card1.setVisibility(View.VISIBLE);
                                card2.setVisibility(View.VISIBLE);
                                card3.setVisibility(View.VISIBLE);
                                card4.setVisibility(View.VISIBLE);
                                card5.setVisibility(View.GONE);
                                card6.setVisibility(View.GONE);

                                JSONArray arr;

                                arr = obj.getJSONArray("products");

                                for (int i = 0; arr.length() > i; i++) {

                                    JSONObject obj1 = arr.getJSONObject(i);

                                    if (i == 0) {

                                        str_qid1 = obj1.getString("qid0");
                                        str_model_one = obj1.getString("enq_product_model_no0");
                                        str_quantity_one = obj1.getString("enq_product_qty0");
                                        str_list_price_one = obj1.getString("enq_product_price0");
                                        str_discount_one = obj1.getString("discount0");
                                        str_desc_one = obj1.getString("enq_product_description0");
                                        str_sap_code_one = obj1.getString("sapcode0");
                                        str_actual_price_one = obj1.getString("actual_price0");

                                        //MODEL
                                        edt_model_one.setText(str_model_one);
                                        edt_sap_code_one.setText(str_sap_code_one);
                                        edt_desc_one.setText(str_desc_one);
                                        edt_quantity_one.setText(str_quantity_one);
                                        edt_list_price_one.setText(str_list_price_one);
                                        edt_discount_one.setText(str_discount_one);
                                        edt_actual_price_one.setText(str_actual_price_one);

                                    } else if (i == 1) {

                                        str_qid2 = obj1.getString("qid1");
                                        str_model_two = obj1.getString("enq_product_model_no1");
                                        str_quantity_two = obj1.getString("enq_product_qty1");
                                        str_list_price_two = obj1.getString("enq_product_price1");
                                        str_discount_two = obj1.getString("discount1");
                                        str_desc_two = obj1.getString("enq_product_description1");
                                        str_sap_code_two = obj1.getString("sapcode1");
                                        str_actual_price_two = obj1.getString("actual_price1");

                                        edt_model_two.setText(str_model_two);
                                        edt_sap_code_two.setText(str_sap_code_two);
                                        edt_desc_two.setText(str_desc_two);
                                        edt_quantity_two.setText(str_quantity_two);
                                        edt_list_price_two.setText(str_list_price_two);
                                        edt_discount_two.setText(str_discount_two);
                                        edt_actual_price_two.setText(str_actual_price_two);

                                    } else if (i == 2) {

                                        str_qid3 = obj1.getString("qid2");
                                        str_model_three = obj1.getString("enq_product_model_no2");
                                        str_quantity_three = obj1.getString("enq_product_qty2");
                                        str_list_price_three = obj1.getString("enq_product_price2");
                                        str_discount_three = obj1.getString("discount2");
                                        str_desc_three = obj1.getString("enq_product_description2");
                                        str_sap_code_three = obj1.getString("sapcode2");
                                        str_actual_price_three = obj1.getString("actual_price2");

                                        edt_model_three.setText(str_model_three);
                                        edt_sap_code_three.setText(str_sap_code_three);
                                        edt_desc_three.setText(str_desc_three);
                                        edt_quantity_three.setText(str_quantity_three);
                                        edt_list_price_three.setText(str_list_price_three);
                                        edt_discount_three.setText(str_discount_three);
                                        edt_actual_price_three.setText(str_actual_price_three);

                                    } else if (i == 3) {

                                        str_qid4 = obj1.getString("qid3");
                                        str_model_four = obj1.getString("enq_product_model_no3");
                                        str_quantity_four = obj1.getString("enq_product_qty3");
                                        str_list_price_four = obj1.getString("enq_product_price3");
                                        str_discount_four = obj1.getString("discount3");
                                        str_desc_four = obj1.getString("enq_product_description3");
                                        str_sap_code_four = obj1.getString("sapcode3");
                                        str_actual_price_four = obj1.getString("actual_price3");

                                        edt_model_four.setText(str_model_four);
                                        edt_sap_code_four.setText(str_sap_code_four);
                                        edt_desc_four.setText(str_desc_four);
                                        edt_quantity_four.setText(str_quantity_four);
                                        edt_list_price_four.setText(str_list_price_four);
                                        edt_discount_four.setText(str_discount_four);
                                        edt_actual_price_four.setText(str_actual_price_four);

                                    }

                                }

                                try {
                                    if (str_list_price_one.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 1 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_two.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 2 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_three.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 3 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_four.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 4 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else {

                                        int price1 = Integer.parseInt(str_list_price_one);
                                        int price2 = Integer.parseInt(str_list_price_two);
                                        int price3 = Integer.parseInt(str_list_price_three);
                                        int price4 = Integer.parseInt(str_list_price_four);

                                        if (price1 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 1 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price2 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 2 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price3 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 3 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price4 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 4 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else {
                                            int result = price1 + price2 + price3 + price4;
                                            edt_total_value.setText("" + result);
                                        }
                                    }

                                    edt_total_value.setText("" + str_total);

                                } catch (Exception e) {

                                }

                            } else if (int_count == 5) {

                                card1.setVisibility(View.VISIBLE);
                                card2.setVisibility(View.VISIBLE);
                                card3.setVisibility(View.VISIBLE);
                                card4.setVisibility(View.VISIBLE);
                                card5.setVisibility(View.VISIBLE);
                                card6.setVisibility(View.GONE);

                                JSONArray arr;

                                arr = obj.getJSONArray("products");

                                for (int i = 0; arr.length() > i; i++) {

                                    JSONObject obj1 = arr.getJSONObject(i);

                                    if (i == 0) {

                                        str_qid1 = obj1.getString("qid0");
                                        str_model_one = obj1.getString("enq_product_model_no0");
                                        str_quantity_one = obj1.getString("enq_product_qty0");
                                        str_list_price_one = obj1.getString("enq_product_price0");
                                        str_discount_one = obj1.getString("discount0");
                                        str_desc_one = obj1.getString("enq_product_description0");
                                        str_sap_code_one = obj1.getString("sapcode0");
                                        str_actual_price_one = obj1.getString("actual_price0");

                                        //MODEL
                                        edt_model_one.setText(str_model_one);
                                        edt_sap_code_one.setText(str_sap_code_one);
                                        edt_desc_one.setText(str_desc_one);
                                        edt_quantity_one.setText(str_quantity_one);
                                        edt_list_price_one.setText(str_list_price_one);
                                        edt_discount_one.setText(str_discount_one);
                                        edt_actual_price_one.setText(str_actual_price_one);

                                    } else if (i == 1) {

                                        str_qid2 = obj1.getString("qid1");
                                        str_model_two = obj1.getString("enq_product_model_no1");
                                        str_quantity_two = obj1.getString("enq_product_qty1");
                                        str_list_price_two = obj1.getString("enq_product_price1");
                                        str_discount_two = obj1.getString("discount1");
                                        str_desc_two = obj1.getString("enq_product_description1");
                                        str_sap_code_two = obj1.getString("sapcode1");
                                        str_actual_price_two = obj1.getString("actual_price1");

                                        edt_model_two.setText(str_model_two);
                                        edt_sap_code_two.setText(str_sap_code_two);
                                        edt_desc_two.setText(str_desc_two);
                                        edt_quantity_two.setText(str_quantity_two);
                                        edt_list_price_two.setText(str_list_price_two);
                                        edt_discount_two.setText(str_discount_two);
                                        edt_actual_price_two.setText(str_actual_price_two);

                                    } else if (i == 2) {

                                        str_qid3 = obj1.getString("qid2");
                                        str_model_three = obj1.getString("enq_product_model_no2");
                                        str_quantity_three = obj1.getString("enq_product_qty2");
                                        str_list_price_three = obj1.getString("enq_product_price2");
                                        str_discount_three = obj1.getString("discount2");
                                        str_desc_three = obj1.getString("enq_product_description2");
                                        str_sap_code_three = obj1.getString("sapcode2");
                                        str_actual_price_three = obj1.getString("actual_price2");

                                        edt_model_three.setText(str_model_three);
                                        edt_sap_code_three.setText(str_sap_code_three);
                                        edt_desc_three.setText(str_desc_three);
                                        edt_quantity_three.setText(str_quantity_three);
                                        edt_list_price_three.setText(str_list_price_three);
                                        edt_discount_three.setText(str_discount_three);
                                        edt_actual_price_three.setText(str_actual_price_three);

                                    } else if (i == 3) {

                                        str_qid4 = obj1.getString("qid3");
                                        str_model_four = obj1.getString("enq_product_model_no3");
                                        str_quantity_four = obj1.getString("enq_product_qty3");
                                        str_list_price_four = obj1.getString("enq_product_price3");
                                        str_discount_four = obj1.getString("discount3");
                                        str_desc_four = obj1.getString("enq_product_description3");
                                        str_sap_code_four = obj1.getString("sapcode3");
                                        str_actual_price_four = obj1.getString("actual_price3");

                                        edt_model_four.setText(str_model_four);
                                        edt_sap_code_four.setText(str_sap_code_four);
                                        edt_desc_four.setText(str_desc_four);
                                        edt_quantity_four.setText(str_quantity_four);
                                        edt_list_price_four.setText(str_list_price_four);
                                        edt_discount_four.setText(str_discount_four);
                                        edt_actual_price_four.setText(str_actual_price_four);

                                    } else if (i == 4) {

                                        str_qid5 = obj1.getString("qid4");
                                        str_model_five = obj1.getString("enq_product_model_no4");
                                        str_quantity_five = obj1.getString("enq_product_qty4");
                                        str_list_price_five = obj1.getString("enq_product_price4");
                                        str_discount_five = obj1.getString("discount4");
                                        str_desc_five = obj1.getString("enq_product_description4");
                                        str_sap_code_five = obj1.getString("sapcode4");
                                        str_actual_price_five = obj1.getString("actual_price4");

                                        edt_model_five.setText(str_model_five);
                                        edt_sap_code_five.setText(str_sap_code_five);
                                        edt_desc_five.setText(str_desc_five);
                                        edt_quantity_five.setText(str_quantity_five);
                                        edt_list_price_five.setText(str_list_price_five);
                                        edt_discount_five.setText(str_discount_five);
                                        edt_actual_price_five.setText(str_actual_price_five);

                                    }

                                }

                                try {
                                    if (str_list_price_one.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 1 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_two.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 2 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_three.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 3 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_four.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 4 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_five.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 5 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else {

                                        int price1 = Integer.parseInt(str_list_price_one);
                                        int price2 = Integer.parseInt(str_list_price_two);
                                        int price3 = Integer.parseInt(str_list_price_three);
                                        int price4 = Integer.parseInt(str_list_price_four);
                                        int price5 = Integer.parseInt(str_list_price_five);

                                        if (price1 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 1 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price2 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 2 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price3 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 3 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price4 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 4 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price5 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 5 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else {
                                            int result = price1 + price2 + price3 + price4 + price5;
                                            edt_total_value.setText("" + result);
                                        }
                                    }

                                } catch (Exception e) {

                                }

                            } else if (int_count == 6) {

                                card1.setVisibility(View.VISIBLE);
                                card2.setVisibility(View.VISIBLE);
                                card3.setVisibility(View.VISIBLE);
                                card4.setVisibility(View.VISIBLE);
                                card5.setVisibility(View.VISIBLE);
                                card6.setVisibility(View.VISIBLE);

                                JSONArray arr;

                                arr = obj.getJSONArray("products");

                                for (int i = 0; arr.length() > i; i++) {

                                    JSONObject obj1 = arr.getJSONObject(i);

                                    if (i == 0) {

                                        str_qid1 = obj1.getString("qid0");
                                        str_model_one = obj1.getString("enq_product_model_no0");
                                        str_quantity_one = obj1.getString("enq_product_qty0");
                                        str_list_price_one = obj1.getString("enq_product_price0");
                                        str_discount_one = obj1.getString("discount0");
                                        str_desc_one = obj1.getString("enq_product_description0");
                                        str_sap_code_one = obj1.getString("sapcode0");
                                        str_actual_price_one = obj1.getString("actual_price0");

                                        //MODEL
                                        edt_model_one.setText(str_model_one);
                                        edt_sap_code_one.setText(str_sap_code_one);
                                        edt_desc_one.setText(str_desc_one);
                                        edt_quantity_one.setText(str_quantity_one);
                                        edt_list_price_one.setText(str_list_price_one);
                                        edt_discount_one.setText(str_discount_one);
                                        edt_actual_price_one.setText(str_actual_price_one);

                                    } else if (i == 1) {

                                        str_qid2 = obj1.getString("qid1");
                                        str_model_two = obj1.getString("enq_product_model_no1");
                                        str_quantity_two = obj1.getString("enq_product_qty1");
                                        str_list_price_two = obj1.getString("enq_product_price1");
                                        str_discount_two = obj1.getString("discount1");
                                        str_desc_two = obj1.getString("enq_product_description1");
                                        str_sap_code_two = obj1.getString("sapcode1");
                                        str_actual_price_two = obj1.getString("actual_price1");

                                        edt_model_two.setText(str_model_two);
                                        edt_sap_code_two.setText(str_sap_code_two);
                                        edt_desc_two.setText(str_desc_two);
                                        edt_quantity_two.setText(str_quantity_two);
                                        edt_list_price_two.setText(str_list_price_two);
                                        edt_discount_two.setText(str_discount_two);
                                        edt_actual_price_two.setText(str_actual_price_two);

                                    } else if (i == 2) {

                                        str_qid3 = obj1.getString("qid2");
                                        str_model_three = obj1.getString("enq_product_model_no2");
                                        str_quantity_three = obj1.getString("enq_product_qty2");
                                        str_list_price_three = obj1.getString("enq_product_price2");
                                        str_discount_three = obj1.getString("discount2");
                                        str_desc_three = obj1.getString("enq_product_description2");
                                        str_sap_code_three = obj1.getString("sapcode2");
                                        str_actual_price_three = obj1.getString("actual_price2");

                                        edt_model_three.setText(str_model_three);
                                        edt_sap_code_three.setText(str_sap_code_three);
                                        edt_desc_three.setText(str_desc_three);
                                        edt_quantity_three.setText(str_quantity_three);
                                        edt_list_price_three.setText(str_list_price_three);
                                        edt_discount_three.setText(str_discount_three);
                                        edt_actual_price_three.setText(str_actual_price_three);

                                    } else if (i == 3) {

                                        str_qid4 = obj1.getString("qid3");
                                        str_model_four = obj1.getString("enq_product_model_no3");
                                        str_quantity_four = obj1.getString("enq_product_qty3");
                                        str_list_price_four = obj1.getString("enq_product_price3");
                                        str_discount_four = obj1.getString("discount3");
                                        str_desc_four = obj1.getString("enq_product_description3");
                                        str_sap_code_four = obj1.getString("sapcode3");
                                        str_actual_price_four = obj1.getString("actual_price3");

                                        edt_model_four.setText(str_model_four);
                                        edt_sap_code_four.setText(str_sap_code_four);
                                        edt_desc_four.setText(str_desc_four);
                                        edt_quantity_four.setText(str_quantity_four);
                                        edt_list_price_four.setText(str_list_price_four);
                                        edt_discount_four.setText(str_discount_four);
                                        edt_actual_price_four.setText(str_actual_price_four);

                                    } else if (i == 4) {

                                        str_qid5 = obj1.getString("qid4");
                                        str_model_five = obj1.getString("enq_product_model_no4");
                                        str_quantity_five = obj1.getString("enq_product_qty4");
                                        str_list_price_five = obj1.getString("enq_product_price4");
                                        str_discount_five = obj1.getString("discount4");
                                        str_desc_five = obj1.getString("enq_product_description4");
                                        str_sap_code_five = obj1.getString("sapcode4");
                                        str_actual_price_five = obj1.getString("actual_price4");

                                        edt_model_five.setText(str_model_five);
                                        edt_sap_code_five.setText(str_sap_code_five);
                                        edt_desc_five.setText(str_desc_five);
                                        edt_quantity_five.setText(str_quantity_five);
                                        edt_list_price_five.setText(str_list_price_five);
                                        edt_discount_five.setText(str_discount_five);
                                        edt_actual_price_five.setText(str_actual_price_five);

                                    } else if (i == 5) {

                                        str_qid6 = obj1.getString("qid5");
                                        str_model_six = obj1.getString("enq_product_model_no5");
                                        str_quantity_six = obj1.getString("enq_product_qty5");
                                        str_list_price_six = obj1.getString("enq_product_price5");
                                        str_discount_six = obj1.getString("discount5");
                                        str_desc_six = obj1.getString("enq_product_description5");
                                        str_sap_code_six = obj1.getString("sapcode5");
                                        str_actual_price_six = obj1.getString("actual_price5");

                                        edt_model_six.setText(str_model_six);
                                        edt_sap_code_six.setText(str_sap_code_six);
                                        edt_desc_six.setText(str_desc_six);
                                        edt_quantity_six.setText(str_quantity_six);
                                        edt_list_price_six.setText(str_list_price_six);
                                        edt_discount_six.setText(str_discount_six);
                                        edt_actual_price_six.setText(str_actual_price_six);

                                    }

                                }

                                try {
                                    if (str_list_price_one.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 1 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_two.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 2 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_three.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 3 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_four.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 4 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_five.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 5 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else if (str_list_price_six.equals("")) {
                                        TastyToast.makeText(getApplicationContext(), "Error in Product 6 Price", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                    } else {

                                        int price1 = Integer.parseInt(str_list_price_one);
                                        int price2 = Integer.parseInt(str_list_price_two);
                                        int price3 = Integer.parseInt(str_list_price_three);
                                        int price4 = Integer.parseInt(str_list_price_four);
                                        int price5 = Integer.parseInt(str_list_price_five);
                                        int price6 = Integer.parseInt(str_list_price_six);

                                        if (price1 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 1 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price2 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 2 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price3 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 3 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price4 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 4 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price5 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 5 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else if (price6 == 0) {
                                            TastyToast.makeText(getApplicationContext(), "Product Price 6 Cannot be 0", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                                        } else {
                                            int result = price1 + price2 + price3 + price4 + price5 + price6;
                                            edt_total_value.setText("" + result);
                                        }
                                    }

                                } catch (Exception e) {

                                }

                            }

                        } else {

                            Alerter.create(Activity_Order_Five_Product_details.this)
                                    .setTitle("GEM CRM")
                                    .setText("No Data Found")
                                    .setBackgroundColor(R.color.Alert_Fail)
                                    .show();

                        }

                        dialog.dismiss();

                    } else if (success == 0) {

                        Alerter.create(Activity_Order_Five_Product_details.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }
                    dialog.dismiss();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Order_Five_Product_details.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("quotation_no", str_ofm_quotation_no);

                System.out.println("quotation_no :::::: " + str_ofm_quotation_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***********************************
     *  Back Click Listener
     * ************************************/

    @Override
    public void onBackPressed() {
        // your code.
        try {
            String str_status = "Want to Exit From This Screen?";
            FunctionAlert(str_status);
        } catch (Exception e) {

        }
    }


    private void FunctionAlert(String status) {

        new AlertDialog.Builder(Activity_Order_Five_Product_details.this)
                .setTitle("GEM CRM")
                .setMessage(status)
                .setIcon(R.mipmap.ic_launcher)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                    }
                })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Intent i = new Intent(getApplicationContext(), Activity_Order_Four_Shipping.class);
                                startActivity(i);
                                finish();
                            }
                        }).show();
    }

}
