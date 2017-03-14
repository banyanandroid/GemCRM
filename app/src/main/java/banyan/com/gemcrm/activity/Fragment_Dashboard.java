package banyan.com.gemcrm.activity;

/**
 * Created by Ravi on 29/07/15.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import banyan.com.gemcrm.R;


public class Fragment_Dashboard extends Fragment {

    public Fragment_Dashboard() {
        // Required empty public constructor
    }

    CardView card1, card2, card3, card4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        card1 = (CardView) rootView.findViewById(R.id.dashboard_card_view1);
        card2 = (CardView) rootView.findViewById(R.id.dashboard_card_view2);
        card3 = (CardView) rootView.findViewById(R.id.dashboard_card_view3);
        card4 = (CardView) rootView.findViewById(R.id.dashboard_card_view4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Target 1", Toast.LENGTH_LONG).show();

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Target 2", Toast.LENGTH_LONG).show();

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Target 3", Toast.LENGTH_LONG).show();

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Target 4", Toast.LENGTH_LONG).show();

            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
