package banyan.com.gemcrm.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.sdsmdg.tastytoast.TastyToast;
import com.tapadoo.alerter.Alerter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.adapter.Appointment_Adapter;
import banyan.com.gemcrm.global.AppConfig;
import banyan.com.gemcrm.global.Config;
import banyan.com.gemcrm.global.SessionManager;
import dmax.dialog.SpotsDialog;

/**
 * Created by User on 3/20/2017.
 * Rise of the emporor...
 */
public class Activity_Enquiry_Process extends AppCompatActivity {

    String str_select_id, str_select_comp_name, str_select_phoneno, str_select_email, str_select_addon_email, str_select_addon_email2, str_select_addon_email3, str_select_comp_address, str_select_pin, str_select_person_name,
            str_select_person_number, str_select_produc_id, str_select_produc_series, str_select_desc, str_select_enq_throu, str_status,
            getStr_select_enq_throu_desc, str_select_createdon, str_select_completeon = "";

    TextView txt_created_on, txt_enq_id, txt_enq_company_name, txt_enq_product, txt_enq_enq_through, txt_enq_enq_thro_des;


    EditText edt_txt_enq_txt_email, edt_txt_enq_address, edt_txt_enq_pin, edt_txt_enq_phone, edt_txt_enq_conact_person,
            edt_txt_enq_person_phone;

    EditText edt_addon_email, edt_addon_email2, edt_addon_email3;

    EditText edt_tax_pnf, edt_tax_vat_cst, edt_tax_exduty;

    EditText edt_spec, edt_discount, edt_price;

    EditText edt_price2, edt_price3, edt_price4, edt_price5, edt_price6;
    EditText edt_add_price, edt_add_price2, edt_add_price3, edt_add_price4, edt_add_price5, edt_add_price6;

    TextView txt_minus, txt_value, txt_add;
    TextView txt_minus2, txt_value2, txt_add2;
    TextView txt_minus3, txt_value3, txt_add3;
    TextView txt_minus4, txt_value4, txt_add4;
    TextView txt_minus5, txt_value5, txt_add5;
    TextView txt_minus6, txt_value6, txt_add6;

    //Hide & Seek

    TextView add_more_1, add_more_2, add_more_3, add_more_4, add_more_5;

    CardView card1, card2, card3, card4, card5;

    ImageView img_delete1, img_delete2, img_delete3, img_delete4, img_delete5;

    int i = 0;

    String actual_discount = "";

    String str_select_group;

    SpotsDialog dialog;
    public static RequestQueue queue;

    SessionManager session;
    String str_user_name, str_user_id;

    String TAG = "add task";

    public static final String TAG_PROD_TITLE = "product_series_name";
    public static final String TAG_Discount = "user_discount";

    public static final String TAG_Model_NO = "product_model_no";

    public static final String TAG_Model_Type = "product_type";
    public static final String TAG_Model_PRICE = "product_price";

    public static final String TAG_GROUP_ID = "product_id";
    public static final String TAG_GROUP_TITLE = "product_group_name";

    public static final String TAG_QUOTATION_NO = "quotation_no";
    public static final String TAG_QUOTATION_PRICE = "enq_product_price";
    public static final String TAG_QUOTATION_DATE = "created_on";


    public static final String TAG_TAX = "tax_name";
    public static final String TAG_TAX_ID = "tax_id";

    int select_product_group = 0;
    int select_product_model = 0;
    int select_product_model_no = 0;

    Spinner spn_group1, spn_group2, spn_group3, spn_group4, spn_group5, spn_group6;
    ArrayList<String> Arraylist_group2 = null;
    ArrayList<String> Arraylist_group_id2 = null;
    String str_Selected_group1 = "";
    String str_Selected_group2 = "";
    String str_Selected_group3 = "";
    String str_Selected_group4 = "";
    String str_Selected_group5 = "";
    String str_Selected_group6 = "";

    Spinner spn_model, spn_model2, spn_model3, spn_model4, spn_model5, spn_model6;
    ArrayList<String> Arraylist_model = null;
    ArrayList<String> Arraylist_model2 = null;
    ArrayList<String> Arraylist_model3 = null;
    ArrayList<String> Arraylist_model4 = null;
    ArrayList<String> Arraylist_model5 = null;
    ArrayList<String> Arraylist_model6 = null;

    String str_Selected_model = "";
    String str_Selected_model2 = "";
    String str_Selected_model3 = "";
    String str_Selected_model4 = "";
    String str_Selected_model5 = "";
    String str_Selected_model6 = "";

    Spinner spn_model_no, spn_model_no2, spn_model_no3, spn_model_no4, spn_model_no5, spn_model_no6;
    ArrayList<String> Arraylist_model_no = null;
    ArrayList<String> Arraylist_model_no2 = null;
    ArrayList<String> Arraylist_model_no3 = null;
    ArrayList<String> Arraylist_model_no4 = null;
    ArrayList<String> Arraylist_model_no5 = null;
    ArrayList<String> Arraylist_model_no6 = null;

    String str_Selected_model_no = "";
    String str_Selected_model_no2 = "";
    String str_Selected_model_no3 = "";
    String str_Selected_model_no4 = "";
    String str_Selected_model_no5 = "";
    String str_Selected_model_no6 = "";

    Spinner spn_model_type, spn_model_type2, spn_model_type3, spn_model_type4, spn_model_type5, spn_model_type6;
    ArrayList<String> Arraylist_model_type = null;
    ArrayList<String> Arraylist_model_price = null;
    String str_Selected_model_type, str_Selected_model_price = "";

    ArrayList<String> Arraylist_model_type2 = null;
    ArrayList<String> Arraylist_model_price2 = null;
    String str_Selected_model_type2, str_Selected_model_price2 = "";

    ArrayList<String> Arraylist_model_type3 = null;
    ArrayList<String> Arraylist_model_price3 = null;
    String str_Selected_model_type3, str_Selected_model_price3 = "";

    ArrayList<String> Arraylist_model_type4 = null;
    ArrayList<String> Arraylist_model_price4 = null;
    String str_Selected_model_type4, str_Selected_model_price4 = "";

    ArrayList<String> Arraylist_model_type5 = null;
    ArrayList<String> Arraylist_model_price5 = null;
    String str_Selected_model_type5, str_Selected_model_price5 = "";

    ArrayList<String> Arraylist_model_type6 = null;
    ArrayList<String> Arraylist_model_price6 = null;
    String str_Selected_model_type6, str_Selected_model_price6 = "";

    Spinner spn_ga_diagram1;
    String str_ga_dia1 = "";

    Spinner spn_status;
    Spinner spn_enq_no_for_comple_drop;

    ArrayList<String> Arraylist_quotation_no = null;
    String str_Selected_quotation_no = "";

    /*Spinner spn_tax;
    ArrayList<String> Arraylist_tax = null;
    ArrayList<String> Arraylist_tax_id = null;
    String str_Selected_tax = "";*/


    String str_tax_pnf, str_tax_vat_cst, str_tax_exduty = "";

    LinearLayout linear_appointment, linear_enq_no;

    EditText edt_enq_appint_date, edt_enq_appint_time;
    int from_year, from_month, from_date;

    Button btn_save, btn_save_pre_quote, btn_send_catalog;

    String str_po_en_no, str_po_comp_name, str_po_email, str_po_address, str_po_pin, str_po_phone, str_po_contact_person,
            str_po_contact_person_phone, str_po_product, str_po_enq_through, str_po_enq_description,
            str_po_group1, str_po_model1, str_po_model_no1, str_po_model_type, str_po_qty1, str_po_price1, str_po_add_price1,
            str_po_group2, str_po_model2, str_po_model_no2, str_po_mode2_type, str_po_qty2, str_po_price2, str_po_add_price2,
            str_po_group3, str_po_model3, str_po_model_no3, str_po_mode3_type, str_po_qty3, str_po_price3, str_po_add_price3,
            str_po_group4, str_po_model4, str_po_model_no4, str_po_mode4_type, str_po_qty4, str_po_price4, str_po_add_price4,
            str_po_group5, str_po_model5, str_po_model_no5, str_po_mode5_type, str_po_qty5, str_po_price5, str_po_add_price5,
            str_po_group6, str_po_model6, str_po_model_no6, str_po_mode6_type, str_po_qty6, str_po_price6, str_po_add_price6,
            str_po_status, str_po_appoint_date, str_po_appoint_time, str_po_discount, str_po_spec = null;

    String str_feright_terms, str_erection, str_delivery_weeks = "";

    Spinner spn_feright_terms, spn_delivery_weeks;

    EditText edt_erection;

    private Toolbar mToolbar;

    //Notification Batchh

    RelativeLayout notificationCount1, parent_batch;
    TextView tv;

    String quotation_no = "";


    // Completed Image capture process

    LinearLayout linear_image_layout;
    LinearLayout linear_po_layout;

    ImageView img_post_image;

    String imagepath1 = "";
    String str_function = "";

    EditText edt_po_number;
    String str_po_no = "";

