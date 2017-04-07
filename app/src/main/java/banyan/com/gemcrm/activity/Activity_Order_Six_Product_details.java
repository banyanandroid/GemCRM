package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;

/**
 * Created by Schan on 26-Mar-17.
 */

public class Activity_Order_Six_Product_details extends AppCompatActivity{

    EditText edt_model_one , edt_model_two  , edt_model_three , edt_model_four , edt_model_five , edt_model_six,
            edt_sap_code_one , edt_sap_code_two , edt_sap_code_three , edt_sap_code_four , edt_sap_code_five , edt_sap_code_six,
            edt_desc_one , edt_desc_two , edt_desc_three , edt_desc_four , edt_desc_five , edt_desc_six,
            edt_quantity_one , edt_quantity_two , edt_quantity_three , edt_quantity_four , edt_quantity_five , edt_quantity_six,
            edt_list_price_one,edt_list_price_two,edt_list_price_three,edt_list_price_four,edt_list_price_five,edt_list_price_six ,
            edt_discount_one ,edt_discount_two,edt_discount_three,edt_discount_four,edt_discount_five,edt_discount_six,
            edt_actual_price_one,edt_actual_price_two,edt_actual_price_three,edt_actual_price_four,edt_actual_price_five,edt_actual_price_six,
            edt_req_date_one,edt_req_date_two,edt_req_date_three,edt_req_date_four,edt_req_date_five,edt_req_date_six,
            edt_note , edt_total_value , edt_p_and_f , edt_VAT_CET , edt_BET , edt_freight_amount , edt_insurance , edt_grand_total ;

    Button btn_previous , btn_next ;

    String str_model_one , str_model_two  , str_model_three , str_model_four , str_model_five , str_model_six,
            str_sap_code_one , str_sap_code_two , str_sap_code_three , str_sap_code_four , str_sap_code_five , str_sap_code_six,
            str_desc_one , str_desc_two , str_desc_three , str_desc_four , str_desc_five , str_desc_six,
            str_quantity_one , str_quantity_two , str_quantity_three , str_quantity_four , str_quantity_five , str_quantity_six,
            str_list_price_one,str_list_price_two,str_list_price_three,str_list_price_four,str_list_price_five,str_list_price_six ,
            str_discount_one ,str_discount_two,str_discount_three,str_discount_four,str_discount_five,str_discount_six,
            str_actual_price_one,str_actual_price_two,str_actual_price_three,str_actual_price_four,str_actual_price_five,str_actual_price_six,
            str_req_date_one,str_req_date_two,str_req_date_three,str_req_date_four,str_req_date_five,str_req_date_six,
            str_note , str_total_value , str_p_and_f , str_VAT_CET , str_BET , str_freight_amount , str_insurance , str_grand_total = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sixth_forwarding_memo_product_details);

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
        edt_p_and_f = (EditText) findViewById(R.id.prod_dtl_edt_pf);
        edt_VAT_CET = (EditText) findViewById(R.id.prod_dtl_edt_vat_cst);
        edt_BET = (EditText) findViewById(R.id.prod_dtl_edt_bed);
        edt_freight_amount = (EditText) findViewById(R.id.prod_dtl_edt_freight_amount);
        edt_insurance = (EditText) findViewById(R.id.prod_dtl_edt_insurance);
        edt_grand_total = (EditText) findViewById(R.id.prod_dtl_edt_grand_total);

        btn_previous = (Button) findViewById(R.id.prod_dtl_btn_next);
        btn_next = (Button) findViewById(R.id.prod_dtl_btn_previous);



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
                str_VAT_CET = edt_VAT_CET.getText().toString();
                str_BET = edt_BET.getText().toString();
                str_freight_amount = edt_freight_amount.getText().toString();
                str_insurance = edt_insurance.getText().toString();
                str_grand_total = edt_grand_total.getText().toString();


               /* if (str_model_one.equals("")) {
                    edt_model_one.setError("Please Enter Model name for Product One");
                    TastyToast.makeText(getApplicationContext(), "Model name for Product One is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_model_two.equals("")) {
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
                }else if (str_sap_code_two.equals("")) {
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
                }else if (str_desc_two.equals("")) {
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
                }else if (str_quantity_two.equals("")) {
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
                }else if (str_list_price_two.equals("")) {
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
                }else if (str_discount_two.equals("")) {
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
                }else if (str_actual_price_two.equals("")) {
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
                }else if (str_req_date_two.equals("")) {
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
                }else if (str_total_value.equals("")) {
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
                } else if (str_freight_amount.equals("")) {
                    edt_freight_amount.setError("Please Enter Freight Amount");
                    TastyToast.makeText(getApplicationContext(), "Freight Amount is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }else if (str_insurance.equals("")) {
                    edt_insurance.setError("Please Enter Insurance");
                    TastyToast.makeText(getApplicationContext(), "Insurance is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_grand_total.equals("")) {
                    edt_grand_total.setError("Please Enter Grand Total");
                    TastyToast.makeText(getApplicationContext(), "Grand Total is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
                //Else For FUNCTION
                else {
                   *//* dialog = new SpotsDialog(Activity_Appoinment_Add.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Appoinment_Add.this);
                    Function_Add_Campaign();*//*

                }*/

                Intent i = new Intent(getApplicationContext(),Activity_Order_Seven_Dispatch.class);
                startActivity(i);
                finish();

            }
        });


    }
}
