package banyan.com.gemcrm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import banyan.com.gemcrm.R;

/**
 * Created by Jo on 3/20/2017.
 */

public class Activity_Enquiry_Completed_Description extends AppCompatActivity {

    // Get from Tab_My_Enq_Frag.java through sharedpref and set those to textviewa

    String str_select_id, str_year_id, str_month_id, str_select_comp_name, str_select_phoneno, str_select_email, str_addon_email, str_addon_email2, str_addon_email3, str_select_comp_address, str_select_pin, str_select_person_name,
            str_select_person_number, str_select_produc_series, str_select_model, str_select_modelno, str_select_pro_type, str_select_prod_qty,
            str_select_price, str_select_allotedto, str_select_team_id, str_select_discount,str_select_quote, str_select_desc, str_select_enq_throu,
            getStr_select_enq_throu_desc, str_select_status, str_select_image, str_select_remark, str_select_createdon, str_select_completeon = "";


    TextView txt_created_on, txt_enq_id, txt_enq_company_name, txt_enq_txt_addon_email, txt_enq_txt_addon_email2, txt_enq_txt_addon_email3,
            txt_enq_txt_email, txt_enq_address, txt_enq_pin, txt_enq_phone, txt_enq_conact_person,
            txt_enq_person_phone, txt_enq_product, txt_enq_model, txt_enq_model_no, txt_enq_model_type, txt_enq_product_qty,
            txt_enq_price, txt_enq_discount, txt_enq_description, txt_enq_enq_through, txt_enq_enq_thro_des, txt_enq_status, txt_enq_remark;

    ImageView img_completed;

