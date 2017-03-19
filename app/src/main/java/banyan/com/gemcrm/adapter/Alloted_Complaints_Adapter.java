package banyan.com.gemcrm.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.activity.Fragment_Appointments;
import banyan.com.gemcrm.activity.Tab_Enquiry_Fragment;


public class Alloted_Complaints_Adapter extends BaseAdapter {
    private Activity activity;
    private Context context;
    private LinearLayout singleMessageContainer;

    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    private String[] bgColors;

    public Alloted_Complaints_Adapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null)
            v = inflater.inflate(R.layout.list_item_alloted_comp, null);

        TextView app_initial = (TextView) v.findViewById(R.id.list_allot_enq_initial);
        TextView app_with = (TextView) v.findViewById(R.id.list_allot_enq_with);
        TextView app_location = (TextView) v.findViewById(R.id.list_allot_enq_location);
        TextView app_des = (TextView) v.findViewById(R.id.list_allot_enq_des);
        TextView app_date = (TextView) v.findViewById(R.id.list_allot_enq_timestamp);

        HashMap<String, String> result = new HashMap<String, String>();
        result = data.get(position);

        app_initial.setText(result.get(Tab_Enquiry_Fragment.TAG_ENQ_END_COMP_NAME).substring(0, 1));
        app_with.setText(result.get(Tab_Enquiry_Fragment.TAG_ENQ_END_COMP_NAME));
        app_location.setText(result.get(Tab_Enquiry_Fragment.TAG_ENQ_PRODUCT_SERIES));
        app_des.setText("Status : "+result.get(Tab_Enquiry_Fragment.TAG_ENQ_STATUS));
        app_date.setText(result.get(Tab_Enquiry_Fragment.TAG_ENQ_CREAATED_ON));

        String color = bgColors[position % bgColors.length];
        app_initial.setBackgroundColor(Color.parseColor(color));

        return v;

    }

}