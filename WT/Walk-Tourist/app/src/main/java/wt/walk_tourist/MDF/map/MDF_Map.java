package wt.walk_tourist.MDF.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

/**
 * PDF_Map���g�p����N���X
 */
public class MDF_Map extends WT_MainDisplayFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // setViewItems(v);

        return inflater.inflate(R.layout.mdf_map_layout, container, false);
    }

    @Override
    public void removeChildFragment()
    {

    }
}