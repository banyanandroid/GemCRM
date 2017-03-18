package banyan.com.gemcrm.global;

/**
 * Created by User on 3/16/2017.
 */

public class AppConfig {

    public static String base_url = "http://gemservice.in/crm/";


    public static String url_authentication = base_url + "android/user_login_validation.php";
    public static String url_lastlogin = base_url + "android/user_last_login.php";

    public static String url_getcampaign = base_url + "android/user_get_campaign_list.php";
    public static String url_addcampaign = base_url + "android/user_create_campaign.php";

    public static String url_getappointment = base_url + "android/user_get_appointment_list.php";
    public static String url_addappointment = base_url + "android/user_create_appointment.php";

    public static String url_product_group = base_url + "android/product_group.php";
    public static String url_enquiry_through = base_url + "android/enquiry_through.php";
    public static String url_campaign = base_url + "android/get_campaign_list.php";
    public static String url_add_enquiry = base_url + "android/user_create_enquiry.php";
}
