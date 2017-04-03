package banyan.com.gemcrm.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.Alloted_Complaints_Adapter;
import banyan.com.gemcrm.adapter.MyTask_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.Pojo_Catalog;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by User on 3/28/2017.
 */

public class Activity_Send_Catelog extends AppCompatActivity {

    MyCustomAdapter dataAdapter = null;
    ListView listView;
    Button myButton;

    EditText edt_email;

    String str_user_name, str_user_id;
    String str_task_name, str_task_des;

    String str_selected, str_select_email = "";

    SpotsDialog dialog;
    public static RequestQueue queue;

    ArrayList<Pojo_Catalog> countryList;

    // Session Manager Class
    SessionManager session;

    String TAG = "add task";

    public static final String TAG_CAT_ID = "catlogue_id";
    public static final String TAG_CAT_Name = "catlogue_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_catalog);

        listView = (ListView) findViewById(R.id.listView1);
        myButton = (Button) findViewById(R.id.send_catalog_btn_send);
        countryList = new ArrayList<Pojo_Catalog>();

        try {
            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getApplicationContext());
            str_select_email = sharedPreferences.getString("enq_company_email", "enq_company_email");

        } catch (Exception e) {

        }

        try {

            dialog = new SpotsDialog(Activity_Send_Catelog.this);
            dialog.show();
            queue = Volley.newRequestQueue(Activity_Send_Catelog.this);
            GetCatalogue();

        } catch (Exception e) {
            // TODO: handle exceptions
        }

        checkButtonClick();

    }

    /*****************************
     * GET My Task
     ***************************/

    public void GetCatalogue() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.GET,
                AppConfig.url_catalogue, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("catlogue");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String id = obj1.getString(TAG_CAT_ID);
                            String catalogue = obj1.getString(TAG_CAT_Name);

                            System.out.println("CAT ID " + id);
                            System.out.println("CAT NAME " + catalogue);

                            Pojo_Catalog country = new Pojo_Catalog(id, catalogue, false);
                            countryList.add(country);

                            //create an ArrayAdaptar from the String Array
                            dataAdapter = new MyCustomAdapter(Activity_Send_Catelog.this,
                                    R.layout.list_item_catalog, countryList);
                            // Assign adapter to ListView
                            listView.setAdapter(dataAdapter);


                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    // When clicked, show a toast with the TextView text
                                    Pojo_Catalog country = (Pojo_Catalog) parent.getItemAtPosition(position);
                                   /* Toast.makeText(getApplicationContext(),
                                            "Clicked on Row: " + country.getName(),
                                            Toast.LENGTH_LONG).show();*/
                                }
                            });

                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Send_Catelog.this)
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
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
               /* Alerter.create(getActivity())
                        .setTitle("GEM CRM")
                        .setText("Internal Error !")
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();*/
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    private class MyCustomAdapter extends ArrayAdapter<Pojo_Catalog> {

        private ArrayList<Pojo_Catalog> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Pojo_Catalog> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Pojo_Catalog>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_item_catalog, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Pojo_Catalog country = (Pojo_Catalog) cb.getTag();
                        Toast.makeText(getApplicationContext(),
                                "Clicked on Checkbox: " + cb.getText() +
                                        " is " + cb.isChecked(),
                                Toast.LENGTH_LONG).show();
                        country.setSelected(cb.isChecked());
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Pojo_Catalog country = countryList.get(position);
            // holder.code.setText(" (" +  country.getCode() + ")");
            holder.name.setText(country.getName());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);

            return convertView;

        }

    }

    private void checkButtonClick() {

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Pojo_Catalog> countryList = dataAdapter.countryList;
                for (int i = 0; i < countryList.size(); i++) {
                    Pojo_Catalog country = countryList.get(i);
                    if (country.isSelected()) {
                        responseText.append("," + country.getName());

                        str_selected = responseText.toString();
                    }
                }

                Toast.makeText(getApplicationContext(),
                        str_selected, Toast.LENGTH_LONG).show();

                FunctionCAllAlert();

            }
        });

    }

    /***************************
     * Function ALert
     * *************************/

    private void FunctionCAllAlert() {

        LayoutInflater li = LayoutInflater.from(Activity_Send_Catelog.this);
        View promptsView = li
                .inflate(R.layout.alertdialog_send_catalogue, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Activity_Send_Catelog.this);
        alertDialogBuilder.setTitle("GEM CRM");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        // alertDialogBuilder.setInverseBackgroundForced(#26A65B);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        edt_email = (EditText) promptsView
                .findViewById(R.id.send_catalog_alert_edt_email);

        edt_email.setText("" + str_select_email);


        alertDialogBuilder.setCancelable(false)

                .setNeutralButton("Done",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                Toast.makeText(getApplicationContext(), str_select_email + " " + str_selected, Toast.LENGTH_LONG).show();


                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    /********************************
     * User Send Catalogue
     *********************************/

    private void Function_SendCatalogue() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_my_task, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Send_Catelog.this)
                                .setTitle("GEM CRM")
                                .setText("Catalogue Sent Successfully :)")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else if (success == 0) {

                        Alerter.create(Activity_Send_Catelog.this)
                                .setTitle("GEM CRM")
                                .setText("Catalogue Sent Failed :(")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                //params.put("user", str_user_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

}
