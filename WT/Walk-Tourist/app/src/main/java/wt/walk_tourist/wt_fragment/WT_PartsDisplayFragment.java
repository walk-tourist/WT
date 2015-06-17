package wt.walk_tourist.wt_fragment;

import android.os.Bundle;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        if ( null != bundle )
        {
            m_LayoutWidth = bundle.getInt(BUNDLE_KEY.BUNDLE_KEY_WIDTH.getKey());
            m_LayoutHeight = bundle.getInt(BUNDLE_KEY.BUNDLE_KEY_HEIGHT.getKey());
        }
        return null;
    }

    public abstract void releaseParts();
}