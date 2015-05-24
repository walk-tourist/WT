package wt.walk_tourist.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.MainActivity;
import wt.walk_tourist.R;
import wt.walk_tourist.game.MDF_Game_Contents;
import wt.walk_tourist.help.MDF_Help;
import wt.walk_tourist.point_management.MDF_PointManagement;
import wt.walk_tourist.tourist_spot.MDF_TouristSpot;
import wt.walk_tourist.tourist_spot.utility.SettingsUtilty;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

/**
 * Created by Akira on 2015/05/25.
 */
public class MDF_Base extends WT_MainDisplayFragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.game_contents_fragment, container, false);

        setViewItems(v);

        return v;
    }

    private void setViewItems(View v) {

        v.findViewById(R.id.button_tourist_spot).setOnClickListener(this);
        v.findViewById(R.id.button_start_game).setOnClickListener(this);
        v.findViewById(R.id.button_point_management).setOnClickListener(this);
        v.findViewById(R.id.button_help).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.move(view);
    }
}
