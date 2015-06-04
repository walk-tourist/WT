package wt.walk_tourist.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

public class MDF_Base extends WT_MainDisplayFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.base_fragment, container, false);

        setViewItems(v);

        return v;
    }

    private void setViewItems(View v) {

        v.findViewById(R.id.button_tourist_spot).setOnClickListener(this);
        v.findViewById(R.id.button_start_game).setOnClickListener(this);
        v.findViewById(R.id.button_point_management).setOnClickListener(this);
        v.findViewById(R.id.button_help).setOnClickListener(this);
        v.findViewById(R.id.button_display_map).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
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
        }
    }
}