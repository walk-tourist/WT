package wt.walk_tourist.wt_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivity内で使用するパーツを管理するクラス
 */
public abstract class WT_PartsDisplayFragment extends WT_Fragment {

    public WT_PartsDisplayFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState );
        View v = inflater.inflate(R.layout.dummy_layout, container, false);
        return v;
    }
}