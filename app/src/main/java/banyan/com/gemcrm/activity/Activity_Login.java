package banyan.com.gemcrm.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

/**
 * Created by User on 3/15/2017.
 */

public class Activity_Login extends Activity {

    Button btn_login;
    EditText edt_username, edt_password;
    TextView txt_forgot_password;

    String user_email, user_password;

    private static long back_pressed;

    private static final String TAG_NAME = "user_name";
    private static final String TAG_ID = "user_id";
    String TAG = "reg";

    SpotsDialog dialog;
    public static RequestQueue queue;


    String str_user_name, str_user_id;

    // Session Manager Class
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.login_btn_login);
        txt_forgot_password = (TextView) findViewById(R.id.login_txt_forgot_password);

        // Session Manager
        session = new SessionManager(getApplicationContext());

        btn_login = (Button) findViewById(R.id.login_btn_login);

        edt_username = (EditText) findViewById(R.id.login_edt_username);
        edt_password = (EditText) findViewById(R.id.login_edt_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_email = edt_username.getText().toString();
                user_password = edt_password.getText().toString();

                if (user_email.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Please Enter User ID", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_username.setError("Please Enter Email ID");
                } else if (user_password.equals("")) {
                    TastyToast.makeText(getApplicationContext(), "Please Enter Password", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    edt_password.setError("Please Enter Password");
                } else {
                    dialog = new SpotsDialog(Activity_Login.this);
                    dialog.show();
                    queue = Volley.newRequestQueue(Activity_Login.this);
                    Function_Authentication();
                }

            }
        });

    }

    /********************************
     * User Authentication
     *********************************/

    private void Function_Authentication() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_authentication, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_LOGIN", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        JSONArray arr;
                        arr = obj.getJSONArray("login_user");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            str_user_name = obj1.getString(TAG_NAME);
                            str_user_id = obj1.getString(TAG_ID);
                        }

                        System.out.println("NAME" + str_user_name);
                        System.out.println("ID" + str_user_id);

                        session.createLoginSession(str_user_name, str_user_id);

                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        TastyToast.makeText(getApplicationContext(), "Bad Credentials :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);
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

                params.put("user", user_email);
                params.put("password", user_password);

                System.out.println("user" + user_email);
                System.out.println("password" + user_password);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

}
