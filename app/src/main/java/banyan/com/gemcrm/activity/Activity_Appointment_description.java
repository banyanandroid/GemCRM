package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import banyan.com.gemcrm.R;

/**
 * Created by Jo on 3/18/2017.
 */

public class Activity_Appointment_description extends AppCompatActivity {

    TextView txt_app_with,txt_app_company_name , txt_app_date, txt_app_time, txt_app_through, txt_app_note, txt_created_on, txt_location;

    String str_appoint_id,str_with, str_company_name, str_date, str_time, str_through, str_note, str_created_on, str_location = "";

    Button btn_edit_appoinment , btn_delete_appoinment ;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_description);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        btn_edit_appoinment =(Button) findViewById(R.id.app_des_btn_edit);
        txt_created_on = (TextView) findViewById(R.id.appoint_des_txt_app_created_on);
        txt_app_with = (TextView) findViewById(R.id.appoint_des_txt_app_with);
        txt_location = (TextView) findViewById(R.id.appoint_des_txt_app_location);
        txt_app_through = (TextView) findViewById(R.id.appoint_des_txt_through);
        txt_app_note = (TextView) findViewById(R.id.appoint_des_txt_app_note);
        txt_app_date = (TextView) findViewById(R.id.appoint_des_txt_app_date);
        txt_app_time = (TextView) findViewById(R.id.appoint_des_txt_time);
        txt_app_company_name = (TextView) findViewById(R.id.appoint_des_txt_company_name);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());


        str_appoint_id = sharedPreferences.getString("str_select_id", "str_select_id");
        str_with = sharedPreferences.getString("str_select_app_with", "str_select_app_with");
        str_company_name = sharedPreferences.getString("str_select_app_company_name", "str_select_app_company_name");
        str_date = sharedPreferences.getString("str_select_app_date", "str_select_app_date");
        str_time = sharedPreferences.getString("str_select_app_time", "str_select_app_time");
        str_through = sharedPreferences.getString("str_select_app_through", "str_select_app_through");
        str_note = sharedPreferences.getString("str_select_app_des", "str_select_app_des");
        str_created_on = sharedPreferences.getString("str_select_app_createdon", "str_select_app_createdon");
        str_location = sharedPreferences.getString("str_select_app_location", "str_select_app_location");


        try {

            txt_created_on.setText(str_created_on);
            txt_app_with.setText(str_with);
            txt_app_time.setText(str_time);
            txt_location.setText(str_location);
            txt_app_through.setText(str_through);
            txt_app_note.setText(str_note);
            txt_app_date.setText(str_date);
            txt_app_company_name.setText(str_company_name);

        }catch (Exception e) {

        }

        btn_edit_appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Activity_Appoinment_Edit.class);
                startActivity(i);
                finish();
            }
        });


    }

}
