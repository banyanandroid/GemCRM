package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.sdsmdg.tastytoast.TastyToast;

import banyan.com.gemcrm.R;
import dmax.dialog.SpotsDialog;


/**
 * Created by steve on 26/3/17.
 */

public class Activity_Order_One_Forwarding_Memo extends AppCompatActivity {

    EditText edt_order_q_date, edt_order_po_date, edt_order_sapcode, edt_order_project, edt_order_oem, edt_order_dealer, edt_order_socketlist, edt_order_region, edt_order_to;

    RadioButton ratio_order_master_date, ratio_order_oem, ratio_order_cbe, ratio_order_mumbai, ratio_order_gom;

    Button btn_order_next, btn_order_previous;

    String str_ratio_master_date, str_ratio_oem, str_ratio_cbe, str_ratio_mumbai, str_ratio_gom, str_txt_q_date, str_txt_po_date, str_txt_sapcode, str_txt_project,
            str_edt_oem, str_txt_dealer, str_txt_socketlist, str_txt_region, str_txt_to = "";
    SpotsDialog dialog;
    public static RequestQueue queue;
    String TAG = " ";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_one_forwarding_memo);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        btn_order_next = (Button) findViewById(R.id.order_forwarding_memo_next);
        btn_order_previous = (Button) findViewById(R.id.order_forwarding_memo_previous);

        edt_order_q_date = (EditText) findViewById(R.id.order_forwarding_memo_q_date);
        edt_order_po_date = (EditText) findViewById(R.id.order_forwarding_memo_po_date);
        edt_order_sapcode = (EditText) findViewById(R.id.order_forwarding_memo_sap);
        edt_order_project = (EditText) findViewById(R.id.order_forwarding_memo_project);
        edt_order_oem = (EditText) findViewById(R.id.order_forwarding_memo_oem);
        edt_order_dealer = (EditText) findViewById(R.id.order_forwarding_memo_dealer);
        edt_order_socketlist = (EditText) findViewById(R.id.order_forwarding_memo_socketlist);
        edt_order_region = (EditText) findViewById(R.id.order_forwarding_memo_region);
        edt_order_to = (EditText) findViewById(R.id.order_forwarding_memo_to);

        ratio_order_master_date = (RadioButton) findViewById(R.id.order_forwarding_memo_master_date_ratio);
        ratio_order_oem = (RadioButton) findViewById(R.id.order_forwarding_memo_oem_ratio);
        ratio_order_cbe = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_cbe);
        ratio_order_mumbai = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_mumbai);
        ratio_order_gom = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_gom);


        btn_order_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_txt_q_date = edt_order_q_date.getText().toString();
                str_txt_po_date = edt_order_po_date.getText().toString();
                str_txt_sapcode = edt_order_sapcode.getText().toString();
                str_txt_project = edt_order_project.getText().toString();
                str_edt_oem = edt_order_oem.getText().toString();
                str_txt_dealer = edt_order_dealer.getText().toString();
                str_txt_socketlist = edt_order_socketlist.getText().toString();
                str_txt_region = edt_order_region.getText().toString();
                str_txt_to = edt_order_to.getText().toString();

                if (str_txt_q_date.equals("")) {
                    edt_order_q_date.setError("Please Enter Q-date");
                    TastyToast.makeText(getApplicationContext(), "Q-date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_po_date.equals("")) {

                    edt_order_po_date.setError("Please Enter PO-date");
                    TastyToast.makeText(getApplicationContext(), "PO-date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_sapcode.equals(" ")) {
                    edt_order_sapcode.setError("Please enter Sapcode");
                    TastyToast.makeText(getApplicationContext(), "Sapcode is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_project.equals(" ")) {

                    edt_order_project.setError("Please enter Project");
                    TastyToast.makeText(getApplicationContext(), "Project is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_dealer.equals("")) {

                    edt_order_dealer.setError("Please enter Dealer");
                    TastyToast.makeText(getApplicationContext(), "Dealer is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_socketlist.equals("")) {

                    edt_order_socketlist.setError("Please enter SocketList");
                    TastyToast.makeText(getApplicationContext(), "SocketList is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_txt_region.equals("")) {

                    edt_order_region.setError("Please enter region");
                    TastyToast.makeText(getApplicationContext(), "region is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_txt_to.equals("")) {
                    edt_order_to.setError("Please enter to");
                    TastyToast.makeText(getApplicationContext(), "to is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_edt_oem.equals("")) {
                    edt_order_oem.setError("Please enter to");
                    TastyToast.makeText(getApplicationContext(), "to is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else {

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("str_ratio_master_date", str_ratio_master_date);
                    editor.putString("str_ratio_oem", str_ratio_oem);
                    editor.putString("str_ratio_cbe", str_ratio_cbe);
                    editor.putString("str_ratio_mumbai", str_ratio_mumbai);
                    editor.putString("str_ratio_gom", str_ratio_gom);
                    editor.putString("str_txt_q_date", str_txt_q_date);
                    editor.putString("str_txt_po_date", str_txt_po_date);
                    editor.putString("str_txt_sapcode", str_txt_sapcode);
                    editor.putString("str_txt_project", str_txt_project);
                    editor.putString("str_edt_oem", str_edt_oem);
                    editor.putString("str_txt_dealer", str_txt_dealer);
                    editor.putString("str_txt_socketlist", str_txt_socketlist);
                    editor.putString("str_txt_region", str_txt_region);
                    editor.putString("str_txt_to", str_txt_to);

                    editor.commit();


                    Intent i = new Intent(getApplicationContext(), Activity_Order_Two_Customerdetails.class);
                    startActivity(i);
                    finish();


                }


            }

        });

        btn_order_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Enquiry_Completed_Description.class);
                startActivity(i);
                finish();


            }
        });


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


            // GET And Set

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


            str_txt_q_date = sharedPreferences.getString("str_txt_q_date", "str_txt_q_date");
            str_txt_po_date = sharedPreferences.getString("str_txt_po_date", "str_txt_po_date");
            str_txt_sapcode = sharedPreferences.getString("str_txt_sapcode", "str_txt_sapcode");
            str_txt_project = sharedPreferences.getString("str_txt_project", "str_txt_project");
            str_txt_dealer = sharedPreferences.getString("str_txt_dealer", "str_txt_dealer");
            str_txt_socketlist = sharedPreferences.getString("str_txt_socketlist", "str_txt_socketlist");
            str_txt_region = sharedPreferences.getString("str_txt_region", "str_txt_region");
            str_txt_to = sharedPreferences.getString("str_txt_to", "str_txt_to");
            str_edt_oem = sharedPreferences.getString("str_edt_oem", "str_edt_oem");


            try {


                edt_order_q_date.setText(str_txt_q_date);
                edt_order_po_date.setText(str_txt_po_date);
                edt_order_sapcode.setText(str_txt_sapcode);
                edt_order_project.setText(str_txt_project);
                edt_order_dealer.setText(str_txt_dealer);
                edt_order_socketlist.setText(str_txt_socketlist);
                edt_order_region.setText(str_txt_region);
                edt_order_to.setText(str_txt_to);
                edt_order_oem.setText(str_edt_oem);

            } catch (Exception e) {

            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}





