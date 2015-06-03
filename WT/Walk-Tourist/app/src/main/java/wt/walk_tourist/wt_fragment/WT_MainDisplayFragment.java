package wt.walk_tourist.wt_fragment;

import android.app.Activity;
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

    public enum MDF_NAME{
        MDF_BASE,
        MDF_GAME,
        MDF_SPOT,
        MDF_MAP,
        MDF_HELP,
        MDF_POINT
    }

    public interface MainFragmentListener
    {
        public void changeMDF(MDF_NAME name);
    }

    protected MainFragmentListener mListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mListener = (MainFragmentListener)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.dummy_layout,null);
    }
}
