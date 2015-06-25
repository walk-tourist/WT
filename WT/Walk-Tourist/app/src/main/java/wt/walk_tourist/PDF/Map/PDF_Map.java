package wt.walk_tourist.PDF.Map;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_PartsDisplayFragment;

/**
 *
 */
public class PDF_Map extends WT_PartsDisplayFragment {
    Map_MapFragment mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.pdf_map_layout, container, false);

        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Map_MapFragment map = (Map_MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if (map != null)
            getFragmentManager().beginTransaction().remove(map).commit();
    }


    @Override
    public void onStart(){
        super.onStart();
    }


    private void setViewItems(View v) {

    }

    public void releaseParts()
    {
        /**    PDF_Map pdf_map;

         View v = getView();
         if( null != v ) {
         pdf_map = (z) v.findViewById(R.id.map);
         pdf_walkAnimation_textureView.stopThread();
         }*/
    }

}