    Button btn_proceed;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_completed_description);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isInternetOn();

        txt_created_on = (TextView) findViewById(R.id.appoint_des_txt_app_created_on);
        txt_enq_id = (TextView) findViewById(R.id.appoint_des_txt_enq_id);
        txt_enq_company_name = (TextView) findViewById(R.id.enq_des_txt_company_name);
        txt_enq_txt_email = (TextView) findViewById(R.id.enq_des_txt_email);
        txt_enq_txt_addon_email = (TextView) findViewById(R.id.enq_des_txt_addon_email);
        txt_enq_txt_addon_email2 = (TextView) findViewById(R.id.enq_des_txt_addon_email2);
        txt_enq_txt_addon_email3 = (TextView) findViewById(R.id.enq_des_txt_addon_email3);
        txt_enq_address = (TextView) findViewById(R.id.enq_des_txt_address);
        txt_enq_pin = (TextView) findViewById(R.id.enq_des_txt_pin);
        txt_enq_phone = (TextView) findViewById(R.id.enq_des_txt_comp_phone);
        txt_enq_conact_person = (TextView) findViewById(R.id.enq_des_txt_conact_person);
        txt_enq_person_phone = (TextView) findViewById(R.id.enq_des_txt_contact_person_phone);
        txt_enq_product = (TextView) findViewById(R.id.enq_des_txt_product);
        /*txt_enq_model = (TextView) findViewById(R.id.enq_des_txt_product_model);
        txt_enq_model_no = (TextView) findViewById(R.id.enq_des_txt_model_no);
        txt_enq_model_type = (TextView) findViewById(R.id.enq_des_txt_model_type);
        txt_enq_product_qty = (TextView) findViewById(R.id.enq_des_txt_product_qty);*/
        txt_enq_price = (TextView) findViewById(R.id.enq_des_txt_price);
        txt_enq_discount = (TextView) findViewById(R.id.enq_des_txt_discount);
        txt_enq_description = (TextView) findViewById(R.id.enq_des_txt_description);
        txt_enq_enq_through = (TextView) findViewById(R.id.enq_des_txt_enq_through);
        txt_enq_enq_thro_des = (TextView) findViewById(R.id.enq_des_txt_enq_thro_des);
        txt_enq_status = (TextView) findViewById(R.id.enq_des_txt_status);
        txt_enq_remark = (TextView) findViewById(R.id.enq_des_txt_remark);

        img_completed = (ImageView) findViewById(R.id.complaint_complete_img);

        btn_proceed = (Button) findViewById(R.id.completed_btn_proceed_ofm);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_select_id = sharedPreferences.getString("enq_no", "enq_no");
        str_year_id = sharedPreferences.getString("enq_year_id", "enq_year_id");
        str_month_id = sharedPreferences.getString("enq_month_id", "enq_month_id");
        str_select_comp_name = sharedPreferences.getString("enq_company_name", "enq_company_name");
        str_select_phoneno = sharedPreferences.getString("enq_company_phn_no", "enq_company_phn_no");
        str_select_email = sharedPreferences.getString("enq_company_email", "enq_company_email");
        str_addon_email = sharedPreferences.getString("enq_addon_email", "enq_addon_email");
        str_addon_email2 = sharedPreferences.getString("enq_addon_email2", "enq_addon_email2");
        str_addon_email3 = sharedPreferences.getString("enq_addon_email3", "enq_addon_email3");
        str_select_comp_address = sharedPreferences.getString("enq_company_address", "enq_company_address");
        str_select_pin = sharedPreferences.getString("enq_company_pincode", "enq_company_pincode");
        str_select_person_name = sharedPreferences.getString("enq_contact_person_name", "enq_contact_person_name");
        str_select_person_number = sharedPreferences.getString("enq_contact_person_phone_no", "enq_contact_person_phone_no");
        str_select_produc_series = sharedPreferences.getString("enq_product_series", "enq_product_series");
        str_select_model = sharedPreferences.getString("str_select_model", "str_select_model");
        str_select_modelno = sharedPreferences.getString("enq_product_model_no", "enq_product_model_no");
        str_select_pro_type = sharedPreferences.getString("enq_product_type", "enq_product_type");
        str_select_prod_qty = sharedPreferences.getString("enq_product_qty", "enq_product_qty");
        str_select_price = sharedPreferences.getString("enq_product_price", "enq_product_price");
        str_select_allotedto = sharedPreferences.getString("enq_alloted_to", "enq_alloted_to");
        str_select_team_id = sharedPreferences.getString("enq_team_id", "enq_team_id");
        str_select_discount = sharedPreferences.getString("enq_discount", "enq_discount");
        str_select_quote = sharedPreferences.getString("enq_quote", "enq_quote");
        str_select_desc = sharedPreferences.getString("enq_description", "enq_description");
        str_select_enq_throu = sharedPreferences.getString("enquiry_through", "enquiry_through");
        getStr_select_enq_throu_desc = sharedPreferences.getString("enquiry_through_description", "enquiry_through_description");
        str_select_status = sharedPreferences.getString("enq_status", "enq_status");
        str_select_image = sharedPreferences.getString("enq_image", "enq_image");
        str_select_remark = sharedPreferences.getString("enq_remarks", "enq_remarks");
        str_select_createdon = sharedPreferences.getString("enq_created_on", "enq_created_on");
        str_select_completeon = sharedPreferences.getString("enq_completed_on", "enq_completed_on");

        System.out.println("enq_no" + str_select_id);
        System.out.println("enq_year_id" + str_year_id);
        System.out.println("enq_month_id" + str_month_id);
        System.out.println("enq_company_name" + str_select_comp_name);
        System.out.println("enq_company_email" + str_select_email);
        System.out.println("enq_company_phn_no" + str_select_phoneno);
        System.out.println("enq_company_address" + str_select_comp_address);
        System.out.println("enq_company_pincode" + str_select_pin);
        System.out.println("enq_contact_person_name" + str_select_person_name);
        System.out.println("enq_contact_person_phone_no" + str_select_person_number);
        System.out.println("enq_product_series" + str_select_produc_series);
        System.out.println("enq_product_model" + str_select_model);
        System.out.println("enq_product_model_no" + str_select_modelno);
        System.out.println("enq_product_type" + str_select_pro_type);
        System.out.println("enq_product_qty" + str_select_prod_qty);
        System.out.println("enq_product_price" + str_select_price);
        System.out.println("enq_alloted_to" + str_select_allotedto);
        System.out.println("enq_team_id" + str_select_team_id);
        System.out.println("enq_discount" + str_select_discount);
        System.out.println("enq_description" + str_select_desc);
        System.out.println("enquiry_through" + str_select_enq_throu);
        System.out.println("enquiry_through_description" + getStr_select_enq_throu_desc);
        System.out.println("enq_remarks" + str_select_remark);
        System.out.println("enq_created_on" + str_select_createdon);
        System.out.println("enq_completed_on" + str_select_completeon);


        try {

            String str_img_path = "http://gemservice.in/crm/po/" + str_select_image;
            Glide.with(getApplicationContext()).load(str_img_path)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img_completed);

        } catch (Exception e) {

        }

        try {

            txt_created_on.setText(str_select_completeon);
            txt_enq_id.setText(str_select_id);
            txt_enq_company_name.setText(str_select_comp_name);
            txt_enq_txt_email.setText(str_select_email);
            txt_enq_txt_addon_email.setText(str_addon_email);
            txt_enq_txt_addon_email2.setText(str_addon_email2);
            txt_enq_txt_addon_email3.setText(str_addon_email3);
            txt_enq_address.setText(str_select_comp_address);
            txt_enq_pin.setText(str_select_pin);
            txt_enq_phone.setText(str_select_phoneno);
            txt_enq_conact_person.setText(str_select_person_name);
            txt_enq_person_phone.setText(str_select_person_number);
            txt_enq_product.setText(str_select_produc_series);
            txt_enq_price.setText(str_select_quote);
            txt_enq_discount.setText(str_select_discount);
            txt_enq_description.setText(str_select_desc);
            txt_enq_enq_through.setText(str_select_enq_throu);
            txt_enq_enq_thro_des.setText(getStr_select_enq_throu_desc);
            txt_enq_status.setText(str_select_status);
            txt_enq_remark.setText(str_select_remark);

        } catch (Exception e) {

        }


        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Order_One_Forwarding_Memo.class);
                startActivity(i);

            }
        });


    }


    /***********************************
     *  Internet Connection
     * ************************************/

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            new AlertDialog.Builder(Activity_Enquiry_Completed_Description.this)
                    .setTitle("GEM CRM")
                    .setMessage("Oops no internet !")
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                }
                            }).show();
            return false;
        }
        return false;
    }


}
