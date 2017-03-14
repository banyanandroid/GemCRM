package banyan.com.gemcrm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fabtransitionactivity.SheetLayout;

import banyan.com.gemcrm.R;
import butterknife.OnClick;


/**
 * Created by Ravi on 29/07/15.
 */
public class Fragment_Appoinments extends Fragment implements SheetLayout.OnFabAnimationEndListener {

    SheetLayout mSheetLayout;
    FloatingActionButton mFab;

    private static final int REQUEST_CODE = 1;

    public Fragment_Appoinments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appoinments, container, false);

        mFab = (FloatingActionButton) rootView.findViewById(R.id.fab_add_appoinment);
        mSheetLayout = (SheetLayout) rootView.findViewById(R.id.bottom_sheet);

        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    @OnClick(R.id.fab_add_appoinment)
    void onFabClick() {
        mSheetLayout.expandFab();
    }


    @Override
    public void onFabAnimationEnd() {
        Intent intent = new Intent(getActivity(), Activity_Appoinment_Add.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            mSheetLayout.contractFab();
        }
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
