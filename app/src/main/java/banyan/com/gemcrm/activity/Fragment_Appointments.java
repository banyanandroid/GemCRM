package banyan.com.gemcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fabtransitionactivity.SheetLayout;

import banyan.com.gemcrm.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by steve on 15/3/17.
 */

public class Fragment_Appointments extends Fragment implements SheetLayout.OnFabAnimationEndListener {

    SheetLayout mSheetLayout;
    FloatingActionButton mFab;

    private static final int REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_appoinments, null);

        ButterKnife.bind(getActivity());
        // setUpToolbarWithTitle(getString(R.string.INBOX), false);

        mFab = (FloatingActionButton) rootview.findViewById(R.id.fab_add_appoinment);
        mSheetLayout = (SheetLayout) rootview.findViewById(R.id.bottom_sheet1);

        mSheetLayout.setFab(mFab);
        mSheetLayout.setFabAnimationEndListener(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSheetLayout.expandFab();
            }
        });

        return rootview;

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
}
