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
import banyan.com.gemcrm.activity.Fragment_Campaign;


public class Camp_Adapter extends BaseAdapter {
    private Activity activity;
    private Context context;
    private LinearLayout singleMessageContainer;

    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    private String[] bgColors;

    public Camp_Adapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            v = inflater.inflate(R.layout.list_item_camp, null);

        TextView camp_initial = (TextView) v.findViewById(R.id.list_camp_initial);
        TextView camp_with = (TextView) v.findViewById(R.id.list_camp_with);
        TextView camp_location = (TextView) v.findViewById(R.id.list_camp_location);
        TextView camp_des = (TextView) v.findViewById(R.id.list_camp_des);
        TextView camp_date = (TextView) v.findViewById(R.id.list_camp_timestamp);

        HashMap<String, String> result = new HashMap<String, String>();
        result = data.get(position);

        camp_initial.setText(result.get(Fragment_Campaign.TAG_CAMP_TITLE).substring(0,1));
        camp_with.setText(result.get(Fragment_Campaign.TAG_CAMP_TITLE));
        camp_location.setText(result.get(Fragment_Campaign.TAG_CAMP_LOCATION));
        camp_des.setText(result.get(Fragment_Campaign.TAG_CAMP_DES));
        camp_date.setText(result.get(Fragment_Campaign.TAG_CAMP_START_DATE));

        String color = bgColors[position % bgColors.length];
        camp_initial.setBackgroundColor(Color.parseColor(color));

        return v;

    }

}