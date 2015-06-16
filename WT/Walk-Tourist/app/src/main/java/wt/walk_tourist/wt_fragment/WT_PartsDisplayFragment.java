package wt.walk_tourist.wt_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivity内で使用するパーツを管理するクラス
 */
public abstract class WT_PartsDisplayFragment extends WT_Fragment {

    public enum BUNDLE_KEY{
        BUNDLE_KEY_WIDTH,
        BUNDLE_KEY_HEIGHT;

        public String getKey(){
            return name();
        }
    }

    public WT_PartsDisplayFragment() {}

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState )
    {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY.BUNDLE_KEY_WIDTH.getKey(), m_LayoutWidth);
        outState.putInt(BUNDLE_KEY.BUNDLE_KEY_HEIGHT.getKey(), m_LayoutHeight);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if ( null != savedInstanceState )
        {
            m_LayoutWidth = savedInstanceState.getInt(BUNDLE_KEY.BUNDLE_KEY_WIDTH.getKey());
            m_LayoutHeight = savedInstanceState.getInt(BUNDLE_KEY.BUNDLE_KEY_HEIGHT.getKey());
        }
        return null;
    }


    public abstract void releaseParts();
}