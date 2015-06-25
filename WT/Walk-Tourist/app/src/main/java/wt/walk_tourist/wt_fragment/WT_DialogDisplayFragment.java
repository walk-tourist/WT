package wt.walk_tourist.wt_fragment;

import android.app.Activity;

import wt.walk_tourist.MainActivity;
import wt.walk_tourist.define.Define;

/**
 * Created by Akira on 2015/05/25.
 * アプリのダイアログFragmentを管理するクラス
 */
public abstract class WT_DialogDisplayFragment extends WT_Fragment {
    public WT_DialogDisplayFragment() {}

    public interface DialogFragmentListener
    {
        void closeDialog();
    }

    protected DialogFragmentListener mListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mListener = (DialogFragmentListener)activity;

    }


    public void callBackKey(){
        return;
    }
}