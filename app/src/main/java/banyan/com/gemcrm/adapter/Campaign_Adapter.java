package banyan.com.gemcrm.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.activity.Fragment_Campaign;


public class Campaign_Adapter extends BaseAdapter{
	private Activity activity;
    private Context context;
    private LinearLayout singleMessageContainer;

    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;

    private String[] bgColors;

    public Campaign_Adapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        bgColors = activity.getApplicationContext().getResources().getStringArray(R.array.movie_serial_bg);
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            View v=convertView;
            if(convertView==null)
                v = inflater.inflate(R.layout.list_campaign, null);

            TextView initial = (TextView)v.findViewById(R.id.list_txt_initial);
            TextView name = (TextView)v.findViewById(R.id.list_camp_name);
            TextView location = (TextView)v.findViewById(R.id.list_camp_location);
            TextView description = (TextView)v.findViewById(R.id.list_camp_des);
            TextView timestamp = (TextView)v.findViewById(R.id.list_camp_timestamp);

            HashMap<String, String> result = new HashMap<String, String>();
            result = data.get(position);

            initial.setText(result.get(Fragment_Campaign.TAG_CAMP_LOCATION).substring(0,1));
            name.setText(result.get(Fragment_Campaign.TAG_CAMP_TITLE));
            location.setText(result.get(Fragment_Campaign.TAG_CAMP_LOCATION));
            description.setText(result.get(Fragment_Campaign.TAG_CAMP_DES));
            timestamp.setText(result.get(Fragment_Campaign.TAG_CAMP_CREATED_ON));

            String color = bgColors[position % bgColors.length];
            initial.setBackgroundColor(Color.parseColor(color));
            return v;
        
    }
    
}