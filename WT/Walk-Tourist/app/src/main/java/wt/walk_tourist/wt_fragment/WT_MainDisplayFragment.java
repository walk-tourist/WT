package wt.walk_tourist.wt_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivityで使用するFragmentを管理するクラス
 */
public class WT_MainDisplayFragment extends WT_Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.base_fragment,null);

    }
}
