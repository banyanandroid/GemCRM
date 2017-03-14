package banyan.com.gemcrm.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import banyan.com.gemcrm.R;

/**
 * Created by Jo on 3/15/2017.
 */

public class Activity_Dashboard_Product_Target extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_product_target);


        pieChart = (PieChart) findViewById(R.id.pie_chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(12, 0));
        entries.add(new Entry(10, 1));
        entries.add(new Entry(15, 2));
        entries.add(new Entry(8, 3));

        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Dryer");
        labels.add("Chiller");
        labels.add("Cooling Tower");
        labels.add("Others");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS); //
        pieChart.setDescription("Product Targer");
        pieChart.setData(data);


        pieChart.animateY(2000);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener


//        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image

    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Product Target Chart\ndeveloped by Banyan");

        return s;
    }

}
