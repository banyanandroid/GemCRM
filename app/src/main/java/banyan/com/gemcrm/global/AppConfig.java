package banyan.com.gemcrm.global;

/**
 * Created by User on 3/16/2017.
 */

public class AppConfig {

    public static String base_url = "http://gemservice.in/crm/";


    public static String url_authentication = base_url + "android/user_login_validation.php";
    public static String url_lastlogin = base_url + "android/user_last_login.php";
    public static String url_my_targets  = base_url + "android/user_target.php";

    public static String url_getcampaign = base_url + "android/user_get_campaign_list.php";
    public static String url_addcampaign = base_url + "android/user_create_campaign.php";

    public static String url_getappointment = base_url + "android/user_get_appointment_list.php";
    public static String url_addappointment = base_url + "android/user_create_appointment.php";

    public static String url_product_group = base_url + "android/product_group.php";
    public static String url_enquiry_through = base_url + "android/enquiry_through.php";
    public static String url_campaign = base_url + "android/get_campaign_list.php";
    public static String url_add_enquiry = base_url + "android/user_create_enquiry.php";

    public static String url_get_alloted_enquiry = base_url + "android/get_alloted_enquiries.php";
    public static String url_my_enquiry = base_url + "android/get_my_enquries.php";
    public static String url_my_succeed_enquiry = base_url + "android/get_my_suceed_enquiries.php";
    public static String url_my_failure_enquiry = base_url + "android/get_my_failed_enquiries.php";

    public static String url_enq_model = base_url + "android/user_get_model.php";
    public static String url_enq_discount = base_url + "android/user_get_discount.php";
    public static String url_enq_modelno = base_url + "android/enquiry_product_type.php";
    public static String url_enq_modeltype = base_url + "android/get_type.php";

    public static String url_post_enq = base_url + "android/enquiry_save_quotation.php";
    public static String url_post_Appointment = base_url + "android/enquiry_save_quotation.php";
    public static String url_Quotation  = base_url + "android/view_quotation_date.php";


    public static String url_my_task = base_url + "android/user_task_list.php";
}
