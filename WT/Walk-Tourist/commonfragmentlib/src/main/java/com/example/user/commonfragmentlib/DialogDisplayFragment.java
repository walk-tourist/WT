package com.example.user.commonfragmentlib;

import android.app.Activity;

/**
 * Created by Akira on 2015/05/25.
 * アプリのダイアログFragmentを管理するクラス
 */
public abstract class DialogDisplayFragment extends CommonFragment {
    public DialogDisplayFragment() {}

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

    public enum DIALOG_TYPE{
        WAIT,
        YES_OR_NO,
        OK,
        OK_ITEM_DETAIL
    }
}