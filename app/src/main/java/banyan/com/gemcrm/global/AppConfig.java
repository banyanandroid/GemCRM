package banyan.com.gemcrm.global;

/**
 * Created by User on 3/16/2017.
 */

public class AppConfig {

    public static String base_url = "http://gemservice.in/crm/";


    public static String url_authentication = base_url + "android/user_login_validation.php";
    public static String url_lastlogin = base_url + "android/user_last_login.php";
    public static String url_my_targets  = base_url + "android/user_target.php";
    public static String url_my_yearly_target  = base_url + "android/dashboard.php";
    public static String url_target_count  = base_url + "android/user_task_count.php";

    public static String url_getcampaign = base_url + "android/user_get_campaign_list.php";
    public static String url_addcampaign = base_url + "android/user_create_campaign.php";
    public static String url_editcampaign = base_url + "android/user_update_campaign.php";

    public static String url_getappointment = base_url + "android/user_get_appointment_list.php";
    public static String url_addappointment = base_url + "android/user_create_appointment.php";
    public static String url_editappointment = base_url + "android/user_update_appointment.php";
    public static String url_deleteappointment = base_url + "android/user_delete_appointment.php";

    public static String url_product_group = base_url + "android/product_group.php";
    public static String url_enquiry_through = base_url + "android/enquiry_through.php";
    public static String url_campaign = base_url + "android/get_campaign_list.php";
    public static String url_region = base_url + "android/get_team.php";
    public static String url_add_enquiry = base_url + "android/user_create_enquiry.php";
    public static String url_quotation_number = base_url + "android/get_all_qoutation.php"; // Change it
    public static String url_tax_info = base_url + "android/user_get_tax.php";

    public static String url_get_alloted_enquiry = base_url + "android/get_alloted_enquiries.php";
    public static String url_my_enquiry = base_url + "android/get_my_enquries.php";
    public static String url_my_succeed_enquiry = base_url + "android/get_my_suceed_enquiries.php"; // Change it
    public static String url_my_failure_enquiry = base_url + "android/get_my_failed_enquiries.php";

    public static String url_enq_model = base_url + "android/user_get_model.php";
    public static String url_enq_discount = base_url + "android/user_get_discount.php";
    public static String url_enq_modelno = base_url + "android/enquiry_product_type.php";
    public static String url_enq_modeltype = base_url + "android/get_type.php";

    public static String url_post_enq = base_url + "android/enquiry_save_quotation.php";
    public static String url_post_Appointment = base_url + "android/enquiry_save_quotation.php";
    public static String url_post_Completed = base_url + "android/enquiry_process.php";
    public static String url_Quotation  = base_url + "android/view_quotation_date.php";
    public static String url_send_quotation  = base_url + "android/send_quotation.php";

    public static String url_followups = base_url + "android/enq_follow_ups.php";

    public static String url_catalogue = base_url + "android/get_catalouge.php";
    public static String url_send_catalogue = base_url + "android/mail_catalogue.php";

    public static String url_my_task = base_url + "android/user_task_list.php";

    public static String url_my_task_edit = base_url + "android/update_task.php";

    public static String url_new_enquiery = base_url + "android/user_new_enquiry_list.php";
    public static String url_self_allotment = base_url + "android/enquiry_self_allotment.php";

    public static String url_ofm_quotation_products = base_url + "android/get_ofm_quotation.php";
    public static String url_ofm_submit = base_url + "android/ofm.php";

}
