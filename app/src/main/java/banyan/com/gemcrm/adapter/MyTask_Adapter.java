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
import banyan.com.gemcrm.activity.Fragment_Target;


public class MyTask_Adapter extends BaseAdapter {
    private Activity activity;
    private Context context;
    private LinearLayout singleMessageContainer;

    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    private String[] bgColors;

    public MyTask_Adapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            v = inflater.inflate(R.layout.list_item_my_task, null);

        TextView app_initial = (TextView) v.findViewById(R.id.list_mytask_initial);
        TextView app_with = (TextView) v.findViewById(R.id.list_mytask_with);
        TextView app_location = (TextView) v.findViewById(R.id.list_mytask_location);
        TextView app_des = (TextView) v.findViewById(R.id.list_mytask_des);
        TextView app_date = (TextView) v.findViewById(R.id.list_mytask_timestamp);

        HashMap<String, String> result = new HashMap<String, String>();
        result = data.get(position);

        app_initial.setText(result.get(Fragment_Target.TAG_TASK_NAME).substring(0,1));
        app_with.setText(result.get(Fragment_Target.TAG_TASK_NAME));
        app_location.setText(result.get(Fragment_Target.TAG_TASK_DES));
        app_des.setText(result.get("Assigned By : "+Fragment_Target.TAG_ASSIGNEDBY));
        app_date.setText(result.get(Fragment_Target.TAG_CREATE_ON));

        String color = bgColors[position % bgColors.length];
        app_initial.setBackgroundColor(Color.parseColor(color));

        return v;

    }

}