package wt.walk_tourist.MDF;

/**
 * Created by taguchi on 2015/11/26.
 */

import android.app.Activity;

import com.example.user.commonfragmentlib.DialogDisplayFragment;
import com.example.user.commonfragmentlib.MainDisplayFragment;

import wt.walk_tourist.WTCommonActivity;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivityで使用するFragmentを管理するクラス
 */
public abstract class WTMainDisplayFragment extends MainDisplayFragment {

    public enum MDF_NAME{
        MDF_START,
        MDF_BASE,
        MDF_GAME,
        MDF_SPOT_FOR_SPOT,
        MDF_SPOT_FOR_PREF,
        MDF_SPOT_FOR_BOR,
        MDF_MAP,
        MDF_HELP,
        MDF_POINT,
        MDF_DFF
    }

    public interface MainFragmentListener
    {
        void changeMDF(MDF_NAME name);
        void changeMDF(MDF_NAME name, int transaction);
        void openDialog(DialogDisplayFragment.DIALOG_TYPE type, int transaction);
        void closeDialog();
    }

    protected MainFragmentListener mListener;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mListener = (WTCommonActivity)activity;

    }

}