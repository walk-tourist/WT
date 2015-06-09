package wt.walk_tourist.map;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_PartsDisplayFragment;

/**
 * パーツのマップフラグメントクラス
 * とりあえずレイアウトはマップンのみ
 */
public class PDF_Map extends WT_PartsDisplayFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        return inflater.inflate(R.layout.pdf_map_layout, container, false);
    }

    public void releaseParts()
    {
  /**      PDF_WalkAnimation_TextureView pdf_walkAnimation_textureView;

        View v = getView();
        if( null != v ) {
            pdf_walkAnimation_textureView = (PDF_WalkAnimation_TextureView) v.findViewById(R.id.walk_animation_texture);
            pdf_walkAnimation_textureView.stopThread();
        }**/
    }

}