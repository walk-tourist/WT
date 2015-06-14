package wt.walk_tourist.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.parts_fragment.PDF_WalkAnimation_Texture;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;
import wt.walk_tourist.wt_fragment.WT_PartsDisplayFragment;

public class MDF_Base extends WT_MainDisplayFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.base_fragment, container, false);

        setViewItems(v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WT_PartsDisplayFragment base_Texture_Fragment = new PDF_WalkAnimation_Texture();
        fragmentTransaction.replace(R.id.base_texture_fragment, base_Texture_Fragment, "base_texture_fragment");

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        fragmentTransaction.commit();

    }

    private void setViewItems(View v) {

        v.findViewById(R.id.button_tourist_spot).setOnClickListener(this);
        v.findViewById(R.id.button_start_game).setOnClickListener(this);
        v.findViewById(R.id.button_point_management).setOnClickListener(this);
        v.findViewById(R.id.button_help).setOnClickListener(this);
        v.findViewById(R.id.button_display_map).setOnClickListener(this);

        v.findViewById(R.id.button_left).setOnClickListener(this);
        v.findViewById(R.id.button_right).setOnClickListener(this);
        v.findViewById(R.id.button_top).setOnClickListener(this);
        v.findViewById(R.id.button_bottom).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        FragmentManager fragmentManager = getFragmentManager();
        PDF_WalkAnimation_Texture fragment = (PDF_WalkAnimation_Texture)fragmentManager.findFragmentByTag("base_texture_fragment");


        switch (view.getId()) {
            case R.id.button_tourist_spot:
                mListener.changeMDF(MDF_NAME.MDF_SPOT);
                break;
            case R.id.button_start_game:
                mListener.changeMDF(MDF_NAME.MDF_GAME);
                break;
            case R.id.button_point_management:
                mListener.changeMDF(MDF_NAME.MDF_POINT);
                break;
            case R.id.button_help:
                mListener.changeMDF(MDF_NAME.MDF_HELP);
                break;
            case R.id.button_display_map:
                mListener.changeMDF(MDF_NAME.MDF_MAP);
                break;

            case R.id.button_right:
                fragment.changeDirection(2);
                break;
            case R.id.button_left:
                fragment.changeDirection(1);
                break;
            case R.id.button_top:
                fragment.changeDirection(3);
                break;
            case R.id.button_bottom:
                fragment.changeDirection(0);
                break;
        }
    }

    @Override
    public void removeChildFragment()
    {
        FragmentManager fragmentManager = getFragmentManager();

        // PDF_Remove処理
        WT_PartsDisplayFragment oldMainDisplayFragment = (WT_PartsDisplayFragment)fragmentManager.findFragmentByTag("base_texture_fragment");

        oldMainDisplayFragment.releaseParts();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(oldMainDisplayFragment);

        fragmentTransaction.commit();
    }

}