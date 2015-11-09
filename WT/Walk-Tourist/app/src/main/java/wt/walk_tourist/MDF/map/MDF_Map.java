package wt.walk_tourist.MDF.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.commonfragmentlib.MainDisplayFragment;

import wt.walk_tourist.PDF.Map.PDF_Map;
import wt.walk_tourist.R;

/**
 * PDF_Map
 */
public class MDF_Map extends MainDisplayFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v  = inflater.inflate(R.layout.sample_map_fragment, container, false);

        setViewItems(v);

        return v;
    }

    private void setViewItems(View v) {

        PDF_Map p_map = new PDF_Map();
        Bundle args = new Bundle();
        args.putInt(PDF_Map.BUNDLE_KEY.BUNDLE_KEY_WIDTH.getKey(), m_LayoutWidth);
        args.putInt(PDF_Map.BUNDLE_KEY.BUNDLE_KEY_HEIGHT.getKey(), m_LayoutHeight);

        p_map.setArguments(args);
        addPartsDisplayFragment(R.id.mdf_map_area, p_map, "mdf_map_area");

    }


    public int getBGMResId()
    {
        return R.raw.bgm02;
    }

}