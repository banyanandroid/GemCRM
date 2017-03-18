package banyan.com.gemcrm.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import banyan.com.gemcrm.R;

/**
 * Created by Jo on 3/18/2017.
 */

public class Activity_Appointment_description extends AppCompatActivity {

    TextView txt_app_with, txt_app_date, txt_app_time, txt_app_through, txt_app_note, txt_created_on, txt_location;

    String str_with, str_date, str_time, str_through, str_note, str_created_on, str_location = "";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_description);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        txt_created_on = (TextView) findViewById(R.id.appoint_des_txt_app_created_on);
        txt_app_with = (TextView) findViewById(R.id.appoint_des_txt_app_with);
        txt_location = (TextView) findViewById(R.id.appoint_des_txt_app_location);
        txt_app_through = (TextView) findViewById(R.id.appoint_des_txt_through);
        txt_app_note = (TextView) findViewById(R.id.appoint_des_txt_app_note);
        txt_app_date = (TextView) findViewById(R.id.appoint_des_txt_app_date);
        txt_app_time = (TextView) findViewById(R.id.appoint_des_txt_time);


        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_with = sharedPreferences.getString("str_select_app_with", "str_select_app_with");
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

        }catch (Exception e) {

        }


    }

}