    /*************************************
     * For Image Capture
     *****************************************/

    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private SharedPreferences permissionStatus;
    String[] permissionsRequired = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};
    private boolean sentToSettings = false;

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;
    private final static int RESULT_LOAD_IMAGE = 3;
    private static final int REQUEST_CAMERA1 = 4;

    Uri photoPath, mImageUri;
    String encodedstring = "";

    private Uri fileUri;

    private GoogleApiClient client;

    String str_appoint_url = "";
    String str_call_TL_url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_process);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isInternetOn();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);

        txt_created_on = (TextView) findViewById(R.id.enq_process_txt_app_created_on);
        txt_enq_id = (TextView) findViewById(R.id.enq_process_txt_id);
        txt_enq_company_name = (TextView) findViewById(R.id.enq_process_txt_company_name);
        txt_enq_product = (TextView) findViewById(R.id.enq_process_txt_product);
        txt_enq_enq_through = (TextView) findViewById(R.id.enq_process_txt_enq_thro);
        txt_enq_enq_thro_des = (TextView) findViewById(R.id.enq_process_txt_enq_thro_desc);

        edt_txt_enq_txt_email = (EditText) findViewById(R.id.enq_process_txt_email);
        edt_txt_enq_address = (EditText) findViewById(R.id.enq_process_txt_address);
        edt_txt_enq_pin = (EditText) findViewById(R.id.enq_process_txt_pin);
        edt_txt_enq_phone = (EditText) findViewById(R.id.enq_process_txt_comp_phone);
        edt_txt_enq_conact_person = (EditText) findViewById(R.id.enq_process_txt_conact_person);
        edt_txt_enq_person_phone = (EditText) findViewById(R.id.enq_process_txt_contact_person_phone);

        txt_minus = (TextView) findViewById(R.id.enq_process_txt_minus1);
        txt_value = (TextView) findViewById(R.id.enq_process_txt_count1);
        txt_add = (TextView) findViewById(R.id.enq_process_txt_add1);

        txt_minus2 = (TextView) findViewById(R.id.enq_process_txt_minus2);
        txt_value2 = (TextView) findViewById(R.id.enq_process_txt_count2);
        txt_add2 = (TextView) findViewById(R.id.enq_process_txt_add2);

        txt_minus3 = (TextView) findViewById(R.id.enq_process_txt_minus3);
        txt_value3 = (TextView) findViewById(R.id.enq_process_txt_count3);
        txt_add3 = (TextView) findViewById(R.id.enq_process_txt_add3);

        txt_minus4 = (TextView) findViewById(R.id.enq_process_txt_minus4);
        txt_value4 = (TextView) findViewById(R.id.enq_process_txt_count4);
        txt_add4 = (TextView) findViewById(R.id.enq_process_txt_add4);

        txt_minus5 = (TextView) findViewById(R.id.enq_process_txt_minus5);
        txt_value5 = (TextView) findViewById(R.id.enq_process_txt_count5);
        txt_add5 = (TextView) findViewById(R.id.enq_process_txt_add5);

        txt_minus6 = (TextView) findViewById(R.id.enq_process_txt_minus6);
        txt_value6 = (TextView) findViewById(R.id.enq_process_txt_count6);
        txt_add6 = (TextView) findViewById(R.id.enq_process_txt_add6);

        edt_addon_email = (EditText) findViewById(R.id.enq_process_txt_addon_email);
        edt_addon_email2 = (EditText) findViewById(R.id.enq_process_txt_addon_email2);
        edt_addon_email3 = (EditText) findViewById(R.id.enq_process_txt_addon_email3);

        edt_discount = (EditText) findViewById(R.id.enq_process_edt_discount);
        edt_spec = (EditText) findViewById(R.id.enq_process_edt_remarks2);
        edt_price = (EditText) findViewById(R.id.enq_process_edt_price1);

        edt_price2 = (EditText) findViewById(R.id.enq_process_edt_price2);
        edt_price3 = (EditText) findViewById(R.id.enq_process_edt_price3);
        edt_price4 = (EditText) findViewById(R.id.enq_process_edt_price4);
        edt_price5 = (EditText) findViewById(R.id.enq_process_edt_price5);
        edt_price6 = (EditText) findViewById(R.id.enq_process_edt_price6);

        edt_add_price = (EditText) findViewById(R.id.enq_process_edt_add_price1);
        edt_add_price2 = (EditText) findViewById(R.id.enq_process_edt_add_price2);
        edt_add_price3 = (EditText) findViewById(R.id.enq_process_edt_add_price3);
        edt_add_price4 = (EditText) findViewById(R.id.enq_process_edt_add_price4);
        edt_add_price5 = (EditText) findViewById(R.id.enq_process_edt_add_price5);
        edt_add_price6 = (EditText) findViewById(R.id.enq_process_edt_add_price6);

        spn_group1 = (Spinner) findViewById(R.id.enquiry_process_spn_product_group1);
        spn_group2 = (Spinner) findViewById(R.id.enquiry_process_spn_product_group2);
        spn_group3 = (Spinner) findViewById(R.id.enquiry_process_spn_product_group3);
        spn_group4 = (Spinner) findViewById(R.id.enquiry_process_spn_product_group4);
        spn_group5 = (Spinner) findViewById(R.id.enquiry_process_spn_product_group5);
        spn_group6 = (Spinner) findViewById(R.id.enquiry_process_spn_product_group6);

        spn_model = (Spinner) findViewById(R.id.enq_process_spn_product_model1);
        spn_model2 = (Spinner) findViewById(R.id.enq_process_spn_product_mode2);
        spn_model3 = (Spinner) findViewById(R.id.enq_process_spn_product_mode3);
        spn_model4 = (Spinner) findViewById(R.id.enq_process_spn_product_mode4);
        spn_model5 = (Spinner) findViewById(R.id.enq_process_spn_product_mode5);
        spn_model6 = (Spinner) findViewById(R.id.enq_process_spn_product_mode6);

        spn_model_no = (Spinner) findViewById(R.id.enq_process_spn_model_no1);
        spn_model_no2 = (Spinner) findViewById(R.id.enq_process_spn_model_no2);
        spn_model_no3 = (Spinner) findViewById(R.id.enq_process_spn_model_no3);
        spn_model_no4 = (Spinner) findViewById(R.id.enq_process_spn_model_no4);
        spn_model_no5 = (Spinner) findViewById(R.id.enq_process_spn_model_no5);
        spn_model_no6 = (Spinner) findViewById(R.id.enq_process_spn_model_no6);

        spn_model_type = (Spinner) findViewById(R.id.enq_process_spn_product_type1);
        spn_model_type2 = (Spinner) findViewById(R.id.enq_process_spn_product_type2);
        spn_model_type3 = (Spinner) findViewById(R.id.enq_process_spn_product_type3);
        spn_model_type4 = (Spinner) findViewById(R.id.enq_process_spn_product_type4);
        spn_model_type5 = (Spinner) findViewById(R.id.enq_process_spn_product_type5);
        spn_model_type6 = (Spinner) findViewById(R.id.enq_process_spn_product_type6);

        // Hide And Seek

        add_more_1 = (TextView) findViewById(R.id.enq_process_txt_add_more1);
        add_more_2 = (TextView) findViewById(R.id.enq_process_txt_add_more2);
        add_more_3 = (TextView) findViewById(R.id.enq_process_txt_add_more3);
        add_more_4 = (TextView) findViewById(R.id.enq_process_txt_add_more4);
        add_more_5 = (TextView) findViewById(R.id.enq_process_txt_add_more5);

        img_delete1 = (ImageView) findViewById(R.id.enq_process_img_delete2);
        img_delete2 = (ImageView) findViewById(R.id.enq_process_img_delete3);
        img_delete3 = (ImageView) findViewById(R.id.enq_process_img_delete4);
        img_delete4 = (ImageView) findViewById(R.id.enq_process_img_delete5);
        img_delete5 = (ImageView) findViewById(R.id.enq_process_img_delete6);

        card1 = (CardView) findViewById(R.id.card_view_prod2);
        card2 = (CardView) findViewById(R.id.card_view_prod3);
        card3 = (CardView) findViewById(R.id.card_view_prod4);
        card4 = (CardView) findViewById(R.id.card_view_prod5);
        card5 = (CardView) findViewById(R.id.card_view_prod6);

        linear_appointment = (LinearLayout) findViewById(R.id.enq_process_inear_appointment);
        linear_enq_no = (LinearLayout) findViewById(R.id.enq_process_enq_no);

        edt_enq_appint_date = (EditText) findViewById(R.id.enq_add_appoint_edt_start_date);
        edt_enq_appint_time = (EditText) findViewById(R.id.enq_add_appoint_edt_time);

        spn_status = (Spinner) findViewById(R.id.enq_process_spinner_status2);
        /*spn_tax = (Spinner) findViewById(R.id.enq_process_spinner_tax);*/
        spn_ga_diagram1 = (Spinner) findViewById(R.id.enq_process_spinner_ga_diagram1);

        spn_enq_no_for_comple_drop = (Spinner) findViewById(R.id.enq_process_spinner_enq_no);

        linear_image_layout = (LinearLayout) findViewById(R.id.linear_image_layout);
        img_post_image = (ImageView) findViewById(R.id.complaintupdate_img_post);

        linear_po_layout = (LinearLayout) findViewById(R.id.enq_process_linear_ponumber);
        edt_po_number = (EditText) findViewById(R.id.enq_process_po_number);

        btn_send_catalog = (Button) findViewById(R.id.enq_process_btn_catalog);
        btn_save_pre_quote = (Button) findViewById(R.id.enq_process_btn_save_preview_quote);

        Arraylist_model = new ArrayList<String>();
        Arraylist_model_no = new ArrayList<String>();
        Arraylist_model_type = new ArrayList<String>();
        Arraylist_model_price = new ArrayList<String>();

        Arraylist_group_id2 = new ArrayList<String>();
        Arraylist_group2 = new ArrayList<String>();

        Arraylist_model2 = new ArrayList<String>();
        Arraylist_model3 = new ArrayList<String>();
        Arraylist_model4 = new ArrayList<String>();
        Arraylist_model5 = new ArrayList<String>();
        Arraylist_model6 = new ArrayList<String>();

        Arraylist_model_no2 = new ArrayList<String>();
        Arraylist_model_no3 = new ArrayList<String>();
        Arraylist_model_no4 = new ArrayList<String>();
        Arraylist_model_no5 = new ArrayList<String>();
        Arraylist_model_no6 = new ArrayList<String>();

        Arraylist_model_type2 = new ArrayList<String>();
        Arraylist_model_type3 = new ArrayList<String>();
        Arraylist_model_type4 = new ArrayList<String>();
        Arraylist_model_type5 = new ArrayList<String>();
        Arraylist_model_type6 = new ArrayList<String>();

        Arraylist_model_price2 = new ArrayList<String>();
        Arraylist_model_price3 = new ArrayList<String>();
        Arraylist_model_price4 = new ArrayList<String>();
        Arraylist_model_price5 = new ArrayList<String>();
        Arraylist_model_price6 = new ArrayList<String>();


        edt_tax_pnf = (EditText) findViewById(R.id.enq_process_edt_tax_pnf);
        edt_tax_vat_cst = (EditText) findViewById(R.id.enq_process_edt_tax_vat_cst);
        edt_tax_exduty = (EditText) findViewById(R.id.enq_process_edt_tax_exice_duty);

        edt_erection = (EditText) findViewById(R.id.enq_process_edterection);

        spn_feright_terms = (Spinner) findViewById(R.id.enq_process_spinner_feright_terms);
        spn_delivery_weeks = (Spinner) findViewById(R.id.enq_process_spinner_delivery);

       /* Arraylist_tax = new ArrayList<String>();
        Arraylist_tax_id = new ArrayList<String>();*/

        Arraylist_quotation_no = new ArrayList<String>();

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        str_select_produc_id = sharedPreferences.getString("enq_product_id", "enq_product_id");
        str_select_createdon = sharedPreferences.getString("enq_created_on", "enq_created_on");
        str_select_id = sharedPreferences.getString("enq_no", "enq_no");
        str_select_comp_name = sharedPreferences.getString("enq_company_name", "enq_company_name");
        str_select_email = sharedPreferences.getString("enq_company_email", "enq_company_email");
        str_select_addon_email = sharedPreferences.getString("enq_addon_email", "enq_addon_email");
        str_select_addon_email2 = sharedPreferences.getString("enq_addon_email2", "enq_addon_email2");
        str_select_addon_email3 = sharedPreferences.getString("enq_addon_email3", "enq_addon_email3");
        str_select_comp_address = sharedPreferences.getString("enq_company_address", "enq_company_address");
        str_select_pin = sharedPreferences.getString("enq_company_pincode", "enq_company_pincode");
        str_select_phoneno = sharedPreferences.getString("enq_company_phn_no", "enq_company_phn_no");
        str_select_person_name = sharedPreferences.getString("enq_contact_person_name", "enq_contact_person_name");
        str_select_person_number = sharedPreferences.getString("enq_contact_person_phone_no", "enq_contact_person_phone_no");
        str_select_produc_series = sharedPreferences.getString("enq_product_series", "enq_product_series");
        str_select_enq_throu = sharedPreferences.getString("enquiry_through", "enquiry_through");
        getStr_select_enq_throu_desc = sharedPreferences.getString("enquiry_through_description", "enquiry_through_description");
        str_select_desc = sharedPreferences.getString("enq_description", "enq_description");
        str_status = sharedPreferences.getString("enq_status", "enq_status");

        System.out.println("IN _enq_created_on " + str_select_createdon);
        System.out.println("IN _ enq_created_on" + str_select_createdon);
        System.out.println("IN _enq_created_on " + str_select_createdon);
        System.out.println("IN _ enq_created_on" + str_select_createdon);

        try {
            Check_Permission();
        } catch (Exception e) {

        }

        img_post_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (imagepath1.equals("")) {
                    str_function = "image";
                    proceedAfterPermission();
                } else {
                    TastyToast.makeText(Activity_Enquiry_Process.this, "Img is already is der", TastyToast.LENGTH_LONG, TastyToast.INFO);
                }

            }
        });

        try {

            txt_created_on.setText(str_select_createdon);
            txt_enq_id.setText(str_select_id);
            txt_enq_company_name.setText(str_select_comp_name);
            edt_txt_enq_txt_email.setText(str_select_email);
            edt_txt_enq_address.setText(str_select_comp_address);
            edt_txt_enq_pin.setText(str_select_pin);
            edt_txt_enq_phone.setText(str_select_phoneno);
            edt_txt_enq_conact_person.setText(str_select_person_name);
            edt_txt_enq_person_phone.setText(str_select_person_number);
            txt_enq_product.setText(str_select_produc_series);
            txt_enq_enq_through.setText(str_select_enq_throu);
            txt_enq_enq_thro_des.setText(getStr_select_enq_throu_desc);
            edt_spec.setText("" + str_select_desc);
            edt_addon_email.setText("" + str_select_addon_email);
            edt_addon_email2.setText("" + str_select_addon_email2);
            edt_addon_email3.setText("" + str_select_addon_email3);

        } catch (Exception e) {

        }

        add_more_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_more_1.setVisibility(View.GONE);
                card1.setVisibility(View.VISIBLE);
                add_more_2.setVisibility(View.VISIBLE);

            }
        });

        add_more_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_more_2.setVisibility(View.GONE);
                card2.setVisibility(View.VISIBLE);
                add_more_3.setVisibility(View.VISIBLE);

            }
        });

        add_more_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_more_3.setVisibility(View.GONE);
                card3.setVisibility(View.VISIBLE);
                add_more_4.setVisibility(View.VISIBLE);

            }
        });

        add_more_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_more_4.setVisibility(View.GONE);
                card4.setVisibility(View.VISIBLE);
                add_more_5.setVisibility(View.VISIBLE);

            }
        });

        add_more_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_more_5.setVisibility(View.GONE);
                card5.setVisibility(View.VISIBLE);

            }
        });

        img_delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                card1.setVisibility(View.GONE);
                add_more_1.setVisibility(View.VISIBLE);
                add_more_2.setVisibility(View.GONE);

            }
        });

        img_delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                card2.setVisibility(View.GONE);
                add_more_2.setVisibility(View.VISIBLE);
                add_more_3.setVisibility(View.GONE);

            }
        });

        img_delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                card3.setVisibility(View.GONE);
                add_more_3.setVisibility(View.VISIBLE);
                add_more_4.setVisibility(View.GONE);

            }
        });

        img_delete4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                card4.setVisibility(View.GONE);
                add_more_4.setVisibility(View.VISIBLE);
                add_more_5.setVisibility(View.GONE);


            }
        });

        img_delete5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                card5.setVisibility(View.GONE);
                add_more_5.setVisibility(View.VISIBLE);


            }
        });

        /****************************************
         *  Spinner Model Interface Ex.2KD, 2Kw
         * **************************************/

        spn_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model = Arraylist_model.get(arg2);

                System.out.println("NAME : " + str_Selected_model);

               /* dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();*/
                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model = 2;
                str_Selected_model2 = Arraylist_model2.get(arg2);

                System.out.println("NAME : " + str_Selected_model2);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model = 3;
                str_Selected_model3 = Arraylist_model3.get(arg2);

                System.out.println("NAME : " + str_Selected_model3);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model = 4;
                str_Selected_model4 = Arraylist_model4.get(arg2);

                System.out.println("NAME : " + str_Selected_model4);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model = 5;
                str_Selected_model5 = Arraylist_model5.get(arg2);

                System.out.println("NAME : " + str_Selected_model5);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo_FOR_Multiple();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model = 6;
                str_Selected_model6 = Arraylist_model6.get(arg2);

                System.out.println("NAME : " + str_Selected_model6);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelNo_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        /*********************************************
         *  Spinner Model No Interface Ex. 2KD0028
         * ********************************************/

        spn_model_no.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_no = Arraylist_model_no.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no);

                /*dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();*/
                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_no2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model_no = 2;

                str_Selected_model_no2 = Arraylist_model_no2.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no2);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_no3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model_no = 3;
                str_Selected_model_no3 = Arraylist_model_no3.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no3);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_no4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model_no = 4;
                str_Selected_model_no4 = Arraylist_model_no4.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no4);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_no5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model_no = 5;
                str_Selected_model_no5 = Arraylist_model_no5.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no5);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_no6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_model_no = 6;
                str_Selected_model_no6 = Arraylist_model_no6.get(arg2);

                System.out.println("NAME : " + str_Selected_model_no6);

                queue = Volley.newRequestQueue(getApplicationContext());
                Get_Product_modelType_FOR_Multiple();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        /*********************************************
         *  Spinner Model Type Interface Ex.
         * ********************************************/

        spn_model_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type = Arraylist_model_type.get(arg2);
                str_Selected_model_price = Arraylist_model_price.get(arg2);

                System.out.println("NAME : " + str_Selected_model_type);

                Toast.makeText(getApplicationContext(), "TYPE : " + str_Selected_model_type + "Price : " + str_Selected_model_price, Toast.LENGTH_LONG).show();

                txt_value.setText("" + 1);
                edt_price.setText("" + str_Selected_model_price);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_type2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type2 = Arraylist_model_type2.get(arg2);
                str_Selected_model_price2 = Arraylist_model_price2.get(arg2);

                txt_value2.setText("" + 1);
                edt_price2.setText("" + str_Selected_model_price2);
                System.out.println("NAME : " + str_Selected_model_type2 + "PRICE : " + str_Selected_model_price2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_type3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type3 = Arraylist_model_type3.get(arg2);
                str_Selected_model_price3 = Arraylist_model_price3.get(arg2);
                System.out.println("NAME : " + str_Selected_model_type3 + "PRICE : " + str_Selected_model_price3);
                txt_value3.setText("" + 1);
                edt_price3.setText("" + str_Selected_model_price3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_type4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type4 = Arraylist_model_type4.get(arg2);
                str_Selected_model_price4 = Arraylist_model_price4.get(arg2);
                txt_value4.setText("" + 1);
                edt_price4.setText("" + str_Selected_model_price4);
                System.out.println("NAME : " + str_Selected_model_type4 + "PRICE : " + str_Selected_model_price4);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_type5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type5 = Arraylist_model_type5.get(arg2);
                str_Selected_model_price5 = Arraylist_model_price5.get(arg2);
                txt_value5.setText("" + 1);
                edt_price5.setText("" + str_Selected_model_price5);
                System.out.println("NAME : " + str_Selected_model_type5 + "PRICE : " + str_Selected_model_price5);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_model_type6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                str_Selected_model_type6 = Arraylist_model_type6.get(arg2);
                str_Selected_model_price6 = Arraylist_model_price6.get(arg2);
                txt_value6.setText("" + 1);
                edt_price6.setText("" + str_Selected_model_price6);
                System.out.println("NAME : " + str_Selected_model_type6 + "PRICE : " + str_Selected_model_price6);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        /*********************************************
         *  Spinner Group Interface Ex. DRYER,CHILLER
         * ********************************************/

        spn_group1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_group = 1;
                str_Selected_group1 = Arraylist_group_id2.get(arg2);

                System.out.println("GROUP :::: " + str_Selected_group1);
                System.out.println("GROUP :::: " + str_Selected_group1);
                System.out.println("GROUP :::: " + str_Selected_group1);

                dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();
                queue = Volley.newRequestQueue(getApplicationContext());
                GetModel_Type();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_group2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_group = 2;
                str_Selected_group2 = Arraylist_group_id2.get(arg2);

                dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();
                queue = Volley.newRequestQueue(getApplicationContext());
                GetModel_Type_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_group3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_group = 3;
                str_Selected_group3 = Arraylist_group_id2.get(arg2);

                dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();
                queue = Volley.newRequestQueue(getApplicationContext());
                GetModel_Type_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_group4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_group = 4;
                str_Selected_group4 = Arraylist_group_id2.get(arg2);

                dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();
                queue = Volley.newRequestQueue(getApplicationContext());
                GetModel_Type_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_group5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_group = 5;
                str_Selected_group5 = Arraylist_group_id2.get(arg2);

                dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();
                queue = Volley.newRequestQueue(getApplicationContext());
                GetModel_Type_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spn_group6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                select_product_group = 6;
                str_Selected_group6 = Arraylist_group_id2.get(arg2);

                dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                dialog.show();
                queue = Volley.newRequestQueue(getApplicationContext());
                GetModel_Type_FOR_Multiple();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        /*********************************************
         *  Product Count 1
         * ********************************************/

        txt_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price);

                    int acutal_value = i * price;

                    edt_price.setText("" + acutal_value);

                }

            }
        });

        txt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price);

                    int acutal_value = i * price;

                    edt_price.setText("" + acutal_value);

                }

            }
        });

        /*********************************************
         *  Product Count 2
         * ********************************************/

        txt_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value2.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value2.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price2);

                    int acutal_value = i * price;

                    edt_price2.setText("" + acutal_value);

                }

            }
        });

        txt_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value2.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value2.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price2);

                    int acutal_value = i * price;

                    edt_price2.setText("" + acutal_value);

                }

            }
        });

        /*********************************************
         *  Product Count 3
         * ********************************************/

        txt_minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value3.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value3.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price3);

                    int acutal_value = i * price;

                    edt_price3.setText("" + acutal_value);

                }

            }
        });

        txt_add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value3.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value3.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price3);

                    int acutal_value = i * price;

                    edt_price3.setText("" + acutal_value);

                }

            }
        });


        /*********************************************
         *  Product Count 4
         * ********************************************/

        txt_minus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value4.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value4.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price4);

                    int acutal_value = i * price;

                    edt_price4.setText("" + acutal_value);

                }

            }
        });

        txt_add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value4.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value4.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price4);

                    int acutal_value = i * price;

                    edt_price4.setText("" + acutal_value);

                }

            }
        });

        /*********************************************
         *  Product Count 5
         * ********************************************/

        txt_minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value5.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value5.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price5);

                    int acutal_value = i * price;

                    edt_price5.setText("" + acutal_value);

                }

            }
        });

        txt_add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value5.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value5.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price5);

                    int acutal_value = i * price;

                    edt_price5.setText("" + acutal_value);

                }

            }
        });

        /*********************************************
         *  Product Count 6
         * ********************************************/

        txt_minus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value6.getText().toString());

                if (i <= 0) {


                } else {
                    i = i - 1;
                    txt_value6.setText("" + i);

                    int price = Integer.parseInt(str_Selected_model_price6);

                    int acutal_value = i * price;

                    edt_price6.setText("" + acutal_value);

                }

            }
        });

        txt_add6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = Integer.parseInt(txt_value6.getText().toString());

                if (i > 9) {


                } else {
                    i = i + 1;
                    txt_value6.setText("" + i);


                    int price = Integer.parseInt(str_Selected_model_price6);

                    int acutal_value = i * price;

                    edt_price6.setText("" + acutal_value);

                }

            }
        });

        /*******************************
         *  Spinner Loaders
         * ******************************/

        // Product and Product Model Spinner

        if (str_status.equals("Pending")) {

            System.out.println("Inside New Spinner Loader");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Activity_Enquiry_Process.this, R.array.enq_status, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_status.setAdapter(adapter);

        } else if (str_status.equals("Process")) {

            System.out.println("Inside Process Spinner Loader");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Activity_Enquiry_Process.this, R.array.enq_process, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_status.setAdapter(adapter);

        }


        // Spinner Product Interface
        spn_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                str_po_status = parent.getItemAtPosition(pos).toString();

                if (str_po_status.equals("null")) {

                    TastyToast.makeText(Activity_Enquiry_Process.this, "Please Select Status", TastyToast.LENGTH_LONG, TastyToast.INFO);

                } else if (str_po_status.equals("Appointment")) {

                    linear_appointment.setVisibility(View.VISIBLE);
                    linear_enq_no.setVisibility(View.GONE);
                    linear_image_layout.setVisibility(View.GONE);
                    linear_po_layout.setVisibility(View.GONE);

                } else if (str_po_status.equals("Completed")) {

                    linear_enq_no.setVisibility(View.VISIBLE);
                    linear_image_layout.setVisibility(View.VISIBLE);
                    linear_po_layout.setVisibility(View.VISIBLE);
                    linear_appointment.setVisibility(View.GONE);

                    try {

                        dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(getApplicationContext());
                        GetEnq_No();

                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else {

                    linear_appointment.setVisibility(View.GONE);
                    linear_enq_no.setVisibility(View.GONE);
                    linear_image_layout.setVisibility(View.GONE);
                    linear_po_layout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }

        });


        /*********************************************
         *  Spinner Get GA Diagram Status
         * ********************************************/

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Activity_Enquiry_Process.this, R.array.gadiagram, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_ga_diagram1.setAdapter(adapter);
        // Spinner GAD Interface
        spn_ga_diagram1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String ga_status;
                ga_status = parent.getItemAtPosition(pos).toString();

                if (ga_status.equals("Yes")) {

                    str_ga_dia1 = ga_status;

                } else if (ga_status.equals("No")) {

                    str_ga_dia1 = ga_status;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }

        });

        /*********************************************
         *  Spinner Get Enq_Quotation Number
         * ********************************************/

        spn_enq_no_for_comple_drop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                String str_quo = Arraylist_quotation_no.get(arg2);

                String[] separated = str_quo.split(":");
                str_Selected_quotation_no = separated[0];

                System.out.println("QUOTATION NUMBER : " + str_Selected_quotation_no);
                System.out.println("QUOTATION NUMBER : " + str_Selected_quotation_no);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        /*********************************************
         *  Spinner Get Enq_Quotation Number
         * ********************************************/

       /* spn_tax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub

                str_Selected_tax = Arraylist_tax_id.get(arg2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });*/


        edt_enq_appint_date.setKeyListener(null);
        edt_enq_appint_time.setKeyListener(null);

        edt_enq_appint_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                from_year = c.get(Calendar.YEAR);
                from_month = c.get(Calendar.MONTH);
                from_date = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(
                        Activity_Enquiry_Process.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                int month = monthOfYear + 1;
                                String formattedMonth = "" + month;
                                String formattedDayOfMonth = "" + dayOfMonth;

                                if (month < 10) {

                                    formattedMonth = "0" + month;
                                }
                                if (dayOfMonth < 10) {

                                    formattedDayOfMonth = "0" + dayOfMonth;
                                }
                                edt_enq_appint_date.setText(formattedDayOfMonth + "/"
                                        + formattedMonth + "/"
                                        + year);

                            }
                        }, from_year, from_month, from_date);
                dpd.show();

            }
        });

        edt_enq_appint_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Activity_Enquiry_Process.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        edt_enq_appint_time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });


        edt_discount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_discount = edt_discount.getText().toString();

                    String str_actual_value = actual_discount;

                    int actual_discount = Integer.parseInt(str_actual_value);

                    int discount = Integer.parseInt(str_discount);

                    if (discount >= actual_discount) {
                        TastyToast.makeText(getApplicationContext(), "Your Discount Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_discount.setText(actual_discount);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        edt_add_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_add_price = edt_add_price.getText().toString();

                    int actual_price = Integer.parseInt(str_add_price);

                    if (actual_price >= 26) {
                        TastyToast.makeText(getApplicationContext(), "Your Discount Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_add_price.setText("25");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        edt_add_price2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_add_price = edt_add_price2.getText().toString();

                    int actual_price = Integer.parseInt(str_add_price);

                    if (actual_price >= 26) {
                        TastyToast.makeText(getApplicationContext(), "Your Discount Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_add_price2.setText("25");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        edt_add_price3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_add_price = edt_add_price3.getText().toString();

                    int actual_price = Integer.parseInt(str_add_price);

                    if (actual_price >= 25) {
                        TastyToast.makeText(getApplicationContext(), "Your Discount Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_add_price3.setText("25");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        edt_add_price4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_add_price = edt_add_price4.getText().toString();

                    int actual_price = Integer.parseInt(str_add_price);

                    if (actual_price >= 25) {
                        TastyToast.makeText(getApplicationContext(), "Your Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_add_price4.setText("25");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        edt_add_price5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_add_price = edt_add_price5.getText().toString();

                    int actual_price = Integer.parseInt(str_add_price);

                    if (actual_price >= 25) {
                        TastyToast.makeText(getApplicationContext(), "Your Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_add_price5.setText("25");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        edt_add_price6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i == 1) {

                    String str_add_price = edt_add_price6.getText().toString();

                    int actual_price = Integer.parseInt(str_add_price);

                    if (actual_price >= 25) {
                        TastyToast.makeText(getApplicationContext(), "Your Limit Exceeded", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                        edt_add_price6.setText("25");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        btn_save_pre_quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_po_en_no = txt_enq_id.getText().toString();
                str_po_comp_name = txt_enq_company_name.getText().toString();
                str_po_email = edt_txt_enq_txt_email.getText().toString();
                str_po_address = edt_txt_enq_address.getText().toString();
                str_po_pin = edt_txt_enq_pin.getText().toString();
                str_po_phone = edt_txt_enq_phone.getText().toString();
                str_po_contact_person = edt_txt_enq_conact_person.getText().toString();
                str_po_contact_person_phone = edt_txt_enq_person_phone.getText().toString();
                str_po_product = txt_enq_product.getText().toString();
                str_po_enq_through = txt_enq_enq_through.getText().toString();
                str_po_enq_description = txt_enq_enq_thro_des.getText().toString();

                str_po_group1 = str_Selected_group1;
                str_po_model1 = str_Selected_model;
                str_po_model_no1 = str_Selected_model_no;
                str_po_model_type = str_Selected_model_type;
                str_po_qty1 = txt_value.getText().toString();
                str_po_price1 = edt_price.getText().toString();
                str_po_add_price1 = edt_add_price.getText().toString();

                str_po_group2 = str_Selected_group2;
                str_po_model2 = str_Selected_model2;
                str_po_model_no2 = str_Selected_model_no2;
                str_po_mode2_type = str_Selected_model_type2;
                str_po_qty2 = txt_value2.getText().toString();
                str_po_price2 = edt_price2.getText().toString();
                str_po_add_price2 = edt_add_price2.getText().toString();

                str_po_group3 = str_Selected_group3;
                str_po_model3 = str_Selected_model3;
                str_po_model_no3 = str_Selected_model_no3;
                str_po_mode3_type = str_Selected_model_type3;
                str_po_qty3 = txt_value3.getText().toString();
                str_po_price3 = edt_price3.getText().toString();
                str_po_add_price3 = edt_add_price3.getText().toString();

                str_po_group4 = str_Selected_group4;
                str_po_model4 = str_Selected_model4;
                str_po_model_no4 = str_Selected_model_no4;
                str_po_mode4_type = str_Selected_model_type4;
                str_po_qty4 = txt_value4.getText().toString();
                str_po_price4 = edt_price4.getText().toString();
                str_po_add_price4 = edt_add_price4.getText().toString();

                str_po_group5 = str_Selected_group5;
                str_po_model5 = str_Selected_model5;
                str_po_model_no5 = str_Selected_model_no5;
                str_po_mode5_type = str_Selected_model_type5;
                str_po_qty5 = txt_value5.getText().toString();
                str_po_price5 = edt_price5.getText().toString();
                str_po_add_price5 = edt_add_price5.getText().toString();

                str_po_group6 = str_Selected_group6;
                str_po_model6 = str_Selected_model6;
                str_po_model_no6 = str_Selected_model_no6;
                str_po_mode6_type = str_Selected_model_type6;
                str_po_qty6 = txt_value6.getText().toString();
                str_po_price6 = edt_price6.getText().toString();
                str_po_discount = edt_discount.getText().toString();
                str_po_spec = edt_spec.getText().toString();
                str_po_add_price6 = edt_add_price6.getText().toString();

                str_select_addon_email = edt_addon_email.getText().toString();
                str_select_addon_email2 = edt_addon_email2.getText().toString();
                str_select_addon_email3 = edt_addon_email3.getText().toString();

                str_po_appoint_date = edt_enq_appint_date.getText().toString();
                str_po_appoint_time = edt_enq_appint_time.getText().toString();

                str_feright_terms = String.valueOf(spn_feright_terms.getSelectedItem());
                str_erection = edt_erection.getText().toString();
                str_delivery_weeks = String.valueOf(spn_delivery_weeks.getSelectedItem());

                str_tax_pnf = edt_tax_pnf.getText().toString();
                str_tax_vat_cst = edt_tax_vat_cst.getText().toString();
                str_tax_exduty = edt_tax_exduty.getText().toString();

                if (str_po_status.equals("Appointment")) {

                    try {

                        if (str_status.equals("Pending")) {

                            str_appoint_url = AppConfig.url_post_enq;

                        } else if (str_status.equals("Process")) {

                            str_appoint_url = AppConfig.url_post_Completed;
                        }

                        dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(getApplicationContext());
                        POST_Apppointment();

                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else if (str_po_status.equals("New Quotation")) {


                    if (str_tax_pnf.equals("")) {
                        TastyToast.makeText(getApplicationContext(), "Please Enter P & F %", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_tax_vat_cst.equals("")) {
                        TastyToast.makeText(getApplicationContext(), "Please Enter VAT / CST %", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_tax_exduty.equals("")) {
                        TastyToast.makeText(getApplicationContext(), "Please Enter Exice Duty %", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        try {

                            dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                            dialog.show();
                            queue = Volley.newRequestQueue(getApplicationContext());
                            POST_ENQ();

                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }

                } else if (str_po_status.equals("Another Quotation")) {

                    if (str_tax_pnf.equals("")) {
                        TastyToast.makeText(getApplicationContext(), "Please Enter P & F %", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_tax_vat_cst.equals("")) {
                        TastyToast.makeText(getApplicationContext(), "Please Enter VAT / CST %", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else if (str_tax_exduty.equals("")) {
                        TastyToast.makeText(getApplicationContext(), "Please Enter Exice Duty %", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                    } else {
                        try {

                            dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                            dialog.show();
                            queue = Volley.newRequestQueue(getApplicationContext());
                            POST_ANOTHER_ENQ();

                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }


                } else if (str_po_status.equals("Call With TL")) {

                    try {

                        if (str_status.equals("Pending")) {

                            str_call_TL_url = AppConfig.url_post_enq;

                        } else if (str_status.equals("Process")) {

                            str_call_TL_url = AppConfig.url_post_Completed;
                        }

                        dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(getApplicationContext());
                        POST_CALL_TL();

                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else if (str_po_status.equals("Completed")) {

                    str_po_no = edt_po_number.getText().toString();

                    try {

                        dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(getApplicationContext());
                        POST_Completed();

                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else if (str_po_status.equals("Dropped")) {

                    try {

                        dialog = new SpotsDialog(Activity_Enquiry_Process.this);
                        dialog.show();
                        queue = Volley.newRequestQueue(getApplicationContext());
                        POST_Dropped();

                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else {

                }


            }
        });

        btn_send_catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Activity_Send_Catelog.class);
                startActivity(i);

            }
        });

        try {

            dialog = new SpotsDialog(Activity_Enquiry_Process.this);
            dialog.show();
            queue = Volley.newRequestQueue(getApplicationContext());
            GetProductGroup();

        } catch (Exception e) {
            // TODO: handle exception
        }

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    /*************************************
     * Runtime Permission call
     *****************************************/

    public void Check_Permission() {

        if (ActivityCompat.checkSelfPermission(Activity_Enquiry_Process.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Activity_Enquiry_Process.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Activity_Enquiry_Process.this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(Activity_Enquiry_Process.this, permissionsRequired[3]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[2])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[3])) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Enquiry_Process.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Activity_Enquiry_Process.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else if (permissionStatus.getBoolean(permissionsRequired[0], false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Enquiry_Process.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant  Camera and Location", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(Activity_Enquiry_Process.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
            }

            Toast.makeText(getApplicationContext(), "Need a Permission", Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(permissionsRequired[0], true);
            editor.commit();
        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
            //check if all permissions are granted
            boolean allgranted = false;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if (allgranted) {
                proceedAfterPermission();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[2])
                    || ActivityCompat.shouldShowRequestPermissionRationale(Activity_Enquiry_Process.this, permissionsRequired[3])) {
//                txtPermissions.setText("Permissions Required");
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Enquiry_Process.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(Activity_Enquiry_Process.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(getBaseContext(), "Unable to get Permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (sentToSettings) {
            if (ActivityCompat.checkSelfPermission(Activity_Enquiry_Process.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }


    public void proceedAfterPermission() {

        if (str_function.equals("image")) {

            if (imagepath1.equals("")) {
                Function_capture_Image();
            } else {
                TastyToast.makeText(Activity_Enquiry_Process.this, "Internal Error", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }

        } else {

        }

    }


    /**********************************
     * Main Menu
     *********************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_follow, menu);

        MenuItem item1 = menu.findItem(R.id.action_alert);
        MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
        notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        parent_batch = (RelativeLayout) MenuItemCompat.getActionView(item1);
        tv = (TextView) notificationCount1.findViewById(R.id.badge_notification_2);
        tv.setText("" + MainActivity.str_count);
        //str_cart = Integer.toString(count);
        //tv.setText("" + cart_size);

        parent_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Pending Tasks", Toast.LENGTH_LONG).show();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Pending Tasks", Toast.LENGTH_LONG).show();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_alert) {

            Toast.makeText(getApplicationContext(), "Pending Tasks", Toast.LENGTH_LONG).show();

            return true;
        }
        if (id == R.id.action_followup) {

            SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(Activity_Enquiry_Process.this);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("follow_up_enq_no", str_select_id);

            editor.commit();

            Intent i = new Intent(getApplicationContext(), Activity_FollowUp.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*************************************
     * Image Capture Functio Begins
     *****************************************/

    public void Function_capture_Image() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    /*     * Receiving activity result method will be called after closing the camera*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity_Enquiry_Process.this.RESULT_OK) {

                // successfully captured the image
                // launching upload activity
                launchUploadActivity(true);


            } else if (resultCode == Activity_Enquiry_Process.this.RESULT_CANCELED) {

                // user cancelled Image capture
                Toast.makeText(Activity_Enquiry_Process.this,
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                // failed to capture image
                Toast.makeText(Activity_Enquiry_Process.this,
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        } else if (requestCode == REQUEST_CAMERA1) {

            File f2 = new File(Environment.getExternalStorageDirectory()
                    .toString());
            for (File temp : f2.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f2 = temp;
                    break;
                }
            }
            try {
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

                bm = BitmapFactory.decodeFile(f2.getAbsolutePath(),
                        btmapOptions);

                bm = Bitmap.createScaledBitmap(bm, 170, 170, true);
                img_post_image.setImageBitmap(bm);

                String path = Environment
                        .getExternalStorageDirectory()
                        + File.separator
                        + "Phoenix" + File.separator + "default";
                f2.delete();
                OutputStream fOut = null;
                File file = new File(path, String.valueOf(System
                        .currentTimeMillis()) + ".jpg");

                imagepath1 = file.getPath();

                try {
                    fOut = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void launchUploadActivity(boolean isImage) {

        imagepath1 = fileUri.getPath();


        BitmapFactory.Options options = new BitmapFactory.Options();

        // Image location URL
        imagepath1 = fileUri.getPath();
        Log.e("path", "----------------" + imagepath1);

        // Image
        Bitmap bmBitmap = BitmapFactory.decodeFile(imagepath1);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bmBitmap.compress(Bitmap.CompressFormat.JPEG, 25, bao);
        byte[] ba = bao.toByteArray();
        encodedstring = Base64.encodeToString(ba, 0);
        img_post_image.setImageBitmap(bmBitmap);
        Log.e("base64", "-----" + encodedstring);


    }

    /* * ------------ Helper Methods ----------------------*/


/*     * Creating file uri to store image/video*/

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }


/*     * returning image / video*/

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {

                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Activity_Pending_Complaint_Update Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    /***************************
     * GET Model Ex. 2KD, 2KW
     ***************************/

    public void GetModel_Type() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_model, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("model");

                        Arraylist_model.clear();
                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String product = obj1.getString(TAG_PROD_TITLE);

                            Arraylist_model.add(product);

                            try {
                                spn_model
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_model));

                            } catch (Exception e) {

                            }
                        }

                        queue = Volley.newRequestQueue(getApplicationContext());
                        Get_Discount();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
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


                dialog.dismiss();

                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("group_id", str_Selected_group1);

                System.out.println("group_id :::: " + str_Selected_group1);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * GET Get Discount
     ***************************/

    public void Get_Discount() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_discount, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("discount");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            actual_discount = obj1.getString(TAG_Discount);

                            try {
                                edt_discount.setText("" + actual_discount);

                            } catch (Exception e) {

                            }
                        }

                       /* try {
                            queue = Volley.newRequestQueue(getApplicationContext());
                            Get_tax_info();
                        } catch (Exception e) {

                        }*/


                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Discount Alerted")
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
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_user_id);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * GET Tax Information
     ***************************/

    /*public void Get_tax_info() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.GET,
                AppConfig.url_tax_info, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("tax");

                        Arraylist_tax.clear();
                        Arraylist_tax_id.clear();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String tax = obj1.getString(TAG_TAX);
                            String tax_id = obj1.getString(TAG_TAX_ID);

                            Arraylist_tax.add(tax);
                            Arraylist_tax_id.add(tax_id);

                            try {
                                spn_tax
                                        .setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_tax));

                            } catch (Exception e) {

                            }

                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
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

                dialog.dismiss();
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
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
    }*/


    /***************************
     * GET Product Model No
     ***************************/

    public void Get_Product_modelNo() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_modelno, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");

                        Arraylist_model_no.clear();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String model_no = obj1.getString(TAG_Model_NO);

                            Arraylist_model_no.add(model_no);

                            try {
                                spn_model_no
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_model_no));

                            } catch (Exception e) {

                            }

                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
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

                dialog.dismiss();
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("series", str_Selected_model);

                System.out.println("series" + str_Selected_model);
                System.out.println("series" + str_Selected_model);
                System.out.println("series" + str_Selected_model);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * GET Model Type
     ***************************/

    public void Get_Product_modelType() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_modeltype, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    Arraylist_model_type.clear();
                    Arraylist_model_price.clear();

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            JSONArray arr_type = obj1.getJSONArray("product_type_main");
                            JSONArray arr_price = obj1.getJSONArray("product_price_main");

                            for (int j = 0; arr_type.length() > j; j++) {
                                JSONObject obj_type = arr_type.getJSONObject(j);

                                String model_type = obj_type.getString(TAG_Model_Type);

                                Arraylist_model_type.add(model_type);

                                try {
                                    spn_model_type
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_type));

                                } catch (Exception e) {

                                }

                            }

                            for (int k = 0; arr_price.length() > k; k++) {
                                JSONObject obj_price = arr_price.getJSONObject(k);

                                String model_price = obj_price.getString(TAG_Model_PRICE);

                                Arraylist_model_price.add(model_price);

                            }

                        }

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
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

                dialog.dismiss();
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("model_no", str_Selected_model_no);

                System.out.println("model_no" + str_Selected_model_no);
                System.out.println("model_no" + str_Selected_model_no);
                System.out.println("model_no" + str_Selected_model_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * GET Product Group
     ***************************/

    public void GetProductGroup() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.GET,
                AppConfig.url_product_group, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");

                        Arraylist_group2.clear();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String id = obj1.getString(TAG_GROUP_ID);
                            String product = obj1.getString(TAG_GROUP_TITLE);

                            Arraylist_group2.add(product);
                            Arraylist_group_id2.add(id);

                            try {

                                System.out.println("GROUP :: " + Arraylist_group2);
                                System.out.println("GROUP ID :: " + Arraylist_group_id2);

                                spn_group1
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_group2));

                                spn_group2
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_group2));

                                spn_group3
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_group2));

                                spn_group4
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_group2));

                                spn_group5
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_group2));

                                spn_group6
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_group2));

                            } catch (Exception e) {

                            }

                        }

                        dialog.dismiss();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
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

    /*******************************************
     * GET Model FOR MULTIPLE Ex. 2KD, 2KW
     *******************************************/

    public void GetModel_Type_FOR_Multiple() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_model, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("model");

                        if (select_product_group == 2) {

                            Arraylist_model2.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String product = obj1.getString(TAG_PROD_TITLE);

                                Arraylist_model2.add(product);

                                try {
                                    spn_model2
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model2));

                                } catch (Exception e) {

                                }
                            }
                        } else if (select_product_group == 3) {

                            Arraylist_model3.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String product = obj1.getString(TAG_PROD_TITLE);

                                Arraylist_model3.add(product);

                                try {
                                    spn_model3
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model3));

                                } catch (Exception e) {

                                }
                            }
                        } else if (select_product_group == 4) {

                            Arraylist_model4.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String product = obj1.getString(TAG_PROD_TITLE);

                                Arraylist_model4.add(product);

                                try {
                                    spn_model4
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model4));

                                } catch (Exception e) {

                                }
                            }
                        } else if (select_product_group == 5) {

                            Arraylist_model5.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String product = obj1.getString(TAG_PROD_TITLE);

                                Arraylist_model5.add(product);

                                try {
                                    spn_model5
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model5));

                                } catch (Exception e) {

                                }
                            }
                        } else if (select_product_group == 6) {

                            Arraylist_model6.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String product = obj1.getString(TAG_PROD_TITLE);

                                Arraylist_model6.add(product);

                                try {
                                    spn_model6
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model6));

                                } catch (Exception e) {

                                }
                            }
                        }

                        /*queue = Volley.newRequestQueue(getApplicationContext());
                        Get_Discount();*/

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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

                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                if (select_product_group == 2) {
                    params.put("group_id", str_Selected_group2);
                    System.out.println("PAss 2" + str_Selected_group2);
                } else if (select_product_group == 3) {
                    params.put("group_id", str_Selected_group3);
                    System.out.println("PAss 3" + str_Selected_group3);
                } else if (select_product_group == 4) {
                    params.put("group_id", str_Selected_group4);
                    System.out.println("PAss 4" + str_Selected_group4);
                } else if (select_product_group == 5) {
                    params.put("group_id", str_Selected_group5);
                    System.out.println("PAss 5" + str_Selected_group5);
                } else if (select_product_group == 6) {
                    params.put("group_id", str_Selected_group6);
                    System.out.println("PAss 6" + str_Selected_group6);
                }


                System.out.println();

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }

    /*******************************************
     * GET Product Model No For Multiple
     ******************************************/

    public void Get_Product_modelNo_FOR_Multiple() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_modelno, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");


                        if (select_product_model == 2) {

                            Arraylist_model_no2.clear();
                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String model_no = obj1.getString(TAG_Model_NO);

                                Arraylist_model_no2.add(model_no);

                                try {
                                    spn_model_no2
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_no2));

                                } catch (Exception e) {

                                }

                            }

                        } else if (select_product_model == 3) {

                            Arraylist_model_no3.clear();
                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String model_no = obj1.getString(TAG_Model_NO);

                                Arraylist_model_no3.add(model_no);

                                try {
                                    spn_model_no3
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_no3));

                                } catch (Exception e) {

                                }

                            }

                        } else if (select_product_model == 4) {

                            Arraylist_model_no4.clear();
                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String model_no = obj1.getString(TAG_Model_NO);

                                Arraylist_model_no4.add(model_no);

                                try {
                                    spn_model_no4
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_no4));

                                } catch (Exception e) {

                                }

                            }

                        } else if (select_product_model == 5) {

                            Arraylist_model_no5.clear();
                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String model_no = obj1.getString(TAG_Model_NO);

                                Arraylist_model_no5.add(model_no);

                                try {
                                    spn_model_no5
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_no5));

                                } catch (Exception e) {

                                }

                            }

                        } else if (select_product_model == 6) {

                            Arraylist_model_no6.clear();
                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                String model_no = obj1.getString(TAG_Model_NO);

                                Arraylist_model_no6.add(model_no);

                                try {
                                    spn_model_no6
                                            .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                    android.R.layout.simple_spinner_dropdown_item,
                                                    Arraylist_model_no6));

                                } catch (Exception e) {

                                }

                            }

                        }


                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
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

                dialog.dismiss();
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                if (select_product_model == 2) {

                    params.put("series", str_Selected_model2);
                    System.out.println("series" + str_Selected_model2);

                } else if (select_product_model == 3) {

                    params.put("series", str_Selected_model3);
                    System.out.println("series" + str_Selected_model3);

                } else if (select_product_model == 4) {

                    params.put("series", str_Selected_model4);
                    System.out.println("series" + str_Selected_model4);

                } else if (select_product_model == 5) {

                    params.put("series", str_Selected_model5);
                    System.out.println("series" + str_Selected_model5);

                } else if (select_product_model == 6) {

                    params.put("series", str_Selected_model6);
                    System.out.println("series" + str_Selected_model6);

                }


                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***********************************
     * GET Model Type For Multiple
     **************************************/

    public void Get_Product_modelType_FOR_Multiple() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_enq_modeltype, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("product_group");

                        if (select_product_model_no == 2) {

                            Arraylist_model_type2.clear();
                            Arraylist_model_price2.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                JSONArray arr_type = obj1.getJSONArray("product_type_main");
                                JSONArray arr_price = obj1.getJSONArray("product_price_main");

                                for (int j = 0; arr_type.length() > j; j++) {
                                    JSONObject obj_type = arr_type.getJSONObject(j);

                                    String model_type = obj_type.getString(TAG_Model_Type);

                                    Arraylist_model_type2.add(model_type);

                                    try {
                                        spn_model_type2
                                                .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                        android.R.layout.simple_spinner_dropdown_item,
                                                        Arraylist_model_type2));

                                    } catch (Exception e) {

                                    }

                                }

                                for (int k = 0; arr_price.length() > k; k++) {
                                    JSONObject obj_price = arr_price.getJSONObject(k);

                                    String model_price = obj_price.getString(TAG_Model_PRICE);

                                    Arraylist_model_price2.add(model_price);

                                }

                            }

                        } else if (select_product_model_no == 3) {

                            Arraylist_model_type3.clear();
                            Arraylist_model_price3.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                JSONArray arr_type = obj1.getJSONArray("product_type_main");
                                JSONArray arr_price = obj1.getJSONArray("product_price_main");

                                for (int j = 0; arr_type.length() > j; j++) {
                                    JSONObject obj_type = arr_type.getJSONObject(j);

                                    String model_type = obj_type.getString(TAG_Model_Type);

                                    Arraylist_model_type3.add(model_type);

                                    try {
                                        spn_model_type3
                                                .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                        android.R.layout.simple_spinner_dropdown_item,
                                                        Arraylist_model_type3));

                                    } catch (Exception e) {

                                    }

                                }

                                for (int k = 0; arr_price.length() > k; k++) {
                                    JSONObject obj_price = arr_price.getJSONObject(k);

                                    String model_price = obj_price.getString(TAG_Model_PRICE);

                                    Arraylist_model_price3.add(model_price);

                                }

                            }

                        } else if (select_product_model_no == 4) {

                            Arraylist_model_type4.clear();
                            Arraylist_model_price4.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                JSONArray arr_type = obj1.getJSONArray("product_type_main");
                                JSONArray arr_price = obj1.getJSONArray("product_price_main");

                                for (int j = 0; arr_type.length() > j; j++) {
                                    JSONObject obj_type = arr_type.getJSONObject(j);

                                    String model_type = obj_type.getString(TAG_Model_Type);

                                    Arraylist_model_type4.add(model_type);

                                    try {
                                        spn_model_type4
                                                .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                        android.R.layout.simple_spinner_dropdown_item,
                                                        Arraylist_model_type4));

                                    } catch (Exception e) {

                                    }

                                }

                                for (int k = 0; arr_price.length() > k; k++) {
                                    JSONObject obj_price = arr_price.getJSONObject(k);

                                    String model_price = obj_price.getString(TAG_Model_PRICE);

                                    Arraylist_model_price4.add(model_price);

                                }

                            }

                        } else if (select_product_model_no == 5) {

                            Arraylist_model_type5.clear();
                            Arraylist_model_price5.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                JSONArray arr_type = obj1.getJSONArray("product_type_main");
                                JSONArray arr_price = obj1.getJSONArray("product_price_main");

                                for (int j = 0; arr_type.length() > j; j++) {
                                    JSONObject obj_type = arr_type.getJSONObject(j);

                                    String model_type = obj_type.getString(TAG_Model_Type);

                                    Arraylist_model_type5.add(model_type);

                                    try {
                                        spn_model_type5
                                                .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                        android.R.layout.simple_spinner_dropdown_item,
                                                        Arraylist_model_type5));

                                    } catch (Exception e) {

                                    }

                                }

                                for (int k = 0; arr_price.length() > k; k++) {
                                    JSONObject obj_price = arr_price.getJSONObject(k);

                                    String model_price = obj_price.getString(TAG_Model_PRICE);

                                    Arraylist_model_price5.add(model_price);

                                }

                            }

                        } else if (select_product_model_no == 6) {

                            Arraylist_model_type6.clear();
                            Arraylist_model_price6.clear();

                            for (int i = 0; arr.length() > i; i++) {
                                JSONObject obj1 = arr.getJSONObject(i);

                                JSONArray arr_type = obj1.getJSONArray("product_type_main");
                                JSONArray arr_price = obj1.getJSONArray("product_price_main");

                                for (int j = 0; arr_type.length() > j; j++) {
                                    JSONObject obj_type = arr_type.getJSONObject(j);

                                    String model_type = obj_type.getString(TAG_Model_Type);

                                    Arraylist_model_type6.add(model_type);

                                    try {
                                        spn_model_type6
                                                .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                        android.R.layout.simple_spinner_dropdown_item,
                                                        Arraylist_model_type6));

                                    } catch (Exception e) {

                                    }

                                }

                                for (int k = 0; arr_price.length() > k; k++) {
                                    JSONObject obj_price = arr_price.getJSONObject(k);

                                    String model_price = obj_price.getString(TAG_Model_PRICE);

                                    Arraylist_model_price6.add(model_price);

                                }

                            }

                        }


                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("No Data Found")
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

                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                if (select_product_model_no == 2) {

                    params.put("model_no", str_Selected_model_no2);
                    System.out.println("model_no" + str_Selected_model_no2);
                    System.out.println("model_no" + str_Selected_model_no2);
                    System.out.println("model_no" + str_Selected_model_no2);
                } else if (select_product_model_no == 3) {

                    params.put("model_no", str_Selected_model_no3);
                    System.out.println("model_no" + str_Selected_model_no3);
                    System.out.println("model_no" + str_Selected_model_no3);
                    System.out.println("model_no" + str_Selected_model_no3);
                } else if (select_product_model_no == 4) {

                    params.put("model_no", str_Selected_model_no4);
                    System.out.println("model_no" + str_Selected_model_no4);
                    System.out.println("model_no" + str_Selected_model_no4);
                    System.out.println("model_no" + str_Selected_model_no4);
                } else if (select_product_model_no == 5) {

                    params.put("model_no", str_Selected_model_no5);
                    System.out.println("model_no" + str_Selected_model_no5);
                    System.out.println("model_no" + str_Selected_model_no5);
                    System.out.println("model_no" + str_Selected_model_no5);
                } else if (select_product_model_no == 6) {

                    params.put("model_no", str_Selected_model_no6);
                    System.out.println("model_no" + str_Selected_model_no6);
                    System.out.println("model_no" + str_Selected_model_no6);
                    System.out.println("model_no" + str_Selected_model_no6);
                }

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * GET Quotation Number
     ***************************/

    public void GetEnq_No() {

        String tag_json_obj = "json_obj_req";
        System.out.println("CAME 1");
        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_quotation_number, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("success");

                    if (success == 1) {

                        JSONArray arr;

                        arr = obj.getJSONArray("quotation");

                        Arraylist_quotation_no.clear();

                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);

                            String quote_no = obj1.getString(TAG_QUOTATION_NO);
                            String quote_price = obj1.getString(TAG_QUOTATION_PRICE);
                            String quote_date = obj1.getString(TAG_QUOTATION_DATE);

                            String product = quote_no + ": " + "Price : " + quote_price + ", " + "Date : " + quote_date;

                            Arraylist_quotation_no.add(product);

                            try {

                                System.out.println("Quotation no :: " + Arraylist_quotation_no);

                                spn_enq_no_for_comple_drop
                                        .setAdapter(new ArrayAdapter<String>(Activity_Enquiry_Process.this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                Arraylist_quotation_no));

                            } catch (Exception e) {

                            }

                        }

                        dialog.dismiss();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                str_po_en_no = txt_enq_id.getText().toString();

                params.put("enq_no", str_po_en_no);

                System.out.println("ENQQQQQQ :::::: " + str_po_en_no);

                return params;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * POST Enquiry
     * *************************/
    public void POST_ENQ() {

        System.out.println("CAME 1" + str_select_group);

        //   String str_url = "http://gemservice.in/crm/android/enquiry_save_quotation.php?enq_no=" + str_po_en_no + "&companyname=" + str_po_comp_name + "&email=" + str_po_email + "&address=" + str_po_address + "&pin=" + str_po_pin + "&phone_no=" + str_po_phone + "&contact_person=" + str_po_contact_person + "&contact_person_phone=" + str_po_contact_person_phone + "&enq_through=" + str_po_enq_through + "&enq_desc=" + str_po_enq_description + "&pro_group=" + str_po_group1 + "&pro_model=" + str_po_model1 + "&pro_model_no=" + str_po_model_no1 + "&pro_model_type=" + str_po_model_type + "&pro_qty=" + str_po_qty1 + "&pro_price=" + str_po_price1 + "&pro_group2=" + str_po_group2 + "&pro_model2=" + str_po_model2 + "&pro_model_no2=" + str_po_model_no2 + "&pro_model_type2=" + str_po_mode2_type + "&pro_qty2=" + str_po_qty2 + "&pro_price2=" + str_po_price2 + "&pro_group3=" + str_po_group3 + "&pro_model3=" + str_po_model3 + "&pro_model_no3=" + str_po_model_no3 + "&pro_model_type3=" + str_po_mode3_type + "&pro_qty3=" + str_po_qty3 + "&pro_price3=" + str_po_price3 + "&pro_group4=" + str_po_group4 + "&pro_model4=" + str_po_model4 + "&pro_model_no4=" + str_po_model_no4 + "&pro_model_type4=" + str_po_mode4_type + "&pro_qty4=" + str_po_qty4 + "&pro_price4=" + str_po_price4 + "&pro_group5=" + str_po_group5 + "&pro_model5=" + str_po_model5 + "&pro_model_no5=" + str_po_model_no5 + "&pro_model_type5=" + str_po_mode5_type + "&pro_qty5=" + str_po_qty5 + "&pro_price5=" + str_po_price5 + "&pro_group6=" + str_po_group6 + "&pro_model6=" + str_po_model6 + "&pro_model_no6=" + str_po_model6 + "&pro_model_type6=" + str_po_mode6_type + "&pro_qty6=" + str_po_qty6 + "&pro_price6=" + str_po_price6 + "&discount=" + str_po_discount + "&remarks=" + str_po_spec + "&status=" + str_po_status + "&user=" + str_user_id;

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_post_enq, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    quotation_no = obj.getString("quotation_no");

                    FunctionCAllAlert(quotation_no);


                    if (success == 1) {

                        quotation_no = obj.getString("quotation_no");

                        FunctionCAllAlert(quotation_no);

                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(Activity_Enquiry_Process.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("quotation_no", quotation_no);

                        System.out.println("quotation_no" + quotation_no);

                        editor.commit();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("enq_no", str_po_en_no);
                params.put("companyname", str_po_comp_name);
                params.put("email", str_po_email);
                params.put("addon_email", str_select_addon_email);
                params.put("addon_email2", str_select_addon_email2);
                params.put("addon_email3", str_select_addon_email3);
                params.put("address", str_po_address);
                params.put("pin", str_po_pin);
                params.put("phone_no", str_po_phone);
                params.put("contact_person", str_po_contact_person);
                params.put("contact_person_phone", str_po_contact_person_phone);
                params.put("enq_through", str_po_enq_through);
                params.put("enq_desc", str_po_enq_description);
                params.put("pro_group", str_Selected_group1);
                params.put("pro_model", str_po_model1);
                params.put("pro_model_no", str_po_model_no1);
                params.put("pro_model_type", str_po_model_type);
                params.put("pro_qty", str_po_qty1);
                params.put("pro_price", str_po_price1);
                params.put("price_add", str_po_add_price1);
                params.put("pro_group2", str_po_group2);
                params.put("pro_model2", str_po_model2);
                params.put("pro_model_no2", str_po_model_no2);
                params.put("pro_model_type2", str_po_mode2_type);
                params.put("pro_qty2", str_po_qty2);
                params.put("pro_price2", str_po_price2);
                params.put("price_add2", str_po_add_price2);
                params.put("pro_group3", str_po_group3);
                params.put("pro_model3", str_po_model3);
                params.put("pro_model_no3", str_po_model_no3);
                params.put("pro_model_type3", str_po_mode3_type);
                params.put("pro_qty3", str_po_qty3);
                params.put("pro_price3", str_po_price3);
                params.put("price_add3", str_po_add_price3);
                params.put("pro_group4", str_po_group4);
                params.put("pro_model4", str_po_model4);
                params.put("pro_model_no4", str_po_model_no4);
                params.put("pro_model_type4", str_po_mode4_type);
                params.put("pro_qty4", str_po_qty4);
                params.put("pro_price4", str_po_price4);
                params.put("price_add4", str_po_add_price4);
                params.put("pro_group5", str_po_group5);
                params.put("pro_model5", str_po_model5);
                params.put("pro_model_no5", str_po_model_no5);
                params.put("pro_model_type5", str_po_mode5_type);
                params.put("pro_qty5", str_po_qty5);
                params.put("pro_price5", str_po_price5);
                params.put("price_add5", str_po_add_price5);
                params.put("pro_group6", str_po_group6);
                params.put("pro_model6", str_po_model6);
                params.put("pro_model_no6", str_po_model_no6);
                params.put("pro_model_type6", str_po_mode6_type);
                params.put("pro_qty6", str_po_qty6);
                params.put("pro_price6", str_po_price6);
                params.put("price_add6", str_po_add_price6);
                params.put("discount", str_po_discount);
                params.put("remarks", str_po_spec);
                params.put("status", str_po_status);
                params.put("user", str_user_id);
                params.put("tax1", str_tax_pnf);
                params.put("tax2", str_tax_vat_cst);
                params.put("tax3", str_tax_exduty);
                params.put("freight_terms", str_feright_terms);
                params.put("erection", str_erection);
                params.put("delivery", str_delivery_weeks);
                params.put("ga", str_ga_dia1);

                System.out.println("tax" + str_po_add_price6);
                System.out.println("gadia" + str_po_add_price5);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * POST AnotherEnquiry
     * *************************/
    public void POST_ANOTHER_ENQ() {

        System.out.println("CAME 1" + str_select_group);

        //   String str_url = "http://gemservice.in/crm/android/enquiry_save_quotation.php?enq_no=" + str_po_en_no + "&companyname=" + str_po_comp_name + "&email=" + str_po_email + "&address=" + str_po_address + "&pin=" + str_po_pin + "&phone_no=" + str_po_phone + "&contact_person=" + str_po_contact_person + "&contact_person_phone=" + str_po_contact_person_phone + "&enq_through=" + str_po_enq_through + "&enq_desc=" + str_po_enq_description + "&pro_group=" + str_po_group1 + "&pro_model=" + str_po_model1 + "&pro_model_no=" + str_po_model_no1 + "&pro_model_type=" + str_po_model_type + "&pro_qty=" + str_po_qty1 + "&pro_price=" + str_po_price1 + "&pro_group2=" + str_po_group2 + "&pro_model2=" + str_po_model2 + "&pro_model_no2=" + str_po_model_no2 + "&pro_model_type2=" + str_po_mode2_type + "&pro_qty2=" + str_po_qty2 + "&pro_price2=" + str_po_price2 + "&pro_group3=" + str_po_group3 + "&pro_model3=" + str_po_model3 + "&pro_model_no3=" + str_po_model_no3 + "&pro_model_type3=" + str_po_mode3_type + "&pro_qty3=" + str_po_qty3 + "&pro_price3=" + str_po_price3 + "&pro_group4=" + str_po_group4 + "&pro_model4=" + str_po_model4 + "&pro_model_no4=" + str_po_model_no4 + "&pro_model_type4=" + str_po_mode4_type + "&pro_qty4=" + str_po_qty4 + "&pro_price4=" + str_po_price4 + "&pro_group5=" + str_po_group5 + "&pro_model5=" + str_po_model5 + "&pro_model_no5=" + str_po_model_no5 + "&pro_model_type5=" + str_po_mode5_type + "&pro_qty5=" + str_po_qty5 + "&pro_price5=" + str_po_price5 + "&pro_group6=" + str_po_group6 + "&pro_model6=" + str_po_model6 + "&pro_model_no6=" + str_po_model6 + "&pro_model_type6=" + str_po_mode6_type + "&pro_qty6=" + str_po_qty6 + "&pro_price6=" + str_po_price6 + "&discount=" + str_po_discount + "&remarks=" + str_po_spec + "&status=" + str_po_status + "&user=" + str_user_id;

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_post_Completed, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    quotation_no = obj.getString("quotation_no");

                    FunctionCAllAlert(quotation_no);


                    if (success == 1) {

                        quotation_no = obj.getString("quotation_no");

                        FunctionCAllAlert(quotation_no);

                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(Activity_Enquiry_Process.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("quotation_no", quotation_no);

                        System.out.println("quotation_no" + quotation_no);

                        editor.commit();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("enq_no", str_po_en_no);
                params.put("companyname", str_po_comp_name);
                params.put("email", str_po_email);
                params.put("addon_email", str_select_addon_email);
                params.put("addon_email2", str_select_addon_email2);
                params.put("addon_email3", str_select_addon_email3);
                params.put("address", str_po_address);
                params.put("pin", str_po_pin);
                params.put("phone_no", str_po_phone);
                params.put("contact_person", str_po_contact_person);
                params.put("contact_person_phone", str_po_contact_person_phone);
                params.put("enq_through", str_po_enq_through);
                params.put("enq_desc", str_po_enq_description);
                params.put("pro_group", str_po_group1);
                params.put("pro_model", str_po_model1);
                params.put("pro_model_no", str_po_model_no1);
                params.put("pro_model_type", str_po_model_type);
                params.put("pro_qty", str_po_qty1);
                params.put("pro_price", str_po_price1);
                params.put("price_add", str_po_add_price1);
                params.put("pro_group2", str_po_group2);
                params.put("pro_model2", str_po_model2);
                params.put("pro_model_no2", str_po_model_no2);
                params.put("pro_model_type2", str_po_mode2_type);
                params.put("pro_qty2", str_po_qty2);
                params.put("pro_price2", str_po_price2);
                params.put("price_add2", str_po_add_price2);
                params.put("pro_group3", str_po_group3);
                params.put("pro_model3", str_po_model3);
                params.put("pro_model_no3", str_po_model_no3);
                params.put("pro_model_type3", str_po_mode3_type);
                params.put("pro_qty3", str_po_qty3);
                params.put("pro_price3", str_po_price3);
                params.put("price_add3", str_po_add_price3);
                params.put("pro_group4", str_po_group4);
                params.put("pro_model4", str_po_model4);
                params.put("pro_model_no4", str_po_model_no4);
                params.put("pro_model_type4", str_po_mode4_type);
                params.put("pro_qty4", str_po_qty4);
                params.put("pro_price4", str_po_price4);
                params.put("price_add4", str_po_add_price4);
                params.put("pro_group5", str_po_group5);
                params.put("pro_model5", str_po_model5);
                params.put("pro_model_no5", str_po_model_no5);
                params.put("pro_model_type5", str_po_mode5_type);
                params.put("pro_qty5", str_po_qty5);
                params.put("pro_price5", str_po_price5);
                params.put("price_add5", str_po_add_price5);
                params.put("pro_group6", str_po_group6);
                params.put("pro_model6", str_po_model6);
                params.put("pro_model_no6", str_po_model_no6);
                params.put("pro_model_type6", str_po_mode6_type);
                params.put("pro_qty6", str_po_qty6);
                params.put("pro_price6", str_po_price6);
                params.put("price_add6", str_po_add_price6);
                params.put("discount", str_po_discount);
                params.put("remarks", str_po_spec);
                params.put("status", str_po_status);
                params.put("user", str_user_id);
                params.put("tax1", str_tax_pnf);
                params.put("tax2", str_tax_vat_cst);
                params.put("tax3", str_tax_exduty);
                params.put("freight_terms", str_feright_terms);
                params.put("erection", str_erection);
                params.put("delivery", str_delivery_weeks);
                params.put("ga", str_ga_dia1);

                System.out.println("tax" + str_po_add_price6);
                System.out.println("gadia" + str_po_add_price5);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * POST Appointment
     * *************************/
    public void POST_Apppointment() {

        System.out.println("CAME 1" + str_select_group);

        StringRequest request = new StringRequest(Request.Method.POST,
                str_appoint_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("Appointment Saved Successfully")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();


                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("enq_no", str_po_en_no);
                params.put("companyname", str_po_comp_name);
                params.put("email", str_po_email);
                params.put("address", str_po_address);
                params.put("pin", str_po_pin);
                params.put("phone_no", str_po_phone);
                params.put("contact_person", str_po_contact_person);
                params.put("contact_person_phone", str_po_contact_person_phone);
                params.put("enq_through", str_po_enq_through);
                params.put("enq_desc", str_po_enq_description);
                params.put("remarks", str_po_spec);
                params.put("status", str_po_status);
                params.put("date", str_po_appoint_date);
                params.put("time", str_po_appoint_time);
                params.put("appointment_through", str_po_contact_person_phone);
                params.put("appointment_location", str_po_address);
                params.put("appointment_description", str_po_contact_person);
                params.put("user", str_user_id);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * POST CALL TL
     * *************************/
    public void POST_CALL_TL() {

        System.out.println("CAME 1" + str_select_group);

        //   String str_url = "http://gemservice.in/crm/android/enquiry_save_quotation.php?enq_no=" + str_po_en_no + "&companyname=" + str_po_comp_name + "&email=" + str_po_email + "&address=" + str_po_address + "&pin=" + str_po_pin + "&phone_no=" + str_po_phone + "&contact_person=" + str_po_contact_person + "&contact_person_phone=" + str_po_contact_person_phone + "&enq_through=" + str_po_enq_through + "&enq_desc=" + str_po_enq_description + "&pro_group=" + str_po_group1 + "&pro_model=" + str_po_model1 + "&pro_model_no=" + str_po_model_no1 + "&pro_model_type=" + str_po_model_type + "&pro_qty=" + str_po_qty1 + "&pro_price=" + str_po_price1 + "&pro_group2=" + str_po_group2 + "&pro_model2=" + str_po_model2 + "&pro_model_no2=" + str_po_model_no2 + "&pro_model_type2=" + str_po_mode2_type + "&pro_qty2=" + str_po_qty2 + "&pro_price2=" + str_po_price2 + "&pro_group3=" + str_po_group3 + "&pro_model3=" + str_po_model3 + "&pro_model_no3=" + str_po_model_no3 + "&pro_model_type3=" + str_po_mode3_type + "&pro_qty3=" + str_po_qty3 + "&pro_price3=" + str_po_price3 + "&pro_group4=" + str_po_group4 + "&pro_model4=" + str_po_model4 + "&pro_model_no4=" + str_po_model_no4 + "&pro_model_type4=" + str_po_mode4_type + "&pro_qty4=" + str_po_qty4 + "&pro_price4=" + str_po_price4 + "&pro_group5=" + str_po_group5 + "&pro_model5=" + str_po_model5 + "&pro_model_no5=" + str_po_model_no5 + "&pro_model_type5=" + str_po_mode5_type + "&pro_qty5=" + str_po_qty5 + "&pro_price5=" + str_po_price5 + "&pro_group6=" + str_po_group6 + "&pro_model6=" + str_po_model6 + "&pro_model_no6=" + str_po_model6 + "&pro_model_type6=" + str_po_mode6_type + "&pro_qty6=" + str_po_qty6 + "&pro_price6=" + str_po_price6 + "&discount=" + str_po_discount + "&remarks=" + str_po_spec + "&status=" + str_po_status + "&user=" + str_user_id;

        StringRequest request = new StringRequest(Request.Method.POST,
                str_call_TL_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);
                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("Call Setted to TL :) ")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();

                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
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
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("enq_no", str_po_en_no);
                params.put("companyname", str_po_comp_name);
                params.put("email", str_po_email);
                params.put("addon_email", str_select_addon_email);
                params.put("addon_email2", str_select_addon_email2);
                params.put("addon_email3", str_select_addon_email3);
                params.put("address", str_po_address);
                params.put("pin", str_po_pin);
                params.put("phone_no", str_po_phone);
                params.put("contact_person", str_po_contact_person);
                params.put("contact_person_phone", str_po_contact_person_phone);
                params.put("enq_through", str_po_enq_through);
                params.put("enq_desc", str_po_enq_description);
                params.put("pro_group", str_po_group1);
                params.put("pro_model", str_po_model1);
                params.put("pro_model_no", str_po_model_no1);
                params.put("pro_model_type", str_po_model_type);
                params.put("pro_qty", str_po_qty1);
                params.put("pro_price", str_po_price1);
                params.put("price_add", str_po_add_price1);
                params.put("pro_group2", str_po_group2);
                params.put("pro_model2", str_po_model2);
                params.put("pro_model_no2", str_po_model_no2);
                params.put("pro_model_type2", str_po_mode2_type);
                params.put("pro_qty2", str_po_qty2);
                params.put("pro_price2", str_po_price2);
                params.put("price_add2", str_po_add_price2);
                params.put("pro_group3", str_po_group3);
                params.put("pro_model3", str_po_model3);
                params.put("pro_model_no3", str_po_model_no3);
                params.put("pro_model_type3", str_po_mode3_type);
                params.put("pro_qty3", str_po_qty3);
                params.put("pro_price3", str_po_price3);
                params.put("price_add3", str_po_add_price3);
                params.put("pro_group4", str_po_group4);
                params.put("pro_model4", str_po_model4);
                params.put("pro_model_no4", str_po_model_no4);
                params.put("pro_model_type4", str_po_mode4_type);
                params.put("pro_qty4", str_po_qty4);
                params.put("pro_price4", str_po_price4);
                params.put("price_add4", str_po_add_price4);
                params.put("pro_group5", str_po_group5);
                params.put("pro_model5", str_po_model5);
                params.put("pro_model_no5", str_po_model_no5);
                params.put("pro_model_type5", str_po_mode5_type);
                params.put("pro_qty5", str_po_qty5);
                params.put("pro_price5", str_po_price5);
                params.put("price_add5", str_po_add_price5);
                params.put("pro_group6", str_po_group6);
                params.put("pro_model6", str_po_model6);
                params.put("pro_model_no6", str_po_model_no6);
                params.put("pro_model_type6", str_po_mode6_type);
                params.put("pro_qty6", str_po_qty6);
                params.put("pro_price6", str_po_price6);
                params.put("price_add6", str_po_add_price6);
                params.put("discount", str_po_discount);
                params.put("remarks", str_po_spec);
                params.put("status", str_po_status);
                params.put("user", str_user_id);
                params.put("ga", str_ga_dia1);

                System.out.println("tax" + str_po_add_price6);
                System.out.println("gadia" + str_po_add_price5);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        // Adding request to request queue
        queue.add(request);
    }


    /***************************
     * POST Completed
     * *************************/
    public void POST_Completed() {

        System.out.println("CAME 1" + str_Selected_quotation_no);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_post_Completed, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("Enquiry Completed Successfully")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();


                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("Enquiry Completed Failed")
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
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_user_id);
                params.put("enq_no", str_po_en_no);
                params.put("status", str_po_status);
                params.put("image", encodedstring);
                params.put("emq_po_no", str_po_no);
                params.put("shows_quotation", str_Selected_quotation_no);
                params.put("enq_remarks", str_po_spec);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * POST Dropped
     * *************************/
    public void POST_Dropped() {

        System.out.println("CAME 1" + str_Selected_quotation_no);

        StringRequest request = new StringRequest(Request.Method.POST,
                AppConfig.url_post_Completed, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                System.out.println("CAME str_url" + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    int success = obj.getInt("success");

                    if (success == 1) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("Enquiry Dropped Successfully")
                                .setBackgroundColor(R.color.Alert_Success)
                                .show();


                    } else if (success == 0) {

                        Alerter.create(Activity_Enquiry_Process.this)
                                .setTitle("GEM CRM")
                                .setText("Enquiry Dropped Failed")
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
                Alerter.create(Activity_Enquiry_Process.this)
                        .setTitle("GEM CRM")
                        .setText(error.getMessage())
                        .setBackgroundColor(R.color.Alert_Warning)
                        .show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user", str_user_id);
                params.put("enq_no", str_po_en_no);
                params.put("status", str_po_status);
                params.put("enq_remarks", str_po_spec);

                return checkParams(params);
            }

            private Map<String, String> checkParams(Map<String, String> map) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
                    if (pairs.getValue() == null) {
                        map.put(pairs.getKey(), "");
                    }
                }
                return map;
            }

        };

        int socketTimeout = 60000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        // Adding request to request queue
        queue.add(request);
    }

    /***************************
     * Function ALert
     * *************************/

    private void FunctionCAllAlert(final String str_number) {

        new android.app.AlertDialog.Builder(Activity_Enquiry_Process.this)
                .setTitle("Gem India")
                .setMessage("Enquiry Posted Successfully\nQuotation Number : " + str_number)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("Done",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                try {

                                    SharedPreferences sharedPreferences = PreferenceManager
                                            .getDefaultSharedPreferences(Activity_Enquiry_Process.this);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString("quotation_no", quotation_no);

                                    editor.commit();

                                    System.out.println("quotation_no" + quotation_no);


                                    Intent i = new Intent(getApplicationContext(), Activity_Quotation.class);
                                    startActivity(i);

                                } catch (Exception e) {

                                }
                            }
                        }).show();

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

            new AlertDialog.Builder(Activity_Enquiry_Process.this)
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
