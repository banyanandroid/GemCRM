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
import banyan.com.gemcrm.activity.Activity_Quotation;
import banyan.com.gemcrm.activity.Fragment_Campaign;


public class Quotation_Adapter extends BaseAdapter {
    private Activity activity;
    private Context context;
    private LinearLayout singleMessageContainer;

    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    private String[] bgColors;

    public Quotation_Adapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            v = inflater.inflate(R.layout.list_item_quotation, null);

        TextView quote_group = (TextView) v.findViewById(R.id.list_quote_group);
        TextView quote_model = (TextView) v.findViewById(R.id.list_quote_model);
        TextView quote_seried = (TextView) v.findViewById(R.id.list_quote_series);
        TextView quote_type = (TextView) v.findViewById(R.id.list_quote_type);
        TextView qty = (TextView) v.findViewById(R.id.list_quote_qty);
        TextView quote_amount = (TextView) v.findViewById(R.id.list_quote_amount);

        HashMap<String, String> result = new HashMap<String, String>();
        result = data.get(position);

        quote_group.setText(result.get(Activity_Quotation.TAG_PRODUCT_GROUP));
        quote_model.setText(result.get(Activity_Quotation.TAG_PRODUCT_MODEL));
        quote_seried.setText(result.get(Activity_Quotation.TAG_PRODUCT_MODEL_NO));
        quote_type.setText(result.get(Activity_Quotation.TAG_PRODUCT_TYPE));
        qty.setText(result.get(Activity_Quotation.TAG_PRODUCT_QTY));
        quote_amount.setText(result.get(Activity_Quotation.TAG_PRODUCT_PRICE));


        return v;

    }

}