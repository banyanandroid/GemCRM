package banyan.com.gemcrm.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import banyan.com.gemcrm.R;

/**
 * Created by Schan on 19-Mar-17.
 */

public class Activity_Campaing_description extends AppCompatActivity {


    TextView Camp_name, Camp_desc, Camp_location, Camp_start_date, Camp_end_date, Camp_created_on;

    Button Camp_desc_delete, Camp_desc_update;

    String str_up_camp_name, str_up_camp_desc, str_up_camp_location, str_up_camp_start_date, str_up_camp_end_date, str_up_camp_created_on;

    private Toolbar mToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_description);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Camp_desc_delete = (Button) findViewById(R.id.camp_des_btn_delete);
        Camp_desc_update = (Button) findViewById(R.id.camp_des_btn_edit);

        Camp_name = (TextView) findViewById(R.id.camp_des_txt_camp_name);
        Camp_desc = (TextView) findViewById(R.id.camp_des_txt_camp_desc);
        Camp_created_on = (TextView) findViewById(R.id.camp_des_txt_camp_created_on);
        Camp_location = (TextView) findViewById(R.id.camp_des_txt_camp_location);
        Camp_start_date = (TextView) findViewById(R.id.camp_des_txt_camp_start_date);
        Camp_end_date = (TextView) findViewById(R.id.camp_des_txt_camp_end_date);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_up_camp_name = sharedPreferences.getString("str_camp_name", "str_camp_name");
        str_up_camp_desc = sharedPreferences.getString("str_camp_des", "str_camp_des");
        str_up_camp_created_on = sharedPreferences.getString("str_camp_created_on", "str_camp_created_on");
        str_up_camp_location = sharedPreferences.getString("str_camp_location", "str_camp_location");
        str_up_camp_start_date = sharedPreferences.getString("str_camp_start_date", "str_camp_start_date");
        str_up_camp_end_date = sharedPreferences.getString("str_camp_end_date", "str_camp_end_date");

        try {

            Camp_name.setText(str_up_camp_name);
            Camp_desc.setText(str_up_camp_desc);
            Camp_created_on.setText(str_up_camp_created_on);
            Camp_location.setText(str_up_camp_location);
            Camp_start_date.setText(str_up_camp_start_date);
            Camp_end_date.setText(str_up_camp_end_date);


        } catch (Exception e) {

        }


    }

}
