package wt.walk_tourist.settings;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.settings.utility.SettingsUtilty;


public class TouristSpotFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.settings_tourist_spot_fragment, container, false);

        // リスト表示処理
        showList(v);

        return v;
    }

    /**
     * リスト表示処理
     */
    protected void showList(View v) {
        // TODO 継承先で実装
    }


    @Override
    public void onClick(View view) {
        // TODO 継承先で実装
    }
}
