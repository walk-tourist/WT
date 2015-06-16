package wt.walk_tourist.parts_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.define.Define;
import wt.walk_tourist.wt_fragment.WT_PartsDisplayFragment;

/**
 * Created by taguchi on 2015/06/08.
 * 背景が流れるViewのパーツ
 */
public class PDF_WalkAnimation_Texture extends WT_PartsDisplayFragment {

    WalkAnimation_TextureView m_WalkAnimation_TextureView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.walk_animation_texture_fragment, container, false);
    }

    private void getWalkAnimation_TextureView()
    {
        View v = getView();
        if (null != v) {
            m_WalkAnimation_TextureView = (WalkAnimation_TextureView) v.findViewById(R.id.walk_animation_texture);
        }
    }

    public void releaseParts()
    {
        if( null == m_WalkAnimation_TextureView) {
            getWalkAnimation_TextureView();
        }
        if( null != m_WalkAnimation_TextureView) {
            m_WalkAnimation_TextureView.stopThread();
        }
    }

    public void changeDirection(Define.DIRECTION_DEF direction)
    {
        if( null == m_WalkAnimation_TextureView) {
            getWalkAnimation_TextureView();
        }
        if( null != m_WalkAnimation_TextureView) {
            m_WalkAnimation_TextureView.changeDirection(direction);
        }
    }

}