package banyan.com.gemcrm.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    // Session Manager Class
    SessionManager session;

    public static String str_id, str_name;
    String str_permission = null;
    String GcmId = null;

    SpotsDialog dialog;
    public static RequestQueue queue;

    //Notification Batch

    RelativeLayout notificationCount1, parent_batch;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        session = new SessionManager(getApplicationContext());

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        str_name = user.get(SessionManager.KEY_USER);
        str_id = user.get(SessionManager.KEY_USER_ID);
        str_permission = user.get(SessionManager.KEY_PERMISSION);

        try {
            System.out.println("user" + str_id);
            queue = Volley.newRequestQueue(MainActivity.this);
            Function_UpdateLastLogin();
        } catch (Exception e) {

        }


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }


    /**********************************
     * Main Menu
     *********************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item1 = menu.findItem(R.id.action_alert);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        parent_batch = (RelativeLayout) MenuItemCompat.getActionView(item1);
        tv = (TextView) notificationCount1.findViewById(R.id.badge_notification_2);
        tv.setText("0");

        parent_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Remaining Tasks", Toast.LENGTH_LONG).show();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Remaining Tasks", Toast.LENGTH_LONG).show();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alert) {

            Toast.makeText(getApplicationContext(), "Batch Clicked", Toast.LENGTH_LONG).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new Fragment_Dashboard();
                title = getString(R.string.title_dashboard);
                break;
            case 1:
                fragment = new Fragment_Enquiry();
                title = getString(R.string.title_enquiry);
                break;
            case 2:
                try {

                    if (str_permission.equals("yes")) {
                        fragment = new Fragment_Allotment();
                        title = getString(R.string.title_self_appointment);
                        break;
                    } else {

                        Alerter.create(MainActivity.this)
                                .setTitle("GEM CRM")
                                .setText("Yout Don't Have a Permission to Acess this Menu!")
                                .setBackgroundColor(R.color.Alert_Fail)
                                .show();
                    }

                } catch (Exception e) {

                }

            case 3:
                fragment = new Fragment_SendCatalogue();
                title = getString(R.string.title_send_catalogue);
                break;
            case 4:
                fragment = new Fragment_Appointments();
                title = getString(R.string.title_appointment);
                break;
            case 5:
                fragment = new Fragment_Target();
                title = getString(R.string.title_target);
                break;
            case 6:
                fragment = new Fragment_Campaign();
                title = getString(R.string.title_campaign);
                break;

            case 7:
                TastyToast.makeText(getApplicationContext(), "About", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                break;

            case 8:

                session.logoutUser();

                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


    /********************************
     * User Authentication
     *********************************/

    private void Function_UpdateLastLogin() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_lastlogin, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Log.d("USER_LOGIN", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    System.out.println("REG" + success);

                    if (success == 1) {

                        /*dialog = new SpotsDialog(MainActivity.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(MainActivity.this);
                        GetMyAllotedEnquiries();*/
                        // TastyToast.makeText(getApplicationContext(), "Done", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                    } else {
                        // TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    }

                    try {
                        queue = Volley.newRequestQueue(MainActivity.this);
                        Function_Task_Count();
                    } catch (Exception e) {

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                TastyToast.makeText(getApplicationContext(), "Internal Error :(", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_id);

                System.out.println("user" + str_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /********************************
     * User Task Count
     *********************************/

    private void Function_Task_Count() {

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_target_count, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);

                    String count = obj.getString("count");
                    try {

                        tv.setText("" + count);

                        System.out.println("SETTTT :::: " + count);

                    } catch (Exception e) {

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

                params.put("user", str_id);

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
            String str_status = "Want to Exit?";
            FunctionAlert(str_status);
        } catch (Exception e) {

        }
    }


    private void FunctionAlert(String status) {

        new AlertDialog.Builder(MainActivity.this)
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
                                // finish();
                                finishAffinity();
                            }
                        }).show();
    }


}