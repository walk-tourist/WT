package wt.walk_tourist.wt_fragment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;

/**
 * Created by Akira on 2015/05/25.
 * アプリのMainActivityで使用するFragmentを管理するクラス
 */
public abstract class WT_MainDisplayFragment extends WT_Fragment {

    protected SoundPool mSoundPool;

    public WT_MainDisplayFragment() {

        if ( Build.VERSION.SDK_INT >= 21 ) {
            // SoundPoolはAPI21以降、インスタンスをBuilderから取得する。
            SoundPool.Builder mSoundPoolBuilder = new SoundPool.Builder();
            mSoundPoolBuilder.setMaxStreams(5);
            mSoundPoolBuilder.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).build());
            mSoundPool = mSoundPoolBuilder.build();
        }else{
            // 過去API用
            mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
    }

    public enum MDF_NAME{
        MDF_BASE,
        MDF_GAME,
        MDF_SPOT,
        MDF_MAP,
        MDF_HELP,
        MDF_POINT,
        MDF_DFF
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
    public void onDestroy()
    {
        super.onDestroy();

        mSoundPool.release();
    }

    // 画面の向きを取得する
    public int getScreenOrientation()
    {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    // 画面での再生曲を取得する
    //public abstract int getBGMResId();
    public int getBGMResId()
    {
        return R.raw.bgm01;
    }


    // 本フラグメント(MDF)破棄時に破棄する必要がある子フラグメント(PDF)を破棄する
    public abstract void removeChildFragment();

}