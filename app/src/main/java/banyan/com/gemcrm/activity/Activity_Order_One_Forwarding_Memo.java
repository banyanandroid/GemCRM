package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import dmax.dialog.SpotsDialog;


/**
 * Created by steve on 26/3/17.
 */

public class Activity_Order_One_Forwarding_Memo extends AppCompatActivity {

    TextView txt_order_q_date,txt_order_po_date,txt_order_sapcode,txt_order_project,txt_order_dealer,txt_order_socketlist,txt_order_region,txt_order_to;

    RadioButton ratio_order_master_date,ratio_order_oem,ratio_order_cbe,ratio_order_mumbai,ratio_order_gom;

    Button btn_order_submit;

    String str_ratio_master_date,str_ratio_oem,str_ratio_cbe,str_ratio_mumbai,str_ratio_gom,str_txt_q_date,str_txt_po_date,str_txt_sapcode,str_txt_project,str_txt_dealer,str_txt_socketlist,str_txt_region,str_txt_to;
    SpotsDialog dialog;
    public static RequestQueue queue;
    String TAG = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_one_forwarding_memo);

        btn_order_submit = (Button) findViewById(R.id.order_forwarding_memo_submit);

        txt_order_q_date = (TextView) findViewById(R.id.order_forwarding_memo_q_date);
        txt_order_po_date = (TextView) findViewById(R.id.order_forwarding_memo_po_date);
        txt_order_sapcode = (TextView) findViewById(R.id.order_forwarding_memo_sap);
        txt_order_project = (TextView) findViewById(R.id.order_forwarding_memo_project);
        txt_order_dealer = (TextView) findViewById(R.id.order_forwarding_memo_dealer);
        txt_order_socketlist = (TextView) findViewById(R.id.order_forwarding_memo_socketlist);
        txt_order_region = (TextView) findViewById(R.id.order_forwarding_memo_region);
        txt_order_to = (TextView) findViewById(R.id.order_forwarding_memo_to);

        ratio_order_master_date = (RadioButton) findViewById(R.id.order_forwarding_memo_master_date_ratio);
        ratio_order_oem = (RadioButton) findViewById(R.id.order_forwarding_memo_oem_ratio);
        ratio_order_cbe = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_cbe);
        ratio_order_mumbai = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_mumbai);
        ratio_order_gom = (RadioButton) findViewById(R.id.order_forwarding_memo_gem_gom);




        btn_order_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if (str_txt_q_date.equals("")) {
                    txt_order_q_date.setError("Please Enter Q-date");
                    TastyToast.makeText(getApplicationContext(), "Q-date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_po_date.equals("")) {

                    txt_order_po_date.setError("Please Enter PO-date");
                    TastyToast.makeText(getApplicationContext(), "PO-date is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_sapcode.equals(" ")) {
                    txt_order_sapcode.setError("Please enter Sapcode");
                    TastyToast.makeText(getApplicationContext(), "Sapcode is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_project.equals(" ")) {

                    txt_order_project.setError("Please enter Project");
                    TastyToast.makeText(getApplicationContext(), "Project is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_dealer.equals("")) {

                    txt_order_dealer.setError("Please enter Dealer");
                    TastyToast.makeText(getApplicationContext(), "Dealer is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else if (str_txt_socketlist.equals("")) {

                    txt_order_socketlist.setError("Please enter SocketList");
                    TastyToast.makeText(getApplicationContext(), "SocketList is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_txt_region.equals("")) {

                    txt_order_region.setError("Please enter region");
                    TastyToast.makeText(getApplicationContext(), "region is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                } else if (str_txt_to.equals("")) {
                    txt_order_to.setError("Please enter to");
                    TastyToast.makeText(getApplicationContext(), "to is Empty", TastyToast.LENGTH_LONG, TastyToast.WARNING);

                } else {
                    dialog = new SpotsDialog(Activity_Order_One_Forwarding_Memo.this);
                    dialog.show();
                    //  queue = Volley.newRequestQueue(Activity_Order_Forwarding_Memo.this);
                    //  Function_Submit();
                }*/

                Intent i = new Intent(getApplicationContext(),Activity_Order_Two_Customerdetails.class);
                startActivity(i);
                finish();

            }

        });

    }
    private void Function_Submit(){


        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_editappointment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("EXAMPLE", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        Alerter.create(Activity_Order_One_Forwarding_Memo.this)
                                .setTitle("GEM CRM")
                                .setText("Order forwarding Successfully (: ")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else {

                        /*adapter = new Appointment_Adapter(Activity_Appoinment_Edit.this,
                                appointment_list);
                        List.setAdapter(adapter);*/

                        Alerter.create(Activity_Order_One_Forwarding_Memo.this)
                                .setTitle("GEM CRM")
                                .setText("Order forwarding Failed :( \n Try Again")
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
                TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("ratio_order_master_date", str_ratio_master_date);
                params.put("ratio_order_oem", str_ratio_oem);
                params.put(" ratio_order_cbe", str_ratio_cbe);
                params.put(" ratio_order_mumbai", str_ratio_mumbai);
                params.put(" ratio_order_gom", str_ratio_gom);
                params.put("txt_order_q_date", str_txt_q_date);
                params.put("txt_order_po_date", str_txt_po_date);
                params.put(" txt_order_sapcode", str_txt_sapcode);
                params.put("txt_order_project", str_txt_project);
                params.put("txt_order_dealer", str_txt_dealer);
                params.put(" txt_order_socketlist", str_txt_socketlist);
                params.put("txt_order_region", str_txt_region);
                params.put("txt_order_project", str_txt_to);


                System.out.println("ratio_order_master_date : " + str_ratio_master_date);
                System.out.println("ratio_order_oem : " + str_ratio_oem);
                System.out.println(" ratio_order_cbe : " + str_ratio_cbe);
                System.out.println(" ratio_order_mumbai : " + str_ratio_mumbai);
                System.out.println(" ratio_order_gom : " + str_ratio_gom);
                System.out.println("txt_order_q_date : " + str_txt_q_date);
                System.out.println("txt_order_po_date : " + str_txt_po_date);
                System.out.println(" txt_order_sapcode : " + str_txt_sapcode);
                System.out.println("txt_order_project : " + str_txt_project);
                System.out.println("txt_order_dealer : " + str_txt_dealer);
                System.out.println(" txt_order_socketlist : " + str_txt_socketlist);
                System.out.println("txt_order_region: " + str_txt_region);
                System.out.println("txt_order_project : " + str_txt_to);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

}



