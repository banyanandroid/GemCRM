package banyan.com.gemcrm.activity;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

import banyan.com.gemcrm.R;
import banyan.com.gemcrm.global.SessionManager;


public class Fragment_Dashboard extends Fragment {

    public Fragment_Dashboard() {
        // Required empty public constructor
    }

    SessionManager session;
    String str_user_name, str_user_id, str_gcm = "";
    CardView card1, card2, card3, card4;
    ImageView thumbnail1,thumbnail2,thumbnail3,thumbnail4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        session = new SessionManager(getActivity());

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        str_user_name = user.get(SessionManager.KEY_USER);
        str_user_id = user.get(SessionManager.KEY_USER_ID);
        str_gcm = user.get(SessionManager.KEY_GCM);

        card1 = (CardView) rootView.findViewById(R.id.dashboard_card_view1);
        card2 = (CardView) rootView.findViewById(R.id.dashboard_card_view2);
        card3 = (CardView) rootView.findViewById(R.id.dashboard_card_view3);
        card4 = (CardView) rootView.findViewById(R.id.dashboard_card_view4);

        thumbnail1 = (ImageView) rootView.findViewById(R.id.dashboard_thumbnail1);
        thumbnail2 = (ImageView) rootView.findViewById(R.id.dashboard_thumbnail2);
        thumbnail3 = (ImageView) rootView.findViewById(R.id.dashboard_thumbnail3);
        thumbnail4 = (ImageView) rootView.findViewById(R.id.dashboard_thumbnail4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Finance_Target.class);
                startActivity(i);

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Product_Target.class);
                startActivity(i);

            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Ativity_Dash.class);
                startActivity(i);

            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Target 4", Toast.LENGTH_LONG).show();

            }
        });

        thumbnail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Finance_Target.class);
                startActivity(i);

            }
        });

        thumbnail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Activity_Dashboard_Product_Target.class);
                startActivity(i);

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
